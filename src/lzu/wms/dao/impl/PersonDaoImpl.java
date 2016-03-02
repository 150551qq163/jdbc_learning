package lzu.wms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.db.DBUtils;
import com.util.db.Template;

import lzu.wms.dao.PersonDAO;
import lzu.wms.domain.Person;

public class PersonDaoImpl implements PersonDAO {
	private Template templete;
	public PersonDaoImpl() {
		templete=new Template();
	}
	@Override
	public void insert(Person person) throws SQLException {
		/**
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("insert into person(name,age,description) values(?,?,?);");
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getAge());
			preparedStatement.setString(3, person.getDescription());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("ÃÌº” ˝æ› ß∞‹");
		} finally {
			DBUtils.close(null, preparedStatement, connection);
		}
		*/
		String sql="insert into person(name,age,description) values(?,?,?);";
		templete.update(sql, person.getName(),person.getAge(),person.getDescription());
	}

	@Override
	public void update(Person person) throws SQLException {
		/**
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("update person set name=?,age=?,description=? where id=?;");
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getAge());
			preparedStatement.setString(3, person.getDescription());
			preparedStatement.setInt(4, person.getId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, preparedStatement, connection);
		}
		*/
		String sql="update person set name=?,age=?,description=? where id=?;";
		templete.update(sql, person.getName(),person.getAge(),person.getDescription(),person.getId());
	}

	@Override
	public void delete(int id) throws SQLException {
		/**
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("delete from person where id=?;");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, preparedStatement, connection);
		}
		*/
		templete.update("delete from person where id=?;", id);
	}

	@Override
	public Person findById(int id) throws SQLException {
		Person person = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from person where id=?;");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				person = new Person(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getInt("age"),
						resultSet.getString("description"));
				System.out.println(person.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("≤È’“ ß∞‹");
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
		return person;
	}

	@Override
	public List<Person> findAll() throws SQLException {
		List<Person> persons = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from person ;");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Person person = new Person(resultSet.getString("name"), resultSet.getInt("age"),
						resultSet.getString("description"));
				System.out.println(person.toString());
				persons.add(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("≤È’“ ß∞‹");
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
		return persons;
	}

}
