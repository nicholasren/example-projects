  /*
  * Created on 2005-9-8
  *
  * TODO To change the template for this generated file go to
  * Window - Preferences - Java - Code Style - Code Templates
  */
 package client;
 import java.awt.*;
 import java.awt.event.*;
 /**
  * @author 09003112С��
  *
  * TODO To change the template for this generated type comment go to
  * Window - Preferences - Java - Code Style - Code Templates
  */
 public class SaveMoney extends Frame 
 {
 	public static final String LOAD="LOAD";                            //��¼
 	public static final String REGISTER="REGISTER";                    //ע��
 	public static final String ALLONLINE="ALLONLINE";                  //��ѯ���������û���
 	public static final String ALLFILE="ALLFILE";                      //��ѯ�����ļ���
 	public static final String FINDFILE="FINDFILE";                    //����ĳ���ļ�
 	public static final String FINDOFONE="FINDOFONE";                  //����ĳ���˵������ļ�
 	public static final String UPLOAD="UPLOAD";                        //�ϴ�
 	public static final String DOWNLOAD="DOWNLOAD";                    //����
 	public static final String TIME="TIME";                            //��ѯʱ�䣬��ת����
 	public static final String REFILELIST="REFILELIST";                //ˢ���ļ��б�
 	public static final String REUSERLIST="REUSERLIST";                 //ˢ���û��б�
 	public static final String SAVEMONEY="SAVEMONEY";                     //ת��
 	
 	
 	 static Frame zhuche=new Frame("����ת��");
 	static TextField txf1=new TextField();//�ʻ���
 	 static TextField txf2=new TextField();//����
 	 static TextField txf3=new TextField();//ת�ʽ��
 	 static Label lab1=new Label("�ʻ��ţ�");
 	 static Label lab2=new Label("�ʻ�����:");
 	 static Label lab3=new Label("ת�ʽ��:");
 	 static Button btn1=new Button ("ȷ��");
 	 static Button btn2=new Button ("����");
 	 
 ///////////////////////////////����ת��////////////////////////////////////////	 
 	public void run()
 	{
 		zhuche.setBounds(300,300,250,220);
 		zhuche.setLayout(null);
 		zhuche.setBackground(Color.BLUE);
 		txf1.setBounds(100,40,120,20);//�ʻ���
 		txf2.setBounds(100,70,120,20);//����
 		txf3.setBounds(100,100,120,20);//��ֵ���
 		lab1.setBounds(20,40,60,20);
 		lab2.setBounds(20,70,60,20);
 		lab3.setBounds(20,100,70,20);
 		btn1.setBounds(50,140,50,30);
 		btn2.setBounds(150,140,50,30);
 		
 		btn1.addActionListener(new ActLisz());
 		btn2.addActionListener(new ActLisz());
 		
 		btn1.setBackground(Color.GREEN);
 		btn2.setBackground(Color.GREEN);
 		txf2.setEchoChar('*');
 		
 		zhuche.add(txf1);
 		zhuche.add(txf2);
 		zhuche.add(txf3);
 		zhuche.add(lab1);
 		zhuche.add(lab2);
 		zhuche.add(lab3);
 		zhuche.add(btn1);
 		zhuche.add(btn2);
 		
 		zhuche.addWindowListener
 		(new WindowAdapter()
 				{public void windowClosing
 			(WindowEvent e)
 				{zhuche.dispose();}});
 		zhuche.setVisible(true);
 		}
 	
 	class ActLisz implements ActionListener{
 	public void actionPerformed(ActionEvent a)
 	{
 	Button  btn=(Button)a.getSource();
 	
///////////////////////////////ע��ȷ��////////////////////////////////////////// 	
 	
 	if(btn==btn1)//ȷ��
 	{
 		//�����������������������Ϣ����ȡ�÷��������ص���Ϣ
 		boolean aa=true;
 		long money=0L;
 			try
 			{
 				money=Integer.parseInt(txf3.getText());//ȡ�ý�Ǯ��
 		    }catch(NumberFormatException r)
 			{
 		    	xiaoxi s=new xiaoxi("������ת�˽��");
 		    	aa=false;
 			}
 		    if(aa)
 		    {
 		    try
 			{
 		         Denlu.out.writeUTF(SAVEMONEY);
 		    	Denlu.out.writeChar('\t');
 		    	Denlu.out.writeUTF(Denlu.userName);
 		    	Denlu.out.writeLong(money);
 		    	Denlu.out.writeChar('\t');
 		    	xiaoxi xx=new xiaoxi(Denlu.in.readUTF()+Denlu.in.readLong()+"ö���");
 		    }catch(Exception s){}
 		    }
 		  }
 	
 /////////////////////////����/////////////////////////////////////////////////////	
 	
 	if(btn==btn2)//����
 	{
 		zhuche.dispose();
 		Zhuyemian.zhuyemian.setVisible(true);
 	}
 	}
 	}
 }


