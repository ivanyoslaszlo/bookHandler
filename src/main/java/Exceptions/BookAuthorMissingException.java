package Exceptions;

public class BookAuthorMissingException extends Exception{
    public BookAuthorMissingException() {
        super("missing author");
    }
}
