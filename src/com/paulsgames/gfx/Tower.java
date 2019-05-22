package com.paulsgames.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.paulsgames.main.Game;

public class Tower {

	private Game game;
	private Random r;
	private BufferedImage towerBlock1, towerBlock2, endBrick, grass1, grass2;

	public Tower(Game game) {
		this.game = game;

		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		towerBlock1 = ss.grabImage(1, 2, 32, 32); // grab image at cell, row, width, height
		towerBlock2 = ss.grabImage(2, 2, 32, 32); // grab image at cell, row, width, height
		endBrick = ss.grabImage(2, 2, 16, 32); // grab image at cell, row, width, height
		grass1 = ss.grabImage(3, 2, 32, 16); // grab image at cell, row, width, height
		grass2 = ss.grabImage(4, 2, 32, 16); // grab image at cell, row, width, height
	}

	public void render(Graphics g) {
		// ======================================================================
		
		g.setColor(new Color(215,216,0));
		g.fillOval(Game.WIDTH - 250, Game.HEIGHT/4, 200, 200);
		for (int i = 0; i < 5; i++) {
			g.setColor(new Color(216,132+(i*10),48));
			g.drawOval((Game.WIDTH - 250)+i, (Game.HEIGHT /4)+i, 200-(i*2), 200-(i*2));
		}
		
		// draw tower
		int towerXStart = (Game.WIDTH / 2) + 150, towerYStart = Game.HEIGHT - 430;
		int towerX = 0, towerY = towerYStart;

		for (int i = 0; i < 12; i++) {
			towerX = towerXStart;
			g.drawImage(endBrick, towerX - 16, towerY, null);
			for (int y = 0; y < 7; y++) {
				if (i % 2 == 0) {
					if (y % 2 == 0) {
						g.drawImage(towerBlock1, towerX, towerY, null);
					} else {
						g.drawImage(towerBlock2, towerX, towerY, null);
					}
					towerX += 32;
				} else {
					g.drawImage(towerBlock1, towerX - 16, towerY, null);
					towerX += 32;
				}
			}
			if (i % 2 == 0)
				g.drawImage(endBrick, towerX, towerY, null);
			else
				g.drawImage(towerBlock1, towerX - 16, towerY, null);
			towerY += 32;

		}

		

		// draw grass
		int grassXStart = 0, grassYStart = Game.HEIGHT - 55;
		for (int i = 0; i < 32; i++) {
			if (i % 2 == 0)
				g.drawImage(grass1, grassXStart, grassYStart, null);
			else
				g.drawImage(grass2, grassXStart, grassYStart, null);
			grassXStart += 32;
		}

		// draw top of tower
		towerX = towerXStart - 32;
		towerY = towerYStart - 32;

		for (int i = 0; i < 5; i++) {
			g.drawImage(towerBlock1, towerX, towerY, null);
			towerX += 64;
		}
	}
}
