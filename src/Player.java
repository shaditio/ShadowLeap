import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import utilities.BoundingBox;


/**
 * The type Player.
 */
public class Player extends Sprite{

    /**
     * Variables in the Player Class
     */
    private int moveDelta = App.TILE_SIZE;
    private int noOfLives = 3;
    private Image livesImage = new Image("assets/lives.png");
    private int livesSeparation = 32;
    private float lifeX = 24;
    private float lifeY = 744;
    private boolean stillHasLives = true;
    private boolean dead = false;
    private int playerDirection;
    private boolean isMoving = false;
    private boolean isInBound = false;

    /**
     * Instantiates a new Player.
     *
     * @param imageSrc the image src
     * @param x        the x
     * @param y        the y
     * @throws SlickException the slick exception
     */
    public Player(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }


    /**
     * Method to update the player sprite if there is key input
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        // Store current x and y coordinates for checking sprite in screen later
        float previousX = this.getX();
        float previousY = this.getY();

        // If there is a direction input; also check if the next move is allowed
        if (input.isKeyPressed(Input.KEY_UP)) {
            if(isAllowedToMoveUp(this.getX(), this.getY())) {
                moveY(delta, Sprite.UP);
            }

        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            if(isAllowedToMoveDown(this.getX(), this.getY())) {
                moveY(delta, Sprite.DOWN);
            }

        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            if(isAllowedToMoveRight(this.getX(), this.getY())) {
                moveX(delta, Sprite.RIGHT);
            }

        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            if(isAllowedToMoveLeft(this.getX(), this.getY())) {
                moveX(delta, Sprite.LEFT);
            }

        }

        // to make player move in the Movers
        moveXMoving(delta, playerDirection);

        // to make player stationary if not in Movers
        this.setSpeed(0);

        // Check if dead
        if (dead){
            minusLife();
            dead = false;
            if (noOfLives <= 0) {
                stillHasLives = false;
            }
        }

        this.isInBound = false;
        // Revert back to original position if not in screen
        if (!isInScreen()){
            setX(previousX);
            setY(previousY);
            this.isInBound = true;
        }
    }

    /**
     * checking sprites that player has contacted
     * @param other
     */
    @Override
    public void contactSprite(Sprite other) {
        // If player collides with water and vehicles, minus life and reset position
        if (other instanceof Vehicles || other instanceof WaterTiles) {
            this.setCollided(false);
            other.setCollided(false);
            minusLife();
            resetPlayer();

        }

        // If player collides with Movers, make the player move with the movers
        if (other instanceof Movers) {
            this.setSpeed(other.getSpeed());

            if(((Movers) other).getDirection()){
                playerDirection = Sprite.RIGHT;
            }
            else{
                playerDirection = Sprite.LEFT;
            }
        }
        else {
            playerDirection = 0;
            this.setSpeed(0);
        }

        // If contact with ExtraLife, gain an extra life
        if (other instanceof ExtraLife){
            gainLife();
        }

        // Check if player still have lives
        if (noOfLives <= 0) {
            stillHasLives = false;
        }
    }


    /**
     * method to render the player
     */
    @Override
    public void render() {
        super.render();

        for(int i = 0; i < noOfLives; i++){
            livesImage.drawCentered(lifeX + (i * livesSeparation), lifeY);
        }
    }

