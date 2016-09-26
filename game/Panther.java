import greenfoot.*;

/**
 * Write a description of class Panther here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Panther extends Tank
{
    private static final int NUM_FRAGMENTS = 10;
    public void next(int d)
    {
        if(waterAhead())
        {
            Greenfoot.playSound("Splash.wav");
            java.util.List<ScoreManager> l = getWorld().getObjects(ScoreManager.class); 
            ScoreManager scoreManager = l.get(0);
            scoreManager.updateHiScore();
            spawnGameOverDialog();
            explode();
            exists = false;
        }
         
        else if(!obstactleAhead())
            move(4);
            
        
        if(!turnAllowed)
            if(turnReload != 0)
                turnReload--;
            else
                turnAllowed = true;
    }
    
    public void checkResponse()
    {
        if(Greenfoot.isKeyDown("space"))
            shoot();
        if(Greenfoot.isKeyDown("right") && turnAllowed && getRotation() != 0)
        {
            setRotation(0);
            turnReload = 15;
            turnAllowed = false;
        }
        if(Greenfoot.isKeyDown("left") && turnAllowed && getRotation() != 180)
        {         
            setRotation(180);
            turnReload = 15;
            turnAllowed = false;
        }
        if(Greenfoot.isKeyDown("up") && turnAllowed && getRotation() != 270)
        {         
            setRotation(270);
            turnReload = 15;
            turnAllowed = false;
        }
        if(Greenfoot.isKeyDown("down") && turnAllowed && getRotation() != 90)
        {         
            setRotation(90);
            turnReload = 15;
            turnAllowed = false;
        }
    }
    
    public void spawnGameOverDialog()
    {
        World w = getWorld();
        java.util.List<Counter> cs = w.getObjects(Counter.class);
        Counter c = cs.get(0);
        getWorld().showText("Game Over \n Score: " + c.getValue(), 550, 300);
    }
    
    public void crashResponse()
    {
        super.crashResponse();
    }
    
    public void explode()
    {
        placeDebris (getX(), getY(), NUM_FRAGMENTS);
        getWorld().removeObject(this);
    }
    
    private void placeDebris(int x, int y, int numFragments)
    {
        for (int i=0; i < numFragments; i++) {
            getWorld().addObject ( new WaterSplash(), x, y );
        }
    }
}
