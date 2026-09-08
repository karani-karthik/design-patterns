# Abstract Factory Design Pattern

## 📌 Definition

The **Abstract Factory Pattern** is a **Creational Design Pattern** that provides an interface for creating **families of related or dependent objects** without specifying their concrete classes.

### In Simple Terms

A **Factory** creates one type of product.

An **Abstract Factory** creates **multiple related products that are designed to work together**.

```text
Factory
   ↓
Creates one product

Abstract Factory
   ↓
Creates a family of related products
```

---

# 🎯 Problem It Solves

Without Abstract Factory, client code may directly create concrete objects:

```java
new RazorpayGateway();
new GSTInvoice();
```

This can lead to:

- **Tight coupling** to concrete classes.
- Violation of the **Open/Closed Principle**.
- Risk of mixing incompatible products.
- Difficulties when supporting multiple regions, platforms, themes, etc.
- Difficult testing and maintenance.

For example:

```text
India
 ├── Razorpay Gateway
 └── GST Invoice

US
 ├── PayPal Gateway
 └── US Invoice
```

The factory ensures that products from the **same family** are used together.

---

# 🏗️ Structure

The Abstract Factory Pattern typically contains:

1. **Abstract Products** – interfaces defining product behavior.
2. **Concrete Products** – implementations belonging to a particular family.
3. **Abstract Factory** – interface defining methods for creating products.
4. **Concrete Factories** – create products belonging to a specific family.
5. **Client** – uses the factory and works only with abstractions.

---

# 💻 Example — Payment & Invoice

## 1. Abstract Products

```java
interface PaymentGateway {
    void processPayment(double amount);
}

interface Invoice {
    void generateInvoice();
}
```

---

## 2. Concrete Products

### India Product Family

```java
class RazorpayGateway implements PaymentGateway {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via Razorpay");
    }
}

class GSTInvoice implements Invoice {

    @Override
    public void generateInvoice() {
        System.out.println("Generating GST Invoice");
    }
}
```

### US Product Family

```java
class PayPalGateway implements PaymentGateway {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing USD payment via PayPal");
    }
}

class USInvoice implements Invoice {

    @Override
    public void generateInvoice() {
        System.out.println("Generating US Invoice");
    }
}
```

---

# 3. Abstract Factory

```java
interface RegionFactory {

    PaymentGateway createPaymentGateway();

    Invoice createInvoice();
}
```

The factory defines **what products can be created**, but does not specify the concrete implementation.

---

# 4. Concrete Factories

### India Factory

```java
class IndiaFactory implements RegionFactory {

    @Override
    public PaymentGateway createPaymentGateway() {
        return new RazorpayGateway();
    }

    @Override
    public Invoice createInvoice() {
        return new GSTInvoice();
    }
}
```

### US Factory

```java
class USFactory implements RegionFactory {

    @Override
    public PaymentGateway createPaymentGateway() {
        return new PayPalGateway();
    }

    @Override
    public Invoice createInvoice() {
        return new USInvoice();
    }
}
```

---

# 5. Client

```java
class CheckoutService {

    private final PaymentGateway paymentGateway;
    private final Invoice invoice;

    public CheckoutService(RegionFactory factory) {
        this.paymentGateway = factory.createPaymentGateway();
        this.invoice = factory.createInvoice();
    }

    public void completeOrder(double amount) {
        paymentGateway.processPayment(amount);
        invoice.generateInvoice();
    }
}
```

### Main

```java
public class Main {

    public static void main(String[] args) {

        RegionFactory indiaFactory = new IndiaFactory();

        CheckoutService indiaCheckout =
                new CheckoutService(indiaFactory);

        indiaCheckout.completeOrder(1999);
    }
}
```

---

# 🔄 How It Works

```text
                    RegionFactory
                         │
             ┌───────────┴───────────┐
             │                       │
      createPaymentGateway()    createInvoice()
             │                       │
             ▼                       ▼
      PaymentGateway              Invoice
             │                       │
       ┌─────┴─────┐           ┌─────┴─────┐
       │           │           │           │
    Razorpay    PayPal      GSTInvoice   USInvoice
       │           │           │           │
       └── India ──┘           └── Family ─┘
```

More accurately, each concrete factory creates a **consistent family**:

```text
IndiaFactory
   ├── RazorpayGateway
   └── GSTInvoice

USFactory
   ├── PayPalGateway
   └── USInvoice
```

---

# ⭐ Main Advantage: Product Consistency

This is the **most important concept** to remember.

Abstract Factory ensures related products belong to the same family.

```text
IndiaFactory
    ↓
Razorpay + GST Invoice       ✅

USFactory
    ↓
PayPal + US Invoice          ✅
```

Avoid accidentally creating:

```text
PayPal + GST Invoice         ❌
```

The factory controls the product family.

---

# 🎯 When to Use Abstract Factory

Use it when:

- Multiple **families of related products** exist.
- Products are designed to work together.
- The application supports different:
  - Countries
  - Platforms
  - Operating systems
  - UI themes
  - Payment systems
  - Configurations
- Client code should remain independent of concrete implementations.
- You want to switch between product families easily.

---

# ✅ Advantages

### 1. Loose Coupling

Client depends on interfaces instead of concrete classes.

### 2. Product Consistency

Products created by the same factory belong to the same compatible family.

### 3. Open/Closed Principle

A new product **family** can be added by creating a new factory without changing the client.

### 4. Scalability

Easy to support new:

- Regions
- Platforms
- Themes
- Environments

### 5. Testability

Factories can be mocked and injected into the client.

---

# ❌ Disadvantages

- Adds more interfaces and classes.
- Can be overkill for simple applications.
- Creates additional boilerplate.
- Adding a **new product type** requires changes to every concrete factory.

For example, if you add:

```java
ShippingLabel createShippingLabel();
```

every factory must implement it.

---

# 🆚 Factory vs Abstract Factory

| Feature | Factory | Abstract Factory |
|---|---|---|
| Creates | One product | Family of related products |
| Product types | Usually one | Multiple |
| Main purpose | Create an object | Create compatible product families |
| Example | `ShapeFactory` | `RegionFactory` |
| Example products | Circle / Square | Gateway + Invoice |
| Focus | Object creation | Product-family creation |
| Complexity | Lower | Higher |

### Easy Way to Remember

```text
Factory
   ↓
"Which object should I create?"

Abstract Factory
   ↓
"Which family of related objects should I create?"
```

The source material similarly distinguishes Factory Method as creating a single product from Abstract Factory as creating families of related products.

---

# 🔥 Key Takeaway

```text
             Abstract Factory
                    ↓
        Creates Product Families
                    ↓
       ┌────────────┴────────────┐
       ↓                         ↓
 Payment Gateway              Invoice
       ↓                         ↓
   Concrete Product          Concrete Product
       └────────────┬────────────┘
                    ↓
             Same Product Family
```

> **Abstract Factory = "Create a family of related objects that are designed to work together."**