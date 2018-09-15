package com.zoo.swing.component;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.zoo.base.Arrs;
import com.zoo.base.Resource;
import com.zoo.mix.Maths;
import com.zoo.se.Colors;

public class ProcessHint extends JPanel {
	
	private static final long serialVersionUID = 7441651348675173801L;
	
	private static final int[] icons= {0,1,2,3,4,5,6,7,8,9};
	private int ico=0;
	private int alpha=216;
	
	protected JTextField textField=null;
	protected Window win=null;
	protected Container container;
	protected String textMessage =null;
	
	public ProcessHint(Container c, String textMessage) {
		this(c, textMessage, 0);
	}
	
	/**
	 * @param c
	 * @param textMessage
	 * @param ico range in[0,1,2,3,4,5,6,7,8,9]
	 */
	public ProcessHint(Container c, String textMessage,int ico) {
		this(c, textMessage, ico, 216);
	}
	
	
	/**
	 * @param c
	 * @param textMessage
	 * @param ico range in[0,1,2,3,4,5,6,7,8,9]
	 * @param alpha range in[0,255]
	 */
	public ProcessHint(Container c, String textMessage,int ico,int alpha) {
		this.container=c;
		this.win=c!=null?SwingUtilities.getWindowAncestor(c):null;
		this.textMessage = textMessage;
		this.ico=Arrs.contains(ico, icons)?ico:0;
		this.alpha=Maths.limit(alpha, 255, 0);
		doComp();
	}
	
	private void doComp() {
		ImageIcon icon=Resource.getIcon("res",ico+".gif");
		JLabel iconLabel=new JLabel(icon);
		if (icon!=null) {
			iconLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
		}
		int w=Math.max(iconLabel.getWidth(), 180),h=Math.max(iconLabel.getHeight(), 80);
		if (container!=null) {
			w=Math.max(w,container.getWidth());
			h=Math.max(h,container.getHeight());
		}
		
		setLayout(null);
    	setBounds(0, 0,w,h);
    	setBackground(new Color(255, 255, 255, alpha));
    	
    	
    	iconLabel.setLocation((w-iconLabel.getWidth())/2, (h-iconLabel.getHeight()-40)/2);
    	add(iconLabel,Integer.valueOf(Integer.MAX_VALUE));
		
		textField = new JTextField(textMessage);
		textField.setToolTipText(textMessage);
		textField.setFont(new Font("微软雅黑",Font.PLAIN,15));
		textField.setBounds(5, iconLabel.getY()+iconLabel.getHeight()+5, w-10, 35);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBorder(null);
		textField.setBackground(Colors.wTransparent);
		add(textField);
		
		if (container!=null) {
			container.add(this,Integer.valueOf(Integer.MAX_VALUE));
			container.repaint();
		}
		
		repaint();
	}
	
	/**
	 * 更改显示的消息
	 * @param text
	 */
	public void putText(String text) {
		textField.setText(text);
		textField.setToolTipText(text);
		repaint();
	}
	
	/**
	 * 设置文本颜色
	 * @param color
	 */
	public void setTextColor(Color color) {
		textField.setForeground(color);
	}
	
	/**
	 * 关闭弹框
	 */
	public void close() {
		if (this.container!=null) {
			this.container.remove(this);
			this.container.repaint();
		}
	}
}
