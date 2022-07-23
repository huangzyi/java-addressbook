package Addressbook;

public interface FriendDao {
    public  void update(String name,String friendname,Friend f) throws Exception;
    public  void insert(String name,Friend f) throws Exception;
    public  void delete(String name,String friendname) throws Exception;

}
