package domain;

public class LibraryName {

    private String name;

    public LibraryName(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        LibraryName other = (LibraryName) obj;
        return this.name.equals(other.name);
    }
}
