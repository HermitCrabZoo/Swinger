package com.zoo.swing.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public final class Style {
	
	private Style() {}
	
	/**
	 * 设置窗体风格为windows系统默认的风格
	 * 设置RadioButton、Button、Label、TextArea、TextField、ToolTip组件为优雅的风格。
	 * 若想要让全局的组件风格生效，那么此方法必须在构造组件之前调用。
	 */
	public static void defaultStyle() {
		// 设置为系统默认主题
		// 必须在所有组件初始化之前设置才对组件生效
		try {
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
		} catch (Exception e) {
			System.out.println("设置主题异常:");
			e.printStackTrace();
		}
		
		Font yaHeiLight=new Font("微软雅黑Light", Font.PLAIN, 14);
		Color selectionBackground= new Color(49, 141, 253);
		LineBorder lineBorder=new LineBorder(Color.LIGHT_GRAY);

		// 设置组件的全局样式
		//单选框
		UIManager.put("RadioButton.font", new Font("微软雅黑Light", Font.PLAIN, 14));
		UIManager.put("RadioButton.foreground", Color.DARK_GRAY);
		UIManager.put("RadioButton.background", new Color(238, 238, 238));
		
		//多选框
		UIManager.put("CheckBox.background", new Color(238, 238, 238));
		UIManager.put("CheckBox.foreground", Color.DARK_GRAY);
		UIManager.put("CheckBox.font", yaHeiLight);
		UIManager.put("CheckBox.focus", new Color(238, 238, 238, 0));

		//按钮
		UIManager.put("Button.border", new Color(238, 238, 238, 0));
		UIManager.put("Button.focus", new Color(238, 238, 238, 0));

		//标签
		UIManager.put("Label.font", yaHeiLight);
		UIManager.put("Label.foreground", Color.DARK_GRAY);

		//滚动面板
		UIManager.put("ScrollPane.border", lineBorder);
		
		//文本框
		UIManager.put("TextField.border", lineBorder);
		UIManager.put("TextField.foreground", Color.DARK_GRAY);
		UIManager.put("TextField.selectionBackground", selectionBackground);
		UIManager.put("TextField.selectionForeground", Color.WHITE);
		UIManager.put("TextField.font", yaHeiLight);

		//文本域
		UIManager.put("TextArea.border", lineBorder);
		UIManager.put("TextArea.foreground", Color.DARK_GRAY);
		UIManager.put("TextArea.selectionBackground", selectionBackground);
		UIManager.put("TextArea.selectionForeground", Color.WHITE);
		UIManager.put("TextArea.font", yaHeiLight);

		//提示框
		UIManager.put("ToolTip.background", selectionBackground);
		UIManager.put("ToolTip.foreground", Color.WHITE);
		UIManager.put("ToolTip.border", selectionBackground);
		
		//按钮开关
		UIManager.put("ToggleButton.font",yaHeiLight);
		UIManager.put("ToggleButton.foreground", Color.DARK_GRAY);
		UIManager.put("ToggleButton.background",new Color(238, 238, 238));
		
		
	}
	
}
