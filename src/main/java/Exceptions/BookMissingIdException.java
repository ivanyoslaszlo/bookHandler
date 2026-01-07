package Exceptions;

public class BookMissingIdException extends Exception {

    public BookMissingIdException(){
       super("Hiányzik az ID!");
    }
}
