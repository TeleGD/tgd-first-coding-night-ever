package fr.tgd.world;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class World {
	static ArrayList<Wall> walls = new ArrayList<Wall>();

	private double w = 500;

	public void addWall(Wall wall) {
		walls.add(wall);
	}

	public void removeWall(Wall wall) {
		walls.remove(wall);
	}

	public double getW() {
		return w;
	}

	public void render(Graphics g) {
		for (Wall wall : walls) {
			wall.render(g);
		}
	}

	public void update(int delta) {
		for (Wall wall : walls) {
			wall.update(delta);
		}
	}

	public static ArrayList<Wall> getWalls() {
		return walls;
	}

	public Wall getCollidingWall(Circle c) {
		for (Wall wall : walls) {
			return wall;
		}
		return null;
	}
}
