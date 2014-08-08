package com.vmoiseenko.Assignment6;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class SwingForm extends JFrame {
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JLabel lblFileName;
	private JTextField txtFileName;
	private JComboBox<File> cboFileName;
	private JTextArea txtFormText;
	private JButton btnOpenFile;
	private JButton btnSubmit;
	private JButton btnClear;
	private JButton btnExit;


	public SwingForm(){
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(700, 200, 400, 400);
	add(createMainPanel());
	setVisible(true);
	setTitle("Text Forms");
}

public JPanel createMainPanel()
{
	mainPanel = new JPanel();
	mainPanel.setLayout(new BorderLayout());
	mainPanel.add(createTopPanel(), BorderLayout.NORTH);
	mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
	mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);
	return mainPanel;
}

public JPanel createTopPanel()
{
	topPanel = new JPanel();
	topPanel.setLayout(new GridLayout(1, 4, 5, 5));
	lblFileName = new JLabel("New File:");
	lblFileName.setHorizontalAlignment(JLabel.RIGHT);
	txtFileName = new JTextField();
	cboFileName = new JComboBox<File>(finder("C:\\Users\\Victoria\\workspace\\Assignment6"));
	topPanel.add(lblFileName);
	topPanel.add(txtFileName);
	topPanel.add(cboFileName);
	
	return topPanel;
}

public JPanel createCenterPanel()
{
	centerPanel = new JPanel();
	centerPanel.setLayout(new BorderLayout());
	txtFormText = new JTextArea();
	txtFormText.setLineWrap(true);
	txtFormText.setWrapStyleWord(true);
	centerPanel.add(txtFormText, BorderLayout.CENTER);
	
	return centerPanel;
}

public JPanel createBottomPanel()
{
bottomPanel = new JPanel();
bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
btnOpenFile = new JButton("Open File");
btnOpenFile.addActionListener(new OpenFileListener());
btnSubmit = new JButton("Submit Form");
btnSubmit.addActionListener(new SubmitFormListener());
btnClear = new JButton("Clear Form");
btnClear.addActionListener(new ClearFormListener());
btnExit = new JButton("Exit");
btnExit.addActionListener(new ExitListener());
bottomPanel.add(btnOpenFile);
bottomPanel.add(btnSubmit);
bottomPanel.add(btnClear);
bottomPanel.add(btnExit);
return bottomPanel;
}

public File[] finder(String dirName)
{
File dir = new File(dirName);
return dir.listFiles(new FilenameFilter()
{
public boolean accept(File dir, String filename)
{	
return filename.endsWith(".txt");
}
}
);
}
public class OpenFileListener implements ActionListener
{
@Override
public void actionPerformed(ActionEvent e)
{
ReadFile read = new ReadFile(cboFileName.getSelectedItem().toString());
try
{
String fileContent = read.getText();
txtFormText.setText(fileContent);
}
catch (IOException e1)
{
System.out.println(e1.getMessage());
}
}
}
public class SubmitFormListener implements ActionListener
{
@Override
public void actionPerformed(ActionEvent e)
{
try
{
WriteFile write = new WriteFile("C:\\temp\\" + txtFileName.getText());
String content = txtFormText.getText();
write.addText(content);
write.closeFile();
topPanel.remove(cboFileName);
cboFileName = new JComboBox<File>(finder("C:\\temp\\"));
topPanel.add(cboFileName);
topPanel.revalidate();
topPanel.repaint();
txtFileName.setText("");
txtFormText.setText("Your file was submitted successfully!");
txtFileName.grabFocus();
}
catch (IOException e1)
{
System.out.println(e1.getMessage());
}
}
}
public class ClearFormListener implements ActionListener
{
@Override
public void actionPerformed(ActionEvent e)
{
txtFileName.setText("");
txtFormText.setText("");
txtFileName.grabFocus();
}
}
public class ExitListener implements ActionListener
{
@Override
public void actionPerformed(ActionEvent e)
{
System.exit(0);
		}
	}
}
