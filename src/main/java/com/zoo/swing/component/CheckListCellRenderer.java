package com.zoo.swing.component;

import java.awt.Component;
import java.io.Serializable;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.zoo.base.Strs;

public class CheckListCellRenderer<T> extends JCheckBox implements ListCellRenderer<T>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3778079070206206947L;
	
	
	protected static Border noFocusBorder;
	
	private Border focusBorder=UIManager.getBorder("List.focusCellHighlightBorder");

	/**
	 * Constructs a default renderer object for an item in a list.
	 */
	public CheckListCellRenderer() {
		super();
		if (noFocusBorder == null) {
			noFocusBorder = new EmptyBorder(1, 1, 1, 1);
		}
		setOpaque(true);
		setBorder(noFocusBorder);
	}

	/**
	 * 生成下拉列表中的组件
	 */
	@SuppressWarnings("rawtypes")
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,boolean cellHasFocus) {
		/**
		 * 若是CheckValue对象就返回当前JCheckBox实例,否则构造并返回JLabel实例
		 */
		Component listComponent=null;
		if (value instanceof CheckValue) {
			listComponent=this;
			CheckValue ckValue = (CheckValue) value;
			this.setText(Strs.toStr(ckValue.getValue()));
			this.setSelected(ckValue.getChecked());
			this.setBorder(cellHasFocus ? focusBorder : noFocusBorder);
			this.setRolloverEnabled(true);
			this.setIcon(ckValue.getIcon());
			this.setRolloverIcon(ckValue.getRolloverIcon());
			this.setSelectedIcon(ckValue.getSelectedIcon());
		}else if (value instanceof JCheckBox) {
			JCheckBox checkBox=(JCheckBox) value;
			checkBox.setSelected(checkBox.isSelected());
			listComponent=checkBox;
		}else {
			JLabel label=new JLabel(Strs.toStr(value));
			label.setBorder(cellHasFocus ? focusBorder : noFocusBorder);
			listComponent=label;
		}
		listComponent.setComponentOrientation(list.getComponentOrientation());
		listComponent.setFont(list.getFont());
		listComponent.setEnabled(list.isEnabled());
		if (isSelected) {
			listComponent.setBackground(list.getSelectionBackground());
			listComponent.setForeground(list.getSelectionForeground());
		} else {
			listComponent.setBackground(list.getBackground());
			listComponent.setForeground(list.getForeground());
		}
		return listComponent;
	}
}
