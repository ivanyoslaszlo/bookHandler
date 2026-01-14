package Exceptions;

public class BookMissingIdException extends Exception {

    public BookMissingIdException(){
       super("missing ID");
    }
}
