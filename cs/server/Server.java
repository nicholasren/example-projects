package server;
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
import java.io.*;
import java.net.*;

public class Server {
public static final String LOAD="LOAD";                            //��¼
public static final String REGISTER="REGISTER";                    //ע��
public static final String ALLONLINE="ALLONLINE";                  //��ѯ���������û���
public static final String ALLFILE="ALLFILE";                      //��ѯ�����ļ���
public static final String FINDFILE="FINDFILE";                    //����ĳ���ļ�
public static final String FINDOFONE="FINDOFONE";                  //����ĳ���˵������ļ�
public static final String UPLOAD="UPLOAD";                        //�ϴ�
public static final String DOWNLOAD="DOWNLOAD";                    //����
public static final String TIME="TIME";                            //��ѯʱ�䣬��ת����
public static final String REFLESH="REFLESH";                      //ˢ��
	public static void main(String[] args)throws IOException 
	{
		ServerSocket serverSocket=null;
		boolean listening=true;
		try
		{
			serverSocket=new ServerSocket(3220);
			System.out.println("�����������ɹ�!���ڼ�������");
		}catch (IOException e)
		{
				System.out.println("can't listen on port 3220");
				System.exit(1);
		}
		
		while(listening)
		{
			new ServerThread(serverSocket.accept()).start();
		}
		serverSocket.close();
	}
}



