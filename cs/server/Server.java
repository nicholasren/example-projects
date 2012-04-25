package server;
/*
 * Created on 2005-9-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author 09003112小组
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.*;
import java.net.*;

public class Server {
public static final String LOAD="LOAD";                            //登录
public static final String REGISTER="REGISTER";                    //注册
public static final String ALLONLINE="ALLONLINE";                  //查询所有在线用户名
public static final String ALLFILE="ALLFILE";                      //查询所有文件名
public static final String FINDFILE="FINDFILE";                    //查找某个文件
public static final String FINDOFONE="FINDOFONE";                  //查找某个人的所有文件
public static final String UPLOAD="UPLOAD";                        //上传
public static final String DOWNLOAD="DOWNLOAD";                    //下载
public static final String TIME="TIME";                            //查询时间，供转帐用
public static final String REFLESH="REFLESH";                      //刷新
	public static void main(String[] args)throws IOException 
	{
		ServerSocket serverSocket=null;
		boolean listening=true;
		try
		{
			serverSocket=new ServerSocket(3220);
			System.out.println("服务器启动成功!正在监听……");
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



