package net.javaguides.springboot.configuration;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class StudentIdGenerator implements IdentifierGenerator{

	@Override
	public 	Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return "STD"+System.currentTimeMillis()%100;
	}

}
