package main.java.steganography.model;

import java.awt.image.BufferedImage;
import java.math.BigInteger;

/**
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 *
 * This class manages the decoding functions of the steganography process
 */
public final class Decode {

	/**
	 * 
	 * @param encoded
	 * @return fin
	 * 
	 * This method converts the bits of the hidden message into a String
	 */
	public final static String getMessage (String encoded) {
		int values = encoded.length()/8;
		byte[] ba = new byte[values];
		ba = new BigInteger(encoded, 2).toByteArray();
		String fin = new String(ba);
		return fin;
	} 

	/**
	 * 
	 * @param image
	 * @return sb
	 * 
	 * This method extracts the bits of the hidden message contained into the manipulated image.
	 * In this section of the code, threads are used in order to have better performance
	 * in decoding process. There is a thread for every image column that realizes the decoding process
	 */
	public final static String decodeMessage(BufferedImage image) {
		StringBuilder sb = new StringBuilder();
		DecodeThread[] dt = new DecodeThread[image.getWidth()];
		for (int x = 0; x < image.getWidth(); x++) {
			dt[x] = new DecodeThread(image,x);
			dt[x].start();
		}
		for (int x = 0; x < image.getWidth(); x++) {
			sb.append(dt[x].getSb());
		}
		return "0"+sb.toString().replaceFirst("0+", "");
	}

}