package Exceptions;

public class BookMissingTitleException extends  Exception{
    public  BookMissingTitleException(){
       super("missing title");
    }
}
