/**
 * Used with background image.
 * Creates "atmosphere."
 */

package uk.connorwright.Pulse.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import uk.connorwright.Pulse.handlers.LevelData;
import uk.connorwright.Pulse.main.GamePanel;

public class Bubble extends GameObject {
	
	private int tick;
	
	public Bubble(double x, double y) {
		this.x = x;
		this.y = y;
		width = height = 16;
		color = new Color(0, 0, 0, 0);
		colorBorder = new Color(200, 200, 200, 128);
		tick = 0;
		dx = 0.7;
		dy = -0.7;
	}
	
	public boolean update() {
		tick++;
		if(tick == 30) {
			tick = 0;
			dx = 0.7 + Math.random() * 0.4 - 0.4;
			dy = -0.7 + Math.random() * 0.4 - 0.4;
		}
		x += dx;
		y += dy;
		if(x > GamePanel.WIDTH || y < 0) {
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g) {
		g.setStroke(LevelData.STROKE_1);
		drawCircle(g);
		g.drawOval((int) x + 2, (int) y - 6, 6, 6);
	}
	
}
