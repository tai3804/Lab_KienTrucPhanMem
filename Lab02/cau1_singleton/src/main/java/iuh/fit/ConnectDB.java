package iuh.fit;

public class ConnectDB {
    private static class SingletonHelper {
        static final ConnectDB INSTANCE = new ConnectDB();
    }

    private ConnectDB() {
        System.out.println("ConnectDB instance created");
    }

    public static ConnectDB getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
