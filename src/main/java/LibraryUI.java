import java.util.Scanner;

public class LibraryUI {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\n==================== Library Menu ====================");
        System.out.println("1. Show available books");
        System.out.println("2. Search book by ID");
        System.out.println("3. Add a new book");
        System.out.println("4. Remove a book");
        System.out.println("5. Loan a book");
        System.out.println("6. Return a book");
        System.out.println("x. Exit");
        System.out.println("======================================================");
        System.out.print("Select an option: ");
    }

    public String readString(String message) {
        if (message != null && !message.isBlank()) {
            System.out.print(message);
        }
        return scanner.nextLine().trim();
    }

    public void printMessage(String message) {
        System.out.println("\n" + message + "\n");
    }

    public void printBooks(String jsonBooks) {
        System.out.println("\n===== Books =====");
        System.out.println(jsonBooks);
        System.out.println("=================");
    }
}
