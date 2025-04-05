package org.java.serde.serializedeserialize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeDemo
{
	public static void main(String[] args)
	{
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.ser")))
		{
			Student s = (Student) in.readObject();
			System.out.println("Deserialized: " + s.name + ", " + s.age + ", " + s.password); // password will be null
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}