import greenfoot.*;
import java.io.*;

/**
 * Write a description of class ScoreManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreManager extends Actor
{
    // instance variables - replace the example below with your own
    private int initHiScore;
    private int hiScore;
    private int score = 0;
    private boolean started = false;
    private Counter counter1, counter2;
    private int gameOverTimer = 250;
    /**
     * Constructor for objects of class ScoreManager
     */
    public ScoreManager()
    {
        
    }
    
    public void act()
    {
//         System.out.println(started);
//         if(!started)
//         {
//             
//             Greenfoot.stop();
//             while(!Greenfoot.isKeyDown("space"))
//             {
//                 
//             }
//             Greenfoot.start();
//             started = true;
//         }
        
        World w = getWorld();
        java.util.List<Panther> panthers = w.getObjects(Panther.class);
        if(panthers.isEmpty() && Greenfoot.isKeyDown("f5"))
        {
            try
            {
                Desert d = new Desert();
                Greenfoot.setWorld(d);
            }
            catch(Exception E)
            {
            }
        }
    }


   /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void updateHiScore()
    {
        if(hiScore>initHiScore)
        {
            try
            {
                String contentToBeWritten = java.lang.Integer.toString(hiScore);
                File file = new File("score.txt");
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(contentToBeWritten);
                bw.close();
            }
            catch(Exception e)
            {
            }
        }
        return;
    }
    
    public void getHiScore() throws IOException
    {
        java.util.List<Counter> counters = getWorld().getObjects(Counter.class);
        counter1 = counters.get(0);
        counter2 = counters.get(1);
        FileReader fr = new FileReader("score.txt");
        BufferedReader textReader = new BufferedReader(fr);
        
        String textData = "";
        
        textData = textReader.readLine();
        hiScore = java.lang.Integer.parseInt(textData);
        initHiScore = hiScore;
        counter2.add(hiScore);
        
        textReader.close();
        fr.close();
    }
    
    public void updateScore()
    {
        counter1.add(5);
        score += 5;
        if(score > hiScore)
        {
            hiScore = score;
            counter2.setValue(hiScore);
        }
    }
    
    public void balancePanzer()
    {
        //add panzers probablistically.
        java.util.List<Panzer> L = getWorld().getObjects(Panzer.class);
        java.util.List<Panther> LP = getWorld().getObjects(Panther.class);
        if((L.size() == 0 || Greenfoot.getRandomNumber(L.size()) < 4) && LP.size()>0)
        {
            // get a random position from the predecided 5. If Panther within a certain range discard these co-ordinates and get new Co-ordinates.
            int randX = LP.get(0).getX(); 
            int randY = LP.get(0).getY();
            int PantherX = randX;
            int PantherY = randY;
            while((randX > (PantherX-50) && randX < (PantherX+50)) && (randY > (PantherY-50) && randY < (PantherY+50)))
            {
                int condition = Greenfoot.getRandomNumber(10);
                switch(condition)
                {
                    case 0:
                        randX = 538;
                        randY = 559;
                        break;
                    case 1:
                        randX = 1054;
                        randY = 422;
                        break;
                    case 2:
                        randX = 1056;
                        randY = 244;
                        break;
                    case 3:
                        randX = 597;
                        randY = 41;
                        break;
                    case 4:
                        randX = 350;
                        randY = 44;
                        break;
                    case 5:
                        randX = 786;
                        randY = 561;
                        break;
                    case 6:
                        randX = 885;
                        randY = 40;
                        break;
                    case 7:
                        randX = 31;
                        randY = 303;
                        break;
                    case 8:
                        randX = 42;
                        randY = 25;
                        break;
                    case 9:
                        randX = 50;
                        randY = 575;
                        break;
                }
            }
            getWorld().addObject (new Panzer(), randX, randY);            
        }
    }
}
