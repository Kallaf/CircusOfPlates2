import java.awt.Polygon;
public class PlateBar extends Polygon{
    int xDirection = 1+GameDrawingPanel.selectedHardness;
    private int leftend;
    private int rightend;
    private int leftPosition=0;
    private int rightPosition=600;
    int width = gameFrame.boardWidth;

    int height = gameFrame.boardHeight;

    int[] polyXArray, polyYArray;

    public static int[] sPolyXArray = {0,750,750,0};

    public static int[] sPolyYArray = {10,10,13,13};

    public PlateBar(int[] polyXArray, int[] polyYArray, int pointsInPoly, int XPos,String upOrDown,String leftOrRight){

        
        super(polyXArray, polyYArray, pointsInPoly);
        leftPosition=XPos;
        rightPosition=XPos+750;
        if(upOrDown=="up"&&leftOrRight=="left"){leftend=-200;rightend=750;}
        else if(upOrDown=="down"&&leftOrRight=="left"){leftend=-300;rightend=650;}
        else if(upOrDown=="up"&&leftOrRight=="right"){leftend=650;rightend=1600;xDirection*=-1;}
        else if(upOrDown=="down"&&leftOrRight=="right"){leftend=750;rightend=1700;xDirection*=-1;}

    }

    public int getLeftposition()
    {
        return this.leftPosition;
    }
       
        public int getrightposition()
    {
        return this.rightPosition;
    }
    public void move()
    {
        if ((super.xpoints[0] < leftend)|| ((super.xpoints[1] > rightend)) )xDirection*=-1;
        for (int i = 0; i < super.xpoints.length; i++){
             super.xpoints[i] += xDirection; 
        }
        leftPosition+=xDirection;
        rightPosition+=xDirection;
    };

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
