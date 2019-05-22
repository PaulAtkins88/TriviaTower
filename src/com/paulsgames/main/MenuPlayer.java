package com.paulsgames.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.paulsgames.gfx.SpriteSheet;
import com.paulsgames.utils.GameObject;
import com.paulsgames.utils.Handler;

public class MenuPlayer extends GameObject{
	Handler handler;
	private BufferedImage player;
	
	public MenuPlayer(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		player = ss.grabImage(1, 1, 32, 32);
		velX = 3;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		if (y <= 0 || y >= Game.HEIGHT - 32) 
			velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 32) 
			velX *= -1;		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(player,(int)x,(int)y,null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
