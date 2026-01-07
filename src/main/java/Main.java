import Exceptions.*;

import java.io.IOException;

public class Main {
    // region peldanyositas
    private static final Library libray = new Library();
    private static final LibraryUI ui = new LibraryUI();
//endregion

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
                    searchBook();
                    break;
                case "2":
                    createBook();
                    break;
                case "3":
                    getBookCount();
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
                    System.out.println("Érvénytelen menüpont!");
                    break;

            }
        }

    }

    public static void createBook() throws IOException {
        String cim = ui.readString("Cím: ");
        String szerzo = ui.readString("Szerző: ");
        String azonosito = ui.readString("Azonosító: ");
        int keszlet= Integer.parseInt(ui.readString("Készlet: "));
        Book newBook = new Book(cim,szerzo,azonosito,keszlet);

        try {
            libray.addBook(newBook);
        }
        catch (BookMissingTitleException | BookMissingIdException  |BookAuthorMissingException |BookStockMissingException e){
            System.out.println("Nem sikerült menteni: "+e.getMessage());
        }
    }

    public static void removeBook() {
        String id = ui.readString("Ad meg az ID-t");
        try {
            Book bookToDelete = libray.findBook(id);
            if (bookToDelete != null) {
                libray.removeBook(bookToDelete);
            }
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void searchBook() throws InterruptedException {
        String id = ui.readString("Keresett könyv ID-ja: ");

        try {
            Book book = libray.findBook(id);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void loanBook() {
        String id = ui.readString("Ki kölcsönözni kivánt könyv ID-ja: ");

        try {
            libray.loanBook(id);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void returnBook() {
        String id = ui.readString("Vissza hozni kivánt köny ID-ja: ");

        try {
            libray.returnBook(id);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getBookCount() {
        libray.printAllBook();
        System.out.println("könyvek száma: " + libray.getBookCount() + "");
        System.out.println("Elérhető könyvek száma: " + libray.getAvailableCount());
        System.out.println();

    }


}

