package iuh.fit;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByType implements SearchBookStrategy {
    @Override
    public List<Book> searchBook(List<Book> books, String text) {
        String keyword = text.toLowerCase();
        return books.stream()
                .filter(book -> book.getType().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }
}
