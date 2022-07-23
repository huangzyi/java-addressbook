package Addressbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Friend extends MySQL implements FriendDao{
	public String name;

	public Friend() {
		super();
	}
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPhonenumber() {
		return phonenumber;
	}



	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}



	public String getGroup() {
		return groupname;
	}



	public void setGroup(String groupname) {
		this.groupname = groupname;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getWechat() {
		return Wechat;
	}
	public void setWechat(String wechat) {
		Wechat = wechat;
	}
	public Friend(String name, String phonenumber, String sex, String qQ, String wechat, String groupname) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
		this.sex = sex;
		this.QQ = qQ;
		this.Wechat = wechat;
		this.groupname = groupname;
	}
	public void Friend(String name, String phonenumber, String sex, String qQ, String wechat, String groupname) {
		this.name = name;
		this.phonenumber = phonenumber;
		this.sex = sex;
		this.QQ = qQ;
		this.Wechat = wechat;
		this.groupname = groupname;
	}
	protected String phonenumber;
	protected String sex;
	protected String QQ;
	protected String Wechat;
	public String groupname;
	
	public  Friend(String name,String groupname) {
		this.name = name;
		this.groupname = groupname;
	}
	
	public  Friend init(String name) throws Exception {
        Connection conn=MySQL.getConnection();
        String sql="select * from " + name + " where friendname = '"+this.name+"'";
        PreparedStatement stmt= conn.prepareStatement(sql);
        ResultSet rs=    stmt.executeQuery();
        while(rs.next()) {
            this.Friend(
            		rs.getString("friendname"), 
            		rs.getString("phonenumber"), 
            		rs.getString("sex"), 
            		rs.getString("QQ"),
            		rs.getString("wechat"),
            		rs.getString("groupname")
                    );
        }
        MySQL.closeAll(conn, stmt, rs);
        return this;
	}
	
	public  void update(String name,String friendname,Friend f) throws Exception {
        Connection conn=MySQL.getConnection();
        String sql="UPDATE friend SET friendname='" +f.name+ 
        		"',phonenumber='"+f.phonenumber + 
        		"',sex='" + f.sex+
        		"',QQ='" + f.QQ+
        		"',wechat='" + f.Wechat+
        		"',groupname='" + f.groupname + 
        		"' where friendname = '"+friendname+
        		"' AND username ='"+name+"'";
        PreparedStatement stmt= conn.prepareStatement(sql);
        //System.out.println(sql);
        int rs=    stmt.executeUpdate();
        if(rs!=0)	System.out.println("success");
        MySQL.closeAll(conn, stmt);
        
	}
	public  void insert(String name,Friend f) throws Exception {
        Connection conn=MySQL.getConnection();
        String sql="INSERT into friend (username,friendname,phonenumber,sex,QQ,wechat,groupname)"
        		+ " VALUES ('" +name+
        		"','"+f.name+ 
        		"','"+f.phonenumber + 
        		"','" + f.sex+
        		"','" + f.QQ+
        		"','" + f.Wechat+
        		"','" + f.groupname + 
        		"')";
        PreparedStatement stmt= conn.prepareStatement(sql);
        System.out.println(sql);
        int rs=    stmt.executeUpdate();
        if(rs!=0)	System.out.println("success");
        MySQL.closeAll(conn, stmt);
        
	}
	public  void delete(String name,String friendname) throws Exception {
        Connection conn=MySQL.getConnection();
        String sql="DELETE FROM friend where friendname = '"+friendname+
        		"' AND username ='"+name+"'";
        PreparedStatement stmt= conn.prepareStatement(sql);
        System.out.println(sql);
        int rs=    stmt.executeUpdate();
        if(rs!=0)	System.out.println("success");
        MySQL.closeAll(conn, stmt); 
	}
	public String toString() {
		return 		  "name="+this.name+
		"  phonenumber="+this.phonenumber  +
		"  sex="+this.sex +
		"  qq="+this.QQ +
		"  wechat="+this.Wechat  +
		"  groupname="+this.groupname ;
	}
	public String string() {
		return 		  "name="+this.name+
		"  groupname="+this.groupname+
		"\nphonenumber="+this.phonenumber  +
		"  sex="+this.sex +
		"\nqq="+this.QQ +
		"  wechat="+this.Wechat;
	}

}
