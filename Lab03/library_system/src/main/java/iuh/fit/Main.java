package iuh.fit;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = Library.getInstance();
        library.registerObserver(new LibraryUser("Nhan vien A"));
        library.registerObserver(new LibraryUser("User theo doi B"));
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n===== MENU THU VIEN =====");
            System.out.println("1. Them sach (Factory Method)");
            System.out.println("2. Xem danh sach sach");
            System.out.println("3. Tim kiem sach (Strategy)");
            System.out.println("4. Mo phong thong bao het han muon (Observer)");
            System.out.println("5. Muon sach voi tinh nang bo sung (Decorator)");
            System.out.println("6. Kiem tra Singleton");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addBookByFactory(scanner, library);
                    break;
                case "2":
                    library.showBooks();
                    break;
                case "3":
                    searchBookByStrategy(scanner, library);
                    break;
                case "4":
                    System.out.print("Nhap ten sach da het han muon: ");
                    String expiredBook = scanner.nextLine().trim();
                    if (!expiredBook.isEmpty()) {
                        library.notifyBookExpired(expiredBook);
                    }
                    break;
                case "5":
                    borrowWithDecorator(scanner, library);
                    break;
                case "6":
                    checkSingleton(library);
                    break;
                case "0":
                    isRunning = false;
                    System.out.println("Da thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
                    break;
            }
        }

        scanner.close();
    }

    private static void addBookByFactory(Scanner scanner, Library library) {
        System.out.println("Chon loai sach:");
        System.out.println("1. Sach giay");
        System.out.println("2. Sach dien tu");
        System.out.println("3. Sach noi");
        System.out.print("Lua chon: ");
        String typeChoice = scanner.nextLine().trim();

        BookFactory factory;
        switch (typeChoice) {
            case "1":
                factory = new PaperBookFactory();
                break;
            case "2":
                factory = new EBookFactory();
                break;
            case "3":
                factory = new AudioBookFactory();
                break;
            default:
                System.out.println("Loai sach khong hop le.");
                return;
        }

        System.out.print("Nhap ten sach: ");
        String name = scanner.nextLine().trim();
        System.out.print("Nhap tac gia: ");
        String author = scanner.nextLine().trim();

        if (name.isEmpty() || author.isEmpty()) {
            System.out.println("Ten sach va tac gia khong duoc de trong.");
            return;
        }

        Book book = factory.createBook(name, author);
        library.addBook(book);
        System.out.println("Da them: " + book.getInfo());
    }

    private static void searchBookByStrategy(Scanner scanner, Library library) {
        System.out.println("Chon kieu tim kiem:");
        System.out.println("1. Theo ten");
        System.out.println("2. Theo tac gia");
        System.out.println("3. Theo the loai");
        System.out.print("Lua chon: ");
        String strategyChoice = scanner.nextLine().trim();

        switch (strategyChoice) {
            case "1":
                library.setSearchStrategy(new SearchByName());
                break;
            case "2":
                library.setSearchStrategy(new SearchByAuthor());
                break;
            case "3":
                library.setSearchStrategy(new SearchByType());
                break;
            default:
                System.out.println("Kieu tim kiem khong hop le.");
                return;
        }

        System.out.print("Nhap tu khoa tim kiem: ");
        String keyword = scanner.nextLine().trim();
        List<Book> result = library.searchBooks(keyword);

        if (result.isEmpty()) {
            System.out.println("Khong tim thay sach phu hop.");
            return;
        }

        System.out.println("Ket qua tim kiem:");
        for (Book book : result) {
            System.out.println("- " + book.getInfo());
        }
    }

    private static void borrowWithDecorator(Scanner scanner, Library library) {
        List<Book> books = library.getBooks();
        if (books.isEmpty()) {
            System.out.println("Chua co sach de muon.");
            return;
        }

        System.out.println("Chon sach de muon:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i).getInfo());
        }
        System.out.print("Nhap so thu tu sach: ");
        String indexText = scanner.nextLine().trim();

        int index;
        try {
            index = Integer.parseInt(indexText) - 1;
        } catch (NumberFormatException ex) {
            System.out.println("So thu tu khong hop le.");
            return;
        }

        if (index < 0 || index >= books.size()) {
            System.out.println("So thu tu khong hop le.");
            return;
        }

        Book borrowedBook = books.get(index);

        System.out.print("Ban co muon gia han thoi gian muon? (y/n): ");
        String extendChoice = scanner.nextLine().trim();
        if ("y".equalsIgnoreCase(extendChoice)) {
            System.out.print("Nhap so ngay gia han: ");
            String daysText = scanner.nextLine().trim();
            try {
                int days = Integer.parseInt(daysText);
                if (days > 0) {
                    borrowedBook = new ExtendTimeDecorator(borrowedBook, days);
                }
            } catch (NumberFormatException ex) {
                System.out.println("So ngay gia han khong hop le. Bo qua gia han.");
            }
        }

        System.out.print("Nhap yeu cau dac biet (de trong neu khong co): ");
        String request = scanner.nextLine().trim();
        if (!request.isEmpty()) {
            borrowedBook = new SpecialBookDecorator(borrowedBook, request);
        }

        System.out.println("Thong tin muon sach: " + borrowedBook.getInfo());
    }

    private static void checkSingleton(Library library) {
        Library anotherLibrary = Library.getInstance();
        if (library == anotherLibrary) {
            System.out.println("=> library va anotherLibrary la cung mot thuc the duy nhat!");
        } else {
            System.out.println("=> Co loi: da tao ra 2 doi tuong Library khac nhau.");
        }
    }
}