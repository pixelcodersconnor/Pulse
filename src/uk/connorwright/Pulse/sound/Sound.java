package uk.connorwright.Pulse.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	// Sound effects
	public static final Sound bouncerHit = new Sound("/sfx/bouncer.wav");
	public static final Sound playerHit = new Sound("/sfx/hit.wav");
	public static final Sound menuClick = new Sound("/sfx/menuclick.wav");
	public static final Sound finishLevel = new Sound("/sfx/finish.wav");
	public static final Sound blackHole = new Sound("/sfx/hole.wav");

	// Music
	public static final Sound backgroundMusic = new Sound("/music/bgmusic1.wav");
	public static final Sound menuMusic = new Sound("/music/menumusic.wav");

	// Easter eggs


	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {

				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
