
/**
 * The instance of the Singleton is created when the class is loaded into
 * memory.
 */
class EagerSingleton {

    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}

public class EagerSingletonExample {

    public static void main(String[] args) {
        EagerSingleton ex1 = EagerSingleton.getInstance();
        EagerSingleton ex2 = EagerSingleton.getInstance(); 

        System.out.println(ex1 == ex2);
    }
}