package com.design.singleton;

class Singleton1 {

	// create the instance at the time of class loading
	private static final Singleton1 INSTANCE = new Singleton1();

	private Singleton1() {

	}

	public static Singleton1 getInstance() {
		return INSTANCE;
	}
}

public class Eager {
	// In this approach, the instance of the Singleton is created when the class is
	// loaded into memory.
	public static void main(String[] args) {
		Singleton1 singleton = Singleton1.getInstance();
		System.out.println(singleton);

		Singleton1 singleton2 = Singleton1.getInstance();
		System.out.println(singleton2);
	}
}
