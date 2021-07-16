package main.java.steganography.model;

import java.awt.image.BufferedImage;

/**
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 *
 */
public final class Decode {

	public final static String getMessage (String encoded) {
		int count = encoded.length()-1;
		StringBuilder message = new StringBuilder();
		int values = encoded.length()/8;
		byte[] ba = new byte[values];
		int arrayCount = values-1;
		while (arrayCount > 0) {
			StringBuilder bits = new StringBuilder();
			for (int i = 0; i < 8; i++) {
				bits.insert(0,encoded.charAt(count-i));
			}
			byte b = (byte) Integer.parseInt(bits.toString(), 2);
			int x = Byte.toUnsignedInt(b);
			ba[arrayCount] = (byte) x;
			char c = (char) x;
			message.insert(0,c);
			
				count = count - 8;
				arrayCount--;
			
		}
		String fin = new String(ba);
		
		return fin;
	} 

	public final static String decodeMessage(BufferedImage image) {
		StringBuilder sb = new StringBuilder();
		
		DecodeThread[] dt = new DecodeThread[image.getWidth()];
		for (int x = 0; x < image.getWidth(); x++) {
			System.out.println("\nParte il thread di decode: " + x);
			dt[x] = new DecodeThread(image,x);
			dt[x].start();
		}
		
		for (int x = 0; x < image.getWidth(); x++) {
			sb.append(dt[x].getSb());
		}
		
		return sb.toString();
	}

}