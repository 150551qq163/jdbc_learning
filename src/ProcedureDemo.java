import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.util.db.DBUtils;

import lzu.wms.domain.Person;

public class ProcedureDemo {
	public static void callProcedure() {
		Connection connection = null;
		CallableStatement callableStatement = null;
		String sql = "{call javaProcedure(?,?)}";
		try {
			connection = DBUtils.getConnection();
			callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, "Ì¬¶È");
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			if (callableStatement.execute()) {
				ResultSet resultSet=callableStatement.getResultSet();
				while (resultSet.next()) {
					System.out.println(new Person(resultSet.getString("name"), resultSet.getInt("age"),resultSet.getString("description") ));
				}
			}
			System.out.println(callableStatement.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(null, callableStatement, connection);
		}
	}

	public static void main(String[] args) {
		callProcedure();
	}
}
