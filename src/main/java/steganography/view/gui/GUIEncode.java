package main.java.steganography.view.gui;


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
import main.java.steganography.model.util.MessageTooLongForChosenImageException;

import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;

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
		frmEncode.setResizable(false);
		frmEncode.setTitle("Encode");
		frmEncode.setBounds(100, 100, 454, 168);
		
		JPanel panel = new JPanel();
		frmEncode.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Choose the image on which you'll apply steganography");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Open");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(panel);
				if (fileChooser.getSelectedFile() != null)
					directory =  fileChooser.getSelectedFile().toString();
			}
		});
		panel.add(btnNewButton);
	
		JLabel lblNewLabel_1 = new JLabel("Insert the steganographed image file name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(13);
		
		JLabel lblNewLabel_2 = new JLabel("Enter the message you want to encode");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(15);
		panel.add(passwordField);
		
		JButton btnNewButton_1 = new JButton("ENCODE");
		btnNewButton_1.setForeground(new Color(46, 139, 87));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String message;
				try {
					String path = "./steganographedImages";
					String fileName = textField.getText();
					message = passwordField.getText();
				
					if (fileName.length() == 0) {
						JOptionPane.showMessageDialog(frmEncode, "Please insert the name of the image that will be steganographed" , "InexistentFileNameException", JOptionPane.ERROR_MESSAGE);
					}
					if (message.length() == 0 || message == null) {
						JOptionPane.showMessageDialog(frmEncode, "Please insert a message" , "MessageException", JOptionPane.ERROR_MESSAGE);
					}
					else {
						String filename = directory;
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
				} catch (MessageTooLongForChosenImageException exception) {
					JOptionPane.showMessageDialog(frmEncode, exception.getMessage() , "ImageException", JOptionPane.ERROR_MESSAGE);
					message = "";
					directory = null;
				} catch (NullPointerException exception) {
					JOptionPane.showMessageDialog(frmEncode, "Please choose an image on which apply steganography" , "ImageException", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton_1);
		frmEncode.setVisible(true);
		frmEncode.setLocationRelativeTo(null);
	}
}