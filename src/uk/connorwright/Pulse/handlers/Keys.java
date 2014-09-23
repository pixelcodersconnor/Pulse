/**
 * Key input handler class.
 */

package uk.connorwright.Pulse.handlers;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import uk.connorwright.Pulse.sound.Sound;

public class Keys {

	public static final int NUM_KEYS = 3;

	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];

	public static int ENTER = 0;
	public static int ESCAPE = 1;
	public static int R = 2;

	public static boolean KeyZ = false;

	private static boolean sound = true;

	public static void keySet(int i, boolean b) {
		if (i == KeyEvent.VK_ENTER)
			keyState[ENTER] = b;
		if (i == KeyEvent.VK_ESCAPE)
			keyState[ESCAPE] = b;
		if (i == KeyEvent.VK_R)
			keyState[R] = b;

		// Easter Egg #1 - SHAUN... SHAUN... SHAUUUUUUUUN!
		if (i == KeyEvent.VK_E) {

			if (!sound) {
				ParticleFactory.createBigWave(320, 240, 60, Color.WHITE);
				System.out.println("SHAUN!... SHAUN!... SHAUUUUUUUUN!");

			} else {
				Random random = new Random();
				int result = random.nextInt(2 + 1);

				if (result == 1) {
					Sound.shaunEasterEgg1.play();
				}

				if (result == 2) {
					Sound.shaunEasterEgg2.play();
				}
			}

		}

	}

	public static void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}

	public static boolean isPressed(int i) {
		return keyState[i] && !prevKeyState[i];
	}

}
