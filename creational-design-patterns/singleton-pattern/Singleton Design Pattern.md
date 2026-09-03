# Singleton Design Pattern

## 📌 Definition

The **Singleton Pattern** is a **Creational Design Pattern** that ensures a class has **only one instance** throughout the application and provides a **global point of access** to that instance.

### Key Characteristics

- Private constructor → prevents external object creation.
- Static instance → stores the single object.
- Public static method → provides access to the instance.
- Controls object creation → ensures only one instance exists.

---

## 🎯 When to Use Singleton

Use Singleton when exactly **one shared instance** is required across the application.

Common examples:

- Configuration Manager
- Logger
- Cache Manager
- Application-wide Settings
- Resource Manager

> ⚠️ Avoid using Singleton just to provide global access. **Dependency Injection** is often a better choice when testability and loose coupling are important.

---

## 🏗️ Basic Structure

```java
public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### How it works

1. Constructor is made `private`.
2. The instance is stored in a `static` variable.
3. `getInstance()` creates the object when required.
4. Subsequent calls return the same instance.

---

# 🔄 Singleton Initialization Approaches

## 1. Eager Initialization

The instance is created when the class is loaded.

```java
public class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```

### Advantages

- Simple implementation.
- Thread-safe due to class initialization.
- No synchronization overhead.

### Disadvantages

- Instance is created even if never used.
- Not ideal for expensive objects.

---

## 2. Lazy Initialization

The instance is created only when `getInstance()` is called.

```java
public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### Advantage

- Object is created only when required.

### Disadvantage

- **Not thread-safe** in a multi-threaded environment.

Two threads can simultaneously pass the `instance == null` check and create two objects.

---

# 🔒 Thread-Safe Singleton

## 3. Synchronized Method

```java
public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### Advantage

- Simple.
- Thread-safe.

### Disadvantage

- Every call acquires the lock, even after the instance has already been created.
- Can introduce unnecessary synchronization overhead.

---

# ⚡ 4. Double-Checked Locking

```java
public class Singleton {

    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {

        if (instance == null) {
            synchronized (Singleton.class) {

                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
```

### Why `volatile`?

`volatile` ensures **visibility** between threads and prevents problematic instruction reordering during object creation.

### Advantages

- Thread-safe.
- Synchronization happens only during initialization.
- Better performance than synchronizing the entire method.

### Disadvantage

- More complex implementation.

---

# ⭐ 5. Bill Pugh Singleton

A static inner class is used to achieve **lazy initialization and thread safety** without explicit synchronization.

```java
public class Singleton {

    private Singleton() {
    }

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
```

### Why does it work?

The `Holder` class is initialized only when it is first referenced.

Therefore:

- Instance creation is **lazy**.
- Class initialization provides **thread safety**.
- No explicit `synchronized` is required.

### Advantages

- Lazy initialization.
- Thread-safe.
- High performance.
- Simple implementation.
- No `volatile` required.

> ⭐ **Bill Pugh Singleton is a commonly recommended implementation when you need a lazy, thread-safe Singleton.**

---

# 📊 Comparison

| Approach | Lazy | Thread Safe | Synchronization Overhead |
|---|---|---|---|
| Eager | ❌ | ✅ | None |
| Lazy | ✅ | ❌ | None |
| Synchronized Method | ✅ | ✅ | High |
| Double-Checked Locking | ✅ | ✅ | Low |
| Bill Pugh | ✅ | ✅ | None |

---

# ✅ Advantages

- Guarantees a single instance.
- Provides centralized access to shared resources.
- Can reduce unnecessary object creation.
- Supports lazy initialization.
- Useful for application-wide services/resources.

---

# ❌ Disadvantages

- Introduces global state.
- Makes unit testing harder.
- Can create tight coupling between classes.
- Can violate the **Single Responsibility Principle**.
- Requires careful handling in multi-threaded applications.
- Overuse can make the architecture difficult to maintain.

---

# 💡 Key Takeaway

```text
Singleton
   ↓
One instance
   ↓
Private constructor
   ↓
Static instance
   ↓
Global access
   ↓
Thread safety must be considered
```

For Java applications, remember these three implementations:

**Eager → Simple & Thread Safe**

**Double-Checked Locking → Lazy & Thread Safe**

**Bill Pugh → Lazy + Thread Safe + No explicit synchronization**