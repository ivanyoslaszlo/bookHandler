import java.util.Scanner;

public class LibraryUI {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {

        System.out.println("Könyv keressés : 1 ");
        System.out.println("Könyv hozzáadás : 2 ");
        System.out.println("Elérhető könyvek száma: 3");
        System.out.println("Könyv eltávolitás: 4");
        System.out.println("Könyv kölcsönzés: 5");
        System.out.println("Könyv visszahozás: 6");
        System.out.println("Kilépes: x");
    }


    public String readString(String message) {
        System.out.println(message);


        return scanner.nextLine();
    }


}
