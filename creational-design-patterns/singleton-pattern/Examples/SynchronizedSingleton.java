
class Singleton {

    private static Singleton instance;

    private Singleton() {};

    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}

public class SynchronizedSingleton {
    
    public static void main(String[] args) {
        Singleton ex1 = Singleton.getInstance();
        Singleton ex2 = Singleton.getInstance(); 

        System.out.println(ex1 == ex2);
    }
}
