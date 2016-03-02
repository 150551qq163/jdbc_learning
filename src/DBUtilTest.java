import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.db.DBUtils;

import lzu.wms.domain.Person;

public class DBUtilTest {
	public static void findAll(){
		Connection connection=DBUtils.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String sql="select name ,age ,description from person";
		try {
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				Person person=new Person(resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("description"));
				System.out.println(person);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}
	public static void main(String[] args) {
		findAll();
	}
}
