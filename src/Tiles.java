import org.newdawn.slick.SlickException;

/**
 * The type Tiles.
 */
public abstract class Tiles extends Sprite{

    /**
     * Instantiates a new Tiles.
     *
     * @param imageSrc the image src
     * @param x        the x
     * @param y        the y
     * @throws SlickException the slick exception
     */
    public Tiles(String imageSrc, float x, float y) throws SlickException{
        super(imageSrc, x, y);
    }

    /**
     * Instantiates a new Tiles.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @throws SlickException the slick exception
     */
    public Tiles(float x, float y, int width, int height) throws SlickException{
        super(x, y, width, height);
    }

    /**
     * method to render Tiles
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * method to check contact sprites
     * @param other the other
     */
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
    }
}
