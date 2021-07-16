package main.java.steganography.model.util;

@SuppressWarnings("serial")
public class MessageTooLongForChosenImageException extends Exception {
	public MessageTooLongForChosenImageException (String message) {
		super(message);
	}
}