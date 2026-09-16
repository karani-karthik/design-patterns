# Builder Design Pattern

## Definition

The **Builder Pattern** is a **Creational Design Pattern** that builds a
complex object step by step and separates the construction of an object
from its representation.

It is especially useful when an object has **many optional parameters**.

## Key Characteristics

-   **Step-by-step construction** → Builds an object incrementally.
-   **Builder class** → Handles the construction process.
-   **Private constructor** → The Product can be created only through
    the Builder.
-   **Required fields** → Usually provided through the Builder
    constructor.
-   **Optional fields** → Configured using builder methods.
-   **Fluent API** → Builder methods return `this` for method chaining.
-   **`build()` method** → Creates and returns the final object.
-   **Immutability** → The final object can be made immutable using
    `final` fields.

## Problem It Solves

The Builder Pattern mainly solves the problem of **complex
constructors** and the **Telescoping Constructor Anti-Pattern**.

Without Builder:

``` java
BurgerMeal(String bun, String patty, boolean cheese, String side, String drink)
```

Problems:

-   Long parameter lists are difficult to read.
-   Parameter order can be confusing.
-   Optional parameters may require `null`.
-   Multiple constructor overloads may be required.
-   Adding new fields makes constructors difficult to maintain.
-   Object creation cannot be configured cleanly step by step.

## Telescoping Constructor

Multiple overloaded constructors are created to handle different
combinations:

``` java
BurgerMeal(String bun, String patty)

BurgerMeal(String bun, String patty, boolean cheese)

BurgerMeal(String bun, String patty, boolean cheese, String side)

BurgerMeal(String bun, String patty, boolean cheese, String side, String drink)
```

This approach becomes difficult to maintain as the number of optional
parameters increases.

## Basic Structure

``` java
public class BurgerMeal {

    private final String bun;
    private final String patty;
    private final boolean cheese;
    private final String side;

    private BurgerMeal(Builder builder) {
        this.bun = builder.bun;
        this.patty = builder.patty;
        this.cheese = builder.cheese;
        this.side = builder.side;
    }

    public static class Builder {

        private final String bun;
        private final String patty;
        private boolean cheese;
        private String side;

        public Builder(String bun, String patty) {
            this.bun = bun;
            this.patty = patty;
        }

        public Builder withCheese(boolean cheese) {
            this.cheese = cheese;
            return this;
        }

        public Builder withSide(String side) {
            this.side = side;
            return this;
        }

        public BurgerMeal build() {
            return new BurgerMeal(this);
        }
    }
}
```

## Usage

``` java
BurgerMeal burger = new BurgerMeal.Builder("wheat", "veg")
        .withCheese(true)
        .withSide("fries")
        .build();
```

## How It Works

1.  The client creates a **Builder**.
2.  Required fields are provided to the Builder.
3.  Optional fields are configured using builder methods.
4.  Each builder method returns `this`, enabling method chaining.
5.  `build()` creates the final Product object.
6.  The Product can be immutable because its fields are `final`.

## Main Components

  Component             Responsibility
  --------------------- -------------------------------------------------------
  **Product**           The complex object being created
  **Builder**           Defines and controls object construction
  **Builder Methods**   Configure optional properties
  **`build()`**         Creates the final Product
  **Director**          Optional; controls a predefined construction sequence

## Advantages

-   Avoids **telescoping constructors**.
-   Improves **code readability**.
-   Handles **optional parameters** cleanly.
-   Supports **flexible object construction**.
-   Can create **immutable objects**.
-   Easy to add new optional fields.
-   Supports **fluent API / method chaining**.

## Disadvantages

-   Adds **boilerplate code**.
-   Requires an additional Builder class.
-   Can be **overkill for simple objects**.
-   Slightly increases implementation complexity.

## When to Use Builder

Use Builder when:

-   An object has **many fields**.
-   Many fields are **optional**.
-   There are multiple possible configurations.
-   Constructor parameters become difficult to understand.
-   **Immutability** is preferred.
-   Readable and maintainable object creation is required.

## When to Avoid Builder

Avoid Builder when:

-   The class has only **1--2 fields**.
-   Object creation is simple.
-   There are no optional parameters.
-   A normal constructor is already clear.
-   The object is created in only one simple place.

## Builder vs Constructor

  -----------------------------------------------------------------------
  Constructor                         Builder
  ----------------------------------- -----------------------------------
  Good for simple objects             Good for complex objects

  Can become difficult with many      Handles many parameters cleanly
  parameters                          

  Parameter order matters             Named methods improve readability

  May require multiple overloads      Usually one Builder

  Less boilerplate for simple classes More boilerplate

  Less flexible                       More flexible
  -----------------------------------------------------------------------

## Real-World Java Example

### Lombok `@Builder`

Lombok can generate Builder Pattern code automatically.

``` java
@Builder
public class User {
    private String name;
    private int age;
    private String address;
}
```

Usage:

``` java
User user = User.builder()
        .name("John")
        .age(30)
        .address("Bangalore")
        .build();
```

## Key Takeaway

``` text
Builder Pattern
       ↓
Build complex object step by step
       ↓
Separate construction from representation
       ↓
Required fields + Optional fields
       ↓
Fluent API / Method Chaining
       ↓
build()
       ↓
Final Object
```

### One-Line Interview Definition

> **Builder Pattern is a Creational Design Pattern that constructs
> complex objects step by step and separates object construction from
> its representation, making objects with many optional parameters
> easier to create and maintain.**
