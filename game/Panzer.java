import greenfoot.*;

/**
 * Write a description of class Panzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Panzer extends Tank
{
    public void next(int d)
    {
        //AI goes here
        move(4);
        if(!turnAllowed)
            if(turnReload != 0)
                turnReload--;
            else
                turnAllowed = true;
    }
       public void checkResponse()
    {
        if(!getWorld().getObjects(Panther.class).isEmpty() && Greenfoot.getRandomNumber(100)<40)
        {
            java.util.List<Panther> targets = getWorld().getObjects(Panther.class);
            Panther target = targets.get(0);
            switch(getRotation())
            {
                case 0:
                    if(target.getY() > this.getY() && target.getY() < this.getY() + this.getImage().getHeight() && target.getX() > this.getX())
                        shoot();
                    break;
                case 270:
                    if(target.getX() > this.getX() && target.getX() < this.getX() + this.getImage().getWidth() && target.getY() < this.getY())
                        shoot();
                    break;
                case 180:
                    if(target.getY() > this.getY() && target.getY() < this.getY() + this.getImage().getHeight() && target.getX() < this.getX())
                        shoot();
                    break;
                case 90:
                    if(target.getX() > this.getX() && target.getX() < this.getX() + this.getImage().getWidth() && target.getY() > this.getY())
                        shoot();
                    break;                
                default:
                    break;
            }
        }
        randomTurn();
    }
    
    public void randomTurn()
    {
        //move at WorldEdge or when touching passive objects for sure otherwise 1 in 50 chance of a turn.
        boolean turned = false;
        while(obstactleAhead())
        {
            turn(90);
            turned = true;
        }
        if(turned)
        {
            turnReload = 15;
            turnAllowed = false;
        }
        
        if(Greenfoot.getRandomNumber(100)<2)
        {
            switch(Greenfoot.getRandomNumber(4))
            {
                case 1:
                    setRotation(0);
                    turnReload = 15;
                    turnAllowed = false;
                    break;
                case 2:
                    setRotation(90);
                    turnReload = 15;
                    turnAllowed = false;
                    break;
                case 3:
                    setRotation(180);
                    turnReload = 15;
                    turnAllowed = false;
                    break;
                case 0:
                    setRotation(270);
                    turnReload = 15;
                    turnAllowed = false;                    
                    break;
            }
        }
    }
    
    
    public boolean obstactleAhead()
    {
        int rot = getRotation();
        java.util.List<Passive> I;
    
       if(isTouching(Passive.class))
        {
            I = getIntersectingObjects(Passive.class);
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
    
    public void crashResponse()
    {
        if(exists)
        {
            java.util.List<Panther> dangers = getIntersectingObjects(Panther.class);
            if(!dangers.isEmpty())
            {
                Panther danger = dangers.get(0);
                getWorld().removeObject(danger);
                blast();
                exists = false;
            }
        }
    }
    
}
