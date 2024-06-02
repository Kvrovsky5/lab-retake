import java.util.List;

public interface BookOperations {
    void addBook(Book book);
    void removeBook(String isbn);
    void updateBook(Book book);
    List<Book> getBooks();
}

