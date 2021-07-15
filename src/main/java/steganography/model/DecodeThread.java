package main.java.steganography.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class DecodeThread extends Thread{
	
	private BufferedImage image;
	private String sb = "";
	private int x;
	
	
	public DecodeThread(BufferedImage image, int x) {
		
		this.x = x;
		this.image = image;
	}
	
	
	@Override
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
	
	
	public String getSb() {
		try {
			this.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return sb;	
	}
}
