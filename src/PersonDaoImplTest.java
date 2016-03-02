import java.sql.SQLException;

import lzu.wms.dao.impl.PersonDaoImpl;
import lzu.wms.domain.Person;

public class PersonDaoImplTest {
	public static void main(String[] args) {
		PersonDaoImpl daoImpl = new PersonDaoImpl();
		/*
		 * try { daoImpl.insert(new Person("wms", 18, "lzuStudent")); } catch
		 * (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } try { daoImpl.update(new Person(2, "xyy", 18,
		 * "saleManager")); } catch (SQLException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		/*
		 * try { daoImpl.findById(10); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } try {
		 * daoImpl.delete(11); } catch (SQLException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		try {
			daoImpl.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
