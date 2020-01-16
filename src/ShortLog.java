import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Short log.
 */
public class ShortLog extends Movers{
    /**
     * The constant SHORT_LOG_SPEED.
     */
    public static final float SHORT_LOG_SPEED = 0.1f;
    private int shortLogDirection;

    /**
     * Instantiates a new Short log.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public ShortLog(String imageSrc, float x, float y, boolean direction) throws SlickException{
        super(imageSrc, x, y, direction);
        this.setSpeed(SHORT_LOG_SPEED);
    }

    /**
     * method to update the short log positions
     * @param input
     * @param delta
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        if(this.getDirection()){
            shortLogDirection = Sprite.RIGHT;
        }
        else{
            shortLogDirection = Sprite.LEFT;
        }

        this.moveX(delta, this.shortLogDirection);

        // Check if the short log is in screen
        if(!isInScreen()){
            if(this.shortLogDirection == Sprite.LEFT){
                this.setX(App.SCREEN_WIDTH + this.getSpriteImage().getWidth() / 2);
            }
            else{
                this.setX(App.START_COORDINATE_X - this.getSpriteImage().getWidth() / 2);
            }
        }
    }

    /**
     * method to render short logs
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * set speed of other sprite to match this speed
     * @param other
     */
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);

        other.setSpeed(SHORT_LOG_SPEED);
    }

    /**
     * method to move the logs
     * @param delta
     * @param direction
     */
    @Override
    public void moveX(int delta, int direction) {
        this.setX(this.getX() + delta * SHORT_LOG_SPEED * direction);
    }
}
