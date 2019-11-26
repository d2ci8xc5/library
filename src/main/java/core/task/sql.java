package core.task;

import core.Book;

import java.sql.*;

public class sql {
    private static final String URL = "jdbc:mysql://localhost:3306/ebookmanager";
    private static final String NAME = "root";
    private static final String PASSWORD = "Mysql123";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Book book = new Book();
        //1.Driver class: The driver class for the mysql database is com.mysql.jdbc.Driver.
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.connect to the database
        conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        stmt = conn.createStatement();
        try (Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD)) {
            System.out.println("Database connected!");
            //get the result
            String t = "abc";
            book.set_title(t);
            String _title = book.get_title();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `articles`(Title) VALUES (?)");
            pstmt.setString(1, _title);
            pstmt.executeUpdate();
            rs = stmt.executeQuery("select * from articles");
            while (rs.next()) {
                //Book book = new Book();
                book.set_author(rs.getString("Author"));
                System.out.println(rs.getString("Title") + "," + rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        finally{
            try{
                if(stmt!=null){
                    stmt.close();
                }
                if(rs!=null){
                    rs.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
