package Exceptions;

public class BookMissingTitleException extends  Exception{
    public  BookMissingTitleException(){
       super("Hiányzik a cím!");
    }
}
