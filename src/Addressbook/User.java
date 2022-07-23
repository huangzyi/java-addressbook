package Addressbook;

import java.security.MessageDigest;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
public class User extends MySQL implements UserDao{
	public String name;
	public void User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	public User() {
		
	}
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

public String toString() {
	return "name:"+name+groupnumber;
}


	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	private String password;
	int groupnumber=0;
	public int getGroupnumber() {
		return groupnumber;
	}
	public void setGroupnumber(int groupnumber) {
		this.groupnumber = groupnumber;
	}

	Group group []= new Group[100];

	
	public  User init(String n,String psw) throws Exception{// ,Exceptionlogin{
        Connection conn=MySQL.getConnection();
        System.out.println("connect");
        //String sql="select * from user WHERE username = '" + n + "'AND password = '" + psw+"'";
        String sql="select * from user WHERE username = ? AND password = ?";
        PreparedStatement stmt= conn.prepareStatement(sql);
        //System.out.println(sql);
        stmt.setString(1, n);
        stmt.setString(2, md5(psw));
        ResultSet rs= stmt.executeQuery();
        if(!rs.next()) ;
        else {
            this.User(
            		rs.getString("username"),
            		rs.getString("password")
                    );
        }
        MySQL.closeAll(conn, stmt, rs);
        return this;
	}
	
	
	public void createview()throws Exception {
        Connection conn=MySQL.getConnection();
        
        String sql="create OR REPLACE VIEW " + this.name + " AS select * from friend WHERE username = '" + this.name+"'";
        PreparedStatement stmt= conn.prepareStatement(sql);
        //System.out.println(sql);
        int rs=  stmt.executeUpdate();
        //System.out.println(rs);
        if(rs==0) {
        	//System.out.println("success");
        };
        MySQL.closeAll(conn, stmt);
        return ;
	}
	
	public void dropview()throws Exception {
        Connection conn=MySQL.getConnection();
        String sql="DROP VIEW "+  this.getName() ;
        PreparedStatement stmt= conn.prepareStatement(sql);
        int rs=    stmt.executeUpdate();
        if(rs!=0)	System.out.println("success");;
        MySQL.closeAll(conn, stmt);
        return ;
	}
	
	public void groupinit() throws Exception {
		 Connection conn=MySQL.getConnection();
	        //System.out.println("connect");
	        String sql1="select COUNT(DISTINCT groupname) c from " + name ;
	        PreparedStatement stmt1= conn.prepareStatement(sql1);
	        //System.out.println("prepare");System.out.println(sql1);
	        ResultSet rs1=    stmt1.executeQuery();
	        
	        while(rs1.next()) {
	        this.groupnumber =rs1.getInt("c");
	        System.out.println(this.groupnumber);
	        };
	        closeSQL(stmt1,rs1);
	        /*
	        String sql2="select *  from " + name +" where groupname is null";
	        PreparedStatement stmt2= conn.prepareStatement(sql2);
	        System.out.println("prepare");System.out.println(sql2);
	        ResultSet rs2=    stmt2.executeQuery();
	        
	        if(rs2.next()) {
	        	//System.out.println("exist");
	        this.groupnumber++;
	        System.out.println(this.groupnumber);
	        };
	        
	        closeSQL(stmt2,rs2);
	       */
        String sql="select DISTINCT groupname c from " + this.name ;
        PreparedStatement stmt= conn.prepareStatement(sql);
        ResultSet rs=    stmt.executeQuery();
        int i=0;
        while(rs.next()) {
        this.group[i++]=new Group(rs.getString("c"));
        };       
        //this.setGroupnumber(i);
        MySQL.closeAll(conn, stmt, rs);
        return ;
	}

	public int search(String n,Friend[] fruit) throws Exception{
	       Connection conn=MySQL.getConnection();
	       String s ="'%"+n+"%'";
	        String sql="select * from " + this.name +
	        		" where friendname LIKE "+s+ 
	        		" or groupname LIKE "+s+
	        		" or phonenumber LIKE " + s+
	        		" or QQ LIKE"+s+
	        		" or wechat LIKE"+s;
	        PreparedStatement stmt= conn.prepareStatement(sql);
	        ResultSet rs=    stmt.executeQuery();
	        int i=0;
	        //Friend fruit[]=new Friend[10];
	        if (!rs.next()) {
	        	return 0;
	        }
	        do{
	        fruit[i++]=new Friend(
            		rs.getString("friendname"), 
            		rs.getString("phonenumber"), 
            		rs.getString("sex"), 
            		rs.getString("QQ"),
            		rs.getString("wechat"),
            		rs.getString("groupname")
                    );
	        //System.out.println(fruit[i-1]);
	        }while(rs.next()) ;
	         
	        MySQL.closeAll(conn, stmt, rs);
	        return i;
	}
	
	public  void insert(String n,String psw) throws Exception{
        Connection conn=MySQL.getConnection();
        String sql="INSERT INTO user VALUES('" + n + "','" + md5(psw)+"')";
        PreparedStatement stmt= conn.prepareStatement(sql);
        int rs= stmt.executeUpdate();
        if(rs==1) {
        	//System.out.println("success");
        };
        MySQL.closeAll(conn, stmt);
        return ;
	}
	public static String md5(String str) {
		return DigestUtils.md5Hex(str);
	}
}