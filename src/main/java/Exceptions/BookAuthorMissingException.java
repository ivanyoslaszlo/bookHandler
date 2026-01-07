package Exceptions;

public class BookAuthorMissingException extends Exception{
    public BookAuthorMissingException() {
        super("hiányzik a szerzo");
    }
}
