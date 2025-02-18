package uspace;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uspace.api.ConfigurationServerRest;

public class Main implements Runnable {
    private static final int PORT = 8181;

    private final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        Server server = new Server(PORT);
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        ResourceConfig packageConfig = new ResourceConfig()
                .packages("uspace")
                .register(JacksonFeature.withoutExceptionMappers())
                .register(ConfigurationServerRest.class);
        ServletContainer container = new ServletContainer(packageConfig);
        ServletHolder servletHolder = new ServletHolder(container);

        contextHandler.addServlet(servletHolder, "/*");

        try {
            server.start();
            logger.info("Server started on port " + PORT);
            server.join();
        } catch (Exception e) {
            logger.error("Error starting server", e);
        } finally {
            if (server.isRunning()) {
                server.destroy();
            }
        }
    }
}