package iuh.fit;

import java.util.List;

public interface SearchBookStrategy {
    List<Book> searchBook(List<Book> books, String text);
}
