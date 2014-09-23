package uk.connorwright.Pulse.entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class HitBall extends GameObject {
	
	public HitBall(double x, double y) {
		this.x = x;
		this.y = y;
		width = height = 8;
		color = new Color(255, 255, 255, 64);
		colorBorder = Color.WHITE;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) {
		drawCircle(g);
	}
	
}
