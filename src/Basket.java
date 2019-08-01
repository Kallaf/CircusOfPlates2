import java.awt.Color;
import java.awt.Desktop;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

class Basket extends Polygon{

    private Color color=Color.BLACK;
    GameDrawingPanel board;
    private int score =0;
    
    public ArrayList<Rock> rocks = new ArrayList<>();
    
    
    int xDirection = 2;
    
    int width = gameFrame.boardWidth;

    int height = gameFrame.boardHeight;

    int[] polyXArray, polyYArray;

    public static int[] sPolyXArray = {10,31,31,10};

    public static int[] sPolyYArray = {10,10,13,13};

    public Basket(int[] polyXArray, int[] polyYArray, int pointsInPoly, int randomStartXPos, int randomStartYPos,GameDrawingPanel board){

        
        super(polyXArray, polyYArray, pointsInPoly);
        this.board=board;

    }

    public Color getColor()
    {
        return color;
    }
    
        public void setColor(Color color)
    {
        this.color=color;
    }
    
        public int getScore()
        {
            return this.score;
        }
        
    
              public Rectangle getBounds() {
        return new Rectangle(super.xpoints[0], super.ypoints[0],26,3);
    }
       
              
    public void checkIntersection()
    {
                 Rectangle basketToCheck = this.getBounds();
         
         
         if(rocks.isEmpty())
        for(Rock rock : board.rocks){
            Rectangle otherRock = rock.getBounds();
            if(otherRock.intersects(basketToCheck)){
                Rock newRock = new Rock(Rock.getpolyXArray(this.xpoints[0]-7), Rock.getpolyYArray(this.ypoints[0]-11), 4,this.xpoints[0]-7,this.ypoints[0]-11,rock.upOrDown,rock.platebar);   
             newRock.setColor(rock.getColor());
             rocks.add(newRock);
             board.rocks.remove(rock);
             break;
            }
        }
         else 
         {
             Rectangle rockToCheck = rocks.get(rocks.size()-1).getBounds();
            for(Rock rock : board.rocks){
            Rectangle otherRock = rock.getBounds();
            if(otherRock.intersects(rockToCheck)){
                Rock newRock = null;
                if(rocks.size()==1)
                newRock = new Rock(Rock.getpolyXArray(this.xpoints[0]-7), Rock.getpolyYArray(this.ypoints[0]-7*(rocks.size()+1)), 4,this.xpoints[0]-7,this.ypoints[0]-7*(rocks.size()+1),rock.upOrDown,rock.platebar);   
                else
                 newRock = new Rock(Rock.getpolyXArray(this.xpoints[0]-7), Rock.getpolyYArray(this.ypoints[0]-5*(rocks.size()+1)), 4,this.xpoints[0]-7,this.ypoints[0]-5*(rocks.size()+1),rock.upOrDown,rock.platebar);    
                newRock.setColor(rock.getColor());
             rocks.add(newRock);
             board.rocks.remove(rock);
             break;
            }}}
         
                 Rock topRock = null,bftop = null,bfbf = null;
        if(rocks.size() >=3)
        {
              topRock=rocks.get(rocks.size()-1);
              bftop=rocks.get(rocks.size()-2); 
              bfbf=rocks.get(rocks.size()-3);
        }
        if(topRock != null)
        if((topRock.getColor().equals(bftop.getColor()))&&(topRock.getColor().equals(bfbf.getColor())))
        {
            rocks.remove(topRock);
            rocks.remove(bftop);
            rocks.remove(bfbf);
            score++;
            JFXPanel j = new JFXPanel();
            final MediaPlayer mediaPlayer =new MediaPlayer(new Media(new File("cartoon015.wav").toURI().toString()));
            mediaPlayer.play();
        }
    }
              
    public void move(int Direction){

        this.xDirection=Direction;
        
        int LeftXPos = super.xpoints[0];
      if (!((LeftXPos < 72&&Direction<0 )|| (((LeftXPos + 26) > 1370)&&Direction>0) )){
        for (int i = 0; i < super.xpoints.length; i++){
             super.xpoints[i] += xDirection; 
        }


        
        for(Rock rock : rocks)
        {
             for (int i = 0; i < rock.xpoints.length; i++){
             rock.xpoints[i] += xDirection; 
        }
        }
        
        
    }}

    public int xCorner()
    {
        return super.xpoints[0];
    }
    
    
    public static int[] getpolyXArray(int randomStartXPos){

        int[] tempPolyXArray = (int[])sPolyXArray.clone();

        for (int i = 0; i < tempPolyXArray.length; i++){

            tempPolyXArray[i] += randomStartXPos;

        }

        return tempPolyXArray;

    }

    public static int[] getpolyYArray(int randomStartYPos){

        int[] tempPolyYArray = (int[])sPolyYArray.clone();

         

        for (int i = 0; i < tempPolyYArray.length; i++){

             

            tempPolyYArray[i] += randomStartYPos;

             

        }

         

        return tempPolyYArray;

         

    }

     

}