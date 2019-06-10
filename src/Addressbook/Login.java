package Addressbook;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.*;


public class Login extends JFrame {   
    JLabel lbl1 = new JLabel("用户名:");
    JLabel lbl2 = new JLabel("密     码:"); 
    JTextField txt = new JTextField(20);
    JPasswordField pwd = new JPasswordField(20); 
    JButton btn1 = new JButton("注册"); 
    JButton btn = new JButton("登录");   
    JPanel pnl = new JPanel(); 
    
     
    public Login(String title) throws HeadlessException {
        super(title);
        init();
    }
 
 
    void close() {
        setVisible(false);
    }
    private void init() {       
        this.setResizable(false);      
         
        pwd.setEchoChar('*');
         
        pnl.add(lbl1);
        pnl.add(txt);
        pnl.add(lbl2);
        pnl.add(pwd);
        pnl.add(btn1); 
        pnl.add(btn);      
        this.getContentPane().add(pnl);
        btn.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
            	User user =new User();
            	String password=new String(pwd.getPassword());
                if(txt.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"请输入用户名","错误",JOptionPane.ERROR_MESSAGE);
                    txt.grabFocus();
                }else if(password.equals("")){
                	JOptionPane.showMessageDialog(null,"请输入密码","错误",JOptionPane.ERROR_MESSAGE);
                    pwd.grabFocus();
                }else { 
                	try {
                	user.init(txt.getText(),password);
                	
                	}catch(Exception e1) {
                		JOptionPane.showMessageDialog(null,"错误：用户名或密码错误");
                		return;
                	}
                    System.out.println("登录成功");
                    close();
                    try {
                    Index index =new Index(user);
                     }catch(Exception e1) {
                    	System.out.println("创建框架失败");
                    }
                }
            }
        });

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                	User user =new User();
                	String password=new String(pwd.getPassword());
                    if(txt.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"请输入用户名","错误",JOptionPane.ERROR_MESSAGE);
                        txt.grabFocus();
                    }else if(password.equals("")){
                    	JOptionPane.showMessageDialog(null,"请输入密码","错误",JOptionPane.ERROR_MESSAGE);
                        pwd.grabFocus();
                    }else { 
                    	try {
                    	user.insert(txt.getText(),password);
                    	
                    	}catch(Exception e1) {
                    		JOptionPane.showMessageDialog(null,"错误：已存在该用户或格式错误");
                    		return;
                    	}
                        JOptionPane.showMessageDialog(null,"注册成功,请登录","提示",JOptionPane.INFORMATION_MESSAGE);
                    }
                	
                
            }
        });

    }  
 
    public static void main(String[] args) {
       Login frm = new Login("login");  
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setBounds(100, 100, 300, 120);
        frm.setVisible(true);
        
    }
}