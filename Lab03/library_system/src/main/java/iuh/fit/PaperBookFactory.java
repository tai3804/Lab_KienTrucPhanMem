package iuh.fit;

public class PaperBookFactory extends BookFactory {
    @Override
    public Book createBook(String name, String author) {
        return new PaperBook(name, author);
    }
}
