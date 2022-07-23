package Addressbook;
import java.awt.*;
import java.awt.event.*;


import javax.swing.*;



public class Index extends JFrame{
	
	User u;
	public Index(User user)throws Exception {
		try{
			this.u=user;
		user.createview();
		}catch(Exception e) {
			System.out.println("创建视图失败");
		}
		try {
			u.groupinit();
	        System.out.println(u);
			}catch(Exception e)
			{
				System.out.println("获取该用户组别失败");
			} 
		Index frame1 = new Index(u,1);  
		
        frame1.setDefaultCloseOperation(Index.EXIT_ON_CLOSE);//一定要设置关闭  
        
        frame1.setVisible(true); 
	}
	
	    private JMenuBar menuBar; 
	    
	   
	  
	    
	public Index(User user,int a) throws Exception{
		 super();
		 this.u=user;
	        this.setSize(280,200);
	       
	        this.setJMenuBar(this.getMenu());//添加菜单
	        addWindowListener(new WindowAdapter() {  
	            public void windowClosing(WindowEvent e) {  
	            	try {
	            		u.dropview();
					} catch (Exception e1) {
						System.out.println("退出失败");
					}
	            	System.out.println("退出成功");
	            }  
	  
	             
	        });  

	        this.setTitle(this.u.name);//设置窗口标题
	        //System.out.println(u.name);
//	        pnl.setLayout(new GridLayout());
	         showallgroup();
	       
	         this.getContentPane().add(pnl);
	        
	        //this.add(pnl,null);
	    }
	
	    void close() {
	        setVisible(false);
	    }
	    
	   
	    private JMenuBar getMenu(){
	        if(menuBar==null){
	            menuBar = new JMenuBar();
	            JMenu m1 = new JMenu();
	            m1.setText("组别");
	            JMenu m2 = new JMenu();
	            m2.setText("好友");
	            JMenu m3 = new JMenu();
	            m3.setText("用户");
	    	    JButton btnsearch= new JButton("搜索"); 

	    	    JTextField txtsearch = new JTextField();
	            txtsearch.setBounds(100,0,10,1);
	            
				btnsearch.addActionListener(new ActionListener() {
			           
		            public void actionPerformed(ActionEvent e) {
		            	int r=0;
		            	Friend[] fruit=new Friend[100];
		                if(txtsearch.getText().equals("")){
		                    JOptionPane.showMessageDialog(null,"请输入好友姓名","错误",JOptionPane.ERROR_MESSAGE);
		                    txtsearch.grabFocus();
		                }else { 
		                	pnl.removeAll();
		                	f5();
		                	try {
		                		r=u.search(txtsearch.getText(),fruit);   	
		                	}catch(Exception e1) {
		                		JOptionPane.showMessageDialog(null,"搜索失败");
		                		return;
		                	}
		                	
		                    JLabel searchrs =new JLabel("搜索到"+r+"条结果");
		                    pnl.add(searchrs);
		                    JTextArea[] frt=new JTextArea[r];
		                    int k=r;
		                    while(k-->0) {
		                    	//String strfrt=fruit[k].string();
		                    	//String strfrt="y n";
		                    	//System.out.println(k);
		                    	frt[k]=new JTextArea(2,20);
		                    	frt[k].setText(fruit[k].string());
		                    	frt[k].setEditable(false);
		                    	pnl.add(frt[k]);            	
		                    }
		                    if(r>3) setSize(300,110+40*r);
		                    pnl.updateUI();
		                    pnl.repaint();
		                    
		                }
		            }
		        });
	            JMenuItem item11 = new JMenuItem();
	            item11.setText("显示");
	            
	            item11.addActionListener(new ActionListener() {
			           
		            public void actionPerformed(ActionEvent e) {
		            	
		            	try {
		            		showallgroup();
		            		setSize(280,200);
						} catch (Exception e1) {
							System.out.println("组显示失败");
						}
		            	System.out.println("组显示成功");
		            }
				        });
	            JMenuItem item21 = new JMenuItem();
	            item21.setText("显示全部");
	            item21.addActionListener(new ActionListener() {
			           
		            public void actionPerformed(ActionEvent e) {
		            	
		            	try {
		            		showallfriend();
		            		setSize(280,200);
						} catch (Exception e1) {
							System.out.println("好友显示失败");
						}
		            	System.out.println("好友显示成功");
		            }
				        });
	            JMenuItem item22 = new JMenuItem();
	            item22.setText("添加");
	            item22.addActionListener(new ActionListener() {
			           
		            public void actionPerformed(ActionEvent e) {
		            	
		            	try {
		            		addfriend();
		            		setSize(300,200);
						} catch (Exception e1) {
							System.out.println("插入显示失败");
						}
		            	System.out.println("插入显示成功");
		            }
				        });
	            
	            JMenuItem item31 = new JMenuItem();
	            item31.setText("退出");
	            item31.addActionListener(new ActionListener() {
			           
		            public void actionPerformed(ActionEvent e) {
		            	
		            	try {
		            		u.dropview();
						} catch (Exception e1) {
							System.out.println("退出失败");
						}
		            	System.out.println("退出成功");
		            	close();
		            }
		            
				        });
	            
	            m1.add(item11);
	            
	            m2.add(item21);
	            m2.add(item22);
	            
	            m3.add(item31);

	            
	            menuBar.add(m1);
	            menuBar.add(m2);
	            menuBar.add(m3);
	            menuBar.add(txtsearch);
	            menuBar.add(btnsearch);
	        }
	        return menuBar;
	    }
	   
	    
        int i=0;
        JPanel pnl = new JPanel(); 
        
