package Exceptions;

public class BookMaxStockError extends  Exception {
    public BookMaxStockError(){
        super("Error: you can't return more book than you loaned");
    }
}
