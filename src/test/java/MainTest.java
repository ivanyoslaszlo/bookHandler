import Exceptions.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    Library libray = new Library();


    @Test
    public void testContent() throws BookAuthorMissingException, BookMissingTitleException, BookStockMissingException, BookMissingIdException, BookNotFoundException {
        long inititalSize = libray.getBookCount();

        Book newbook = new Book("konyv01", "szerzo01", "033F", 231);
        boolean result = libray.addBook(newbook);

        assertTrue(result);
        assertEquals(inititalSize + 1, libray.getBookCount());
        assertEquals(newbook, libray.findBook("033F"));

    }

}
