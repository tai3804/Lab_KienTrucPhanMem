package iuh.fit;

public class EBook implements Book {
    private final String name;
    private final String author;

    public EBook(String name, String author) {
        this.name = name;
        this.author = author;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getType() {
        return "Sách điện tử";
    }

    @Override
    public String getInfo() {
        return String.format("%s | Tác giả: %s | Loại: %s", name, author, getType());
    }
}
