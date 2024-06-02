import java.util.ArrayList;
import java.util.List;

public class BookManager implements BookOperations {
    private List<Book> books;

    public BookManager() {
        this.books = new ArrayList<>();
        
        books.add(new Book("Przykładowa książka", "Autor1", "ISBN1", 2003));
        books.add(new Book("Przykładowa książka1", "Autor2", "ISBN2", 2002));
        books.add(new Book("Przykładowa książka2", "Autor3", "ISBN3", 2003));
        books.add(new Book("Przykładowa książka3", "Autor4", "ISBN4", 2004));
        books.add(new Book("Przykładowa książka4", "Autor5", "ISBN5", 2005));
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    @Override
    public void updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(updatedBook.getIsbn())) {
                books.set(i, updatedBook);
                break;
            }
        }
    }

    @Override
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}


