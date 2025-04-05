package org.java.serde.customserde.serdewithinheritance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main
{
	public static void main(String[] args)
	{
		SubClass subClass = new SubClass();
		subClass.setId(1);
		subClass.setValue("Hello");
		subClass.setName("World");

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("subclass.ser")))
		{
			oos.writeObject(subClass);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("subclass.ser")))
		{
			SubClass deserializedSubClass = (SubClass) ois.readObject();
			System.out.println("Deserialized SubClass: " + deserializedSubClass);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
