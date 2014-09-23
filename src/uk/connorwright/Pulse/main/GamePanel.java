package uk.connorwright.Pulse.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import uk.connorwright.Pulse.handlers.GameStateManager;
import uk.connorwright.Pulse.handlers.Keys;
import uk.connorwright.Pulse.handlers.LevelData;
import uk.connorwright.Pulse.handlers.Mouse;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener,
MouseListener, MouseMotionListener {

	// dimensions
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;

	// image
	private BufferedImage image;
	private Graphics2D g;

	// game state manager
	private GameStateManager gsm;

	// other
	private boolean screenshot;

	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			addKeyListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
			thread = new Thread(this);
			thread.start();
		}
	}

	private void init() {

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		running = true;

		LevelData.init();
		gsm = new GameStateManager();

	}

	public void run() {

		init();

		long start;
		long elapsed;
		long wait;

		// game loop
		while (running) {

			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;

			wait = targetTime - elapsed / 1000000;
			if (wait < 0)
				wait = 5;

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void update() {
		gsm.update();
		Keys.update();
		Mouse.update();
	}

	private void draw() {
		gsm.draw(g);
	}

	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
		if (screenshot) {
			screenshot = false;
			try {
				java.io.File out = new java.io.File("screenshot "
						+ System.nanoTime() + ".png");
				javax.imageio.ImageIO.write(image, "png", out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void keyPressed(KeyEvent key) {
		if (key.isControlDown()) {
			if (key.getKeyCode() == KeyEvent.VK_S) {
				screenshot = true;
				return;
			}
		}
		Keys.keySet(key.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), false);
	}

	public void mousePressed(MouseEvent me) {
		Mouse.setAction(Mouse.PRESSED);
	}

	public void mouseReleased(MouseEvent me) {
		Mouse.setAction(Mouse.RELEASED);
	}

	public void mouseMoved(MouseEvent me) {
		Mouse.setPosition(me.getX(), me.getY());
	}

	public void mouseDragged(MouseEvent me) {
		Mouse.setAction(Mouse.PRESSED);
		Mouse.setPosition(me.getX(), me.getY());
	}

	public void mouseEntered(MouseEvent me) {
	}

	public void mouseExited(MouseEvent me) {
	}

	public void mouseClicked(MouseEvent me) {
	}

	public void keyTyped(KeyEvent key) {
	}

}