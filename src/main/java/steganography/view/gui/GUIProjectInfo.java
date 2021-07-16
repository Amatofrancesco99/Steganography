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
import java.awt.Font;
import java.awt.Toolkit;

/**
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 */
@SuppressWarnings("serial")
public class GUIProjectInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextPane message = new JTextPane();
	

	/**
	 * Create the dialog.
	 */
	public GUIProjectInfo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIProjectInfo.class.getResource("/main/java/steganography/view/gui/resources/logo.png")));
		setResizable(false);
		setTitle("Description");
		setBounds(100, 100, 552, 340);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			initMessage();
			message.setFont(new Font("Times New Roman", Font.PLAIN, 15));
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
				okButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
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
		try {
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			this.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initMessage() {
		message.setText(" Steganography Application \u00A9 \n"
						
						+ "  This application has the aim to apply steganography to images, using threads."
						+ "\n\n"
				
						
						+ " Meaning\n"

						+ "  Steganography is the practice of concealing a message within another message"
						+ "\n  or a physical object. In computing/electronic contexts, a computer file, "
						+ "\n  message, image, or video is concealed within another file, message, image, "
						+ "\n  or video. The word steganography comes from Greek steganographia, which "
						+ "\n  combines the words steganós (στεγανός), meaning \"covered or concealed\", "
						+ "\n  and graphia (γραφή) meaning \"writing\".\n"
						+ "\n"
						+ "  With least significant bit steganography, the approach revolves around changing "
						+ "\n  the least significant bit of each pixel's RGB values to match a corresponding "
						+ "\n  bit in the message we want to encode. "
						+ "\n  This way, the change is so small that the encoded image won't be noticably "
						+ "\n  naked-eye different from the original.\n\n"
						
						
						+ " Usage\n"
		
						+ "  The application is developed with Java Swing. If you choose: \n"
						+ "   - ENCODE you can apply steganography to an image (hide the message). "
						+ "\n    You have to:\n"
						+ "       - choose the image on which steganography will be applied;\n"
						+ "       - insert the name of the steganographed image;\n"
						+ "       - add the hidden message;\n"
						+ "       - all steganographed images will be saved by default in "
						+ "\n         steganographedImages folder.\n"
						+ "   - DECODE you can read the message from a steganographed image."
						
						
						+ "\n\n\n"
						+ " Developed by: Francesco Amato, Francesco Minaglia and Filippo Maria Rognoni");
	}

}