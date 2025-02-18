package domain;

import java.util.Map;

public class Library {

    private LibraryName name;
    private Map<BookName, Book> books;

    public Library(LibraryName name, Map<BookName, Book> books) {
        this.name = name;
        this.books = books;
    }

    public LibraryName getName() {
        return name;
    }

    public Map<BookName, Book> getBooks() {
        return books;
    }
}
