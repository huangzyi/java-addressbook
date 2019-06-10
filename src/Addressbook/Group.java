package Addressbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Group extends MySQL{
	String groupname;
	int number;
	Friend[] member = new Friend[100];
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public Group(){
		number = 0;
	}
	public Group(String gn) {
		this.groupname = gn;
	}
	public String toString() {
		int i=0;
		String str="group";
		while(i<this.number)
		str+=i+member[i].name;
		return str;
	}
	
	public void init(String name) throws Exception {
        Connection conn=MySQL.getConnection();
        System.out.println("connect");
        String sql1="select COUNT(DISTINCT friendname) c from " + name +" WHERE groupname='"+this.groupname+"'";
        PreparedStatement stmt1= conn.prepareStatement(sql1);
        //System.out.println("prepare");System.out.println(sql1);
        ResultSet rs1=    stmt1.executeQuery();
        
        while(rs1.next()) {
        this.number =rs1.getInt("c");
        System.out.println(this.number);
        };
        closeSQL(stmt1,rs1);
        String sql2="select friendname from " + name +" where groupname = '"+this.groupname+"'";
        PreparedStatement stmt2= conn.prepareStatement(sql2);
        //System.out.println("prepare");System.out.println(sql2);
        ResultSet rs2=    stmt2.executeQuery();
        int i=0;
        while(rs2.next()) {
        	
        this.member[i++]=new Friend(
        		rs2.getString("friendname"),
        		this.groupname);
        //System.out.println(this.member[i-1]);
        		};       
        MySQL.closeAll(conn, stmt2, rs2);
        //System.out.println("close");
        return ;
	}
}
