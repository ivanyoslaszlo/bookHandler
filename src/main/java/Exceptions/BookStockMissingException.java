package Exceptions;

public class BookStockMissingException  extends Exception{
    public BookStockMissingException(){
        super("Hiányzik a készlet mennyiség!");
    }

}
