import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Vehicles.
 */
public abstract class Vehicles extends MovingObjects{

    /**
     * Instantiates a new Vehicles.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Vehicles(String imageSrc, float x, float y, boolean direction) throws SlickException{
        super(imageSrc, x, y, direction);
    }

    /**
     * updating the vehicles
     * @param input
     * @param delta
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
    }

    /**
     * rendering the vehicles
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * method of contact sprite with the vehicles
     * @param other
     */
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
    }

    /**
     * moving the vehicles
     * @param delta
     * @param direction
     */
    @Override
    public void moveX(int delta, int direction) {
        super.moveX(delta, direction);
    }
}
