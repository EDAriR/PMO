package com.syntrontech.pmo.mv.auth.model.common;

import java.io.Serializable;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public class StringArrayType implements UserType {
	
	private final int[] SQL_TYPES = { Types.ARRAY};

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return this.deepCopy(cached);
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value == null ? null : ((String[]) value).clone();
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable)value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return x == null ? y == null : x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x == null ? 0 : x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		if(names != null && names.length > 0 && resultSet != null && resultSet.getArray(names[0]) != null){
			String[] results = (String[]) resultSet.getArray(names[0]).getArray();
			return results;
		}
		return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (value != null && statement != null){
			String[] castObject = (String[]) value;
			Array array = session.connection().createArrayOf("varchar", castObject);
			statement.setArray(index, array);
		}else{
			statement.setNull(index, SQL_TYPES[0]);
		}
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public Class<String[]> returnedClass() {
		return String[].class;
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

}
