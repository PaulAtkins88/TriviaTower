package com.paulsgames.utils;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {

	public ArrayList<GameObject> object = new ArrayList<GameObject>();

	public void tick() {
		// turn on the tick method for every game object... Every one player, monsters,
		// coins etc
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	

	public void render(Graphics g) {
		// turn on the render function for every game object , like above. same thing.
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}

	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}