import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.Mover;
import utilities.BoundingBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type World.
 */
public class World {
    /**
     * The constant PLAYER_START_X.
     */
	public static final int PLAYER_START_X = 512;
    /**
     * The constant PLAYER_START_Y.
     */
    public static final int PLAYER_START_Y = 720;
    /**
     * The constant FINISH_TILE_X_START.
     */
    public static final int FINISH_TILE_X_START = 120;
    /**
     * The constant FINISH_TILE_Y_START.
     */
    public static final int FINISH_TILE_Y_START = 48;
    /**
     * The constant FINISH_TILE_SEPARATION.
     */
    public static final int FINISH_TILE_SEPARATION = 192;

    // Variables to initialise classes
    private static ArrayList<Vehicles> vehicles;
    private static ArrayList<Movers> movers;
	private static ArrayList<Tiles> tiles;
	private static ArrayList<Integer> logIndexList;
    private Player player;
    private ExtraLife extraLife;

	// Variables regarding levels
    private int level = 0;
    private static final int WIN_CONDITION = 5;
    private int finishCount = 0;
    private int worldTime;
    private boolean waterTileIsSafe = false;
    private int totalLevel = 1;

    // Variables regarding the extra life
    private int lifeSpawnTime = 0;
    private int lifeDurationTimer = 0;
    private int lifeMoveTimer = 0;
    private boolean lifeMoveRight = true;
    private boolean lifeSpawned = false;


    /**
     * Instantiates a new World.
     *
     * @throws SlickException the slick exception
     * @throws IOException    the io exception
     */
    public World() throws SlickException, IOException {
		// Perform initialisation logic
        worldTime = 0;
		vehicles = new ArrayList<>();
		movers = new ArrayList<>();
        tiles = new ArrayList<>();
        logIndexList = new ArrayList<>();
        player = new Player("assets/frog.png", PLAYER_START_X, PLAYER_START_Y);

        initialiseFinishTile(FINISH_TILE_X_START, FINISH_TILE_Y_START, FINISH_TILE_SEPARATION);

		LevelReader.initialiseLevel(this, "assets/levels/"+ level + ".lvl");

		// Initialising the logIndexList
        int count = 0;
		for(Movers mover : movers){
		    if(mover instanceof ShortLog || mover instanceof LongLog){
		        logIndexList.add(count);
            }
            count++;
        }

	}

    /**
     * Updating the world.
     *
     * @param input the input
     * @param delta the delta
     * @throws IOException    the io exception
     * @throws SlickException the slick exception
     */
    public void update(Input input, int delta) throws IOException, SlickException {
        // Time
        worldTime += delta;
        lifeSpawnTime += delta;

        // Update player sprite
        player.update(input, delta);

        /**
         * Vehicle collisions
         */

        // Update player sprite if there is collision with vehicles
        for (Vehicles vehicle : vehicles) {
            vehicle.update(input, delta);

            if (player.getBoundingBox().intersects(vehicle.getBoundingBox())) {
                player.contactSprite(vehicle);
            }

        }

        /**
         * Movers collisions
         */

        // Update player sprite if there is collision with movers
        for (Movers mover : movers) {
            mover.update(input, delta);
            if (player.getBoundingBox().intersects(mover.getBoundingBox())) {
                player.contactSprite(mover);

                // If on movers, water tiles are safe
                waterTileIsSafe = true;

                // Check if bulldozer has pushed player to edge of screen
                if ((mover instanceof Bulldozer) && (player.isInBound())) {
                    player.resetPlayer();
                    player.minusLife();
                }

                // CHeck if player is still standing on turtles that are in water
                if ((mover instanceof Turtles) && (((Turtles) mover).isInWater())) {
                    player.resetPlayer();
                    player.minusLife();
                }
            }
        }

        // If not on movers anymore
        if (!waterTileIsSafe) {
            // Update player if there is collision with water tiles
            for (Tiles tile : tiles) {
                if (tile instanceof WaterTiles) {
                    if (player.getBoundingBox().intersects(tile.getBoundingBox())) {
                        player.contactSprite(tile);
                        break;
                    }
                }
            }
        }

        /**
         * Tiles collisions
         */
        // Update player sprite if there is collision with tiles
        for(Tiles tile: tiles){

            // Check if player contacts the finish tile
            if(tile instanceof FinishTiles){
                if(player.getBoundingBox().intersects(tile.getBoundingBox())){
                    tile.contactSprite(player);
                    player.resetPlayer();
                    finishCount ++;
                }
            }

            // Modifying the waterTileIsSafe when on grass or finish tile
            if(tile instanceof GrassTiles || tile instanceof FinishTiles){
                waterTileIsSafe = false;
            }
        }

        /**
         * Win conditions and level loading
         */
        // Winning the level
        if(finishCount == WIN_CONDITION){

            // Go the next level
            level++;

            // Check if already at the last level
            if(level <= totalLevel) {

                // Reset the world if there is a new level to be loaded
                finishCount = 0;
                this.resetWorld();
                LevelReader.initialiseLevel(this, "assets/levels/" + level + ".lvl");

                // Initialising the logIndexList
                int count = 0;
                for(Movers mover : movers){
                    if(mover instanceof ShortLog || mover instanceof LongLog){
                        logIndexList.add(count);
                    }
                    count++;
                }
            }
            else{
                App.gameOver = true;
            }
        }

        // Also check if player still has lives
        else if(!player.StillHasLives()){
            App.gameOver = true;
        }


        /**
         * Extra Life collisions
         */
        // ExtraLife is spawned when reached a random time
        if(lifeSpawnTime >= ExtraLife.getRandomSpawnTime()) {

            // Timer for lasting duration and moving
            lifeDurationTimer += delta;
            lifeMoveTimer += delta;


            // Check if the extra life has been spawned
            if(!lifeSpawned) {
                lifeSpawned = true;

                // Choose random log
                Movers randomLog = movers.get(logIndexList.get(new Random().nextInt(logIndexList.size())));

                // initialise the life
                this.extraLife = new ExtraLife("assets/extralife.png", randomLog.getX(),
                        randomLog.getY(), randomLog, lifeMoveRight);
            }

            // Updating the extra life
            extraLife.update(input,delta);

            // Check if the extra life should move
            if (lifeMoveTimer >= ExtraLife.LIFE_MOVE_DURATION) {
                lifeMoveTimer = 0;
                extraLife.move(input, delta);
            }

            // If player contacts with the extra life, reset the life times
            if(player.getBoundingBox().intersects(extraLife.getBoundingBox())){
                player.contactSprite(extraLife);
                resetLifeTime();
            }

            // If the extra life expires, reset the life times
            if(lifeDurationTimer >= ExtraLife.LIFE_LAST_DURATION){
                resetLifeTime();
            }
        }
	}

