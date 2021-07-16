package main.java.steganography.model;

import java.awt.image.BufferedImage;
import java.math.BigInteger;

import main.java.steganography.model.util.MessageTooLongForChosenImageException;

/**
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 *
 * This class manages the encoding functions of the steganography process
 */
public final class Encode {

	/**
	 * 
	 * @param message
	 * @return bitString
	 * 
	 * This method converts the message into bits
	 */
	public final static String encodeMessage (String message) {
		String bitString = new BigInteger(message.getBytes()).toString(2);
		if (bitString.length()%8 != 0) {
			String zeroes = "";
			while ((bitString.length() + zeroes.length()) % 8 != 0) {
				zeroes = zeroes + "0";
			}
			bitString = zeroes + bitString;
		}
		return bitString;
	}
	
	/**
	 * 
	 * @param bit
	 * @param image
	 * @return image
	 * @throws MessageTooLongForChosenImageException
	 * 
	 * This method applies the steganography process to the selected image.
	 * In this section of the code, threads are used in order to have better performance
	 * in encoding process. There is a thread for every image column that realizes the encoding process
	 */
	public final static BufferedImage encodeImage (String bit, BufferedImage image) throws MessageTooLongForChosenImageException {
		System.out.println(((image.getWidth()*image.getHeight()) * 3) - 1);
		if (bit.length() > (((image.getWidth()*image.getHeight()) * 3) - 1))
			throw new MessageTooLongForChosenImageException();
		int pointer = bit.length()-1; //bit string pointer
		for (int x = image.getWidth()-1; x >= 0; x--) {
			EncodeThread et = new EncodeThread(x, pointer, bit, image);
			et.start();
			pointer = pointer - image.getHeight();
			
		}
		return image;
	}
	
}