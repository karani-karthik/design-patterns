
class Singleton {

    private Singleton() {};

    // Inner static class responsible for creating the Singleton instance
    // The instance will be created only when the class is loaded by the classloader
    private static class HELPER {
        private static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return HELPER.instance;
    }

}

public class BillPughSingleton {

    public static void main(String[] args) {
        Singleton ex1 = Singleton.getInstance();
        Singleton ex2 = Singleton.getInstance(); 

        System.out.println(ex1 == ex2);        
    }
}
