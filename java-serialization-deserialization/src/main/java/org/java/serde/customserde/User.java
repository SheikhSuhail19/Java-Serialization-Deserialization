package org.java.serde.customserde;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable
{
	@Serial
	private static final long serialVersionUID = 1L;

	private final String username;
	private transient String password; // Won't be serialized unless handled manually

	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	// 🛠 writeObject(ObjectOutputStream out)
	@Serial
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.defaultWriteObject();       // Serialize normal fields
		out.writeUTF(password);         // Manually serializes the transient 'password'
	}

	// 🔄 readObject(ObjectInputStream in)
	@Serial
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();       // Deserialize normal fields
		password = in.readUTF();      // Manually reads and sets the transient 'password'
	}

	public static void main(String[] args)
	{
		try
		{
			User user = new User("john_doe", "secret123");

			// Serialize the object
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.ser"));
			out.writeObject(user);
			out.close();

			// Deserialize the object
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.ser"));
			User deserializedUser = (User) in.readObject();
			in.close();

			System.out.println("Deserialized User: " + deserializedUser.username + ", " + deserializedUser.password);
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}