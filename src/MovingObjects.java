import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Moving objects.
 */
public abstract class MovingObjects extends Sprite{

    // Variables
    private boolean direction;

    /**
     * Instantiates a new Moving object.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public MovingObjects(String imageSrc, float x, float y, boolean direction) throws SlickException{
        super(imageSrc, x, y);
        this.direction = direction;
    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
    }

    @Override
    public void moveX(int delta, int direction) {
        super.moveX(delta, direction);
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public boolean getDirection() {
        return direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(boolean direction) {
        this.direction = direction;
    }
}
