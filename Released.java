import java.awt.*;

/**
 * Created by kdle15 on 6/19/2017.
 */
public class Released implements Mousetracks {
    private Point p;
    private String button;
    private int delay;

    public Released(Point p, String button, int delay){
        this.p = p;
        this.button = button;
        this.delay = delay;
    }

    @Override
    public void draw(Graphics2D page) {
        if(button.equals("1")){
            page.setColor(Color.ORANGE);
        }
        if(button.equals("2")){
            page.setColor(Color.CYAN);
        }if(button.equals("3")){
            page.setColor(Color.GRAY);
        }
        page.fillOval(p.x-15,p.y-20,30,40);
    }

    @Override
    public int getDelay() {
        return delay;
    }

    @Override
    public String getAction() {
        return "Mouse released";
    }
}