    /**
     * Is allowed to move up boolean.
     *
     * @param currentX the current x
     * @param currentY the current y
     * @return the boolean
     */
    public boolean isAllowedToMoveUp(float currentX, float currentY) {
        float up = currentY - App.TILE_SIZE;
        BoundingBox upBB = new BoundingBox(currentX, up,
                             this.getBoundingBox().getWidth(), this.getBoundingBox().getHeight());

        // Check if the next movement will have solid sprites (bulldozers and trees)
        for (Movers mover : World.getMovers()) {
            if (mover instanceof Bulldozer) {
                if (upBB.intersects(mover.getBoundingBox())) {
                    return false;
                }
            }
        }

        for (Tiles tile : World.getTiles()) {
            if (tile instanceof TreeTiles) {
                if (upBB.intersects(tile.getBoundingBox())) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Is allowed to move down boolean.
     *
     * @param currentX the current x
     * @param currentY the current y
     * @return the boolean
     */
    public boolean isAllowedToMoveDown(float currentX, float currentY) {
        float down = currentY + App.TILE_SIZE;
        BoundingBox downBB = new BoundingBox(currentX, down, this.getBoundingBox().getWidth(), this.getBoundingBox().getHeight());

        // Check if the next movement will have solid sprites (bulldozers and trees)
        for (Movers mover : World.getMovers()) {
            if (mover instanceof Bulldozer) {
                if (downBB.intersects(mover.getBoundingBox())) {
                    return false;
                }
            }
        }

        for (Tiles tile : World.getTiles()) {
            if (tile instanceof TreeTiles) {
                if (downBB.intersects(tile.getBoundingBox())) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Is allowed to move left boolean.
     *
     * @param currentX the current x
     * @param currentY the current y
     * @return the boolean
     */
    public boolean isAllowedToMoveLeft(float currentX, float currentY) {
        float left = currentX - App.TILE_SIZE;
        BoundingBox leftBB = new BoundingBox(left, currentY, this.getBoundingBox().getWidth(), this.getBoundingBox().getHeight());

        // Check if the next movement will have solid sprites (bulldozers and trees)
        for (Movers mover : World.getMovers()) {
            if (mover instanceof Bulldozer) {
                if (leftBB.intersects(mover.getBoundingBox())) {
                    return false;
                }

            }
        }
        for (Tiles tile : World.getTiles()) {
            if (tile instanceof TreeTiles) {
                if (leftBB.intersects(tile.getBoundingBox())) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Is allowed to move right boolean.
     *
     * @param currentX the current x
     * @param currentY the current y
     * @return the boolean
     */
    public boolean isAllowedToMoveRight(float currentX, float currentY) {
        float right = currentX + App.TILE_SIZE;
        BoundingBox rightBB = new BoundingBox(right, currentY, this.getBoundingBox().getWidth(), this.getBoundingBox().getHeight());

        // Check if the next movement will have solid sprites (bulldozers and trees)
        for (Movers mover : World.getMovers()) {
            if (mover instanceof Bulldozer) {
                if (rightBB.intersects(mover.getBoundingBox())) {
                    return false;
                }
            }
        }

        for (Tiles tile : World.getTiles()) {
            if (tile instanceof TreeTiles) {
                if (rightBB.intersects(tile.getBoundingBox())) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * method to move the player within x axis
     * @param delta
     * @param direction
     */
    @Override
    public void moveX(int delta, int direction) {
        this.setX(this.getX() + moveDelta * direction);

    }

    /**
     * method to keep moving player for Movers.
     *
     * @param delta     the delta
     * @param direction the direction
     */
    public void moveXMoving(int delta, int direction){
        this.setX(this.getX() + delta * this.getSpeed() * playerDirection);
    }

    /**
     * method to move player in Y axis
     * @param delta
     * @param direction
     */
    @Override
    public void moveY(int delta, int direction) {
        this.setY(this.getY() + moveDelta * direction);
    }

    /**
     * Is alive boolean.
     *
     * @return the boolean
     */
    public boolean StillHasLives() {
        return stillHasLives;
    }

    /**
     * Reset player.
     */
    public void resetPlayer(){
        this.setX(World.PLAYER_START_X);
        this.setY(World.PLAYER_START_Y);
    }

    /**
     * Minus life.
     */
    public void minusLife(){
        noOfLives--;
    }

    /**
     * Gain life.
     */
    public void gainLife() {
        noOfLives++;
    }

    /**
     * Is in bound boolean for checking bulldozer collision with end of screen.
     *
     * @return the boolean
     */
    public boolean isInBound() {
        return isInBound;
    }

}
