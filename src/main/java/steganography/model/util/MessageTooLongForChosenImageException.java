package main.java.steganography.model.util;

@SuppressWarnings("serial")
/**
 * 
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 *
 * This class displays a string when the user inserts a message that is too long compared to the chosen image dimension
 */
public class MessageTooLongForChosenImageException extends Exception {
	public MessageTooLongForChosenImageException () {
		super("The inserted message is too long for the chosen image");
	}
}