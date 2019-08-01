import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class gameFrame extends JFrame{
    public static int boardWidth = 900;
    public static int boardHeight = 700;
    public static boolean key1Held = false;
    public static int key1HeldCode;
    public static boolean key2Held = false;
    public static int key2HeldCode;
    public static boolean Enter = false;

    public static void main(String [] args)
    {
            JFXPanel j = new JFXPanel();
            final MediaPlayer mediaPlayer =new MediaPlayer(new Media(new File("backgroundmusic.mp3").toURI().toString()));
            mediaPlayer.play();
            new gameFrame();
    }    
    public static void music()
    {
            JFXPanel j = new JFXPanel();
            new MediaPlayer(new Media(new File("backgroundmusic.mp3").toURI().toString())).play();
       
       
    }

    
    public gameFrame()
    {

        this.setSize(boardWidth, boardHeight);
        this.setLocationRelativeTo(null);
        this.setTitle("Java Asteroids");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==68)
                {
                    key1HeldCode = e.getKeyCode();
                    key1Held = true;
                } else if (e.getKeyCode()==65){
                    key1HeldCode = e.getKeyCode();
                    key1Held = true;
                }
                else if (e.getKeyCode()==39){
                    key2HeldCode = e.getKeyCode();
                    key2Held = true;

                }

                else if (e.getKeyCode()==37){
                    key2HeldCode = e.getKeyCode();
                    key2Held = true;
                }
                else if (e.getKeyCode()==32){
                    Enter = true;
                }

            }
            public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode()==65||e.getKeyCode()==68){
                    key1Held = false;

                }

                else if (e.getKeyCode()==37||e.getKeyCode()==39){
                    key2Held = false;

                }
                else if (e.getKeyCode()==32){
                    Enter = false;
                }
            }
        });
        
        GameDrawingPanel gamePanel = new GameDrawingPanel();
        this.add(gamePanel, BorderLayout.CENTER);
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(new RepaintTheBoard(this), 0L, 20L, TimeUnit.MILLISECONDS);
        this.setVisible(true);
    }

}

class RepaintTheBoard implements Runnable{
    gameFrame theBoard;
    public RepaintTheBoard(gameFrame theBoard){
        this.theBoard = theBoard;
    }

