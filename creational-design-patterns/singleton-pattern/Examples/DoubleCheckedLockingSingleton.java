
class Singleton {

    private static volatile Singleton instance;

    private Singleton() {};

    // Thread-safe Singleton instance with Double-Checked Locking
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}

public class DoubleCheckedLockingSingleton {
    public static void main(String[] args) {
        Singleton ex1 = Singleton.getInstance();
        Singleton ex2 = Singleton.getInstance(); 

        System.out.println(ex1 == ex2);
    }
}
