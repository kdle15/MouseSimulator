import java.awt.*;

/**
 * Created by kdle15 on 6/16/2017.
 */
public class Move implements Mousetracks{
    private Point p1, p2;
    private int delay;


    public Move(Point p1, Point p2, int delay){
        this.p1  = p1;
        this.p2 = p2;
        this.delay = delay;
    }


    @Override
    public void draw(Graphics2D page) {
        page.setStroke(new BasicStroke(2));
        page.setColor(Color.BLACK);
        page.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    @Override
    public int getDelay() {
        return delay;
    }

    @Override
    public String getAction() {
        return "Mouse moved";
    }
}
