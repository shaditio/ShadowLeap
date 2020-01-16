import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Movers.
 */
public abstract class Movers extends MovingObjects{

    /**
     * Instantiates a new Movers.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Movers(String imageSrc, float x, float y, boolean direction) throws SlickException{
        super(imageSrc, x, y, direction);
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

    @Override
    public boolean getDirection() {
        return super.getDirection();
    }
}