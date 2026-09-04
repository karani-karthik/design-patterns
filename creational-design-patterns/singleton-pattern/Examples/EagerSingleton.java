/**
 * The instance of the Singleton is created when the class is loaded into
 * memory.
 */
class Singleton {

    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}

public class EagerSingleton {

    public static void main(String[] args) {
        Singleton ex1 = Singleton.getInstance();
        Singleton ex2 = Singleton.getInstance(); 

        System.out.println(ex1 == ex2);
    }
}