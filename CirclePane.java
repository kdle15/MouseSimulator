import javax.swing.*;
import java.awt.*;

/**
 * Created by KhangLe on 7/5/2017.
 */
public class CirclePane extends JPanel {
    private int targetsize;
    public CirclePane(int targetsize) {
        this.setLayout(null);
        setOpaque(false);
        this.targetsize = targetsize;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int radius = targetsize/2-1;
        int xOffset = (getWidth() - radius) / 2;
        int yOffset = (getHeight() - radius) / 2;

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillOval(xOffset, yOffset, radius, radius);
        g2d.setColor(Color.GRAY);
        g2d.drawOval(xOffset, yOffset, radius, radius);
        g2d.dispose();

    }

    public static void main(String args[]){
        JFrame frame = new JFrame();
        //CirclePane pane = new CirclePane(20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.add(pane);
        frame.pack();;
        frame.setVisible(true);
    }
}

