
// product
interface Notification {
    void send();
}

// concrete products
class EmailNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Email sent");
    }
}

class SmsNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Sms sent");
    }
}

// creator
abstract class NotificationFactory {

    public abstract Notification createNotification();

    public void notifyUser() {
        Notification notification = createNotification();
        notification.send();
    }
}

// concrete creators
class EmailNotificationFactory extends NotificationFactory {

    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}

class SmsNotificationFactory extends NotificationFactory {

    @Override
    public Notification createNotification() {
        return new SmsNotification();
    }
}

// client
public class FactoryMethod {
    public static void main(String[] args) {
        NotificationFactory emailFactory = new EmailNotificationFactory();
        emailFactory.notifyUser();
    }
}
