package org.shadow.codestatistic;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import org.shadow.codestatistic.pojo.Config;
import org.shadow.codestatistic.pojo.Result;

public class StatisticGUI extends Frame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		StatisticGUI gui = new StatisticGUI();
		gui.launch();
	}

	public void launch() {
		Panel inputPanel = new Panel();
		Panel outputPanel = new Panel();
		Panel btnPanel = new Panel();

		TextField pathField = new TextField("D:\\to\\code\\path", 20);
		TextField extensionField = new TextField(".java,.xml", 3);
		TextField singleCommentField = new TextField("//,#", 3);
		TextField multiCommentField = new TextField("/* */,<!-- -->", 3);

		inputPanel.setLayout(new GridLayout(5, 2));
		inputPanel.add(new Label("code path:"));
		inputPanel.add(pathField);
		inputPanel.add(new Label("file extension:"));
		inputPanel.add(extensionField);
		inputPanel.add(new Label("single line comment pattern:"));
		inputPanel.add(singleCommentField);
		inputPanel.add(new Label("multi line comment pattern:"));
		inputPanel.add(multiCommentField);

		TextField codeLineField = new TextField(3);
		TextField commentLineField = new TextField(3);
		TextField blankLineField = new TextField(3);
		TextField totalLineField = new TextField(3);

		outputPanel.setLayout(new GridLayout(4, 2));
		outputPanel.add(new Label("code lines:"));
		outputPanel.add(codeLineField);
		outputPanel.add(new Label("comment lines:"));
		outputPanel.add(commentLineField);
		outputPanel.add(new Label("blank lines:"));
		outputPanel.add(blankLineField);
		outputPanel.add(new Label("total lines:"));
		outputPanel.add(totalLineField);

		Button confirmBtn = new Button("Confirm");
		Button clearBtn = new Button("Clear");
		Button aboutBtn = new Button("About");

		btnPanel.add(confirmBtn);
		btnPanel.add(clearBtn);
		btnPanel.add(aboutBtn);

		setSize(600, 400);
		setLocationRelativeTo(null);
		setTitle("Code Statistic Tool，By Shadow");
		setLayout(new GridLayout(3, 2));
		add(inputPanel, "North");
		add(outputPanel, "Center");
		add(btnPanel, "South");

		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		confirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Config config = new Config();

				config.setRootPath(pathField.getText());
				config.setExtensions(extensionField.getText());
				config.setSingleComment(singleCommentField.getText());
				config.setMultiComment(multiCommentField.getText());

				try {
					Result result = new Statistic().run(config);

					codeLineField.setText(result.getCodeLines() + "");
					commentLineField.setText(result.getCommentLines() + "");
					blankLineField.setText(result.getBlankLines() + "");
					totalLineField.setText(result.getTotalLines() + "");
				} catch (Exception ex) {
					showMessage(ex.getMessage());
				}

			}
		});

		clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				codeLineField.setText("");
				commentLineField.setText("");
				blankLineField.setText("");
				totalLineField.setText("");
			}
		});

		aboutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "You can choose the programing language which you want to stat by configuring \"file extension\",\n"
						+ "\"single line comment pattern\" and \"multi line comment pattern\". All extension or pattern separated\n"
						+ "by comma, the prefix and suffix of mulit line comment pattren should be separated by space.\n";
				showMessage(message);
			}
		});

	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
