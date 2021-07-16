package main.java.steganography.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * @author Francesco Amato
 * @author Francesco Minaglia
 * @author Filippo Maria Rognoni
 *
 * This class manages the decoding threads functions
 */
public class DecodeThread extends Thread{
	
	private BufferedImage image;
	private String sb = "";
	private int x;
	
	/**
	 * 
	 * @param image
	 * @param x
	 * 
	 * The constructor gets as inputs the image and one of its columns
	 */
	public DecodeThread(BufferedImage image, int x) {
		this.x = x;
		this.image = image;
	}
	
	
	@Override
	/**
	 * This method looks for the RGB spectrum components of a single pixel, and it saves them into an array.
	 * Consequentially an AND is applied;
	 * if the result is equal to 1, the same number is appended to the sb string, otherwise a 0
	 */
	public void run() {
		for (int y = 0; y < image.getHeight(); y++) {
			Color c = new Color(image.getRGB(x,y)); //color of pixel
			byte r = (byte) c.getRed(); //split into red
			byte g = (byte) c.getGreen(); //split into green 
			byte b = (byte) c.getBlue(); //split into blue
			byte[] RGB = {r, g, b};
			
			for (int i = 0; i < 3; i++) {
				if ((RGB[i] & 1) == 1) { //LSB is a 1
					sb += "1";
				} else { //else it is a 0
					sb += "0";
				}
			}
		}
	}
	
	/**
	 * 
	 * @return sb
	 * 
	 * This method allows to obtain the complete string message after the thread job is finished
	 */
	public String getSb() {
		try {
			this.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return sb;	
	}
	
}