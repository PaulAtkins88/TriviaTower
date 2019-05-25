package com.paulsgames.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

import com.paulsgames.gfx.BufferedImageLoader;
import com.paulsgames.gfx.Tower;
import com.paulsgames.gfx.Window;
import com.paulsgames.utils.AudioPlayer;
import com.paulsgames.utils.Handler;
import com.paulsgames.utils.Menu;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 2342922613046817154L;

	public static final int WIDTH = 1024, HEIGHT = WIDTH / 12 * 9;

	private boolean running = false;
	private Thread thread;

	private Menu menu;
	private Handler handler;
	private HUD hud;
	private Tower tower;

	public static BufferedImage sprite_sheet;

	public enum STATE {
		Menu, Game, Help, Win, End
	};

	public static STATE gameState = STATE.Menu; // start the game in the menu

	public Game() {
		File myFile = new File("res/spriteSheet.png");
		BufferedImageLoader imageLoader = new BufferedImageLoader();
		sprite_sheet = imageLoader.loadImage(myFile);
		this.tower = new Tower(this); // create the tower
		this.handler = new Handler(); // create the handler which turns on all the render/tick methods for objects
		this.hud = new HUD(this, handler); // create the HUD (main game)
		this.menu = new Menu(this, handler); // create the Menu
		this.addMouseListener(menu); // listen for the mouse in menu
		this.addMouseListener(hud); // listen for the mouse in HUD
		AudioPlayer.load(); // load the sound files
		AudioPlayer.getMusic("music").loop(); // loop the background music

		// TODO: Make this a slider in options or something to that effect
		AudioPlayer.getMusic("music").setVolume(0.1f);
		new Window(WIDTH, HEIGHT, "Trivia Towers", this); // create the window.
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		float amountOfTicks = 60.0f;
		float ns = 1000000000 / amountOfTicks;
		float delta = 0f;
		long timer = System.currentTimeMillis();
		// int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			// frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				// frames = 0;
			}
		}
		stop();
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join(); // this kills the thread. Remember this
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(new Color(0, 255, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);

		if (gameState == STATE.Game) {
			tower.render(g);
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End
				|| gameState == STATE.Win) {
			menu.render(g);
			tower.render(g);
		}

		g.dispose();
		bs.show();
	}

	private void tick() {
		if (gameState == STATE.Game) {
			// do game stuff
			hud.tick();
			handler.tick();
			if (HUD.HEALTH <= 0) {
				HUD.HEALTH = 100;
				gameState = STATE.End;
				handler.object.clear();
			} else if (HUD.level == 12) {
				HUD.HEALTH = 100;
				gameState = STATE.Win;
				handler.object.clear();
			}
		} else if (gameState == STATE.Menu) {
			menu.tick();
			handler.tick();
		}
	}

	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public static void main(String[] args) {
		new Game();

	}

}
