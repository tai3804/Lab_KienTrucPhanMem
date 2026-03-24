package iuh.fit;

public class EBookFactory extends BookFactory {

    @Override
    public Book createBook(String name, String author) {
        return new EBook(name, author);
    }
}
