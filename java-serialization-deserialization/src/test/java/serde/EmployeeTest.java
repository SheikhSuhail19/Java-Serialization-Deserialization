package serde;

import org.java.serde.Address;
import org.java.serde.Employee;
import org.java.serde.Person;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;

public class EmployeeTest
{
	@Test
	public void whenCustomSerializingAndDeserializing_ThenObjectIsTheSame()
			throws IOException, ClassNotFoundException
	{
		Person p = new Person();
		p.setAge(20);
		p.setName("Joe");

		Address a = new Address();
		a.setHouseNumber(1);

		Employee e = new Employee();
		e.setPerson(p);
		e.setAddress(a);

		FileOutputStream fileOutputStream
				= new FileOutputStream("yourfile2.txt");
		ObjectOutputStream objectOutputStream
				= new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(e);
		objectOutputStream.flush();
		objectOutputStream.close();

		FileInputStream fileInputStream
				= new FileInputStream("yourfile2.txt");
		ObjectInputStream objectInputStream
				= new ObjectInputStream(fileInputStream);
		Employee e2 = (Employee) objectInputStream.readObject();
		objectInputStream.close();

		assertEquals(e2.getPerson().getAge(), e.getPerson().getAge());
		assertEquals(e2.getAddress().getHouseNumber(), e.getAddress().getHouseNumber());
	}
}
