package org.java.serde.advancedserde.singletonpattern;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;

public class Singleton implements Serializable
{
	private static final Singleton INSTANCE = new Singleton();

	private Singleton()
	{
		// private constructor
	}

	public static Singleton getInstance()
	{
		return INSTANCE;
	}

	@Serial
	private Object readResolve() throws ObjectStreamException
	{
		return INSTANCE;
	}

	public static void main(String[] args)
	{
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();

		System.out.println("Are both instances the same? " + (singleton1 == singleton2));
	}
}
