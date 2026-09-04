/**
 * The Singleton instance is created only when it is first accessed.
 */

class Singleton {

    private static Singleton instance;

    private Singleton() {};

    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}

public class LazySingleton {
    public static void main(String[] args) {
        Singleton ex1 = Singleton.getInstance();
        Singleton ex2 = Singleton.getInstance(); 

        System.out.println(ex1 == ex2);
    }
}
