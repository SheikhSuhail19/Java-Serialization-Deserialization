package org.java.serde.customserde.serdewithinheritance;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

public class SubClass extends SuperClass implements Serializable, ObjectInputValidation
{
	@Serial
	private static final long serialVersionUID = 1L;
	private String name;

	public SubClass()
	{
		// No-argument constructor
	}

	// Getters and setters
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Serial
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.defaultWriteObject();       // Serialize subclass fields
		out.writeInt(getId());          // Manually serialize superclass fields
		out.writeObject(getValue());
	}

	@Serial
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();         // Deserialize subclass fields
		setId(in.readInt());            // Manually deserialize superclass fields
		setValue((String) in.readObject());
		in.registerValidation(this, 0); // Register validation
	}

	@Override
	public void validateObject() throws InvalidObjectException
	{
		if (name == null || name.isEmpty())
		{
			throw new InvalidObjectException("Name cannot be null or empty");
		}
		if (getId() <= 0)
		{
			throw new InvalidObjectException("ID must be positive");
		}
	}

	@Override
	public String toString()
	{
		return "SubClass{id=" + getId() + ", value=" + getValue() + ", name='" + name + "'}";
	}
}
