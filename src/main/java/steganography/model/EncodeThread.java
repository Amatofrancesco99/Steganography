package main.java.steganography.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class EncodeThread extends Thread{
	
	int x, pointer;
	String bit;
	BufferedImage image;
	
	public EncodeThread(int x, int pointer, String bit, BufferedImage image) {
		
		this.x = x;
		this.pointer = pointer;
		this.image = image;
		this.bit = bit;
	}
	
	@Override
	public void run() {
		
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
}
