package com.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Template {
	/**
	 * 增删改的抽象
	 * @param sql
	 * @param args
	 */
	public int update(String sql,Object ...args){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			connection=DBUtils.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				preparedStatement.setObject(i+1, args[i]);
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}
}
