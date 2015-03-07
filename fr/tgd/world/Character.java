package fr.tgd.world;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Character extends Circle  {
	protected int stamina;
	protected float speedX;
	protected int movement;
	protected boolean isMoving;
	public Character(double x, double y, int stamina, float speedX, int radius ) {
		super(x, y,radius);
		this.stamina = stamina;
		this.speedX = speedX;
	}
	public int getStamina() {
		return stamina;
	}
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	public float getSpeedX() {
		return speedX;
	}
	public void setSpeedX(float speed) {
		this.speedX = speed;
	}
	
	public void movement(int delta) {
		if(isMoving){
			switch(movement) {
			case 0 :
				x-=speedX*delta;
				//if (Collisions.collisionCircleAnyRect(this) && this.x>=200){x+=speedX*delta;}
				break;
			case 1 : 
				x+=speedX*delta;
				//if(Collisions.collisionCircleAnyRect(this) && this.x<=600){x-=speedX*delta;}
				break;
		}
		}
	}
	
		
	
	
	public void update(int delta) {
	movement(delta);	
	}

	public void render(Graphics g) {
		g.drawOval((float) x, (float) y, 10, 10);
	}
	
	
	
	
}