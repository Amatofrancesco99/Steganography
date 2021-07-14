package main.java.steganography.view.gui;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import main.java.steganography.model.Encode;

import javax.swing.JPasswordField;

/**
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 */
public class GUIEncode {

	private JFrame frmEncode;
	private JTextField textField;
	private JPasswordField passwordField;
	private String directory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIEncode window = new GUIEncode();
					window.frmEncode.setVisible(true);
					window.frmEncode.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIEncode() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEncode = new JFrame();
		frmEncode.setTitle("Encode");
		frmEncode.setBounds(100, 100, 486, 180);
		
		JPanel panel = new JPanel();
		frmEncode.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Choose the image path on which you'll apply steganography");
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Choose");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(panel);
				if (fileChooser.getSelectedFile() != null)
					directory = fileChooser.getCurrentDirectory().toString() + fileChooser.getSelectedFile().toString();
			}
		});
		panel.add(btnNewButton);
	
		JLabel lblNewLabel_1 = new JLabel("Enter steganographed image file name");
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(14);
		
		JLabel lblNewLabel_2 = new JLabel("Enter the message you want to encode");
		panel.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(15);
		panel.add(passwordField);
		
		JButton btnNewButton_1 = new JButton("Encode");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String path = directory;
					String fileName = textField.getText();
					@SuppressWarnings("deprecation")
					String message = passwordField.getText();
				
					if (fileName.length() == 0) {
						JOptionPane.showMessageDialog(frmEncode, "Please insert the name of the image that will be steganographed" , "InexistentFileNameException", JOptionPane.ERROR_MESSAGE);
					}
					if (message.length() == 0 || message == null) {
						JOptionPane.showMessageDialog(frmEncode, "Please insert a message" , "MessageException", JOptionPane.ERROR_MESSAGE);
					}
					else {
						String filename = path + fileName;
						File inFile = new File(filename);
						BufferedImage initImage = null;
					
						initImage = ImageIO.read(inFile);
						String bitMsg = Encode.encodeMessage(message);
						BufferedImage newImage = Encode.encodeImage(bitMsg,initImage);
						
						File dir = new File(path);
						File finalImage = new File(dir, fileName);			
						ImageIO.write(newImage,"png",finalImage);
						JOptionPane.showMessageDialog(frmEncode, "New image saved!" , "Status", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (IOException exception) {
					JOptionPane.showMessageDialog(frmEncode, exception.getMessage() , "IOException", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton_1);
	}
}