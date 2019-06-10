package Addressbook;

import java.sql.*;
 
public class MySQL{
 
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/register";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "";
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(DB_URL, USER, PASS);    
}

public static void closeAll(Connection conn,Statement stmt,ResultSet rs) throws SQLException {
    if(rs!=null) {
        rs.close();
    }
    if(stmt!=null) {
        stmt.close();
    }
    if(conn!=null) {
        conn.close();
    }
}

public static void closeSQL(Statement stmt,ResultSet rs) throws SQLException {
    if(rs!=null) {
        rs.close();
    }
    if(stmt!=null) {
        stmt.close();
    }
}


public static void closeAll(Connection conn, PreparedStatement stmt)throws SQLException {
    if(stmt!=null) {
        stmt.close();
    }
    if(conn!=null) {
        conn.close();
    }
    }
	
}

	


