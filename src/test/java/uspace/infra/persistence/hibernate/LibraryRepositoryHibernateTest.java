package uspace.infra.persistence.hibernate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uspace.domain.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LibraryRepositoryHibernateTest {

    private static final LibraryName ANY_NAME = new LibraryName("a_name");
    private static final LibraryName ANY_SAME_NAME = new LibraryName("a_name");
    private static final LibraryName ANY_DIFFERENT_NAME = new LibraryName("another_name");
    private static final String ANY_AUTHOR_NAME = "Bob";
    private static final Author ANY_AUTHOR = new Author(ANY_AUTHOR_NAME);
    private static final String ANY_OTHER_AUTHOR_NAME = "Joe";
    private static final Author ANY_OTHER_AUTHOR = new Author(ANY_OTHER_AUTHOR_NAME);
    private static final BookName ANY_BOOK_NAME = new BookName("a_bookname");
    private static final Book ANY_BOOK = new Book(ANY_BOOK_NAME, List.of(ANY_AUTHOR, ANY_OTHER_AUTHOR));
    private static final BookName ANY_OTHER_BOOK_NAME = new BookName("an_other_bookname");
    private static final Library ANY_LIBRARY = new Library(ANY_NAME,
                                                           Map.of(
                                                                   ANY_BOOK_NAME, ANY_BOOK
                                                                   ));
    private LibraryRepositoryHibernate libraryRepositoryHibernate;

    @BeforeEach
    void createLibraryRepositoryHibernate() {
        libraryRepositoryHibernate = new LibraryRepositoryHibernate();
    }

    @Test
    void givenASavedLibrary_whenFindingByName_thenReturnsTheLibrary() {
        libraryRepositoryHibernate.saveOrUpdate(ANY_LIBRARY);
        int bookNumber = 1;
        int authorsNumberForBook = 2;

        Library foundLibrary = libraryRepositoryHibernate.findByName(ANY_SAME_NAME);

        assertEquals(ANY_NAME, foundLibrary.getName());
        assertEquals(bookNumber, foundLibrary.getBooks().size());
        Book bookInFoundLibrary = foundLibrary.getBooks().get(ANY_BOOK_NAME);
        assertEquals(ANY_BOOK_NAME, bookInFoundLibrary.getName());
        List<Author> foundBookAuthors = bookInFoundLibrary.getAuthors();
        assertEquals(authorsNumberForBook, foundBookAuthors.size());
        assertTrue(foundBookAuthors.stream().anyMatch(author -> author.getName().equals(ANY_AUTHOR_NAME)));
        assertTrue(foundBookAuthors.stream().anyMatch(author -> author.getName().equals(ANY_OTHER_AUTHOR_NAME)));
    }

    @Test
    void givenANotSavedLibrary_whenFindingById_thenReturnsNull() {
        libraryRepositoryHibernate.saveOrUpdate(ANY_LIBRARY);

        Library foundLibrary = libraryRepositoryHibernate.findByName(ANY_DIFFERENT_NAME);

        assertNull(foundLibrary);
    }

    @Test
    void givenAnAlreadySavedLibrary_whenSaving_thenUpdatesTheLibrary() {
        libraryRepositoryHibernate.saveOrUpdate(ANY_LIBRARY);
        Book newBook = new Book(ANY_OTHER_BOOK_NAME, List.of(ANY_AUTHOR));
        Map<BookName, Book> moreBooks = Map.of(
                ANY_BOOK_NAME, ANY_BOOK,
                ANY_OTHER_BOOK_NAME, newBook
                              );
        Library updatedLibrary = new Library(ANY_NAME, moreBooks);
        int bookNumber = 2;
        int authorsNumberForExistingBook = 2;
        int authorsNumberForNewBook = 1;

        libraryRepositoryHibernate.saveOrUpdate(updatedLibrary);

        Library foundLibrary = libraryRepositoryHibernate.findByName(ANY_NAME);
        assertEquals(ANY_NAME, foundLibrary.getName());
        assertEquals(bookNumber, foundLibrary.getBooks().size());
        Book existingBookInFoundLibrary = foundLibrary.getBooks().get(ANY_BOOK_NAME);
        assertEquals(ANY_BOOK_NAME, existingBookInFoundLibrary.getName());
        List<Author> existingBookAuthors = existingBookInFoundLibrary.getAuthors();
        assertEquals(authorsNumberForExistingBook, existingBookAuthors.size());
        assertTrue(existingBookAuthors.stream().anyMatch(author -> author.getName().equals(ANY_AUTHOR_NAME)));
        assertTrue(existingBookAuthors.stream().anyMatch(author -> author.getName().equals(ANY_OTHER_AUTHOR_NAME)));
        Book newBookInFoundLibrary = foundLibrary.getBooks().get(ANY_OTHER_BOOK_NAME);
        assertEquals(ANY_OTHER_BOOK_NAME, newBookInFoundLibrary.getName());
        List<Author> newBookAuthors = existingBookInFoundLibrary.getAuthors();
        assertEquals(authorsNumberForNewBook, newBookAuthors.size());
        assertTrue(newBookAuthors.stream().anyMatch(author -> author.getName().equals(ANY_AUTHOR_NAME)));
    }

}