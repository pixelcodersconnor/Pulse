/**
 * Just an image class.
 * Can either be filled or not filled.
 */

package uk.connorwright.Pulse.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import uk.connorwright.Pulse.handlers.ImageLoader;

public class Star {
	
	private BufferedImage star;
	private int tick;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Star(int x, int y, boolean full) {
		this.x = x;
		this.y = y;
		tick = 0;
		if(full) star = ImageLoader.STAR;
		else star = ImageLoader.STAROUTLINE;
		width = 32;
		height = 32;
	}
	
	public void update() {
		tick++;
		if(tick == 120) {
			tick = 0;
		}
		if(tick < 60) {
			width = (int) 32.0 * tick / 60;
		}
		else {
			width = (int) 32.0 * (120 - tick) / 60;
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(star, x - width / 2, y, width, height, null);
	}
	
}
