import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by kdle15 on 6/13/2017.
 */
public class MouseEventDemo extends JPanel implements MouseListener,MouseMotionListener {

    static FileWriter writer = null;

    public static void main(String[] args) throws Exception{
        JFrame frame = new JFrame("MouseEventDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JComponent ContentPane = new MouseEventDemo();
        frame.setContentPane(ContentPane);

        //Display the window
        String location = "C:\\Users\\kdle15\\Desktop\\Files\\"+ System.nanoTime() + "_newCsvFile.csv";
        File file = new File(location);
        //String location = "C:\\Users\\kdle15\\Desktop\\newCsvFile.csv";

        writer = new FileWriter(file);
        writer.append("TimeStart");
        writer.append(',');
        writer.append("X-cor");
        writer.append(',');
        writer.append("Y-cor");
        writer.append(',');
        writer.append("Action");
        writer.append('\n');

        frame.pack();
        frame.setVisible(true);

    }

    public MouseEventDemo() {
        addMouseListener(this);
        addMouseMotionListener(this);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void eventOutput(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription
                + " coordinate at"
                + " (" + e.getX() + "," + e.getY() + ")"
                + " detected"
                + "\n");

        int t = 0;
        while(eventDescription.charAt(t) != ' '){
            t = t+1;
        }

        try {
            writer.append(eventDescription.substring(0,t));
            writer.append(',');
            writer.append(Integer.toString(e.getX()));
            writer.append(',');
            writer.append(Integer.toString(e.getY()));
            writer.append(',');
            writer.append(eventDescription.substring(t+1));
            writer.append('\n');
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        eventOutput(System.nanoTime() + " Mouse clicked (# of clicks: "
                + e.getClickCount() + ") using " + "button# " +  e.getButton(), e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        eventOutput(System.nanoTime() + " Mouse pressed (# of clicks: "
                + e.getClickCount() + ") using " + "button# " +  e.getButton(), e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        eventOutput(System.nanoTime() + " Mouse released (# of clicks: "
                + e.getClickCount() + ") using " + "button# " + e.getButton(), e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        eventOutput(System.nanoTime() + " Mouse entered", e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        eventOutput(System.nanoTime() + " Mouse exited", e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        eventOutput(System.nanoTime() + " Mouse dragged", e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        eventOutput(System.nanoTime() + " Mouse moved", e);
    }
}