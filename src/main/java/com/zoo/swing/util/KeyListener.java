package com.zoo.swing.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 文本框非法字符输入监听器类
 * @author Administrator
 *
 */
public final class KeyListener{
	
	private KeyListener() {}
	
	/**
	 * 密码框监听器，只能输入数字和字母，无字数限制
	 */
	public static KeyAdapter passKeyAdapter=new KeyAdapter()
	{
		public void keyTyped(KeyEvent e)
		{
			char key=e.getKeyChar();
			if( (key<'0') || ((key>'9')&&(key<'A')) || ((key>'Z')&&(key<'a')) || (key>'z'))
			{
				if (key != '\n') // 回车字符  
	                e.setKeyChar('\0');
			}
		}	
	};
	
	
	/**
	 * 文本框只能输入数字，无字数限制
	 */
	public static KeyAdapter numberKeyAdapter=new KeyAdapter()
	{
		public void keyTyped(KeyEvent e)
		{
			char key=e.getKeyChar();
			if((key<'0')||(key>'9'))
			{
				if (key != '\n') // 回车字符  
	                e.setKeyChar('\0');
			}
		}
	};
	
	
	/**
	 * 年龄文本框将设置为只允许输入两个字符，且只能输入大于零的数字
	 * @param ageField
	 */
	public static void setAgeKeyAdapter(final JTextField ageField)
	{
		if(ageField!=null)
		{
			KeyAdapter ageKeyAdapter=new KeyAdapter()
			{
				public void keyTyped(KeyEvent e)
				{
					char key=e.getKeyChar(); 
					String str=ageField.getText();
					if(str.length()>=2)
					{
						e.setKeyChar('\0');
					}
					if(str.length()<=0&&key=='0')
					{
						e.setKeyChar('\0');
					}
					if((key<'0')||(key>'9'))
					{
						if (key != '\n') // 回车字符  
			                e.setKeyChar('\0');
					}
				}
			};
			ageField.addKeyListener(ageKeyAdapter);
		}
	}
	
	
	/**
	 * 文本框将设置为只允许输入limit个字符，且只能输入数字
	 * @param ageField
	 * @param limit
	 */
	public static void setNumberKeyAdapter(final JTextField textField,final int limit)
	{
		if(limit>0&&textField!=null)
		{
			KeyAdapter integerKeyAdapter=new KeyAdapter()
			{
				public void keyTyped(KeyEvent e)
				{
					char key=e.getKeyChar(); 
					String str=textField.getText();
					if(str.length()>=limit)
					{
						e.setKeyChar('\0');
					}
					if((key<'0')||(key>'9'))
					{
						if (key != '\n') // 回车字符  
			                e.setKeyChar('\0');
					}
				}
			};
			textField.addKeyListener(integerKeyAdapter);
		}
	}
	
	
	/**
	 * 限制文本框只能输入整数，无字数限制。
	 * @param textField
	 */
	public static void setIntegerKeyAdapter(final JTextField textField)
	{
		if(textField!=null)
		{
			KeyAdapter integerKeyAdapter=new KeyAdapter()
			{
				public void keyTyped(KeyEvent e)
				{
					char key=e.getKeyChar(); 
					String str=textField.getText();
					if((str.equals("0")&&key>='0'&&key<='9'))
					{
						e.setKeyChar('\0');
					}
					if((key<'0')||(key>'9'))
					{
						if (key != '\n') // 回车字符  
			                e.setKeyChar('\0');
					}
				}
			};
			textField.addKeyListener(integerKeyAdapter);
		}
	}
	
	
	/**
	 * 限制文本框只能输入limit位数的整数。
	 * @param textField
	 */
	public static void setIntegerKeyAdapter(final JTextField textField,final int limit)
	{
		if(textField!=null)
		{
			KeyAdapter integerKeyAdapter=new KeyAdapter()
			{
				public void keyTyped(KeyEvent e)
				{
					char key=e.getKeyChar(); 
					String str=textField.getText();
					if(str.length()>=limit)
					{
						e.setKeyChar('\0');
					}
					if((str.equals("0")&&key>='0'&&key<='9'))
					{
						e.setKeyChar('\0');
					}
					if((key<'0')||(key>'9'))
					{
						if (key != '\n') // 回车字符  
			                e.setKeyChar('\0');
					}
				}
			};
			textField.addKeyListener(integerKeyAdapter);
		}
	}
	
	
	/**
	 * 限制文本框只能输入整数或小数。
	 */
	public static void setDecimalKeyAdapter(final JTextField textField)
	{
		if(textField!=null)
		{
			KeyAdapter ageKeyAdapter=new KeyAdapter()
			{
				public void keyTyped(KeyEvent e)
				{
					char key=e.getKeyChar(); 
					String str=textField.getText();
					if(str.equals("0")&&key>='0'&&key<='9')
					{
						e.setKeyChar('\0');
					}
					if((key<'0')||(key>'9'))
					{
						if(key=='.')
						{
							if(str.length()<=0||str.indexOf(".")!=-1)
							{
								e.setKeyChar('\0');
							}
						}else
						{
							if (key != '\n') // 回车字符  
				                e.setKeyChar('\0');
						}
					}
				}
			};
			textField.addKeyListener(ageKeyAdapter);
		}
	}
	
	
	/**
	 * 限制密码框只能输入limit个字符，并且只能输入数字和字母。
	 * @param passField
	 * @param limit
	 */
	public static void setPassKeyAdapter(final JPasswordField passField,final int limit)
	{
		if(limit>0&&passField!=null)
		{
			KeyAdapter passKeyAdapter=new KeyAdapter()
			{
				public void keyTyped(KeyEvent e)
				{
					char key=e.getKeyChar();
					char[] ch=passField.getPassword();
					String str=String.valueOf(ch);
					if(str.length()>=limit)
					{
						e.setKeyChar('\0');
					}
					if( (key<'0') || ((key>'9')&&(key<'A')) || ((key>'Z')&&(key<'a')) || (key>'z'))
					{
						if (key != '\n') // 回车字符  
			                e.setKeyChar('\0');
					}
				}	
			};
			passField.addKeyListener(passKeyAdapter);
		}
	}
	
	
	/**
	 * 限制文本框只能输入limit个字符，无字符类型限制。
	 * @param textField
	 * @param limit
	 */
	public static void setLengthKeyAdapter(final JTextField textField,final int limit)
	{
		if(limit>0&&textField!=null)
		{
			KeyAdapter ageKeyAdapter=new KeyAdapter()
			{
				public void keyTyped(KeyEvent e)
				{
					String str=textField.getText();
					if(str.length()>=limit)
					{
						e.setKeyChar('\0');
					}
				}
			};
			textField.addKeyListener(ageKeyAdapter);
		}
	}
	
	
}
