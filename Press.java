import java.awt.*;

/**
 * Created by kdle15 on 6/16/2017.
 */
public class Press implements Mousetracks {

    private Point p;
    private String button;
    private int delay;

    public Press(Point p, String button,int delay){
        this.p = p;
        this.button = button;
        this.delay = delay;
    }

    @Override
    public void draw(Graphics2D page) {
        if (button.equals("1")){
            page.setColor(Color.RED);
        }
        if (button.equals("2")){
            page.setColor(Color.GREEN);
        }
        if (button.equals("3")){
            page.setColor(Color.BLUE);
        }
        page.fillRect(p.x-6,p.y-6,12,12);
    }

    @Override
    public int getDelay() {
        return this.delay;
    }

    @Override
    public String getAction() {
        return "Mouse pressed";
    }
}
