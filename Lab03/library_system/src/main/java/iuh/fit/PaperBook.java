package iuh.fit;

public class PaperBook implements Book {
    private final String name;
    private final String author;

    public PaperBook(String name, String author) {
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
        return "Sách giấy";
    }

    @Override
    public String getInfo() {
        return String.format("%s | Tác giả: %s | Loại: %s", name, author, getType());
    }
}
