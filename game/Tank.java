import greenfoot.*;
/**
 * Write a description of class Panther here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tank extends Active
{
    protected boolean loaded = true;
    protected int reloadTime = 10;
    protected int turnReload = 15;
    protected boolean turnAllowed = true;
    protected boolean exists = true;
    /**
     * Act - do whatever the Panther wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        terrainResponse();
        checkResponse();
        next(4);
        reload();
        
        crashResponse();
    }    
    
    public void next(int d){}
       
    public void checkResponse(){}
    
    public void terrainResponse(){}
    
    public void shoot() 
    {
        Bullet b = new Bullet(this.getClass());
        GreenfootImage i = b.getImage();
        i.scale(7, 8);
        b.setImage(i);
        b.setRotation(this.getRotation());
        World w = this.getWorld();
        if(loaded)
        {    
            loaded = false;
            reloadTime = 10;
            w.addObject(b,this.getX(),this.getY());
            Greenfoot.playSound("Cannon.wav");
        }
    }
    
    public void reload()
    {
        if(!loaded)
            if(reloadTime != 0)
                reloadTime--;
            else
                loaded = true;
    }
    
    public void crashResponse()
    {
        if(exists)
        {
            java.util.List<Panzer> dangers = getIntersectingObjects(Panzer.class);
            if(!dangers.isEmpty())
            {
                Panzer danger = dangers.get(0);
                World w = getWorld();
                java.util.List<Counter> cs = w.getObjects(Counter.class);
                Counter c = cs.get(0);
                getWorld().showText("Game Over \n Score: " + c.getValue(), 550, 300);
                w.removeObject(danger);
                blast();
                exists = false;
            }
        }
    }
    
    public void blast()
    {
        explosion E1 = new explosion(111);
        getWorld().addObject(E1, this.getX(), this.getY());
        getWorld().removeObject(this);
    }
    
    public boolean obstactleAhead()
    {
        int rot = getRotation();
        java.util.List<Impermissive> I;
    
       if(isTouching(Impermissive.class))
        {
            I = getIntersectingObjects(Impermissive.class);
            switch(rot)
            {
                case 0: //right
                    if(I.get(0).getX()>getX())
                        return true;
                    else
                        return false;
                case 90: //down
                    if(I.get(0).getY()>getY())
                        return true;
                    else
                        return false;
                case 180: //left
                    if(I.get(0).getX()<getX())
                        return true;
                    else
                        return false;
                case 270: //up 
                    if(I.get(0).getY()<getY())
                        return true;
                    else
                        return false;
            }
        }
        return false;
    }
    
    public boolean waterAhead()
    {
        int rot = getRotation();
        java.util.List<Water> I;
    
       if(isTouching(Water.class))
        {
            I = getIntersectingObjects(Water.class);
            switch(rot)
            {
                case 0: //right
                    if(I.get(0).getX()>getX())
                        return true;
                    else
                        return false;
                case 90: //down
                    if(I.get(0).getY()>getY())
                        return true;
                    else
                        return false;
                case 180: //left
                    if(I.get(0).getX()<getX())
                        return true;
                    else
                        return false;
                case 270: //up 
                    if(I.get(0).getY()<getY())
                        return true;
                    else
                        return false;
            }
        }
        return false;
    }
    
}
