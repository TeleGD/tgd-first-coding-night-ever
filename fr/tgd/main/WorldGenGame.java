package fr.tgd.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.tgd.menus.GOMenu;
import fr.tgd.menus.MainMenu;
import fr.tgd.world.Character;
import fr.tgd.world.WallGenerator;
import fr.tgd.world.World;

public class WorldGenGame extends BasicGameState {

	public static int ID = 1;

	private World world = new World();
	private WallGenerator gen = new WallGenerator(world);
	private Character character;

	private GameContainer container;
	StateBasedGame game;

	// ======================================================================================
	// DECLARATION DES CONSTANTES
	// ======================================================================================

	static public int hauteurW = 600, largeurW = 800;

	// ======================================================================================
	// DECLARATION DES VARIABLES
	// ======================================================================================

	// ======================================================================================
	// CREATEUR
	// ======================================================================================

	// ======================================================================================
	// FONCTION INIT
	// ======================================================================================

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.container = container;
		container.setShowFPS(false);
		character = new Character(world, 400d, 500d, 100, 0.3f, 10);
		this.game = game;
	}

	// ======================================================================================
	// FONCTION RENDER
	// ======================================================================================

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.translate((int) ((container.getWidth() - world.getW()) / 2), 0);
		world.render(g);
		character.render(g);
		g.translate(-(int) ((container.getWidth() - world.getW()) / 2), 0);

	}

	// ======================================================================================
	// FONCTION UPDATE
	// ======================================================================================

	int time = 0;

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		world.update(delta);
		character.update(delta);
		gen.update(delta);

	}

	// ======================================================================================
	// ENTREES CLAVIER
	// ======================================================================================

	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			container.exit();
		}
		switch (key) {
		case Input.KEY_LEFT:
			character.setMoving(false);
			break;
		case Input.KEY_RIGHT:
			character.setMoving(false);
			break;
		case Input.KEY_LSHIFT:
			character.setSpeedX(0.3f);
			character.setIsDash(false);
			break;

		case Input.KEY_S:
			break;
		}
	}

	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_LEFT:
			character.setMoving(true);
			character.setMovement(0);
			break;
		case Input.KEY_RIGHT:
			character.setMoving(true);
			character.setMovement(1);
			break;
		case Input.KEY_LSHIFT:
			if (character.getStamina() >= 50) {
				character.setSpeedX(0.9f);
				character.setIsDash(true);
			}
			break;

		case Input.KEY_S:
			game.enterState(GOMenu.ID);
			break;
		}
	}

	public int getID() {
		return ID;
	}
}
