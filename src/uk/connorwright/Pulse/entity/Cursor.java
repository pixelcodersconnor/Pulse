/**
 * Replaces the mouse pointer with a circle.
 */

package uk.connorwright.Pulse.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import uk.connorwright.Pulse.handlers.LevelData;
import uk.connorwright.Pulse.handlers.Mouse;

public class Cursor extends GameObject {
	
	public Cursor() {
		width = height = 10;
		color = new Color(255, 128, 128, 64);
		colorBorder = new Color(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public void update() {
		x = Mouse.x;
		y = Mouse.y;
	}
	
	public void draw(Graphics2D g) {
		g.setStroke(LevelData.STROKE_2);
		drawCircle(g);
	}
	
}
