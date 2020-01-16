import org.newdawn.slick.SlickException;

/**
 * The type Tree tiles.
 */
public class TreeTiles extends Tiles {

    /**
     * Instantiates a new Tree tiles.
     *
     * @param imageSrc the image src
     * @param x        the x
     * @param y        the y
     * @throws SlickException the slick exception
     */
    public TreeTiles(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    /**
     * method to render tree tiles
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * check contact sprite
     * @param other the other
     */
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
    }
}