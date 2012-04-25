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
 * @author 09003112小组
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class xiaoxi extends Frame{
	
Frame xiao=new Frame ("用户消息");
 public Label lab=new Label();

public xiaoxi(String s){
	xiao.setBounds(350,200,400,100);
	xiao.setLayout(null);
	xiao.setBackground(Color.WHITE);
    lab.setText(s);
    lab.setBounds(20,40,360,20);
    xiao.add(lab);
    xiao.addWindowListener
	(new WindowAdapter()
		{public void windowClosing
	(WindowEvent e)
		{xiao.dispose();}});
    xiao.setVisible(true);
}
public xiaoxi(String s,int x,int y){
	xiao.setBounds(x,y,400,100);
	xiao.setLayout(null);
	xiao.setBackground(Color.WHITE);
    lab.setText(s);
    lab.setBounds(20,40,360,20);
    xiao.add(lab);
    xiao.addWindowListener
	(new WindowAdapter()
		{public void windowClosing
	(WindowEvent e)
		{xiao.dispose();}});
    xiao.setVisible(true);
}
}
