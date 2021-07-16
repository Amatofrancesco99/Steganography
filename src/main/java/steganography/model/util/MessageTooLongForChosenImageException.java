package main.java.steganography.model.util;

@SuppressWarnings("serial")
public class MessageTooLongForChosenImageException extends Exception {
	public MessageTooLongForChosenImageException () {
		super("The inserted message is too long for the chosen image");
	}
}