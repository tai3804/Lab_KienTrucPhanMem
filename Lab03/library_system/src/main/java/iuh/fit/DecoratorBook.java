package iuh.fit;

abstract class DecoratorBook implements Book {
    private final Book book;

    public DecoratorBook(Book book) {
        this.book = book;
    }

    @Override
    public String getName() {
        return book.getName();
    }

    @Override
    public String getAuthor() {
        return book.getAuthor();
    }

    @Override
    public String getType() {
        return book.getType();
    }

    @Override
    public String getInfo() {
        return book.getInfo();
    }
}
