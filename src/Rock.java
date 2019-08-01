import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle; 
import java.util.ArrayList;

class Rock extends Polygon{

    private Color color;
    int uLeftXPos, uLeftYPos;
    private int yPosition;
    PlateBar platebar;
    int colorNO=(int)(Math.random()*(3+(GameDrawingPanel.selectedHardness/3)));
    public String upOrDown;
     private boolean turnPoint=true;
     static ArrayList<Rock> rocks = new ArrayList<Rock>();
     
    int xDirection = 1+GameDrawingPanel.selectedHardness;
    int yDirection = 1+GameDrawingPanel.selectedHardness;

    int[] polyXArray, polyYArray;

    public static int[] sPolyXArray = {10,26,26,10};

    public static int[] sPolyYArray = {10,10,13,13};

    public Rock(int[] polyXArray, int[] polyYArray, int pointsInPoly, int randomStartXPos, int randomStartYPos,String upOrDown,PlateBar platebar){

        
        super(polyXArray, polyYArray, pointsInPoly);
        this.upOrDown = upOrDown;
        this.platebar=platebar;
                switch (colorNO) {
            case 0:
                color=Color.BLUE;
                break;
            case 1:
                color=Color.RED;
                break;
            case 3:
                color=Color.GREEN;
                break;
            case 4:
                color=Color.PINK;
                break;    
            default:
                color=Color.YELLOW;
                break;
        }
        
        this.uLeftXPos = randomStartXPos;
        this.uLeftYPos = randomStartYPos;

    }

    public Color getColor()
    {
        return color;
    }
    
    public void setColor(Color color)
    {
        this.color=color;
    }
    
       int getYPositon()
    {
       return this.yPosition;
    }
    
       public Rectangle getBounds() {
        return new Rectangle(super.xpoints[0], super.ypoints[0],16,3);
    }

       
       
    public void move(){

        
        int uLeftXPos = super.xpoints[0];
        int uUPYPos = super.ypoints[0];
        yPosition=uUPYPos;
        String go = null ;
        if(upOrDown=="up")
        {
        if(this.turnPoint&&uLeftXPos < platebar.getrightposition()&&this.uLeftXPos==0)go="left";
        else if(this.turnPoint&&uLeftXPos > platebar.getLeftposition()&&this.uLeftXPos==1370)go="right";
        else{
            this.turnPoint=false;
            go="down";
        }}
        
        if(upOrDown=="down")
        {
         
        if(this.turnPoint&&uLeftXPos < platebar.getrightposition()&&this.uLeftXPos==0)go="left";
        else if(this.turnPoint&&uLeftXPos > platebar.getLeftposition()&&this.uLeftXPos==1370)go="right";
        else{
            this.turnPoint=false;
            go="down";
        }   
        }
        
//        if (uLeftXPos < 0 || (uLeftXPos + 25) > width) xDirection = -xDirection;
//
//        if (uUPYPos < 0 || (uUPYPos + 50) > height) yDirection = -yDirection;

        for (int i = 0; i < super.xpoints.length; i++){

            if(go=="left")
             super.xpoints[i] += xDirection;
            else if(go=="right")
             super.xpoints[i] -= xDirection;
            else 
             super.ypoints[i] += yDirection; 
        }

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