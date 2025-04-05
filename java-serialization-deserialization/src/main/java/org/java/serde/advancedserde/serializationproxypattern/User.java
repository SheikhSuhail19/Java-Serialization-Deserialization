package org.java.serde.advancedserde.serializationproxypattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable
{

	private final String username;
	private final transient String password; // Marked as transient to avoid serialization

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	// Getters and other methods

	@Serial
	private Object writeReplace() throws ObjectStreamException
	{
		return new UserProxy(this);
	}

	private static class UserProxy implements Serializable
	{
		private final String username;

		UserProxy(User user)
		{
			this.username = user.username;
		}

		@Serial
		private Object readResolve() throws ObjectStreamException
		{
			return new User(username, "defaultPassword"); // Password needs to be reset
		}
	}

	public static void main(String[] args)
	{
		User user = new User("john_doe", "secret123");
		try
		{
			// Serialization
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userNew" +
					".ser"));
			oos.writeObject(user);
			oos.close();

			// Deserialization
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userNew.ser"));
			User deserializedUser = (User) ois.readObject();
			ois.close();

			System.out.println("Deserialized User: " + deserializedUser.getUsername() + ", " + deserializedUser.getPassword());
		}
		catch (IOException | ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}
}