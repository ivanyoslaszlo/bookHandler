import Exceptions.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library {
    List<Book> bookList = new ArrayList<>();

    public Library() {
        bookList.add(new Book("Egri csillagok", "Gárdonyi Géza", "001",12));
        bookList.add(new Book("A Pál utcai fiúk", "Molnár Ferenc", "002",34));
        bookList.add(new Book("Tüskevár", "Fekete István", "003",123));
        bookList.add(new Book("1984", "George Orwell", "004",32));
        bookList.add(new Book("A kis herceg", "Antoine de Saint-Exupéry", "005",25));
        bookList.add(new Book("Harry Potter és a bölcsek köve", "J.K. Rowling", "006",20));
        updateDatabase();

    }

    public void updateDatabase() {
        try {
            FileSave.saveBook(bookList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addBook(Book book) throws BookMissingTitleException, BookMissingIdException, BookAuthorMissingException, BookStockMissingException {
        if (book.getCim() == null || book.getCim().isEmpty()) {
            throw new BookMissingTitleException();
        }
        if (book.getAzonositoKod().isBlank()){
            throw new BookMissingIdException();
        }
        if (book.getSzerzo().isBlank()){
            throw new BookAuthorMissingException();
        }
        if (book.getKeszlet()<=0){
            throw new BookStockMissingException();

        }
        Boolean add = bookList.add(book);
        updateDatabase();
        return add;
    }

    public String removeBook(Book book) {

        String cim = book.getCim();
        bookList.remove(book);
        updateDatabase();
        return cim + " törölve lett !";

    }

    public Book findBook(String searchedBook) throws BookNotFoundException {

       return bookList.stream().filter(n->n.getAzonositoKod().equals(searchedBook))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Nem létező könyv: " + searchedBook));

    }

    public long getBookCount() {
        return bookList.stream().count();
    }

    public long getAvailableCount() {

        return bookList.stream()
                .filter(n->n.getElerheto())
                .count();

    }

    public void printAllBook() {
        if (bookList.isEmpty()) {
            System.out.println("A könyvtár üres");
            return;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(bookList);

        System.out.println(jsonString);


    }

    public boolean loanBook(String id) throws BookNotFoundException {

        Book book = findBook(id);
        if (book != null && book.getElerheto()) {
            book.setElerheto(false);
            updateDatabase();
            return true;
        }
        return false;

    }

    public boolean returnBook(String id) throws BookNotFoundException {
        Book book = findBook(id);
        if (book != null && !book.getElerheto()) {
            book.setElerheto(true);
            updateDatabase();
            return true;

        }
        return false;
    }

}


