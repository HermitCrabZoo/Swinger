package com.zoo.swing.component;

import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;

@SuppressWarnings("restriction")
public class WindowsCheckComboBoxUI extends WindowsComboBoxUI {
	public WindowsCheckComboBoxUI() {
		currentValuePane = new CheckCellRendererPane();
	}
}
