package iuh.fit;

public class AudioBookFactory extends BookFactory {
    @Override
    public Book createBook(String name, String author) {
        return new AudioBook(name, author);
    }
}
