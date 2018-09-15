package com.zoo.swing.component;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Window;
import java.net.URL;
import java.util.Objects;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import com.zoo.base.Resource;
import com.zoo.base.Strs;
import com.zoo.mix.Pather;
import com.zoo.swing.util.WindowMover;

public final class Hints {
	
	private Hints() {}
	
	
	/**
	 * 显示提示信息对话框
	 * 
	 * @param win
	 * @param textMessage
	 */
	public static void showMessage(Component c, String textMessage) {
		MessageHint messageHint = new MessageHint(c, textMessage);
		WindowMover.dragable(messageHint);
		messageHint.setVisible(true);
	}

	/**
	 * 显示确认信息对话框
	 * 
	 * @param win
	 * @param textMessage
	 */
	public static boolean showConfirm(Component c, String textMessage) {
		ConfirmHint confirmHint = new ConfirmHint(c, textMessage);
		WindowMover.dragable(confirmHint);
		confirmHint.setVisible(true);
		return confirmHint.flag();
	}

	/**
	 * 显示确认信息对话框
	 * 
	 * @param win
	 * @param textMessage
	 */
	public static String showInput(Component c, String textMessage) {
		return showInput(c, textMessage, null);
	}
	
	
	/**
	 * 显示确认信息对话框
	 * 
	 * @param win
	 * @param textMessage
	 */
	public static String showInput(Component c, String textMessage,Consumer<JTextField> free) {
		InputHint inputHint = new InputHint(c, textMessage, free);
		WindowMover.dragable(inputHint);
		inputHint.setVisible(true);
		return inputHint.inputMessage();
	}
	
	/**
	 * 显示信息闪现对话框(此对话框显示2秒后自动消失)
	 * @param win
	 * @param textMessage
	 */
	public static void showWink(Component c, String textMessage) {
		showWink(c, textMessage, 2000);
	}
	
	/**
     * 显示信息闪现对话框(此对话框显示1秒后自动消失)
     * @param win
     * @param textMessage
     */
	public static void showWink(Component c, String textMessage, long millis) {
		new Thread(() -> {
			WinkHint winkHint = new WinkHint(c, textMessage);
			WindowMover.dragable(winkHint);
			winkHint.setVisible(true);
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			winkHint.dispose();
		}).start();
	}
    
	
	/**
	 * 处理过程消息框
	 * @param c
	 * @param textMessage
	 * @return
	 */
	public static ProcessHint showProcess(Container c, String textMessage) {
		return showProcess(c, textMessage, 0);
	}
	
	/**
	 * 处理过程消息框
	 * @param c
	 * @param textMessage
	 * @param ico
	 * @return
	 */
	public static ProcessHint showProcess(Container c, String textMessage,int ico) {
		return showProcess(c, textMessage, ico, 216);
	}
	
	
	/**
	 * 处理过程消息框
	 * @param c
	 * @param textMessage
	 * @param ico
	 * @param alpha
	 * @return
	 */
	public static ProcessHint showProcess(Container c, String textMessage,int ico,int alpha) {
		ProcessHint processHint=new ProcessHint(c, textMessage,ico,alpha);
		processHint.setVisible(true);
		return processHint;
	}
	
}


abstract class Hint extends JDialog{
	private static final long serialVersionUID = 2294139359200676621L;
	
	protected JPanel panel=null;
	protected JLabel textLabel=null;
	protected Window win=null;
	protected Component comp;
	protected String textMessage =null;
	
	
	public Hint(Component c,String textMessage) {
		super(c!=null?SwingUtilities.getWindowAncestor(c):null);
		this.comp=c;
		this.win=c!=null?SwingUtilities.getWindowAncestor(c):null;
		this.textMessage = textMessage;
		init();
	}
	
	private void init() {
    	setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
    	panel=new JPanel();
    	panel.setLayout(null);
    	panel.setBackground(new Color(255,251,240,0));
    	setContentPane(panel);
    	setBackground(new Color(255,251,240,0));
        
        textLabel = new JLabel("<html>" + textMessage + "</html>", JLabel.LEFT);
        textLabel.setBackground(new Color(255, 251, 240,0));
        panel.add(textLabel);
        
        doAnyMore();
	}
	
	/**
	 * 在生成背景前先生成其他组件,以免被背景阻挡了
	 */
	abstract void doAnyMore();
	
	ImageIcon icon(String filename){
		ImageIcon icon=null;
    	try{
        	URL url=Hints.class.getClassLoader().getResource("res/"+Pather.toPath(filename));
        	icon = new ImageIcon(url);// 创建图标
    	}catch(Exception e){}
    	return icon;
	}
}

abstract class IntroHint extends Hint{
	private static final long serialVersionUID = 1L;
	
	protected JButton exitButton=null;
	protected JButton confirmButton=null;
	protected JLabel backLabel=null;
	
	public IntroHint(Component c, String textMessage) {
		super(c, textMessage);
		backLabel=new JLabel(icon("hint.png"));
        backLabel.setBounds(0, 0, 280, 130);
        panel.add(backLabel,Integer.valueOf(Integer.MIN_VALUE));
	}
	
