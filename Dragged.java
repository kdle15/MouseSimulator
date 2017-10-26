import java.awt.*;

/**
 * Created by kdle15 on 6/19/2017.
 */
public class Dragged implements Mousetracks {
    private Point p1, p2;
    private String button;
    private int delay;

    public Dragged(Point p1, Point p2, String button, int delay){
        this.p1 = p1;
        this.p2 = p2;
        this.delay = delay;
        this.button = button;
    }
    @Override
    public void draw(Graphics2D page) {
        page.setStroke(new BasicStroke(6));
        page.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    @Override
    public int getDelay() {
        return 0;
    }

    @Override
    public String getAction() {
        return null;
    }
}