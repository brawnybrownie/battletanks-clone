import greenfoot.*;

/**
 * Write a description of class Brick here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Brick extends Impermissive
{
    private static final int NUM_FRAGMENTS = 10;
    /**
     * Act - do whatever the Brick wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void explode()
    {
        placeDebris (getX(), getY(), NUM_FRAGMENTS);
        getWorld().removeObject(this);
    }
    
    private void placeDebris(int x, int y, int numFragments)
    {
        for (int i=0; i < numFragments; i++) {
            getWorld().addObject ( new Debris(), x, y );
        }
    }
}
