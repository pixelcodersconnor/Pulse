/**
 * Spawns pushballs at set intervals.
 */

package uk.connorwright.Pulse.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import uk.connorwright.Pulse.handlers.EnemyFactory;

public class Spawner extends GameObject {
	
	private int tick;
	private int delay;
	private double speed;
	
	public Spawner(double x, double y, int width, int height, int delay, double speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.delay = delay;
		this.speed = speed;
		tick = 0;
		color = new Color(255, 0, 0, 64);
		colorBorder = Color.RED;
	}
	
	public void update() {
		tick++;
		if(tick == delay) {
			tick = 0;
			EnemyFactory.spawn(x, y, speed);
		}
	}
	
	public void draw(Graphics2D g) {
		drawCircle(g);
	}
	
}