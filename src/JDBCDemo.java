
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

import lzu.wms.domain.Person;

public class JDBCDemo {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	public final static String URL = "jdbc:mysql://localhost:3306/jdbc";
	public final static String USERNAME = "root";
	public final static String PASSWORD = "123456";

	/**
	 * �������
	 */
	public static void insert() {
		try {
			// 1.��������
			Class.forName(DRIVER);
			// 2.��ȡ���ݿ�����
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3.����SQL���
			String sql = "insert into person(name,age,description) values('С��',25,'һ��������ǲ���������')";
			// 4.����һ��Statementʵ��(��������sql��������)
			Statement statement = connection.createStatement();
			// 5.ִ��SQL���
			int result = statement.executeUpdate(sql);
			// 6.�ر�����
			statement.close();
			connection.close();
			System.out.println(result);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void update() {
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "update person set age=19 where id=1";
			Statement statement = connection.createStatement();
			System.out.println(statement.executeUpdate(sql));
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void query() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", USERNAME, PASSWORD);
			String sql = "select * from person";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("id"));
				System.out.println(resultSet.getString("name"));
				System.out.println(resultSet.getInt("age"));
				System.out.println(resultSet.getString("description"));
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void insertWithValue(Person person){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "123456");
			String sql="insert into person(name,age,description) values(?,?,?)";
			PreparedStatement  preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getAge());
			preparedStatement.setString(3,person.getDescription());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void update(Person person){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "123456");
			String sql="update person set name=?,age=? where name=?";
			PreparedStatement preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getAge());
			preparedStatement.setString(3, "С��");
			preparedStatement.execute();
			preparedStatement.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void delete(int id){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "123456");
			PreparedStatement preparedStatement=connection.prepareStatement("delete from person where id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Person findById(int id){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", USERNAME, PASSWORD);
			PreparedStatement preparedStatement=conn.prepareStatement("select * from person where id=?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return new Person(id,resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("description"));
			}else{
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void login(String name){
		Person p=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "123456");
			PreparedStatement prepareStatement = conn.prepareStatement("select * from person where name =?");
			prepareStatement.setString(1, name);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
				p=new Person(resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("description"));
			}
			System.out.println(p.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test(){
		Connection conn =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root", "123456");
			conn.setAutoCommit(false);
			PreparedStatement preparedStatement1=conn.prepareStatement("insert into person(name,age,description)values('С��',19,'�������')");
			preparedStatement1.executeUpdate();
			PreparedStatement preparedStatement2=conn.prepareStatement("update person set name=? where id=?");
			preparedStatement2.setString(1, "С��");
			preparedStatement2.setInt(2, 5);
			preparedStatement2.executeUpdate();
			conn.commit();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		// insert();
		// update();
//		query();
//		insertWithValue(new Person("С��", 18, "һ���ܺڵ���"));
//		update(new Person("����", 30, "��Ц������ں�"));
//		delete(1);
//		System.out.println(findById(2));
//		login("С��");
		test();
	}
}
