package lzu.wms.dao;

import java.sql.SQLException;
import java.util.List;

import lzu.wms.domain.Person;

public interface PersonDAO {
	public void insert(Person person) throws SQLException;
	public void update(Person person) throws SQLException;
	public void delete(int id) throws SQLException;
	public Person findById(int id) throws SQLException;
	public List<Person> findAll() throws SQLException;
}
