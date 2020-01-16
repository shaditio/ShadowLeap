/** The code for the Bike Class */

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Bike.
 * @author Steven
 */
public class Bike extends Vehicles {

    /**
     * The constant BIKE_SPEED.
     */
    public static final float BIKE_SPEED = 0.2f;
    private int bikeDirection;

    /**
     * Instantiates a new Bike.
     *
     * @param imageSrc  the image source
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Bike(String imageSrc, float x, float y, boolean direction) throws SlickException {
        super(imageSrc, x, y, direction);
        this.setSpeed(BIKE_SPEED);
    }

    // Check if the bike sprite is still in screen
    @Override
    public boolean isInScreen(){
        return this.getX() > App.START_COORDINATE_X - this.getSpriteImage().getWidth() / 2
                && this.getX() < App.SCREEN_WIDTH + this.getSpriteImage().getWidth() / 2;
    }

    // Method to update the bikes location
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        if(this.getDirection()){
            bikeDirection = Sprite.RIGHT;
        }
        else{
            bikeDirection = Sprite.LEFT;
        }

        this.moveX(delta, this.bikeDirection);

        // If the bike goes out of screen, set X back to the most end (depending on the direction)
        if((this.getBoundingBox().getLeft() < 0) && !this.getDirection()){
            this.setDirection(!this.getDirection());
        } else if ((this.getBoundingBox().getRight() > App.SCREEN_WIDTH) && this.getDirection()){
            this.setDirection(!this.getDirection());
        }
    }

    // Method to render the sprite
    @Override
    public void render() {
        super.render();
    }

    // Method to make bikes move
    @Override
    public void moveX(int delta, int direction) {
        this.setX(this.getX() + delta * BIKE_SPEED * direction);
    }

}