package Exceptions;

public class BookStockMissingException  extends Exception{
    public BookStockMissingException(){
        super("sorry we ran out of this book");
    }

}
