import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Turtles.
 */
public class Turtles extends Movers{
    /**
     * The constant TURTLES_SPEED.
     */
    public static final float TURTLES_SPEED = 0.085f;

    // turtles direction
    private int turtleDirection;

    /**
     * The constant turtlesSurfaceTime.
     */
    public static final int turtlesSurfaceTime = 7000;
    /**
     * The constant turtlesUnderwaterTime.
     */
    public static final int turtlesUnderwaterTime = 2000;

    // Variable to make turtles in water and reappear later
    private int turtleTime = 0;
    private boolean isInWater = false;


    /**
     * Instantiates a new Turtles.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Turtles(String imageSrc, float x, float y, boolean direction) throws SlickException{
        super(imageSrc, x, y, direction);
        this.setSpeed(TURTLES_SPEED);
    }

    /**
     * updating turtles
     * @param input
     * @param delta
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        // start time to check if turtle should go in water later
        turtleTime += delta;

        if(this.getDirection()){
            turtleDirection = Sprite.RIGHT;
        }
        else{
            turtleDirection = Sprite.LEFT;
        }

        this.moveX(delta, this.turtleDirection);

        // Check if turtles are in screen
        if(!isInScreen()){
            if(this.turtleDirection == -1){
                this.setX(App.SCREEN_WIDTH + this.getSpriteImage().getWidth() / 2);
            }
            else{
                this.setX(App.START_COORDINATE_X - this.getSpriteImage().getWidth() / 2);
            }
        }
    }

    /**
     * rendering the turtles
     */
    @Override
    public void render() {
        // Check if it's time for turtles to go in water / reappear
        if((turtleTime >= turtlesSurfaceTime) && (!isInWater)){
            isInWater = true;
            turtleTime = 0;
        }
        else if((turtleTime >= turtlesUnderwaterTime) && (isInWater)){
            isInWater = false;
            turtleTime = 0;
        }

        // if turtles are not in water, render it
        if(!isInWater){
            super.render();
        }

    }

    /**
     * check contact sprite with turtles
     * @param other
     */
    @Override
    public void contactSprite(Sprite other) {

        other.setSpeed(TURTLES_SPEED);
    }

    /**
     * method to move turtles
     * @param delta
     * @param direction
     */
    @Override
    public void moveX(int delta, int direction) {
        this.setX(this.getX() + delta * TURTLES_SPEED * direction);
    }

    /**
     * Is in water boolean.
     *
     * @return the boolean
     */
    public boolean isInWater() {
        return isInWater;
    }

}
