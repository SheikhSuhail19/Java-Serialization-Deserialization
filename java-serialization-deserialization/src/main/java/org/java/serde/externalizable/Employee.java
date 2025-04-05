package org.java.serde.externalizable;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Employee implements Externalizable
{
	private String name;
	private int age;

	// Mandatory no-arg constructor
	public Employee()
	{
	}

	public Employee(String name, int age)
	{
		this.name = name;
		this.age = age;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException
	{
		out.writeObject(name);
		out.writeInt(age);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
	{
		name = (String) in.readObject();
		age = in.readInt();
	}

	@Override
	public String toString()
	{
		return "Employee{name='" + name + "', age=" + age + "}";
	}

	// Demo main method
	public static void main(String[] args)
	{
		Employee original = new Employee("Alice", 30);
		String filename = "employee.ser";

		// Serialize to file
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
		{
			oos.writeObject(original);
			System.out.println("Serialized: " + original);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		// Deserialize from file
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
		{
			Employee deserialized = (Employee) ois.readObject();
			System.out.println("Deserialized: " + deserialized);
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}


