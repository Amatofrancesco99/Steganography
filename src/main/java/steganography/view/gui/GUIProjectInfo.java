package main.java.steganography.view.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 */
@SuppressWarnings("serial")
public class GUIProjectInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static GUIProjectInfo dialog = new GUIProjectInfo();
	private JTextPane message = new JTextPane();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GUIProjectInfo() {
		setTitle("Description");
		setBounds(100, 100, 533, 340);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			initMessage();
			message.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(message);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Continue");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				okButton.setActionCommand("Continue");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Exit");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(ABORT);
					}
				});
				cancelButton.setActionCommand("Exit");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void initMessage() {
		message.setText(" Steganography Application \u00A9 \n"
						
						+ " This application has the aim to apply steganography to images, using threads."
						+ "\n\n"
				
						
						+ " Meaning\n"
						
						+ " Steganography is the practice of concealing a message within another message"
						+ "\n or a physical object. In computing/electronic contexts, a computer file,"
						+ "\n message, image, or video is concealed within another file, message, image,"
						+ "\n or video. The word steganography comes from Greek steganographia, which"
						+ "\n combines the words steganós (στεγανός), meaning \"covered or concealed\","
						+ "\n and graphia (γραφή) meaning \"writing\".\n"
						
						+ "\n With least significant bit steganography, the approach revolves around"
						+ "\n changing the least significant bit of each pixel's RGB values to match a"
						+ "\n corresponding bit in the message we want to encode. This way, the change"
						+ "\n is so small that the encoded image won't be noticably different to the"
						+ "\n naked eye from the original."
						+"\n\n"
						
						
						+ " Usage\n"
		
						
						+ "\n\n"
						+ " Developed by: Francesco Amato, Francesco Minaglia & Filippo Maria Rognoni");
	}

}
