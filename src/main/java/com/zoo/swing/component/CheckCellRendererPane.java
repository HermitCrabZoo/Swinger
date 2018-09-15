package com.zoo.swing.component;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.CellRendererPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.zoo.base.Arrs;

public class CheckCellRendererPane extends CellRendererPane {
	private static final long serialVersionUID = 6858977146371240718L;

	
	// 渲染非下拉部分内容
	@SuppressWarnings("rawtypes")
	@Override
	public void paintComponent(Graphics g, Component c, Container p, int x, int y, int w, int h,boolean shouldValidate) {
		if (p instanceof MultipleComboBox) {
			MultipleComboBox<?> mcb=(MultipleComboBox) p;
			String text=Arrs.join(",", mcb.getAllSelectedAndCheckeds());
			c=new JLabel(text);//在显示框(非下拉面板)里用JLabel来展示
		}else if(p instanceof JComboBox){
			if (c instanceof JCheckBox) {
				JCheckBox checkBox=(JCheckBox) c;
				String text=checkBox.getText();
				c=new JLabel(text);//在显示框(非下拉面板)里用JLabel来展示
			}
		}
		super.paintComponent(g, c, p, x, y, w, h, shouldValidate);
	}
}
