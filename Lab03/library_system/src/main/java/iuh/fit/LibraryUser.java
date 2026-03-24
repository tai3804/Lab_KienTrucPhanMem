package iuh.fit;

public class LibraryUser implements Observer {
    private String name;
    public LibraryUser(String n) { this.name = n; }

    public void update(String msg) {
        System.out.println("Thông báo đến " + name + ": " + msg);
    }
}
