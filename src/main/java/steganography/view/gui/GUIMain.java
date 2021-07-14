package main.java.steganography.view.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 */
public class GUIMain {

	private JFrame frmSteganography;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain window = new GUIMain();
					window.frmSteganography.setVisible(true);
					window.frmSteganography.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSteganography = new JFrame();
		frmSteganography.setTitle("Steganography");
		frmSteganography.setBounds(100, 100, 260, 126);
		frmSteganography.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmSteganography.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Encode");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIEncode.main(null);
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Decode");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIDecode.main(null);
			}
		});
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		frmSteganography.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_2 = new JButton("Info");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIProjectInfo.main(null);
			}
		});
		panel_1.add(btnNewButton_2);
	}
}