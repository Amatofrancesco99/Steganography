package main.java.steganography.view.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.steganography.model.Decode;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

/**
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 */
public class GUIDecode {

	private JFrame frmDecode;
	private String steganographedImage;
	

	/**
	 * Create the application.
	 */
	public GUIDecode() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDecode = new JFrame();
		frmDecode.setResizable(false);
		frmDecode.setTitle("Decode");
		frmDecode.setBounds(100, 100, 302, 105);
		frmDecode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmDecode.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Select the steganographed image");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Open");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(panel);
				if (fileChooser.getSelectedFile() != null)
					steganographedImage = fileChooser.getSelectedFile().toString();
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("GET THE MESSAGE");
		btnNewButton_1.setForeground(new Color(220, 20, 60));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File outFile = new File(steganographedImage);
					BufferedImage image = null;
					image = ImageIO.read(outFile);
					String bitMessage = Decode.decodeMessage(image);
					JOptionPane.showMessageDialog(frmDecode, Decode.getMessage(bitMessage), "Hidden message", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException exception) {
					JOptionPane.showMessageDialog(frmDecode, exception.getMessage() , "IOException", JOptionPane.ERROR_MESSAGE);
				}
				catch (NullPointerException exception) {
					JOptionPane.showMessageDialog(frmDecode, "You haven't choose any file" , "NullPointerException", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton_1);
		frmDecode.setVisible(true);
		frmDecode.setLocationRelativeTo(null);
	}

}