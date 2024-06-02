import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private BookManager bookManager = new BookManager();
    private ObservableList<Book> booksObservableList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Book Manager");

        //pola tesktowe
        Label titleLabel = new Label("Tytuł:");
        TextField titleField = new TextField();

        Label authorLabel = new Label("Autor:");
        TextField authorField = new TextField();

        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField();

        Label yearLabel = new Label("Rok wydania:");
        TextField yearField = new TextField();
        
        //przycksi 
        Button addButton = new Button("Add Book");
        Button removeButton = new Button("Remove Book");
        Button updateButton = new Button("Update Book");
        Button listButton = new Button("List Books");

        ListView<Book> bookListView = new ListView<>();
        booksObservableList = FXCollections.observableArrayList();
        bookListView.setItems(booksObservableList);

        //wyglad
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        GridPane.setConstraints(titleLabel, 0, 0);
        GridPane.setConstraints(titleField, 1, 0);
        GridPane.setConstraints(authorLabel, 0, 1);
        GridPane.setConstraints(authorField, 1, 1);
        GridPane.setConstraints(isbnLabel, 0, 2);
        GridPane.setConstraints(isbnField, 1, 2);
        GridPane.setConstraints(yearLabel, 0, 3);
        GridPane.setConstraints(yearField, 1, 3);
        GridPane.setConstraints(addButton, 0, 4);
        GridPane.setConstraints(removeButton, 1, 4);
        GridPane.setConstraints(updateButton, 0, 5);
        GridPane.setConstraints(listButton, 1, 5);

        gridPane.getChildren().addAll(titleLabel, titleField, authorLabel, authorField, isbnLabel, isbnField, yearLabel, yearField, addButton, removeButton, updateButton, listButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(gridPane, bookListView);

        //akcje
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            int year = Integer.parseInt(yearField.getText());
            Book book = new Book(title, author, isbn, year);
            bookManager.addBook(book);
            refreshBookList();
        });

        removeButton.setOnAction(e -> {
            String isbn = isbnField.getText();
            bookManager.removeBook(isbn);
            refreshBookList();
        });

        updateButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            int year = Integer.parseInt(yearField.getText());
            Book book = new Book(title, author, isbn, year);
            bookManager.updateBook(book);
            refreshBookList();
        });

        listButton.setOnAction(e -> refreshBookList());

        Scene scene = new Scene(vBox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        //przeładowanie books
        refreshBookList();
    }

    private void refreshBookList() {
        List<Book> books = bookManager.getBooks();
        booksObservableList.setAll(books);
    }
}
