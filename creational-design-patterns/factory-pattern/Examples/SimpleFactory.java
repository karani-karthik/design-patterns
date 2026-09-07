
// Product interface
interface PaymentGateway {
    void pay(double amount);
}

// concrete products
class RazorpayGateway implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid using Razorpay");
    }
}

class PaypalGateway implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid using paypal");
    }
}

// Factory
class PaymentGatewayFactory {
    public static PaymentGateway gateWay(String key) {
        return switch (key) {
            case "Razorpay" -> new RazorpayGateway();
            case "Paypal" -> new PaypalGateway();

            default -> throw new IllegalArgumentException("Unexpected value: " + key);
        };
    }
}

public class SimpleFactory {
    public static void main(String[] args) {
        PaymentGateway gateway = PaymentGatewayFactory.gateWay("Paypal");
		gateway.pay(5);
    }
}