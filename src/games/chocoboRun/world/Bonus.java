package games.chocoboRun.world;

import org.newdawn.slick.Graphics;

import games.chocoboRun.World;
import games.chocoboRun.util.Collisions;
import games.chocoboRun.util.Timer;

public abstract class Bonus extends Circle {

	Timer timer = new Timer();
	public Bonus(World world, double x, double y, int radius) {
		super(world, x, y, radius);
	}


	public void update(int delta){
		setY(getY()+this.world.character.wallSpeed*delta);

		if(timer.getTime()>5000 && !Collisions.collisionCircleAnyRect(this.world.character, this.world.map)){
			this.world.character.setInvincible(false);
			timer.stop();
		}

	}

	public abstract void render(Graphics g);

	public void used(){
		timer.start();
	}





}
