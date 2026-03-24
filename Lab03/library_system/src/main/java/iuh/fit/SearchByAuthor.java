package iuh.fit;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByAuthor implements SearchBookStrategy {
    @Override
    public List<Book> searchBook(List<Book> books, String text) {
        String keyword = text.toLowerCase();
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }
}
