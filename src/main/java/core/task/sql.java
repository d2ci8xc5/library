package core.task;

import java.sql.*;

public class sql {
    private static final String URL = "jdbc:mysql://localhost:3306/ebookmanager";
    private static final String NAME = "root";
    private static final String PASSWORD = "Mysql123";

    public static void main(String[] args) throws Exception {

        //1.Driver class: The driver class for the mysql database is com.mysql.jdbc.Driver.
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.connect to the database
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        //3.通过数据库的连接操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        try (Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        ResultSet rs = stmt.executeQuery("select * from articles");//选择import java.sql.ResultSet;
//        while (rs.next()) {//如果对象中有数据，就会循环打印出来
//            System.out.println(rs.getString("Author") + "," + rs.getInt("Title"));
//        }
    }
}
