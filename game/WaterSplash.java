import greenfoot.*;

/**
 * Write a description of class WaterSplash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WaterSplash extends Debris
{
    private static final Vector GRAVITY = new Vector(90, 1.5);
    private static final int FORCE = 15;
    /**
     * Act - do whatever the WaterSplash wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        increaseSpeed(GRAVITY);
        move();       
    }    
}
