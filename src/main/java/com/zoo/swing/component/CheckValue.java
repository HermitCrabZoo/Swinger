package com.zoo.swing.component;

import javax.swing.Icon;

public class CheckValue {

	private boolean checked;
	
	private String value;

	private Checker type = Checker.SELF;
	
	private Icon icon;
	
	private Icon rolloverIcon;
	
	private Icon selectedIcon;

	public CheckValue() {}

	/**
	 * @param value 值
	 */
	public CheckValue(String value) {
		this.value = value;
	}
	
	
	/**
	 * @param value 值
	 * @param checked 是否选中
	 */
	public CheckValue(String value, boolean checked) {
		this.checked = checked;
		this.value = value;
	}

	/**
	 * @param value 值
	 * @param checked 是否选中
	 * @param type 对象类型
	 */
	public CheckValue(String value, boolean checked, Checker type) {
		this.checked = checked;
		this.value = value;
		setType(type);
	}
	
	
	/**
	 * @param value 值
	 * @param checked 是否选中
	 * @param type 对象类型
	 * @param icon checkbox的图标
	 * @param rolloverIcon checkbox鼠标移上去的图标
	 * @param selectedIcon checkbox选中的图标
	 */
	public CheckValue(String value, boolean checked, Checker type, Icon icon, Icon rolloverIcon, Icon selectedIcon) {
		this.value = value;
		this.checked = checked;
		setType(type);
		this.icon = icon;
		this.rolloverIcon = rolloverIcon;
		this.selectedIcon = selectedIcon;
	}

	public boolean getChecked() {
		return checked;
	}

	public CheckValue setChecked(boolean checked) {
		this.checked = checked;
		return this;
	}

	public String getValue() {
		return value;
	}

	public CheckValue setValue(String value) {
		this.value = value;
		return this;
	}

	public Checker getType() {
		return type;
	}

	public CheckValue setType(Checker type) {
		if (type!=null) {
			this.type = type;
		}
		return this;
	}
	
	public Icon getIcon() {
		return icon;
	}

	public CheckValue setIcon(Icon icon) {
		this.icon = icon;
		return this;
	}

	public Icon getRolloverIcon() {
		return rolloverIcon;
	}

	public CheckValue setRolloverIcon(Icon rolloverIcon) {
		this.rolloverIcon = rolloverIcon;
		return this;
	}

	public Icon getSelectedIcon() {
		return selectedIcon;
	}

	public CheckValue setSelectedIcon(Icon selectedIcon) {
		this.selectedIcon = selectedIcon;
		return this;
	}

	/**
	 * 返回代表当前对象类型是否是{@link Checker#SELF}类型的boolean值
	 * @return
	 */
	public boolean self() {
		return this.type==Checker.SELF;
	}
	
	
	/**
	 * 返回代表当前对象类型是否是{@link Checker#ALL}类型的boolean值
	 * @return
	 */
	public boolean all() {
		return this.type==Checker.ALL;
	}
	
	
	/**
	 * 返回代表当前对象类型是否是{@link Checker#TURN}类型的boolean值
	 * @return
	 */
	public boolean turn() {
		return this.type==Checker.TURN;
	}

	@Override
	public String toString() {
		return "CheckValue [checked=" + checked + ", value=" + value + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckValue other = (CheckValue) obj;
		if (type != other.type)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}
