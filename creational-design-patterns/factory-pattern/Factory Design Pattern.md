# Factory Design Pattern

## 📌 Definition

The **Factory Pattern** is a **Creational Design Pattern** that provides a way to create objects without exposing the object creation logic to the client.

Instead of directly using `new`, the client asks a **Factory** to create the required object.

### In Simple Terms

```text
Client → Factory → Concrete Object
```

The client works with an **interface/abstract class** instead of depending directly on concrete implementations.

---

## 🎯 When to Use Factory

Use the Factory Pattern when:

- Multiple implementations of an interface exist.
- The object type needs to be decided at **runtime**.
- Object creation logic is complex or needs to be centralized.
- You want to reduce coupling between client code and concrete classes.
- New implementations are expected to be added later.

---

# 🏗️ Main Components

| Component | Responsibility |
|---|---|
| **Product** | Interface/abstract class defining common behavior |
| **Concrete Product** | Actual implementations of the Product |
| **Factory** | Creates and returns the required Concrete Product |
| **Client** | Uses the Product without knowing how it is created |

---

# 💻 Basic Example

```java
interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}

class ShapeFactory {

    public static Shape getShape(String type) {

        if (type.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        }

        if (type.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }

        throw new IllegalArgumentException("Unknown shape: " + type);
    }
}
```

### Client

```java
public class Main {

    public static void main(String[] args) {

        Shape shape = ShapeFactory.getShape("CIRCLE");

        shape.draw();
    }
}
```

### Flow

```text
Main
 ↓
ShapeFactory
 ↓
Checks requested type
 ↓
Creates Circle
 ↓
Returns Shape
 ↓
Client calls draw()
```

The client does **not** need to know:

```java
new Circle();
```

It only asks:

```java
ShapeFactory.getShape("CIRCLE");
```

---

# ❌ Without Factory

```java
if (mode.equals("Air")) {
    Logistics logistics = new Air();
    logistics.send();
}
else if (mode.equals("Road")) {
    Logistics logistics = new Road();
    logistics.send();
}
```

### Problems

- Client/business logic is tightly coupled to concrete classes.
- Object creation logic is mixed with business logic.
- Adding new implementations increases modifications to existing code.
- Testing and maintenance become harder.

---

# ✅ With Factory

```java
class LogisticsFactory {

    public static Logistics getLogistics(String mode) {

        if (mode.equalsIgnoreCase("AIR")) {
            return new Air();
        }

        if (mode.equalsIgnoreCase("ROAD")) {
            return new Road();
        }

        throw new IllegalArgumentException("Unknown mode: " + mode);
    }
}
```

Client:

```java
Logistics logistics = LogisticsFactory.getLogistics("AIR");

logistics.send();
```

### Improvement

```text
Before:

Business Logic
     ↓
new Air()
new Road()
new Ship()


After:

Business Logic
     ↓
LogisticsFactory
     ↓
Air / Road / Ship
```

Object creation is now **centralized**.

---

# ⭐ Key Benefits

### 1. Loose Coupling

Client depends on the interface rather than concrete implementations.

```java
Logistics logistics;
```

instead of:

```java
Air logistics;
```

### 2. Centralized Object Creation

All creation logic is handled by the Factory.

### 3. Runtime Flexibility

The required implementation can be selected based on:

- User input
- Configuration
- Request type
- Business rules
- Runtime conditions

### 4. Separation of Concerns

The Factory handles:

```text
"Which object should I create?"
```

The service handles:

```text
"What should I do with the object?"
```

### 5. Easier Maintenance

Creation logic is kept in one place.

---

# ⚠️ Disadvantages

- Adds additional classes/code.
- Can increase complexity for simple applications.
- Factory can become large if it handles too many product types.
- Adding a new product may still require modifying the factory in a basic `if/else` implementation.

---

# Factory vs Direct Creation

### Direct Creation

```java
Logistics logistics = new Air();
```

Client knows the concrete implementation.

### Factory

```java
Logistics logistics =
        LogisticsFactory.getLogistics("AIR");
```

Client only knows the abstraction.

> **Main goal of Factory: Hide and centralize object creation while reducing coupling between the client and concrete implementations.**

---

# 🆚 Factory vs Abstract Factory

| Factory | Abstract Factory |
|---|---|
| Creates usually one type/category of product | Creates a family of related products |
| Usually one factory method/class | Factory contains multiple related creation methods |
| Example: `ShapeFactory` | Example: `WindowsUIFactory` / `MacUIFactory` |
| Focuses on choosing one product | Focuses on creating compatible product families |

---

# 🔥 Key Takeaway

```text
Factory Pattern
      ↓
Centralizes Object Creation
      ↓
Hides "new" from Client
      ↓
Client depends on Interface
      ↓
Loose Coupling
      ↓
Easier Maintenance & Extension
```

### Remember

> **Factory Pattern = "Don't ask the client to create the object. Ask the Factory to create it."**