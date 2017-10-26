import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by kdle15 on 6/19/2017.
 */
public class Tasks extends JPanel implements MouseListener, MouseMotionListener,ActionListener {
    private int distance, targetsize;
    private int direction;
    private Point start;
    private JPanel o1,o2;
    private Timer timer;
    private FileWriter writer;
    private boolean StartRecord,isStaying = false;

    public int getDistance() {
        return distance;
    }

    public int getTargetsize() {
        return targetsize;
    }

    public int getDirection() {
        return direction;
    }

    public Tasks(Point startpostion, int direction, int distance, int targetsize, FileWriter writer1){
        this.setLayout(null);
        this.start = startpostion;
        this.distance = distance;
        this.targetsize = targetsize;
        this.direction = direction;
        writer = writer1;

        //start object
        o1 = new JPanel();
        o1.setBounds(new Rectangle(start.x-25,start.y-25,50,50));
        o1.setBackground(Color.RED);
        o1.addMouseListener(this);

        //destination
        double x = start.x + distance*Math.cos(Math.toRadians(direction));
        double y = start.y - distance*Math.sin(Math.toRadians(direction));
        Point endpostion = new Point((int) x,(int) y);
        this.o2 = new CirclePane(targetsize);
        o2.setBounds(endpostion.x-targetsize/4,endpostion.y-targetsize/4,
                targetsize/2+2,targetsize/2+2);
        o2.setBackground(Color.GREEN);
        o2.addMouseListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.add(o1);
        this.add(o2);

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                try {
                    //Time
                    writer.append("0");
                    writer.append(',');
                    //Xcor
                    writer.append("0");
                    writer.append(',');
                    //Ycor
                    writer.append("0");
                    writer.append(',');
                    //Notice
                    writer.append("0");
                    writer.append('\n');
                    writer.flush();
                    writer1.flush();
                    writer1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

//        timer = new Timer(3000, this);
//        timer.start();
    }

    private void eventOutput(String eventDescription, MouseEvent e) {
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
            //Time
            writer.append(eventDescription.substring(0,t));
            writer.append(',');
            //Xcor
            writer.append(Integer.toString(e.getX()));
            writer.append(',');
            //Ycor
            writer.append(Integer.toString(e.getY()));
            writer.append(',');
            //Notice
            writer.append(eventDescription.substring(t+1));
            writer.append('\n');
            writer.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.timer) {
            timer.stop();
            timer = null;
            StartRecord = true;
            System.err.println(System.nanoTime() + " Record started");
          /*
          if(!StartRecord){
              if(isStaying){
                  if(!timer.isRunning()){
                      timer.restart();
                  }
                  else{
                      StartRecord = true;
                  }
              }
              else{
                  if(timer.isRunning()){
                      timer.stop();
                      timer = null;
                  }
              }
          }
           */
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (StartRecord) {
            java.awt.Point p = new java.awt.Point(e.getLocationOnScreen());
            SwingUtilities.convertPointFromScreen(p, e.getComponent());
            String name = e.getComponent().getClass().getName();
            if (name.equals("javax.swing.JPanel")
                    ||name.equals("CirclePane")) {
                //System.out.println(e.getComponent().getClass().getName() + " detected clicked");
                eventOutput(System.nanoTime() + " Mouse clicked (# of clicks: "
                + e.getClickCount() + ") using " + "button# " +  e.getButton(), e);
            }
        }  StartRecord = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        java.awt.Point p = new java.awt.Point(e.getLocationOnScreen());
        SwingUtilities.convertPointFromScreen(p, e.getComponent());
        String name = e.getComponent().getClass().getName();
        if (name.equals("javax.swing.JPanel")
                ||name.equals("CirclePane")) {
            if(!StartRecord) {
                isStaying = true;
                timer = new Timer(3000, this);
                timer.start();
//                eventOutput(System.nanoTime() + " Mouse entered", e);
                //StartRecord = true;
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!StartRecord) {
            java.awt.Point p = new java.awt.Point(e.getLocationOnScreen());
            SwingUtilities.convertPointFromScreen(p, e.getComponent());
            String name = e.getComponent().getClass().getName();
            if (name.equals("javax.swing.JPanel")
                    ||name.equals("CirclePane")) {
//                eventOutput(System.nanoTime() + " Mouse exited", e);
                    if (timer != null) {
                        timer.stop();
                        timer = null;
                    }
                }
                isStaying = false;
            }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (StartRecord){
            //System.out.println(e.getComponent().getClass().getName() + " detected moved");
            eventOutput(System.nanoTime() + " Mouse moved", e);
        }
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setPreferredSize(new Dimension(1000, 1000));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        //get JFrame x and y coordinates
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);;

        //recording file destination
        String location = "C:\\Users\\kdle15\\Desktop\\Files\\Day1\\Try1\\"+
                System.nanoTime() + "_newCsvFile.csv";
        File file = new File(location);

        FileWriter writer1 = new FileWriter(file);
        Tasks ContentPane = new Tasks(new Point(x,y), 90, 260,50,writer1);
        frame.setContentPane(ContentPane);
        writer1.append("TimeStart");
        writer1.append(',');
        writer1.append("X-cor");
        writer1.append(',');
        writer1.append("Y-cor");
        writer1.append(',');
        writer1.append("Action");
        writer1.append('\n');

        frame.pack();
        frame.setVisible(true);
    }
}