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
		frame.setContentPane(tabPane);// ���ò���
		folderLabel.setBounds(10, 10, 70, 20);
		folderPath.setBounds(75, 10, 120, 20);
		openFolderButton.setBounds(210, 10, 50, 20);
		fileLabel.setBounds(10, 35, 70, 20);
		filePath.setBounds(75, 35, 120, 20);
		openFileButton.setBounds(210, 35, 50, 20);
		startComparisonButton.setBounds(30, 60, 60, 20);
		openFolderButton.addActionListener(this); // ����¼�����
		openFileButton.addActionListener(this); // ����¼�����
		startComparisonButton.addActionListener(this); // ����¼�����
		con.add(folderLabel);
		con.add(folderPath);
		con.add(openFolderButton);
		con.add(fileLabel);
		con.add(filePath);
		con.add(openFileButton);
		con.add(startComparisonButton);
		frame.setVisible(true);// ���ڿɼ�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ʹ�ܹرմ��ڣ���������
		tabPane.add("1���", con);// ��Ӳ���1
	}

	/**
	 * ʱ������ķ���
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(openFolderButton)) {// �жϴ��������İ�ť���ĸ�
			jfc.setFileSelectionMode(1);// �趨ֻ��ѡ���ļ���
			int state = jfc.showOpenDialog(null);// �˾��Ǵ��ļ�ѡ��������Ĵ������
			if (state == 1) {
				return;
			} else {
				File f = jfc.getSelectedFile();// fΪѡ�񵽵�Ŀ¼
				folderPath.setText(f.getAbsolutePath());
			}
		}
		// �󶨵�ѡ���ļ��������ļ��¼�
		if (e.getSource().equals(openFileButton)) {
			jfc.setFileSelectionMode(0);// �趨ֻ��ѡ���ļ�
			int state = jfc.showOpenDialog(null);// �˾��Ǵ��ļ�ѡ��������Ĵ������
			if (state == 1) {
				return;// �����򷵻�
			} else {
				File f = jfc.getSelectedFile();// fΪѡ�񵽵��ļ�
				filePath.setText(f.getAbsolutePath());
			}
		}
		if (e.getSource().equals(startComparisonButton)) {
			// �����Ի�����Ըı�����Ĳ�������ÿ�����Լ�ȥ����ʱ��ܶ�
			JOptionPane.showMessageDialog(null, "�����Ի����ʵ������ӭ��-�ᰬ�գ�", "��ʾ", 2);
		}
	}

	public static void main(String[] args) {
		new OpenFolderInteractiveInterface();
	}
}