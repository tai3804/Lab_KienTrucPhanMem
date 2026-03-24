package iuh.fit;

public class SpecialBookDecorator extends DecoratorBook {
    private final String request;

    public SpecialBookDecorator(Book book, String request) {
        super(book);
        this.request = request;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Yêu cầu đặc biệt: " + request;
    }
}
