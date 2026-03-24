package iuh.fit;

class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(">>> Thông báo gửi đến " + name + ": " + message);
    }
}