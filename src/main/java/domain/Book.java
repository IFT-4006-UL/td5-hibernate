package domain;

import java.util.List;

public class Book {

    private BookName name;
    private List<Author> authors;

    public Book(BookName name, List<Author> authors) {
        this.name = name;
        this.authors = authors;
    }

    public BookName getName() {
        return name;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
