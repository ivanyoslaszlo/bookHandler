import Exceptions.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class Main {

    private static final Library libray = new Library();
    private static final LibraryUI ui = new LibraryUI();


    public static void main(String[] Args) throws IOException, InterruptedException {
        mainLoop();

    }


    public static void mainLoop() throws InterruptedException, IOException {
        boolean running = true;

        while (running) {
            ui.showMenu();
            String command = ui.readString("");

            switch (command) {
                case "1":
                    getAvailableBooks();
                    break;
                case "2":
                    searchBook();
                    break;
                case "3":
                    createBook();
                    break;
                case "4":
                    removeBook();
                    break;
                case "5":
                    loanBook();
                    break;
                case "6":
                    returnBook();
                    break;
                case "x":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;

            }
        }

    }

    public static void createBook() throws IOException {
        String cim = ui.readString("Title: ");
        String szerzo = ui.readString("Author: ");
        String azonosito = ui.readString("ID: ");
        int keszlet= Integer.parseInt(ui.readString("stock: "));
        Book newBook = new Book(cim,szerzo,azonosito,keszlet);

        try {
            libray.addBook(newBook);
            System.out.println("Book successfully added");
        }
        catch (BookMissingTitleException | BookMissingIdException  |BookAuthorMissingException |BookStockMissingException e){
            System.out.println("Failed to save "+e.getMessage());
        }
    }

    public static void removeBook() {
        String id = ui.readString("Enter the ID:");
        try {
            Book bookToDelete = libray.findBook(id);
            if (bookToDelete != null) {
                libray.removeBook(bookToDelete);
                System.out.println("Book successfully removed");
            }
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void searchBook() throws InterruptedException {
        String id = ui.readString("Enter the searched book's ID:");

        try {
            Book book = libray.findBook(id);
            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            System.out.println("The searched book: "+gson.toJson(book));
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void loanBook() {
        String id = ui.readString("Book ID:");

        try {
            libray.loanBook(id);
            System.out.println("You have successfully loaned the book!");
        } catch (BookNotFoundException | BookStockMissingException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void returnBook() {
        String id = ui.readString("ID of the book: ");

        try {
            libray.returnBook(id);
            System.out.println("You have successfully returned the book!");
        } catch (BookNotFoundException | BookMaxStockError e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getAvailableBooks() {
        libray.printAllBook();
        System.out.println("All books count: " + libray.getBookCount() + "");
        System.out.println("Available books count: " + libray.getAvailableCount());
        System.out.println();

    }


}

