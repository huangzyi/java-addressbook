package Addressbook;

public interface UserDao {
    public void createview()throws Exception;
    public void dropview()throws Exception;
    public void groupinit() throws Exception;
    public int search(String n,Friend[] fruit) throws Exception;
    public  void insert(String n,String psw) throws Exception;

    public static String md5(String str) {
        return "";
    }
}