	/**
	 * 在生成背景前先生成其他组件,以免被背景阻挡了
	 */
	void doAnyMore() {
		setSize(280,130);
		setLocationRelativeTo(win);
		setModal(true);//该句会导致调用setVisible方法阻塞
		textLabel.setFont(new Font("微软雅黑",Font.PLAIN,13));
		textLabel.setToolTipText(textMessage);
		textLabel.setBackground(new Color(255, 251, 240,0));
    	
        exitButton=new JButton(icon("hint_exit.png"));
        exitButton.setBorder(null);
        exitButton.setBounds(243, 2, 35, 20);
        exitButton.setRolloverIcon(icon("hint_exit_up.png"));
        exitButton.setPressedIcon(icon("hint_exit_press.png"));
        exitButton.addActionListener(e->IntroHint.this.dispose());
        panel.add(exitButton);
        
        
        confirmButton=new JButton(icon("hint_confirm.png"));
        confirmButton.setBorder(null);
        confirmButton.setRolloverIcon(icon("hint_confirm_up.png"));
        confirmButton.setPressedIcon(icon("hint_confirm_press.png"));
        panel.add(confirmButton);
	}
	
	ImageIcon icon(String filename){
    	return Resource.getIcon("res/"+Pather.toPath(filename));
	}
}


/**
 * 消息提示对话框
 * @author ZOO
 *
 */
class MessageHint extends IntroHint{
	private static final long serialVersionUID = -1424634733306602364L;

	public MessageHint(Component c, String textMessage) {
		super(c, textMessage);
	}
	
	@Override
	void doAnyMore() {
		super.doAnyMore();
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setBounds(10, 40, 260, 50);
        confirmButton.setBounds(110, 95, 60, 25);
        confirmButton.addActionListener(e->MessageHint.this.dispose());
		getRootPane().setDefaultButton(confirmButton);
	}
	
}


/**
 * 消息确认对话框
 * @author ZOO
 *
 */
class ConfirmHint extends IntroHint{
	private static final long serialVersionUID = -8311256227162409927L;

	private boolean flag=false;
	private JButton cancelButton=null;
	
	public ConfirmHint(Component c, String textMessage) {
		super(c, textMessage);
	}
	
	@Override
	void doAnyMore() {
		super.doAnyMore();
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setBounds(10, 40, 260, 50);
        confirmButton.setBounds(75, 95, 60, 25);
        confirmButton.addActionListener(e->{
			flag=true;
			ConfirmHint.this.dispose();
    	});
        
        cancelButton=new JButton(icon("hint_cancel.png"));
        cancelButton.setBorder(null);
        cancelButton.setBounds(145, 95, 60, 25);
        cancelButton.setRolloverIcon(icon("hint_cancel_up.png"));
        cancelButton.setPressedIcon(icon("hint_cancel_press.png"));
        cancelButton.addActionListener(e->{
			flag=false;
			ConfirmHint.this.dispose();
    	});
        panel.add(cancelButton);
	}
	
	/**
	 * 是确认还是取消的boolean值,true:confirmed,false:cancelled.
	 * @return
	 */
	public boolean flag() {
		return flag;
	}
	
}


/**
 * 文本输入对话框
 * @author ZOO
 *
 */
class InputHint extends IntroHint{
	private static final long serialVersionUID = -3756391099564983234L;
	
	private String inputMessage=Strs.empty();
	private JTextField inputField=null;
	private JButton cancelButton=null;
	
	public InputHint(Component c, String textMessage,Consumer<JTextField> free) {
		super(c, textMessage);
		if (Objects.nonNull(free)) {
			free.accept(inputField);
		}
	}
	
	
	@Override
	void doAnyMore() {
		super.doAnyMore();
		textLabel.setBounds(20, 35, 260, 23);
		textLabel.setHorizontalAlignment(JLabel.LEFT);
		
		inputField=new JTextField();
        inputField.setBorder(new LineBorder(Color.LIGHT_GRAY));
        inputField.setFont(new Font("微软雅黑",Font.PLAIN,12));
        inputField.setBackground(Color.WHITE);
        inputField.setForeground(Color.DARK_GRAY);
        inputField.setBounds(20, 65, 240, 23);
		inputField.setColumns(10);
		panel.add(inputField);
        
		final JTextField connot=inputField;
		
        confirmButton.setBounds(75, 95, 60, 25);
        confirmButton.addActionListener(e->{
			inputMessage=connot.getText();
			InputHint.this.dispose();
    	});
        
        cancelButton=new JButton(icon("hint_cancel.png"));
        cancelButton.setBorder(null);
        cancelButton.setBounds(145, 95, 60, 25);
        cancelButton.setRolloverIcon(icon("hint_cancel_up.png"));
        cancelButton.setPressedIcon(icon("hint_cancel_press.png"));
        cancelButton.addActionListener(e->{
        	System.out.println(connot);
			inputMessage=Strs.empty();
			InputHint.this.dispose();
    	});
        panel.add(cancelButton);
		
	}
	
	
	public String inputMessage() {
		return inputMessage;
	}
	
}

/**
 * 自动消失提示框
 * @author ZOO
 *
 */
class WinkHint extends Hint{
	private static final long serialVersionUID = 7441651348675173801L;
	protected JLabel backLabel=null;
	
	public WinkHint(Component c, String textMessage) {
		super(c, textMessage);
		backLabel=new JLabel(icon("hint_wink.png"));
        backLabel.setBounds(0, 0, 180, 80);
        panel.add(backLabel,Integer.valueOf(Integer.MIN_VALUE));
	}
	
	@Override
	void doAnyMore() {
		setSize(180,80);
		setLocationRelativeTo(win);
		textLabel.setFont(new Font("微软雅黑",Font.PLAIN,15));
    	textLabel.setBounds(4, 2, 176, 76);
		textLabel.setHorizontalAlignment(JLabel.CENTER);
	}
}
