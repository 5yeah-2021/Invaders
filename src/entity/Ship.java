package entity;

import java.awt.Color;
import java.util.Set;

import engine.Cooldown;
import engine.Core;
import music.Music;
import engine.DrawManager.SpriteType;

/**
 * Implements a ship, to be controlled by the player.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class Ship extends Entity {

	/** Time between shots. */
	private static final int SHOOTING_INTERVAL = 750;
	/** Speed of the bullets shot by the ship. */
	private static int BULLET_SPEED = -6;
	/** Movement of the ship for each unit of time. */
	private static final int SPEED = 2;
	private static int manybullet = 1;
	
	/** Minimum time between shots. */
	private Cooldown shootingCooldown;
	/** Time spent inactive between hits. */
	private Cooldown destructionCooldown;
	private int level;
	private Color color;

	/**
	 * Constructor, establishes the ship's properties.
	 * 
	 * @param positionX
	 *            Initial position of the ship in the X axis.
	 * @param positionY
	 *            Initial position of the ship in the Y axis.
	 */
	public Ship(int levell, final int positionX, final int positionY) {
		super(positionX, positionY, 13 * 2, 8 * 2, Color.GREEN);

		level = levell;

		/**레벨에 맞는 spritetype지정 */
		if (level == 1 ) {this.spriteType = SpriteType.Ship;}
		else if (level == 2){this.spriteType = SpriteType.Ship2;}
		else if (level == 3 ){this.spriteType = SpriteType.Ship3;}
		else if (level == 4 ){this.spriteType = SpriteType.Ship4;}
		else if (level == 5 ){this.spriteType = SpriteType.Ship5;}
		else if (level == 6 ){this.spriteType = SpriteType.Ship6;}
		else if (level == 7 ){this.spriteType = SpriteType.Ship7;}
		this.shootingCooldown = Core.getCooldown(SHOOTING_INTERVAL);
		this.destructionCooldown = Core.getCooldown(1000);
	}

	/**
	 * Moves the ship speed uni ts right, or until the right screen border is
	 * reached.
	 */
	public final void moveRight() {
		this.positionX += SPEED;
	}

	/**
	 * Moves the ship speed units left, or until the left screen border is
	 * reached.
	 */
	public final void moveLeft() {
		this.positionX -= SPEED;
	}

	/**
	 * Shoots a bullet upwards.
	 * 
	 * @param bullets
	 *            List of bullets on screen, to add the new bullet.
	 * @return Checks if the bullet was shot correctly.
	 */
	public final boolean shoot(final Set<Bullet> bullets) {
		if (this.shootingCooldown.checkFinished()) {
			this.shootingCooldown.reset();
			for (int i = 0; i < manybullet; i++) {
				if (i % 2 == 0) {
					bullets.add(BulletPool.getBullet(positionX + this.width / 2 + i * 5,
					positionY, BULLET_SPEED));
				}
				else {
					bullets.add(BulletPool.getBullet(positionX + this.width / 2 - (i+1) * 5,
					positionY, BULLET_SPEED));
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Updates status of the ship.
	 */
	public final void update() {
		if (!this.destructionCooldown.checkFinished())
			this.spriteType = SpriteType.ShipDestroyed;
		else
		/**레벨에 맞는 spritetype지정 */
			if (level == 1 ) {this.spriteType = SpriteType.Ship;}
			else if (level == 2){this.spriteType = SpriteType.Ship2;}
			else if (level == 3){this.spriteType = SpriteType.Ship3;}
			else if (level == 4){this.spriteType = SpriteType.Ship4;}
			else if (level == 5){this.spriteType = SpriteType.Ship5;}
			else if (level == 6){this.spriteType = SpriteType.Ship6;}
			else if (level == 7){this.spriteType = SpriteType.Ship7;}
	}

	/**
	 * Switches the ship to its destroyed state.
	 */
	public final void destroy() {
		this.destructionCooldown.reset();
		
		Music effect2 = new Music ("effect2.mp3",false);
		effect2.start();
	}

	/**
	 * Checks if the ship is destroyed.
	 * 
	 * @return True if the ship is currently destroyed.
	 */
	public final boolean isDestroyed() {
		return !this.destructionCooldown.checkFinished();
	}

	/**
	 * Getter for the ship's speed.
	 * 
	 * @return Speed of the ship.
	 */
	public final int getSpeed() {
		return SPEED;
	}
	
	public static void setmanybullet() {
		if (manybullet < 5) {
			manybullet += 1;
		}
	}
	public static void setBULLET_SPEED() {
		if (BULLET_SPEED > -18) {
			BULLET_SPEED -= 1;
		}
	}
}
