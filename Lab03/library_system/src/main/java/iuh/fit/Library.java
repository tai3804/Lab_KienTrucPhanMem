package iuh.fit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Library {
    private static Library instance;
    private final List<Book> books;
    private final List<Observer> observers;
    private SearchBookStrategy searchStrategy;

    private Library() {
        books = new ArrayList<>();
        observers = new ArrayList<>();
        searchStrategy = new SearchByName();
        System.out.println("--- Khởi tạo Thư viện Duy nhất ---");
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
        notifyObservers("Sách mới được thêm: " + book.getInfo());
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void notifyBookExpired(String bookName) {
        notifyObservers("Sách đã hết hạn mượn: " + bookName);
    }

    public void setSearchStrategy(SearchBookStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<Book> searchBooks(String keyword) {
        return searchStrategy.searchBook(books, keyword);
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }

        String output = books.stream()
                .map(Book::getInfo)
                .collect(Collectors.joining("\n- ", "- ", ""));
        System.out.println("Danh sách sách trong thư viện:\n" + output);
    }
}