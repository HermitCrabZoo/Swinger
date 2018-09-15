package com.zoo.swing.util;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 鼠标拖动窗体实现类
 * 
 * @author Administrator
 *
 */
public final class WindowMover {
	
	private WindowMover() {}
	
	/**
	 * 设置窗体可以被拖拽,重复调用此方法传入同一个window对象不会导致事件重复添加.
	 * @param window
	 */
	public static void dragable(Window window) {
		if (window!=null) {
			//不重复添加
			for (MouseListener ml : window.getMouseListeners()) {
				if (ml.getClass() == WindowMouseAdapter.class) {
					return;
				}
			}
			for (MouseMotionListener ml : window.getMouseMotionListeners()) {
				if (ml.getClass() == WindowMouseAdapter.class) {
					return;
				}
			}
			WindowMouseAdapter wma=new WindowMouseAdapter(window);
			window.addMouseListener(wma);
			window.addMouseMotionListener(wma);
		}
	}
}

/**
 * 鼠标拖拽事件适配器
 * @author ZOO
 *
 */
final class WindowMouseAdapter extends MouseAdapter {
	
	private Window window;
	private boolean movable;
	private Point pre_point;
	
	public WindowMouseAdapter(Window window) {
		this.window=window;
	}
	
	/**
	 * 鼠标释放时触发
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		movable = false;// 鼠标释放了以后，是不能再拖拽的了
		window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**
	 * 鼠标按下时触发
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		movable = true;//鼠标按下去后设置窗体可被拖拽
		pre_point = new Point(e.getX(), e.getY());// 得到按下去的坐标
		window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//手指的样式
	}
	
	/**
	 * 鼠标拖拽时触发
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		/*
		 * 可拖拽的情况下计算窗体拖拽后的新坐标
		 */
		if (movable) {
			Point end_point = new Point(window.getLocation().x + e.getX() - pre_point.x,window.getLocation().y + e.getY() - pre_point.y);
			window.setLocation(end_point);
		}
	}
}
