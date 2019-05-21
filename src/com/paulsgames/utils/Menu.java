package com.paulsgames.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.paulsgames.main.Game;
import com.paulsgames.main.ID;
import com.paulsgames.main.Game.STATE;
import com.paulsgames.main.Player;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private Random r;

	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
		this.r = new Random();
	}
	

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState == STATE.Menu) {
			if (mouseOver(mx,my,(Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) - 150, 200,64)) {
				game.gameState = STATE.Game;
				
				// TODO: This adds the player object to the game, this needs to be drawn AFTER the tower.
				handler.addObject(new Player((Game.WIDTH/2) +100,(Game.HEIGHT/2) + 180,ID.Player,handler));
				return;
			} else if (mouseOver(mx,my,(Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) - 75, 200, 64)){
				game.gameState = STATE.Help;
				return;				
			} else if (mouseOver(mx,my,(Game.WIDTH / 2) - 100, (Game.HEIGHT / 2), 200, 64)) {
				//EXIT BUTTON
				System.exit(0);
			}
		}
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx,my,(Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) +200, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void tick() {

	}

	public void render(Graphics g) {
		Font headingFont = new Font("arial", 1, 80);
		Font titleFont = new Font("arial", 1, 50);
		Font menuFont = new Font("arial", 1, 30);
		Font footerFont = new Font("arial", 2, 15);
		if (game.gameState == STATE.Menu) {
			g.setFont(headingFont);
			g.setColor(Color.orange);
			g.drawString("Trivia Towers", (Game.WIDTH / 2) - 250, (Game.HEIGHT / 2) - 300);

			g.setFont(titleFont);
			g.setColor(Color.cyan);
			g.drawString("Menu", (Game.WIDTH / 2) - 75, (Game.HEIGHT / 2) - 225);

			g.setColor(Color.lightGray);
			g.setFont(menuFont);
			g.drawRect((Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) - 150, 200, 64);
			g.setColor(Color.gray);
			g.fillRect((Game.WIDTH / 2) - 99, (Game.HEIGHT / 2) - 149, 199, 63);
			g.setColor(Color.green);
			g.drawString("Play", (Game.WIDTH / 2) - 35, (Game.HEIGHT / 2) - 110);

			g.setColor(Color.lightGray);
			g.drawRect((Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) - 75, 200, 64);
			g.setColor(Color.gray);
			g.fillRect((Game.WIDTH / 2) - 99, (Game.HEIGHT / 2) - 74, 199, 63);
			g.setColor(Color.green);
			g.drawString("Help", (Game.WIDTH / 2) - 35, (Game.HEIGHT / 2) - 30);

			g.setColor(Color.lightGray);
			g.drawRect((Game.WIDTH / 2) - 100, (Game.HEIGHT / 2), 200, 64);
			g.setColor(Color.gray);
			g.fillRect((Game.WIDTH / 2) - 99, (Game.HEIGHT / 2) + 1, 199, 63);
			g.setColor(Color.green);
			g.drawString("Quit", (Game.WIDTH / 2) - 35, (Game.HEIGHT / 2) + 45);

			g.setFont(footerFont);
			g.setColor(Color.white);
			g.drawString("coded by rookie paulatkins88@github.com", 10, Game.HEIGHT - 50);
		} else if (game.gameState == STATE.Help) {
			g.setFont(headingFont);
			g.setColor(Color.orange);
			g.drawString("Trivia Towers", (Game.WIDTH / 2) - 250, (Game.HEIGHT / 2) - 300);

			g.setFont(titleFont);
			g.setColor(Color.cyan);
			g.drawString("Help", (Game.WIDTH / 2) - 75, (Game.HEIGHT / 2) - 225);
			
			g.setFont(menuFont);
			g.setColor(Color.white);
			g.drawString("Answer questions correctly to help the dude up the tower!",15,300);
			g.drawString("Answer incorrectly and watch the dude fall down!", 15, 350);

			g.setColor(Color.lightGray);
			g.drawRect((Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) +200, 200, 64);
			g.setColor(Color.gray);
			g.fillRect((Game.WIDTH / 2) - 99, (Game.HEIGHT / 2) + 201, 199, 63);
			g.setColor(Color.green);
			g.drawString("Back", (Game.WIDTH / 2) - 35, (Game.HEIGHT / 2) + 245);

			g.setFont(footerFont);
			g.setColor(Color.white);
			g.drawString("coded by rookie paulatkins88@github.com", 10, Game.HEIGHT - 50);
		}

	}

}
