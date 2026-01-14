import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileHandling {

    public static void saveBook(List<Book> bookList) throws IOException {
        Path path = Path.of("books.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(bookList);
        Files.writeString(path, jsonString, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);


    }
    public static List<Book> loadDatabase() {
        Path path = Path.of("books.json");
        String jsonString;
        try {
            jsonString = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        List<Book> books = gson.fromJson(jsonString, new TypeToken<List<Book>>() {}.getType());

        return books;
    }

}