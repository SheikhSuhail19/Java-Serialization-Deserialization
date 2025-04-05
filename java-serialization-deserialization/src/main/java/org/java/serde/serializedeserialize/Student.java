package org.java.serde.serializedeserialize;

import java.io.Serial;
import java.io.Serializable;

public class Student implements Serializable
{
	@Serial
	private static final long serialVersionUID = 1L;
	String name;
	int age;
	transient String password; // This field will not be serialized

	public Student(String name, int age, String password)
	{
		this.name = name;
		this.age = age;
		this.password = password;
	}
}

