package com.paulsgames.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.paulsgames.gfx.SpriteSheet;
import com.paulsgames.utils.GameObject;
import com.paulsgames.utils.Handler;

public class Player extends GameObject{
	Handler handler;
	private BufferedImage player_image1,player_image2;
	
	public Player(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		player_image1 = ss.grabImage(1, 1, 32, 32);
		player_image2 = ss.grabImage(2, 1, 32, 32);
	}

	@Override
	public void tick() {
		this.x += velX;
		this.y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 46);
		y = Game.clamp(y, 0, Game.HEIGHT - 72);
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(player_image1,(int)x,(int)y,null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
