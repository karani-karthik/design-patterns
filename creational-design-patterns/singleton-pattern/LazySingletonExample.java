
/**
 * The Singleton instance is created only when it is first accessed.
 */

class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {};

    public static LazySingleton getInstance() {
        if(instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }
}

public class LazySingletonExample {
    public static void main(String[] args) {
        LazySingleton ex1 = LazySingleton.getInstance();
        LazySingleton ex2 = LazySingleton.getInstance(); 

        System.out.println(ex1 == ex2);
    }
}
