import greenfoot.*;
/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Active
{
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private Class createrId;
    
    public Bullet(Class id)
    {
        this.createrId = id;
    }
    public void act()
    {
        move(8);
        react();
    }    
    
    public void react()
    {
        if(isTouching(Panther.class) && createrId == Panzer.class)
        {
            java.util.List<Panther> panthers = getWorld().getObjects(Panther.class);
            Panther p = panthers.get(0);
            p.spawnGameOverDialog();
            removeTouching(Panther.class);
            java.util.List<ScoreManager> l = getWorld().getObjects(ScoreManager.class); 
            ScoreManager scoreManager = l.get(0);
            scoreManager.updateHiScore();
            blast();
        }
        else if(isTouching(Panzer.class) && createrId == Panther.class)
        {
            removeTouching(Panzer.class);
            java.util.List<ScoreManager> l = getWorld().getObjects(ScoreManager.class); 
            ScoreManager scoreManager = l.get(0);
            scoreManager.updateScore();
            scoreManager.balancePanzer();
            blast();
        }
        else if(isTouching(Brick.class))
        {
            java.util.List<Brick> b = getIntersectingObjects(Brick.class);
            b.get(0).explode();
            Greenfoot.playSound("FallingWall.wav");
            getWorld().removeObject(this);
        }
        else if(isTouching(Metal.class))
        {
            Greenfoot.playSound("Thud.wav");
            getWorld().removeObject(this);
        }
        else if(isAtEdge())
            getWorld().removeObject(this);
    }
    
    public void blast()
    {
        explosion E1 = new explosion(111);
        getWorld().addObject(E1, this.getX(), this.getY());
        getWorld().removeObject(this);
    }
}