        public void f5() {
        	pnl.updateUI();
            pnl.repaint();
        }
	    public void showallgroup() {
	    	pnl.removeAll();
	    	int x=10,y= 10;
	    	JLabel lblgrp = new JLabel("组别");
	    	lblgrp.setLocation(x,y);
	    	//getContentPane().add(lblgrp);
	    	 pnl.add(lblgrp);
	    	 
	    	 System.out.println(u.groupnumber);
	    	 JButton[] btngrp= new JButton[u.groupnumber];
					for(i=0;i<this.u.groupnumber;i++) {
						x=x+20;
						y=y+20;
						 btngrp[i] = new JButton();
						 btngrp[i].setText(u.group[i].groupname);
						btngrp[i].setLocation(x,y);
						 pnl.add(btngrp[i]);
						 btngrp[i].setActionCommand(""+i);
						 btngrp[i].addActionListener(new ActionListener() {
				           
			            public void actionPerformed(ActionEvent e) {
			            	int num = 0;
			            	try {
			            		JButton button=(JButton)e.getSource();
			            		num = Integer.valueOf(button.getActionCommand());
								u.group[num].init(u.name);
								//System.out.println(u.group[num].member[0]);
								//System.out.println(u.group[num]);
							} catch (Exception e1) {
								System.out.println("组初始化失败");
							}
			                pnl.removeAll();
			                
			                showgroupfriend(u.group[num]);
			                f5();
			            }
					        });
						 //getContentPane().repaint();
						 
				}	
					f5();
     
			
	    }
	    int j=0;
	    public void showgroupfriend(Group g) {
	    	pnl.removeAll();
	    	int x=10,y= 10;
	    	JLabel lblfd = new JLabel("Friend");
	    	lblfd.setLocation(x,y);
	    	
	    	 pnl.add(lblfd);
	    	 f5();
	    	 System.out.println(g.number);
	    	 JButton[] btnfd= new JButton[g.number];
					for(int j=0;j<g.number;j++) {
						x=x+20;
						y=y+20;
						 btnfd[j] = new JButton();
						btnfd[j].setText(g.member[j].name);
						btnfd[j].setLocation(x,y);
						btnfd[j].setActionCommand(""+j);
						 pnl.add(btnfd[j]);
						 btnfd[j].addActionListener(new ActionListener() {
					           
					            public void actionPerformed(ActionEvent e) {
					            	int num=0;
					            	try {JButton button=(JButton)e.getSource();
					            	 num= Integer.valueOf(button.getActionCommand());
					            		System.out.println(num);
					            		g.member[num].init(u.name);
										System.out.println(g.member[num]);
									} catch (Exception e1) {
										System.out.println("组初始化失败");
									}
					                pnl.removeAll();
					                showfriend(g.member[num]);
					                f5();
					            }
							        });
				}	
					f5();
				
	    };
	    public void showallfriend() {
	    	pnl.removeAll();
	    	int x=10,y= 10;
	    	JLabel lblfd = new JLabel("Friend");
	    	lblfd.setLocation(x,y);
	    	
	    	 pnl.add(lblfd);
	    	 f5();
	    	 int r=0;
         		Friend[] fruit=new Friend[100];      
             	try {
             		r=u.search("",fruit);   	
             	}catch(Exception e1) {
             		JOptionPane.showMessageDialog(null,"搜索失败");
             		return;
             	}
                 
                 JButton[] frt=new JButton[r];
                 int k=r;
                 while(k-->0) {
                 	frt[k]=new JButton(fruit[k].name);
                 	frt[k].setActionCommand(""+k);
                 	frt[k].addActionListener(new ActionListener() {
				           
			            public void actionPerformed(ActionEvent e) {
			            	int num=0;
			            	try {JButton button=(JButton)e.getSource();
			            	 num= Integer.valueOf(button.getActionCommand());
			            		System.out.println(num);
								System.out.println(fruit[num]);
							} catch (Exception e1) {
								System.out.println("组初始化失败");
							}
			                pnl.removeAll();
			                showfriend(fruit[num]);
			                f5();
			            }
					        });
                 	pnl.add(frt[k]);
                 }
                 f5();
	    	 
				}	
	    public void showfriend(Friend f) {
					pnl.removeAll();
//					pnl.setLayout(new GridLayout(4,4));
					JLabel lfd1 = new JLabel("姓名");
					JLabel lfd2 = new JLabel("电话");
					JLabel lfd3 = new JLabel("性别");
					JLabel lfd4 = new JLabel("组名");
					JLabel lfd5 = new JLabel("QQ");
					JLabel lfd6 = new JLabel("wechat");
					
					JTextField txtfd1 =new JTextField(f.name,7);
					JTextField txtfd2 =new JTextField(f.phonenumber,8);
					JTextField txtfd3 =new JTextField(f.QQ,7);
					JTextField txtfd4 =new JTextField(f.Wechat,7);
					
					JComboBox bxfd1 = new JComboBox();
						bxfd1.setPreferredSize(new Dimension(110,20));
						bxfd1.addItem("Male");  
						bxfd1.addItem("Female");  
						bxfd1.addItem("Unknown");
						int s;
						switch(f.sex) {
		            		case "Male":
		            			s=0;
		            			break;
		            		case "Female":
		            			s=1;
		            			break;
		            		default:
		            			s=2;
		            };
		            bxfd1.setSelectedIndex(s);
					JComboBox bxfd2 = new JComboBox();

						int c=0;
						for(int i=0;i<u.groupnumber;i++)
						{
							bxfd2.addItem(u.group[i].groupname);
							if(f.groupname==u.group[i].groupname)	c=i;
							
						}
						bxfd2.setSelectedIndex(c);
						bxfd2.setEditable(true);
						bxfd2.setPreferredSize(new Dimension(90,20));
					JButton btnfddel =new JButton("删除该好友");
					JButton btnfdupdt =new JButton("修改");
					
					btnfdupdt.addActionListener(new ActionListener() {
				           
			            public void actionPerformed(ActionEvent e) {
			                if(txtfd1.getText().equals("")){
			                    JOptionPane.showMessageDialog(null,"请输入好友姓名","错误",JOptionPane.ERROR_MESSAGE);
			                    txtfd1.grabFocus();
			                }else { 
			                	String name=f.name;
			                	f.Friend(
			                			txtfd1.getText(),
			                			txtfd2.getText(),
			                			(String)bxfd1.getSelectedItem(),
			                		    txtfd3.getText(),
			                			txtfd4.getText(), 
			                			(String) bxfd2.getSelectedItem());
			                	try {
			                	f.update(u.name, name, f);    	
			                	}catch(Exception e1) {
			                		JOptionPane.showMessageDialog(null,"修改失败");
			                		return;
			                	}
			                    JOptionPane.showMessageDialog(null,"修改成功","提示",JOptionPane.INFORMATION_MESSAGE);

								try {
									u.groupinit();
//									System.out.println(u);
								} catch (Exception ex) {
									throw new RuntimeException(ex);
								}


							}
			            }
			        });
					btnfddel.addActionListener(new ActionListener() {
				           
			            public void actionPerformed(ActionEvent e) {
			                
			                	String name=f.name;
			                	try {
			                	f.delete(u.name, name);    	
			                	}catch(Exception e1) {
			                		JOptionPane.showMessageDialog(null,"删除失败");
			                		return;
			                	}
			                    JOptionPane.showMessageDialog(null,"删除成功","提示",JOptionPane.INFORMATION_MESSAGE);

								try {
									u.groupinit();
//									System.out.println(u);
								} catch (Exception ex) {
									throw new RuntimeException(ex);
								}


								pnl.removeAll();
			                    f5();
			   
			            }
			        });
		            pnl.add(lfd1);pnl.add(txtfd1);
		            pnl.add(lfd2);pnl.add(txtfd2);
		            pnl.add(lfd3);pnl.add(bxfd1);
		            pnl.add(lfd4);pnl.add(bxfd2);
		            pnl.add(lfd5);pnl.add(txtfd3);
		            pnl.add(lfd6);pnl.add(txtfd4);
		            pnl.add(btnfdupdt);pnl.add(btnfddel);
		            f5();
					
	            };
	            public void addfriend() {
					pnl.removeAll();
					JLabel lfd1 = new JLabel("姓名");
					JLabel lfd2 = new JLabel("电话");
					JLabel lfd3 = new JLabel("性别");
					JLabel lfd4 = new JLabel("组名");
					JLabel lfd5 = new JLabel("QQ");
					JLabel lfd6 = new JLabel("wechat");
					
					JTextField txtfd1 =new JTextField(7);
					JTextField txtfd2 =new JTextField(8);
					JTextField txtfd3 =new JTextField(7);
					JTextField txtfd4 =new JTextField(7);
					
					JComboBox bxfd1 = new JComboBox();
						bxfd1.setPreferredSize(new Dimension(110,20));
						bxfd1.addItem("Male");  
						bxfd1.addItem("Female");  
						bxfd1.addItem("Unknown");
						
		            
					JComboBox bxfd2 = new JComboBox();
					bxfd2.setPreferredSize(new Dimension(90,20));
						for(int i=0;i<u.groupnumber;i++)
							bxfd2.addItem(u.group[i].groupname);
						bxfd2.setEditable(true);
					
					JButton btnfdadd =new JButton("添加");
					
					btnfdadd.addActionListener(new ActionListener() {
				           
			            public void actionPerformed(ActionEvent e) {
			                if(txtfd1.getText().equals("")){
			                    JOptionPane.showMessageDialog(null,"请输入好友姓名","错误",JOptionPane.ERROR_MESSAGE);
			                    txtfd1.grabFocus();
			                }else {
			                	System.out.println(bxfd1.getSelectedItem());
			                	Friend f=new Friend(
			                			txtfd1.getText(),
			                			txtfd2.getText(),
			                			bxfd1.getSelectedItem().toString(),
			                			txtfd3.getText(),
			                			txtfd4.getText(), 
			                			bxfd2.getSelectedItem().toString());
			                	System.out.println(f);
			                	try {
			                	f.insert(u.name, f);    	
			                	}catch(Exception e1) {
			                		JOptionPane.showMessageDialog(null,"插入失败");
			                		return;
			                	}
			                    JOptionPane.showMessageDialog(null,"插入成功","提示",JOptionPane.INFORMATION_MESSAGE);
								try {
									u.groupinit();
								} catch (Exception ex) {
									throw new RuntimeException(ex);
								}
								pnl.removeAll();
			                    showfriend(f);
			                    f5();
			                }
			            }
			        });
					
		            pnl.add(lfd1);pnl.add(txtfd1);
		            pnl.add(lfd2);pnl.add(txtfd2);
		            pnl.add(lfd3);pnl.add(bxfd1);
		            pnl.add(lfd4);pnl.add(bxfd2);
		            pnl.add(lfd5);pnl.add(txtfd3);
		            pnl.add(lfd6);pnl.add(txtfd4);
		            pnl.add(btnfdadd);
		            f5();
					
	            };

//    public static void main(String[] args) {
//
//        User us= new User("huangzyi","123");
//        try {
//        Index i=new Index(us);
//        }catch(Exception e) {
//        	System.out.println("创建框架失败");
//        }
//     }
}
