import java.awt.*;


public class Equipment {

    public int xpos;
    public int ypos;
    public int dx = 2;
    public int dy = -2;
    public int width = 150;
    public int height = 150;
    public String name;
    public Rectangle rec;

    public Equipment(String paramName, int paramXpos, int paramypos) {
        name = paramName;
        xpos = paramXpos;
        ypos = paramypos;
        rec = new Rectangle(xpos,ypos,width,height);



    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (ypos < 0) {
            dy = -dy;
        }


        if (ypos > 700 - height) {
            dy = -dy;
        }

        if (xpos < 0) {
            dx = -dx;
        }

        if (xpos > 1000 - width) {
            dx = -dx;




        }
        rec = new Rectangle(xpos,ypos,width,height);
    }
    public void wrap() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (ypos < -50) {
            ypos = ypos + 800;
        }

        if (ypos > 800) {
            ypos = ypos - 800;

        }

        if (xpos < 0) {
            xpos = xpos + 1000;
        }

        if (xpos > 1000) {
            xpos = xpos - 1000;

        }

        rec = new Rectangle(xpos,ypos,width,height);
    }



}