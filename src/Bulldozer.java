import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Bulldozer.
 * @author Steven
 */
public class Bulldozer extends Movers{

    /**
     * The constant BULLDOZER_SPEED.
     */
    public static final float BULLDOZER_SPEED = 0.05f;
    private int bulldozerDirection;

    /**
     * Instantiates a new Bulldozer.
     *
     * @param imageSrc  the image source
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Bulldozer(String imageSrc, float x, float y, boolean direction) throws SlickException{
        super(imageSrc, x, y, direction);
        this.setSpeed(BULLDOZER_SPEED);
    }

    /**
     * Updating the Bulldozer positions
     * @param input
     * @param delta
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        // Get the direction of the Bulldozer
        if(this.getDirection()){
            bulldozerDirection = Sprite.RIGHT;
        }
        else{
            bulldozerDirection = Sprite.LEFT;
        }

        this.moveX(delta, this.bulldozerDirection);

        // Check the bulldozer is still in screen
        if(!isInScreen()){
            if(this.bulldozerDirection == Sprite.LEFT){
                this.setX(App.SCREEN_WIDTH + this.getSpriteImage().getWidth() / 2);
            }
            else{
                this.setX(App.START_COORDINATE_X - this.getSpriteImage().getWidth() / 2);
            }
        }
    }

    /**
     * rendering the Bulldozer
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * actions done if a sprite comes to contact with Bulldozer
     * @param other
     */
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);

        // Make the other sprite move with the bulldozer
        other.setSpeed(BULLDOZER_SPEED);
    }

    /**
     * method to move the Bulldozer
     * @param delta
     * @param direction
     */
    @Override
    public void moveX(int delta, int direction) {
        this.setX(this.getX() + delta * BULLDOZER_SPEED * direction);
    }
}