
class SynchronizedSingleton {

    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {};

    public static SynchronizedSingleton getInstance() {
        if(instance == null) {
            instance = new SynchronizedSingleton();
        }

        return instance;
    }
}

public class SynchronizedSingletonExample {
    
    public static void main(String[] args) {
        SynchronizedSingleton ex1 = SynchronizedSingleton.getInstance();
        SynchronizedSingleton ex2 = SynchronizedSingleton.getInstance(); 

        System.out.println(ex1 == ex2);
    }
}
