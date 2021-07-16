package main.java.steganography.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.math.BigInteger;

import main.java.steganography.model.util.SteganographyException;

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
	
	public final static BufferedImage encodeImage (String bit, BufferedImage image) throws SteganographyException {
		
		if (bit.length() > (image.getWidth()*image.getHeight()/8))
			throw new SteganographyException("The inserted message is too long for the chosen image");
		
		int pointer = bit.length()-1; //bit string pointer
		
		for (int x = image.getWidth()-1; x >= 0; x--) {
			for (int y = image.getHeight()-1; y >= 0; y--) { //for each pixel
				
				Color c = new Color(image.getRGB(x,y)); //color of pixel
				byte r = (byte) c.getRed(); //split into red
				byte g = (byte) c.getGreen(); //split into green 
				byte b = (byte) c.getBlue(); //split into blue
				byte[] RGB = {r, g, b};
				byte[] newRGB = new byte[3];
				
				for (int i = 2; i >= 0; i--) { //for each RGB value/set new RGB value
					if (pointer >= 0) { //if we still have bits to encode, change to 1 or 0
						//get LSB of respective RGB component
						int lsb;
						if ((RGB[i] & 1) == 1) {
							lsb = 1;
						} else {
							lsb = 0;
						}
						
						//if LSB doesn't match current message bit, change
						//System.out.println(pointer-i);
						if (Character.getNumericValue(bit.charAt(pointer)) != lsb) {
							if (lsb == 1) { //change to 0
								newRGB[i] = (byte) (RGB[i] & ~(1));
							} else { //change to 1
								newRGB[i] = (byte) (RGB[i] | 1);
							}
						} else {
							newRGB[i] = RGB[i];
						}
					} else {  //else we don't, make 0
						//change to 0
						newRGB[i] = (byte) (RGB[i] & ~(1));
					}
					
					pointer--;
				}
			
				Color newColor = new Color(Byte.toUnsignedInt(newRGB[0]), Byte.toUnsignedInt(newRGB[1]), Byte.toUnsignedInt(newRGB[2]));
				image.setRGB(x,y,newColor.getRGB());
			}
		}
		return image;
	}
	
}