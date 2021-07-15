package main.java.steganography.view.gui;

import java.awt.EventQueue;

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

/**
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 */
public class GUIDecode {

	private JFrame frmDecode;
	private String steganographedImage;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIDecode window = new GUIDecode();
					window.frmDecode.setVisible(true);
					window.frmDecode.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frmDecode.setTitle("Decode");
		frmDecode.setBounds(100, 100, 475, 117);
		frmDecode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmDecode.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Choose the full path of the image file you want to decode");
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Choose");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(panel);
				if (fileChooser.getSelectedFile() != null)
					steganographedImage = fileChooser.getSelectedFile().toString();
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Get the message");
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
	}

}