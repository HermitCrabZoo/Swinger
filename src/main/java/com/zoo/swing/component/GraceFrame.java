package com.zoo.swing.component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 定义好一部分属性的JFrame子类
 * @author ZOO
 *
 */
public abstract class GraceFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6453818963047508169L;
	
	
	/**
	 * 主面板
	 */
	protected JPanel contentPanel= new JPanel();
	
	public  void initialize(int x, int y, int width, int height) {
		setBounds(x, y, width, height);// 窗体位置、大小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置默认的退出按钮操作为退出
		setUndecorated(true);// 去掉窗口修饰
		setLocationRelativeTo(null);// 相对位置为null代表屏幕居中
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
	}
}
