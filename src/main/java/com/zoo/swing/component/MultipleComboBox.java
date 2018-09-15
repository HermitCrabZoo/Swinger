package com.zoo.swing.component;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

import com.zoo.base.Strs;

/**
 * 推荐使用{@link CheckValue}作为该容器的元素.
 * @author ZOO
 *
 * @param <T>
 */
public class MultipleComboBox<T> extends JComboBox<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6827920220094575548L;

	private ActionListener beforeCheckedActionListener;
	private ActionListener afterCheckedActionListener;
	
	public MultipleComboBox() {
		setUI(new WindowsCheckComboBoxUI());
		setRenderer(new CheckListCellRenderer<T>());
		this.addActionListener(e->{
			//before checked fire the afterCheckedActionListener
			if (MultipleComboBox.this.beforeCheckedActionListener!=null) {
				beforeCheckedActionListener.actionPerformed(e);
			}
			itemSelected();
			//after checked fire the afterCheckedActionListener
			if (MultipleComboBox.this.afterCheckedActionListener!=null) {
				afterCheckedActionListener.actionPerformed(e);
			}
		});
	}

	private void itemSelected() {
		Object selected=getSelectedItem();
		if (selected instanceof CheckValue) {
			CheckValue checkValue=(CheckValue) selected;
			checkValue.setChecked(!checkValue.getChecked());//改变状态
			if (checkValue.all()) {
				selectedAllItem(checkValue.getChecked(),getSelectedIndex());
			} else {
				setSelectedIndex(getSelectedIndex());
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					/* 选中后依然保持当前弹出状态 */
					showPopup();
				}
			});
		}else if (selected instanceof JCheckBox) {
			JCheckBox checkBox=(JCheckBox) selected;
			checkBox.setSelected(!checkBox.isSelected());
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					/* 选中后依然保持当前弹出状态 */
					showPopup();
				}
			});
		}
	}

	private void selectedAllItem(boolean checked,int selectedIndex) {
		for (int i = 0; i < getItemCount(); i++) {
			T t=getItemAt(i);
			if (t instanceof CheckValue) {
				CheckValue checkValue=(CheckValue) t;
				checkValue.setChecked(checked);
			}else if (t instanceof JCheckBox) {
				JCheckBox checkBox=(JCheckBox) t;
				checkBox.setSelected(checked);
			}
		}
		setSelectedIndex(selectedIndex);
	}

	/**
	 * 获取选中的对象(不包含{@link Checker#ALL}和{@link Checker#TURN})类型的
	 * @return
	 */
	public List<CheckValue> getCheckeds() {
		List<CheckValue> checks=new ArrayList<>();
		for (int i = 0,len=getItemCount(); i < len; i++) {
			T t=getItemAt(i);
			if (t instanceof CheckValue) {
				CheckValue checkValue=(CheckValue) t;
				if (checkValue.getChecked() && checkValue.self()) {
					checks.add(checkValue);
				}
			}
		}
		return checks;
	}
	
	/**
	 * 获取全部选中的CheckBox(状态为{@link JCheckBox#isSelected()}==true)
	 * @return
	 */
	public List<JCheckBox> getCheckedBoxs(){
		List<JCheckBox> checkBoxs=new ArrayList<>();
		for (int i = 0,len=getItemCount(); i < len; i++) {
			T t=getItemAt(i);
			if (t instanceof JCheckBox) {
				JCheckBox checkBox=(JCheckBox) t;
				if (checkBox.isSelected()) {
					checkBoxs.add(checkBox);
				}
			}
		}
		return checkBoxs;
	}
	
	
	/**
	 * 获取全部选中的文本
	 * @return
	 */
	public List<String> getAllSelectedAndCheckeds(){
		List<String> checks=new ArrayList<>();
		int s=getSelectedIndex();
		for (int i = 0,len=getItemCount(); i < len; i++) {
			T t=getItemAt(i);
			if (t instanceof CheckValue) {
				CheckValue checkValue=(CheckValue) t;
				if (checkValue.getChecked() && checkValue.self()) {
					checks.add(checkValue.getValue());
				}
			}else if (t instanceof JCheckBox) {
				JCheckBox checkBox=(JCheckBox) t;
				if (checkBox.isSelected()) {
					checks.add(checkBox.getText());
				}
			}else if (i==s) {
				checks.add(Strs.toStr(getSelectedItem()));
			}
		}
		return checks;
	}
	
	public MultipleComboBox<T> setBeforeCheckedActionListener(ActionListener l){
		this.beforeCheckedActionListener=l;
		return this;
	}
	
	public ActionListener getBeforeCheckedActionListener(){
		return this.beforeCheckedActionListener;
	}
	
	public MultipleComboBox<T> setAfterCheckedActionListener(ActionListener l){
		this.afterCheckedActionListener=l;
		return this;
	}
	
	public ActionListener getAfterCheckedActionListener(){
		return this.afterCheckedActionListener;
	}
}
