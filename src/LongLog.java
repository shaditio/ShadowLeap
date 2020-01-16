import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Long log.
 */
public class LongLog extends Movers{
    /**
     * The constant LONG_LOG_SPEED.
     */
    public static final float LONG_LOG_SPEED = 0.07f;

    // Direction of the long log
    private int longLogDirection;

    /**
     * Instantiates a new Long log.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public LongLog(String imageSrc, float x, float y, boolean direction) throws SlickException{
        super(imageSrc, x, y, direction);
        this.setSpeed(LONG_LOG_SPEED);
    }

    /**
     * Updating the long log
     * @param input
     * @param delta
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        if(this.getDirection()){
            longLogDirection = Sprite.RIGHT;
        }
        else{
            longLogDirection = Sprite.LEFT;
        }

        this.moveX(delta, this.longLogDirection);

        // If the long log goes out of screen, set X back to the most end (depending on the direction)
        if(!isInScreen()){
            if(this.longLogDirection == -1){
                this.setX(App.SCREEN_WIDTH + this.getSpriteImage().getWidth() / 2);
            }
            else{
                this.setX(App.START_COORDINATE_X - this.getSpriteImage().getWidth() / 2);
            }
        }
    }

    /**
     * rendering long log
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * actions done to other sprite if it comes contact to the long log
     * @param other
     */
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);

        // Set other speed to match the long log
        other.setSpeed(LONG_LOG_SPEED);
    }

    /**
     * method to move the long log
     * @param delta
     * @param direction
     */
    @Override
    public void moveX(int delta, int direction) {
        this.setX(this.getX() + delta * LONG_LOG_SPEED * direction);
    }
}