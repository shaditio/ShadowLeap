/** The code for the Bus Class */

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Bus.
 */
public class Bus extends Vehicles {

    /**
     * The constant BUS_SPEED.
     * @author Steven
     */
    public static final float BUS_SPEED = 0.15f;
    private int busDirection;

    /**
     * Instantiates a new Bus.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */

    // Constructor for the Bus Class
    public Bus(String imageSrc, float x, float y, boolean direction) throws SlickException {
        super(imageSrc, x, y, direction);
        this.setSpeed(BUS_SPEED);
    }

    /**
     * Check if Bus is still in screen
     *
     */
    @Override
    public boolean isInScreen(){
        return this.getX() > App.START_COORDINATE_X - this.getSpriteImage().getWidth() / 2
                && this.getX() < App.SCREEN_WIDTH + this.getSpriteImage().getWidth() / 2;
    }

    /**
     * Method to update buses location
     * @param input
     * @param delta
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        if(this.getDirection()){
            busDirection = Sprite.RIGHT;
        }
        else{
            busDirection = Sprite.LEFT;
        }

        this.moveX(delta, this.busDirection);

        // If the bus goes out of screen, set X back to the most end (depending on the direction)
        if(!isInScreen()){
            if(this.busDirection == Sprite.LEFT){
                this.setX(App.SCREEN_WIDTH + this.getSpriteImage().getWidth() / 2);
            }
            else{
                this.setX(App.START_COORDINATE_X - this.getSpriteImage().getWidth() / 2);
            }
        }
    }

    /**
     * Method to render the buses
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * Method to make the buses move
     * @param delta
     * @param direction
     */
    @Override
    public void moveX(int delta, int direction) {
        this.setX(this.getX() + delta * BUS_SPEED * direction);
    }

}
