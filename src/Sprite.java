/** The code for the Sprite Class */

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * The type Sprite.
 */
public class Sprite {
	/**
	 * The constant UP.
	 */
	public static final int UP = -1;
	/**
	 * The constant DOWN.
	 */
	public static final int DOWN = 1;
	/**
	 * The constant LEFT.
	 */
	public static final int LEFT = -1;
	/**
	 * The constant RIGHT.
	 */
	public static final int RIGHT = 1;

	// Variables for the sprite class
	private float x;
	private float y;
	private BoundingBox boundingBox;
	private Image spriteImage;
	private float speed;
	private boolean isCollided = false;
	private int width;
	private int height;

	/**
	 * Instantiates a new Sprite.
	 *
	 * @param imageSrc the image src
	 * @param x        the x
	 * @param y        the y
	 * @throws SlickException the slick exception
	 */
	public Sprite(String imageSrc, float x, float y) throws SlickException {
		this.x = x;
		this.y = y;
		this.spriteImage = new Image(imageSrc);
		this.boundingBox = new BoundingBox(spriteImage, x, y);
    }

	/**
	 * Instantiates a new Sprite.
	 *
	 * @param x      the x
	 * @param y      the y
	 * @param width  the width
	 * @param height the height
	 */
	public Sprite(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.boundingBox = new BoundingBox(x, y, width, height);
	}

	/**
	 * Updating the sprite with bounding box
	 *
	 * @param input the input
	 * @param delta the delta
	 */
	public void update(Input input, int delta) {
		this.boundingBox.setX(this.x);
		this.boundingBox.setY(this.y);
	}

	/**
	 * Rendering the sprite
	 */
	public void render() {
		spriteImage.drawCentered(x, y);
	}

	/**
	 * method to check if bounding boxes intersects
	 *
	 * @param other the other
	 */
	public void contactSprite(Sprite other) {
        if(this.boundingBox.intersects(other.boundingBox)){
			this.isCollided = true;
			other.isCollided = true;
        }
	}

	/**
	 * Is in screen boolean.
	 *
	 * @return the boolean
	 */
	public boolean isInScreen(){
		if(this instanceof Player){
			return this.x > 0 && this.y > 0
					&& this.x <= App.SCREEN_WIDTH - this.getSpriteImage().getWidth() / 2
					&& this.y <= App.SCREEN_HEIGHT - this.getSpriteImage().getHeight() / 2;
		}
		else {
			return this.x + this.getSpriteImage().getWidth() / 2 > 0 && this.y > 0 + this.getSpriteImage().getHeight()
					&& this.x <= App.SCREEN_WIDTH + this.getSpriteImage().getWidth() / 2
					&& this.y <= App.SCREEN_HEIGHT + this.getSpriteImage().getHeight() / 2;
		}
	}


	/**
	 * Gets sprite image.
	 *
	 * @return the sprite image
	 */
	public Image getSpriteImage() {
		return spriteImage;
	}

	/**
	 * Gets bounding box.
	 *
	 * @return the bounding box
	 */
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	/**
	 * Sets bounding box.
	 *
	 * @param boundingBox the bounding box
	 */
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}

	/**
	 * Sets x.
	 *
	 * @param x the x
	 */
// Update X coordinate
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Sets y.
	 *
	 * @param y the y
	 */
// Update Y coordinate
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Gets x.
	 *
	 * @return the x
	 */
// Get the X coordinates
	public float getX() {
		return this.x;
	}

	/**
	 * Gets y.
	 *
	 * @return the y
	 */
// Get the Y coordinates
	public float getY() {
		return this.y;
	}

	/**
	 * Gets speed.
	 *
	 * @return the speed
	 */
// Get the move speed for sprites
	public float getSpeed() {
		return speed;
	}

	/**
	 * Sets speed.
	 *
	 * @param speed the speed
	 */
// Set move speed for sprites
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * Move x.
	 *
	 * @param delta     the delta
	 * @param direction the direction
	 */
	public void moveX(int delta, int direction){
		this.x += delta * getSpeed() * direction;
	}

	/**
	 * Move y.
	 *
	 * @param delta     the delta
	 * @param direction the direction
	 */
	public void moveY(int delta, int direction){
		this.y += delta * getSpeed() * direction;
	}

	/**
	 * Is collided boolean.
	 *
	 * @return the boolean
	 */
	public boolean isCollided() {
		return isCollided;
	}

	/**
	 * Sets collided.
	 *
	 * @param collided the collided
	 */
	public void setCollided(boolean collided) {
		isCollided = collided;
	}

	/**
	 * Sets sprite image.
	 *
	 * @param spriteImage the sprite image
	 */
	public void setSpriteImage(Image spriteImage) {
		this.spriteImage = spriteImage;
	}
}
