/*
 * Created on 2005-9-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author 09003112С��
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread 
{
	UserMgr UM=new UserMgr();
	MyFileMgr MFM=new MyFileMgr();
	public static final String str=new String(" ");
	public static final String LOAD="LOAD";                            //��¼
	public static final String REGISTER="REGISTER";                    //ע��
	public static final String ALLONLINE="ALLONLINE";                  //��ѯ���������û���
	public static final String ALLFILE="ALLFILE";                      //��ѯ�����ļ���
	public static final String FINDFILE="FINDFILE";                    //����ĳ���ļ�
	public static final String FINDOFONE="FINDOFONE";                  //����ĳ���˵������ļ�
	public static final String UPLOAD="UPLOAD";                        //�ϴ�
	public static final String DOWNLOAD="DOWNLOAD";                    //����
	public static final String REUSERLIST="REUSERLIST";                //ˢ���û��б�
	public static final String SAVEMONEY="SAVEMONEY";                      //��ֵ
	public static final String QUIT="QUIT";                             //�˳�
	public static final String CHANGEPASSWORD="CHANGEPASSWORD";      //�޸�����
	public static final String REFRESHUSERINFOR="REFRESHUSERINFOR";//ˢ�µ�ǰ�û���Ϣ
	
	     private Socket socket=null;
	     public ServerThread(Socket socket)
	     {
	     	super("ServerThread");
	     	this.socket=socket;
	     	}
	     
///////////////////////////////////run()////////////////////////////////////////////	     
	     
	     public void run()
	     {
	     	DataInputStream in=null;
	     	DataOutputStream out=null;
	     	try{
	     	in=new DataInputStream(socket.getInputStream());
	     	out=new DataOutputStream(socket.getOutputStream());
	     	
	     	while(true)
	     	{
	     		String order=in.readUTF();
	     		in.readChar();
	     		if(order.equals(LOAD))                           //��¼
	     			{
	     			String name=in.readUTF();
	     			in.readChar();
	     			long password=in.readLong();
	     			int onLine=0;
	     			onLine=UM.selectInfor_OL(name);
	     			if(onLine==1)
	     			{
	     				out.writeUTF("�û��ѵ�½�����Ժ��½");   //�û��ѵ�½�����Ժ��½
	     			    out.flush();
	     			    }

	     			else if(UM.checkUser(name,password)==0)//�����ݿ��в��ҿ���û��������֣�
	     				{
	     				out.writeUTF("�û���������,��ע��");   //�п�����Ȳ��ȣ�û�з�������ע��
	     			    out.flush();
	     			    }
	     			else if(UM.checkUser(name,password)==-1)
	     				{
	     				out.writeUTF("�������");
	     				out.flush();
	     				}
	     			else 
	     				{	
                            out.writeUTF("��¼�ɹ�");
	     				    out.flush();
	     				    UM.updateInfor_Load(name);
	     				   double startTime=System.currentTimeMillis(); //ȡ�õ�ǰʱ��
	     				   long a=(long)startTime/1000;
	     				   UM.updateInfor_StartT(a,name);
	     				}
	     			}
	     		
	     		
	     		else if(order.equals(REGISTER)) //ע��
	     		    {
	     			String uname=null;
	     			uname=in.readUTF();
	     			//System.out.println("sdsa");
	     			in.readChar();
	     			long password=in.readLong();
	     			in.readChar();
	     			if(UM.userExist(uname))
	     				{out.writeUTF("����,����������");}
	     			else
	     			    {
	     				    UM.insertInfor(uname,password);
	     				    out.writeUTF("��ϲ,ע��ɹ�!");
	     			    }
	     		    }
	     		
	     		
	     		else if(order.equals(ALLONLINE)) 
	     			{
	     		      int r=UM.getRow();
	     		      String []onLineUser=new String[r];
	     			  onLineUser=UM.showUserOnLine_Name(r);//��ѯ���������û���
                      synchronized(str)
	     			  {
	     			  out.writeInt(r);
	     			  out.writeChar('\t');
	     			  out.flush();
	     			  for(int i=0;i<r;i++)
	     			  {
	     			     if(onLineUser[i]!=null)
	     			         out.writeUTF(onLineUser[i]);
	     			     out.writeChar('\t');
	     			     out.flush();
	     			  }
	     			  }
	     			}
	     		
	     		
	     		else if(order.equals(ALLFILE))                      //��ѯ�����ļ���
	     			{
	     			int r=MFM.getRow();
   		            String []allFile=new String[r];
   			        allFile=MFM.showInfor_Name(r);
   			        synchronized(str)
				    {
				    out.writeInt(r);
				    out.writeChar('\t');
				    out.flush();
				    for(int i=0;i<r;i++)
				      {
				       if(allFile[i]!=null)
				         out.writeUTF(allFile[i]);
				       out.writeChar('\t');
				       out.flush();
				      }
				    }
                    }
	     		
	     		
	     		else if(order.equals(FINDFILE))                    //����ĳ���ļ�
	     		    {
	     			   String fileName=new String();
	     			   fileName=in.readUTF();
	     			   int r=MFM.getRow();
	     			   if(MFM.fileNameExsit(fileName,r))
	     			   	  out.writeUTF("�ļ��Ѵ���");
	     			   else
	     			   	  out.writeUTF("�ļ�δ����");
	     		    }
	     		else if(order.equals(FINDOFONE))                    //����ĳ���˵������ļ�
	     			{
	     			String s=in.readUTF();
	     			int r=MFM.getRow();
   		            String []fileOfOne=new String[r];
   			        fileOfOne=MFM.selectInfor_file_uploader(s,r);
   			        synchronized(str)
				    {
				    out.writeInt(r);
				    out.writeChar('\t');
				    out.flush();
				    for(int i=0;i<r;i++)
				      {
				       if(fileOfOne[i]!=null)
				       out.writeUTF(fileOfOne[i]);
				       out.writeChar('\t');
				       out.flush();
				      }
				    }
                    }
	     		
	     		else if(order.equals(UPLOAD))     //�ϴ��ļ�             
	     		    {
     			    String fn=in.readUTF();    //ȡ���ļ���
     			    String f="E:/share/"+fn;
     			    int row=MFM.getRow();
     			    
     			    in.readChar();
     			    if(!MFM.fileNameExsit(fn,row))
     			   {
     			    	out.writeUTF("������");
     			    	long length=0;
     			    length=in.readLong();
     			    String uploader=null;
     			    uploader=in.readUTF();
     			    try
				    {
 			         FileOutputStream fis=new FileOutputStream(f);
 			          //ֻ���ϴ��ڷ�����E����share�ļ�����
 			        for(int i=0;i<length;i++)
 				       fis.write(in.read());
 			         
 			         }catch(IOException e){}
     			     MFM.insertInfor(fn,uploader,0,length); 
     			     UM.updateInfor_M(uploader,10);
     			     out.writeUTF("�ļ�"+fn+"�ϴ��ɹ�");
     			   }
     			    else
     			    	out.writeUTF("���ϴ����ļ�"+fn+"�Ѵ��ڣ�������ѡ���ļ��ϴ���");
	     		   }
	     		
	     		
	     		else if(order.equals(DOWNLOAD))                  //����        
	     			{
	     			    int c;
	     				String fn=in.readUTF();//�ļ��� 
	     				in.readChar();
	     				String Dloader=in.readUTF();//����������
	     				
	     				String f="E:/share/"+fn;
	     				File file=new File(f);
	     				synchronized(fn)
						{
	     			     try
						   {
	     			         FileInputStream fis=new FileInputStream(f);
	     			         long length=file.length();
	     			         out.writeLong(length);
	     			         for(int i=0;i<length;i++)
	     				        out.write(fis.read());
	     			       }catch(IOException e){}
	     		         }
	     				  String Uloader=MFM.showInfor_Uploader(fn);
	     				  System.out.println(Uloader);
	     				  long addMoney=MFM.showInfor_Price(fn);
	     				  UM.updateInfor_M(Uloader,addMoney);
	     				  UM.updateInfor_M(Dloader,(0-addMoney));
	     				  MFM.updateInfor_TOD(fn);
	     				  long newTOD=MFM.showInfor_TOD(fn);
	     				  if(newTOD<50)
	     				  { 
	     				  	int a=(int) newTOD/10;
	     				   long newPrice=a*10;
	     				   MFM.updateInfor_Price(fn,newPrice);
	     				  }
	     				  else MFM.updateInfor_Price(fn,50);
	     			   }

	     		
	     		
	     		else if(order.equals(REUSERLIST))               // ˢ���û��б�
	     		{
	     			int r=UM.getRow();
	     		      String []onLineUser=new String[r];
	     			  onLineUser=UM.showUserOnLine_Name(r);//��ѯ���������û���
                  synchronized(str)
	     			  {
	     			  out.writeInt(r);
	     			  out.writeChar('\t');
	     			  out.flush();
	     			  for(int i=0;i<r;i++)
	     			  {
	     			     if(onLineUser[i]!=null)
	     			         out.writeUTF(onLineUser[i]);
	     			     out.writeChar('\t');
	     			     out.flush();
	     			  }
	     			  }
	     			 }
	     		
	     		
	     		
	     		else if(order.equals(QUIT))  //�˳�
	     		{
	     		     String userName=in.readUTF();
	     		     in.readChar();
	     		     UM.updateInfor_Quit(userName); //���ݿ��и��û�����״̬Ϊ0
	     		     double endTime=System.currentTimeMillis();//ȡ�õ�ǰʱ��
	     		     long b=(long)endTime/1000;
	     		     long a=(long)UM.selectInfor_StartT(userName);
	     		     long addMoney=b-a;
	     		     UM.updateInfor_M(userName,addMoney);
	     		     //������û�����ʱ��,��ת��Ϊ��Ǯ���뵽���ݿ���
	     		     in.close();
	     		     out.close();
	     		    socket.close();
	     		}
	     		
	     		
	     		
	     		else if(order.equals(CHANGEPASSWORD))//�޸�����
	     		{
	     			long newPassword=0;
	     			newPassword=in.readLong();
	     			String name=null;
	     			in.readChar();
	     			name=in.readUTF();
	     			//System.out.println(name);
	     			//System.out.println(newPassword);
	     			UM.updateInfor_PS(newPassword,name);
	     			//�����ݿ����޸ĸ��û���������
	     		}
	     		
	     		
	     		else if(order.equals(SAVEMONEY))    //��ֵ
	     		{
	     		    long money=0;
	     		    String name=null;
	     		    name=in.readUTF();
	     		    money=in.readLong();
	     		    money=money*10;
	     		    in.readChar();
	     		    UM.updateInfor_M(name,money);
	     		    out.writeUTF("���Ѿ��ɹ���ֵ");
	     		    out.writeLong(money);
					
	     		}
	     		
	     		
	     		
	     	else if(order.equals(REFRESHUSERINFOR))
	     	{
	     		try 
				{   long newMoney=0;
				    String name=null;
				    
				    name=in.readUTF();
				    newMoney=UM.selectInfor_M(name);
				    in.readChar();
				    out.writeLong(newMoney);
	     		}catch(IOException w){System.out.println("ˢ�µ�ǰ�û���Ϣ����");}
	     	 }
	     		}
	     	}catch(IOException e){}
	    }
	}
