package org.java.serde.serializedeserialize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeDemo
{
	public static void main(String[] args)
	{
		Student s = new Student("John", 20, "secret123");

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.ser")))
		{
			out.writeObject(s);
			System.out.println("Object serialized");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
