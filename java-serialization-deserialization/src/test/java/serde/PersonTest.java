package serde;

import org.java.serde.Person;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;

public class PersonTest
{
	@Test
	public void whenSerializingAndDeserializing_ThenObjectIsTheSame()
			throws IOException, ClassNotFoundException
	{
		Person person = new Person();
		person.setAge(20);
		person.setName("Joe");

		FileOutputStream fileOutputStream
				= new FileOutputStream("yourfile.txt");
		ObjectOutputStream objectOutputStream
				= new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(person);
		objectOutputStream.flush();
		objectOutputStream.close();

		FileInputStream fileInputStream
				= new FileInputStream("yourfile.txt");
		ObjectInputStream objectInputStream
				= new ObjectInputStream(fileInputStream);
		Person p2 = (Person) objectInputStream.readObject();
		objectInputStream.close();

		assertEquals(p2.getAge(), person.getAge());
		assertEquals(p2.getName(), person.getName());
	}
}
