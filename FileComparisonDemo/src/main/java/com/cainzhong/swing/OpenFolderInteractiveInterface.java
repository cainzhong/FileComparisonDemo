package com.cainzhong.swing;

import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class OpenFolderInteractiveInterface implements ActionListener {

	// Frame Layout
	JFrame frame = new JFrame("File Comparison Tool");
	// Tab Layout
	JTabbedPane tabPane = new JTabbedPane();
	Container con = new Container();
	JLabel folderLabel = new JLabel("Select a folder");
	JLabel fileLabel = new JLabel("Select a file");
	// The path of a folder.
	JTextField folderPath = new JTextField();
	// The path of a file.
	JTextField filePath = new JTextField();
	JButton openFolderButton = new JButton("...");
	JButton openFileButton = new JButton("...");
	//JFileChooser provides a simple mechanism for the user to choose a file.
	JFileChooser jfc = new JFileChooser();
	JButton startComparisonButton = new JButton("Start");

	public OpenFolderInteractiveInterface() {
		//Set Disk D as default directory
		jfc.setCurrentDirectory(new File("d://"));

		double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

		double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		//Set the location of the window.
		frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));
		//Set the size of the window.
		frame.setSize(500, 400);
		frame.setContentPane(tabPane);// 设置布局
		folderLabel.setBounds(10, 10, 70, 20);
		folderPath.setBounds(75, 10, 120, 20);
		openFolderButton.setBounds(210, 10, 50, 20);
		fileLabel.setBounds(10, 35, 70, 20);
		filePath.setBounds(75, 35, 120, 20);
		openFileButton.setBounds(210, 35, 50, 20);
		startComparisonButton.setBounds(30, 60, 60, 20);
		openFolderButton.addActionListener(this); // 添加事件处理
		openFileButton.addActionListener(this); // 添加事件处理
		startComparisonButton.addActionListener(this); // 添加事件处理
		con.add(folderLabel);
		con.add(folderPath);
		con.add(openFolderButton);
		con.add(fileLabel);
		con.add(filePath);
		con.add(openFileButton);
		con.add(startComparisonButton);
		frame.setVisible(true);// 窗口可见
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使能关闭窗口，结束程序
		tabPane.add("1面板", con);// 添加布局1
	}

	/**
	 * 时间监听的方法
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(openFolderButton)) {// 判断触发方法的按钮是哪个
			jfc.setFileSelectionMode(1);// 设定只能选择到文件夹
			int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
			if (state == 1) {
				return;
			} else {
				File f = jfc.getSelectedFile();// f为选择到的目录
				folderPath.setText(f.getAbsolutePath());
			}
		}
		// 绑定到选择文件，先择文件事件
		if (e.getSource().equals(openFileButton)) {
			jfc.setFileSelectionMode(0);// 设定只能选择到文件
			int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
			if (state == 1) {
				return;// 撤销则返回
			} else {
				File f = jfc.getSelectedFile();// f为选择到的文件
				filePath.setText(f.getAbsolutePath());
			}
		}
		if (e.getSource().equals(startComparisonButton)) {
			// 弹出对话框可以改变里面的参数具体得靠大家自己去看，时间很短
			JOptionPane.showMessageDialog(null, "弹出对话框的实例，欢迎您-漆艾琳！", "提示", 2);
		}
	}

	public static void main(String[] args) {
		new OpenFolderInteractiveInterface();
	}
}