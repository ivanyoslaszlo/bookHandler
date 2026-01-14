import Exceptions.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Library {
    List<Book> bookList = new ArrayList<>();

    public Library() {
        Path path=Path.of("books.json");

        if  (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            bookList.add(new Book("Egri csillagok", "Gárdonyi Géza", "001", 2));
            bookList.add(new Book("A Pál utcai fiúk", "Molnár Ferenc", "002", 1));
            bookList.add(new Book("Tüskevár", "Fekete István", "003", 2));

            updateDatabase();
        }
        else{
            bookList= FileHandling.loadDatabase();
        }


    }


    public void updateDatabase() {
        try {
            FileHandling.saveBook(bookList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addBook(Book book) throws BookMissingTitleException, BookMissingIdException, BookAuthorMissingException, BookStockMissingException {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new BookMissingTitleException();
        }
        if (book.getID().isBlank()) {
            throw new BookMissingIdException();
        }
        if (book.getAuthor().isBlank()) {
            throw new BookAuthorMissingException();
        }
        if (book.getStock() <= 0) {
            throw new BookStockMissingException();

        }
        Boolean add = bookList.add(book);
        updateDatabase();
        return add;
    }

    public String removeBook(Book book) {

        String cim = book.getTitle();
        bookList.remove(book);
        updateDatabase();
        return cim + " deleted !";

    }

    public Book findBook(String searchedBook) throws BookNotFoundException {

        return bookList.stream().filter(n -> n.getID().equals(searchedBook))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("This book doesn't exist.: " + searchedBook));

    }

    public long getBookCount() {
        return bookList.stream().count();
    }

    public long getAvailableCount() {

        return bookList.stream()
                .filter(Book::getAvailable)
                .count();

    }

    public void printAllBook() {
        if (bookList.isEmpty()) {
            System.out.println("The library is empty");
            return;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(bookList);

        System.out.println(jsonString);


    }

    public boolean loanBook(String id) throws BookNotFoundException, BookStockMissingException {
        Book book = findBook(id);


        if (book == null) {
            throw new BookNotFoundException("Book can't be found in the database:" + id);
        }
        if (book.getStock()<=0){
            throw new BookStockMissingException();
        }

        if (book.getStock() > 0 && book.getAvailable()) {


            int ujKeszlet = book.getStock() - 1;
            book.setStock(ujKeszlet);


            if (ujKeszlet == 0) {
                book.setAvailable(false);
            }

            updateDatabase();
            return true;
        }


        return false;
    }

    public boolean returnBook(String id) throws BookNotFoundException, BookMaxStockError {
        Book book = findBook(id);

        if (book == null) {
            throw new BookNotFoundException("Can't be returned it doesen't exist: " + id);
        }


        if (book.getStock() >= book.getMaxStock()) {
            throw new BookMaxStockError();
        }

        int ujKeszlet = book.getStock() + 1;
        book.setStock(ujKeszlet);
        book.setAvailable(true);

        updateDatabase();
        return true;
    }
}


