package fr.tgd.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class Wall extends Entity {
	private double w, h;
	

	public Wall(double x, double y, double w , double h) {
		super(x, y);
		this.w = w;
		this.h = h;
	}

	@Override
	public void update() {
		setY(getY()+1);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)getX(), (int)getY(), (int)w, (int)h);
	}
	
	
}


