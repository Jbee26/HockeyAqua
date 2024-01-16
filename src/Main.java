//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class Main implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public Equipment net;

    public Image netPic;

    public Equipment lamp;

    public Image lampPic;

    public Equipment clamp;

    public Image clampPic;


    public Equipment puck;


    public Image puckPic;

    public Image rink;

    public Equipment fan;

    public Image fanPic;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        Main ex = new Main();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public Main() { // BasicGameApp constructor

        setUpGraphics();
        puck = new Equipment("puck", 200, 100);
        net = new Equipment("net", 600, 500);
        lamp = new Equipment("lamp", -200, 0);
        fan = new Equipment("fan", -700, 50);
        clamp = new Equipment("clamp", -700, 50);
        clampPic = Toolkit.getDefaultToolkit().getImage("lamp copy.png");
        netPic = Toolkit.getDefaultToolkit().getImage("net.png");
        lampPic = Toolkit.getDefaultToolkit().getImage("lamp.png");
        puckPic = Toolkit.getDefaultToolkit().getImage("puck.png");
        rink = Toolkit.getDefaultToolkit().getImage("ice.jpeg");
        fanPic = Toolkit.getDefaultToolkit().getImage("fan.png");


        //variable and objects
        //create (construct) the objects needed for the game

    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            collisions();
            render();  // paint the graphics
            pause(10); // sleep for 10 ms

        }
    }

    public void moveThings() {
        //call the move() code for each object
        puck.move();
        net.wrap();
    }

    public void collisions() {
        if (puck.rec.intersects(net.rec)) {
            puck.dx = -puck.dx;
            puck.dy = -puck.dy;
            net.dx = -net.dx;
            net.dy = -net.dy;
            lamp.xpos = 450;
            lamp.ypos = -25;
            fan.xpos = 700;
            fan.ypos = 50;


            lamp.rec = new Rectangle(lamp.xpos,lamp.ypos,lamp.width,lamp.height);

        }

        if (puck.rec.intersects(lamp.rec)) {
            lamp.xpos = -200;
            clamp.xpos = 450;
            clamp.ypos = -25;


        }

}



    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(rink, 0,0,1000,700, null);
        g.drawImage(puckPic, puck.xpos, puck.ypos, puck.width, puck.height, null );
        g.drawImage(netPic, net.xpos, net.ypos, puck.width, puck.height, null);
        g.drawImage(lampPic, lamp.xpos,lamp.ypos, lamp.width, lamp.height, null );
        g.drawImage(clampPic, clamp.xpos,clamp.ypos, clamp.width, clamp.height, null );
        g.drawImage(fanPic, fan.xpos, fan.ypos, fan.width, fan.height, null);
        g.drawRect(puck.rec.x, puck.rec.y, puck.rec.width, puck.rec.height);
        g.drawRect(net.rec.x, net.rec.y, net.rec.width, net.rec.height);
        g.drawRect(lamp.rec.x, lamp.rec.y, lamp.rec.width, lamp.rec.height);

        //draw the images

        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

}