import greenfoot.*;

/**
 * Write a description of class explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class explosion extends Active
{
    private java.util.List<GreenfootImage> exp = new java.util.ArrayList<GreenfootImage>();
    private int frameCount = 0, imageCount = 0, speed = 1;
    /**
     * Act - do whatever the explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public explosion(int speed)
    {
        super();
        init();
        speed = speed;
        Greenfoot.playSound("Explosion.wav");
    }
    
    public void act() 
    {
        if(frameCount < 10*speed && frameCount%speed == 0)
        {
            this.setImage(exp.get(imageCount));
            imageCount++;
        }
        else if(frameCount > 10*speed && frameCount < 15*speed && frameCount%speed==0 && getImage().getTransparency()<245)
            this.getImage().setTransparency(getImage().getTransparency() + 10);
        else if(frameCount == 15*speed)
            getWorld().removeObject(this);
        frameCount++;
        //setImage()
    }   
    
    public void init()
    {
        exp.add(new GreenfootImage("Explosion1.png"));
        exp.add(new GreenfootImage("Explosion2.png"));
        exp.add(new GreenfootImage("Explosion3.png"));
        exp.add(new GreenfootImage("Explosion4.png"));
        exp.add(new GreenfootImage("Explosion5.png"));
        exp.add(new GreenfootImage("Explosion6.png"));
        exp.add(new GreenfootImage("Explosion7.png"));
        exp.add(new GreenfootImage("Explosion8.png"));
        exp.add(new GreenfootImage("Explosion9.png"));
        exp.add(new GreenfootImage("Explosion10.png"));
    }
}