    @Override
    public void run() {
        theBoard.repaint();
    }
}
@SuppressWarnings("serial")
class GameDrawingPanel extends JComponent {
    int timer=0;
    public ArrayList<Rock> rocks = new ArrayList<>();
    public Basket basket1 = new Basket(Basket.getpolyXArray(500), Basket.getpolyYArray(600),4,500,600,this);
    public Basket basket1_ = new Basket(Basket.getpolyXArray(432), Basket.getpolyYArray(600),4,432,600,this);
    public Basket basket2 = new Basket(Basket.getpolyXArray(890), Basket.getpolyYArray(600),4,890,600,this);
    public Basket basket2_ = new Basket(Basket.getpolyXArray(822), Basket.getpolyYArray(600),4,822,600,this);
    public PlateBar bar1 = new PlateBar(PlateBar.getpolyXArray(-200),PlateBar.getpolyYArray(60),4,-200,"up","left");
    public PlateBar bar2 = new PlateBar(PlateBar.getpolyXArray(-300),PlateBar.getpolyYArray(160),4,-300,"down","left");
    public PlateBar bar3 = new PlateBar(PlateBar.getpolyXArray(700),PlateBar.getpolyYArray(60),4,700,"up","right");
    public PlateBar bar4 = new PlateBar(PlateBar.getpolyXArray(800),PlateBar.getpolyYArray(160),4,800,"down","right");    
    int[] polyXArray = Rock.sPolyXArray;
    int[] polyYArray = Rock.sPolyYArray;
    int width = gameFrame.boardWidth;
    int height = gameFrame.boardHeight;
    BufferedImage Background;
    BufferedImage player1;
    BufferedImage player2;
    BufferedImage player1win;
    BufferedImage player2win;
    BufferedImage gametie;
    BufferedImage player1text;
    BufferedImage player2text;
    BufferedImage easy;
    BufferedImage medium;
    BufferedImage hard;
    BufferedImage easySelected;
    BufferedImage mediumSelected;
    BufferedImage hardSelected;
    boolean gameStarted =false;
    boolean EasySelected=true;
    boolean MediumSelected=false;
    boolean HardSelected=false;
    public static int selectedHardness=0;
    public GameDrawingPanel()
    { 
                 try {
            this.easy = ImageIO.read(gameFrame.class.getResourceAsStream("easy#.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            try {
            this.medium = ImageIO.read(gameFrame.class.getResourceAsStream("medium#.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
                    try {
            this.hard = ImageIO.read(gameFrame.class.getResourceAsStream("hard#.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
                 try {
            this.easySelected = ImageIO.read(gameFrame.class.getResourceAsStream("easy.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            try {
            this.mediumSelected = ImageIO.read(gameFrame.class.getResourceAsStream("medium.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
                    try {
            this.hardSelected = ImageIO.read(gameFrame.class.getResourceAsStream("hard.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            this.Background = ImageIO.read(gameFrame.class.getResourceAsStream("background2.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            try {
            this.player1 = ImageIO.read(gameFrame.class.getResourceAsStream("player1.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
                    try {
            this.player2 = ImageIO.read(gameFrame.class.getResourceAsStream("player2.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }    
            try {
            this.player1win = ImageIO.read(gameFrame.class.getResourceAsStream("player1win.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
            try {
            this.player2win = ImageIO.read(gameFrame.class.getResourceAsStream("player2win.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
                        try {
            this.gametie = ImageIO.read(gameFrame.class.getResourceAsStream("gametie.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
           
                    try {
            this.player1text = ImageIO.read(gameFrame.class.getResourceAsStream("player1text.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
            try {
            this.player2text = ImageIO.read(gameFrame.class.getResourceAsStream("player2text.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GameDrawingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void paint(Graphics g) {

        
        Graphics2D graphicSettings = (Graphics2D)g;

        graphicSettings.setPaint(Color.WHITE);
        graphicSettings.fillRect(0, 0, getWidth(), getHeight());
        if(!gameStarted)
        {     if(gameFrame.key2Held == true && gameFrame.key2HeldCode == 39){
             if(selectedHardness!=6)
             {
                 selectedHardness++;
             }
        } else
            if(gameFrame.key2Held == true && gameFrame.key2HeldCode == 37){
             if(selectedHardness!=0){
                 selectedHardness--;
             }
            }
        else
            if(gameFrame.Enter == true){
             gameStarted=true;
            }
        switch (selectedHardness) {
            case 1:
                EasySelected=true;
                MediumSelected=false;
                HardSelected=false;
                break;
            case 3:
                EasySelected=false;
                MediumSelected=true;
                HardSelected=false;
                break;
            case 6:
                EasySelected=false;
                MediumSelected=false;
                HardSelected=true;
                break;
            default:   
                break;
        }
        if(EasySelected)
        {   graphicSettings.drawImage(easySelected, 225, 300, this);
        graphicSettings.drawImage(medium, 535, 300, this);
        graphicSettings.drawImage(hard, 845, 300, this);}
        else if(MediumSelected)
        {graphicSettings.drawImage(easy, 225, 300, this);
        graphicSettings.drawImage(mediumSelected, 535, 300, this);
        graphicSettings.drawImage(hard, 845, 300, this);}
        else if(HardSelected)
        {graphicSettings.drawImage(easy, 225, 300, this);
        graphicSettings.drawImage(medium, 535, 300, this);
        graphicSettings.drawImage(hardSelected, 845, 300, this);}}
               
        graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if(timer<6000&&gameStarted){
                timer++;

            graphicSettings.drawImage(Background, 0, 0, this);
            graphicSettings.drawImage(player1text, 140, 370, this);
            graphicSettings.setColor(Color.blue);
            graphicSettings.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
            graphicSettings.drawString(String.valueOf(basket1.getScore()+basket1_.getScore()), 192, 435);
            graphicSettings.drawImage(player2text, 1190, 370, this);
            graphicSettings.setColor(Color.red);
            graphicSettings.drawString(String.valueOf(basket2.getScore()+basket2_.getScore()), 1242, 435);
            int randomStartYPos1 = 150;

            if(timer%(31-2*selectedHardness)==0){
            rocks.add(new Rock(Rock.getpolyXArray(0), Rock.getpolyYArray(randomStartYPos1), 4, 0, randomStartYPos1,"down",bar2));
            Rock.rocks = rocks;}
        
            if(timer%(32-2*selectedHardness)==0){
            rocks.add(new Rock(Rock.getpolyXArray(1370), Rock.getpolyYArray(randomStartYPos1), 4, 1370, randomStartYPos1,"down",bar4));
            Rock.rocks = rocks;}
            
            int randomStartYPos2 = 50;

            if(timer%(33-2*selectedHardness)==0){
            rocks.add(new Rock(Rock.getpolyXArray(0), Rock.getpolyYArray(randomStartYPos2), 4, 0, randomStartYPos2,"up",bar1));
            Rock.rocks = rocks;}
        
            if(timer%(34-2*selectedHardness)==0){
            rocks.add(new Rock(Rock.getpolyXArray(1370), Rock.getpolyYArray(randomStartYPos2), 4, 1370, randomStartYPos2,"up",bar3));
            Rock.rocks = rocks;}
            
            for(Rock rock : rocks){
              if(rock.getYPositon()>620)
              {  rocks.remove(rock);
              break;
              }
            }
                    

            bar1.move();
            graphicSettings.setPaint(Color.MAGENTA);
            graphicSettings.fillPolygon(bar1);
            graphicSettings.drawPolygon(bar1);
        
            bar2.move();
            graphicSettings.setPaint(Color.MAGENTA);
            graphicSettings.fillPolygon(bar2);
            graphicSettings.drawPolygon(bar2);
            
            bar3.move();
            graphicSettings.setPaint(Color.MAGENTA);
            graphicSettings.fillPolygon(bar3);
            graphicSettings.drawPolygon(bar3);
        
            bar4.move();
            graphicSettings.setPaint(Color.MAGENTA);
            graphicSettings.fillPolygon(bar4);
            graphicSettings.drawPolygon(bar4);
            
            
                    for(Rock rock : rocks){
            rock.move();
            graphicSettings.setPaint(rock.getColor());
            graphicSettings.fillPolygon(rock);
            graphicSettings.drawPolygon(rock);
        }
        
        basket1.setColor(Color.WHITE);
        basket1_.setColor(Color.WHITE);
        if(gameFrame.key1Held == true && gameFrame.key1HeldCode == 68){
             basket1.move(4);
             basket1_.move(4);
        } else
            if(gameFrame.key1Held == true && gameFrame.key1HeldCode == 65){
                basket1.move(-4);
                basket1_.move(-4);
            }
        basket1.checkIntersection();
        basket1_.checkIntersection();
        graphicSettings.drawImage(player1, basket1.xCorner()-72,614, this); 
        
                if(gameFrame.key2Held == true && gameFrame.key2HeldCode == 39){
             basket2.move(4);
             basket2_.move(4);
        } else
            if(gameFrame.key2Held == true && gameFrame.key2HeldCode == 37){
                basket2.move(-4);
                basket2_.move(-4);
            }
         basket2.checkIntersection();
         basket2_.checkIntersection();
         graphicSettings.drawImage(player2, basket2.xCorner()-72,614, this);         
         
                 for(Rock rock : basket1.rocks)
        {

            graphicSettings.setPaint(rock.getColor());
            graphicSettings.fillPolygon(rock);
            graphicSettings.drawPolygon(rock);
        }
             for(Rock rock : basket1_.rocks)
        {

            graphicSettings.setPaint(rock.getColor());
            graphicSettings.fillPolygon(rock);
            graphicSettings.drawPolygon(rock);
        }
         
        
                 for(Rock rock : basket2.rocks)
        {

            graphicSettings.setPaint(rock.getColor());
            graphicSettings.fillPolygon(rock);
            graphicSettings.drawPolygon(rock);
        }   
                 
        
                 for(Rock rock : basket2_.rocks)
        {

            graphicSettings.setPaint(rock.getColor());
            graphicSettings.fillPolygon(rock);
            graphicSettings.drawPolygon(rock);
        }    
    }else if(timer==6000)
            {
            JFXPanel j = new JFXPanel();
            final MediaPlayer mediaPlayer =new MediaPlayer(new Media(new File("Harp1.wav").toURI().toString()));
            mediaPlayer.play();
            timer++;
            }
    else if(timer>6000)
            {
                if(basket1.getScore()+basket1_.getScore()>basket2.getScore()+basket2_.getScore())
                {
                    graphicSettings.drawImage(player1win, 0, 0, this);
                }
                else if(basket2.getScore()+basket2_.getScore()>basket1.getScore()+basket1_.getScore())
                {
                    graphicSettings.drawImage(player2win, 0, 0, this);
                }
                else graphicSettings.drawImage(gametie,435,300, this);
            }
    
    }
}