package iuh.fit;

public class ExtendTimeDecorator extends DecoratorBook {
    private final int extendTime;

    public ExtendTimeDecorator(Book book, int extendTime) {
        super(book);
        this.extendTime = extendTime;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Gia hạn thêm " + this.extendTime + " ngày";
    }
}
