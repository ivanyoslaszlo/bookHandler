

public class Book {

    private String title;
    private String author;
    private String ID;
    private int stock;
    private int maxStock;
    private boolean isAvailable = true;


    public Book() {
    }
    public Book(String title, String author, String ID, int stock) {
        this.title = title;
        this.author = author;
        this.ID = ID;
        this.stock = stock;
        this.maxStock = stock;
        this.isAvailable = true;
    }


    public int getStock() {
        return stock;
    }
    public int getMaxStock() {
        return maxStock;
    }

    public Book setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getID() {
        return ID;
    }

    public Book setID(String ID) {
        this.ID = ID;
        return this;
    }

    public boolean getAvailable() {

        return this.stock >0;
    }

    public Book setAvailable(boolean available) {
        this.isAvailable = available;
        return this;
    }



    @Override
    public String toString() {
        return title + " " + author + " " + ID + " " + isAvailable + "\n";
    }
}
