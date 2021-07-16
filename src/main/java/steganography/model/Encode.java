package main.java.steganography.model;

import java.awt.image.BufferedImage;
import java.math.BigInteger;

/**
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 *
 */
public final class Encode {

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
	
	public final static BufferedImage encodeImage (String bit, BufferedImage image) {
		int pointer = bit.length()-1; //bit string pointer
		
		for (int x = image.getWidth()-1; x >= 0; x--) {
			System.out.println("\nParte il thread di encode: " + x);
			EncodeThread et = new EncodeThread(x, pointer, bit, image);
			et.start();
			pointer = pointer - image.getHeight();
			
		}
		
		return image;
	}
	
}