    /**
     * Rendering the world
     *
     * @param g the graphics
     */
    public void render(Graphics g){

        // Draw all vehicles
        for (Vehicles vehicle : vehicles) {
            vehicle.render();
        }

        // Draw all tiles
        for (Tiles tile : tiles){
            if(tile instanceof FinishTiles){

                // Check if the finish tiles are already occupied so that it wouldn't be rendered
                if(((FinishTiles) tile).isOccupied()) {
                    tile.render();
                }
            }
            else {
                tile.render();
            }
        }

        // Draw all Mover sprites
        for (Movers mover : movers){
            mover.render();
        }

        // If the extra life is spawned, render it
        if(lifeSpawned){
            extraLife.render();
        }

        // Render the player sprite
        player.render();

    }

    /**
     * Initialise finish tile.
     *
     * @param startX         the start x
     * @param startY         the start y
     * @param separationTile the separation tile
     * @throws SlickException the slick exception
     */
    public void initialiseFinishTile(int startX, int startY, int separationTile) throws SlickException{
        for(int i = startX; i < App.SCREEN_WIDTH; i += separationTile){
           FinishTiles finishTile = new FinishTiles(i, startY, FinishTiles.finishWidth, FinishTiles.finishHeight);
           tiles.add(finishTile);
        }
    }

    /**
     * Resetting the world.
     *
     * @throws SlickException the slick exception
     */
    public void resetWorld() throws SlickException {
        // Variables that need to be reset
        worldTime = 0;
        vehicles = new ArrayList<>();
        movers = new ArrayList<>();
        tiles = new ArrayList<>();
        logIndexList = new ArrayList<>();
        player = new Player("assets/frog.png", PLAYER_START_X, PLAYER_START_Y);

        // Reinitialise finish tiles
        initialiseFinishTile(FINISH_TILE_X_START, FINISH_TILE_Y_START, FINISH_TILE_SEPARATION);

        // Need to reset extra lives time
        resetLifeTime();
    }

    /**
     * Gets vehicles.
     *
     * @return the vehicles
     */
    public static ArrayList<Vehicles> getVehicles() {
        return vehicles;
    }

    /**
     * Gets movers.
     *
     * @return the movers
     */
    public static ArrayList<Movers> getMovers() {
        return movers;
    }

    /**
     * Gets tiles.
     *
     * @return the tiles
     */
    public static ArrayList<Tiles> getTiles() {
        return tiles;
    }

    /**
     * Reset life time.
     */
    public void resetLifeTime(){
        lifeSpawnTime = 0;
        lifeDurationTimer = 0;
        lifeMoveTimer = 0;
        lifeSpawned = false;
    }
}
