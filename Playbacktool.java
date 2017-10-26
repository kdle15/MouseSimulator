/**
 * Created by kdle15 on 6/16/2017.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by kdle15 on 6/14/2017.
 */
public class Playbacktool extends JPanel implements ActionListener {

    private ArrayList<Mousetracks> dataset = new ArrayList<>();
    ArrayList<Mousetracks> actions;
    Timer timer = null;
    ArrayList<String> info = null;
    File file1 = null;
    String isPressed = "";

    public Playbacktool(File file){
        //read from file:
        file1 = file;
        managedata();
        actions = new ArrayList<>();

        //start the timer.
        long delay = (Long.parseLong(info.get(8)) - Long.parseLong(info.get(4)))/1000000;
        timer = new Timer((int) delay, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics page){
        for (int i = 1; i < actions.size(); i++){
            actions.get(i).draw((Graphics2D) page);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.timer){
            timer.stop();
            //System.out.println(i + ", " + actions.size());
            if (actions.size() < dataset.size()-1) {
                Mousetracks action = dataset.get(actions.size());
                actions.add(action);
                super.repaint();

                //start a new timer
                long delay = action.getDelay();
                //System.out.println(delay);
                timer = new Timer((int) delay, this);
                timer.start();
            }
        }
    }

    private void managedata() {
        Scanner scn = null;
        //store all the raw data
        info = new ArrayList<>();

        try {
            scn = new Scanner(file1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scn.useDelimiter("\n");

        while(scn.hasNext()){
            String j = scn.next();
            Scanner scn2 = new Scanner(j);

            scn2.useDelimiter(",");
            while(scn2.hasNext()){
                info.add(scn2.next());
            }
            scn2.close();
        }
        scn.close();

        // manage data
        for (int i = 4; i < info.size(); i = i+4){
            String actiontype = info.get(i+3);

            //handling mouse moved
            if (actiontype.equals("Mouse moved")){
                Point p1 = new Point(Integer.parseInt(info.get(i+1)), Integer.parseInt(info.get(i+2)));
                Point p2 = new Point(Integer.parseInt(info.get(i+5)), Integer.parseInt(info.get(i+6)));
                long delay =  (Long.parseLong(info.get(i+4)) - Long.parseLong(info.get(i))) / 1000000;
                delay = (delay < 1 ? 1: delay);
                Move m = new Move(p1,p2,(int) delay);
                dataset.add(m);
            }

            // handling mouse pressed
            if(actiontype.length() > 13 && actiontype.substring(0,13).equals("Mouse pressed")){
                Point p1 = new Point(Integer.parseInt(info.get(i+1)), Integer.parseInt(info.get(i+2)));
                String button = actiontype.substring(actiontype.length()-1,actiontype.length());
                long delay =  (Long.parseLong(info.get(i+4)) - Long.parseLong(info.get(i))) / 1000000;
                delay = (delay < 1 ? 1: delay);
                Press p = new Press(p1,button,(int) delay);
                isPressed = button;
                dataset.add(p);
            }

            //handling mouse released
            if (actiontype.length() > 13 && actiontype.substring(0,14).equals("Mouse released")){
                Point p1 = new Point(Integer.parseInt(info.get(i+1)), Integer.parseInt(info.get(i+2)));
                String button = actiontype.substring(actiontype.length()-1,actiontype.length());
                long delay =  (Long.parseLong(info.get(i+4)) - Long.parseLong(info.get(i))) / 1000000;
                delay = (delay < 1 ? 1: delay);
                Released r = new Released(p1,button,(int) delay);
                isPressed = "";
                dataset.add(r);
            }

            //handling mouse dragged
            if (actiontype.equals("Mouse dragged")){
                Point p1 = new Point(Integer.parseInt(info.get(i+1)), Integer.parseInt(info.get(i+2)));
                Point p2 = new Point(Integer.parseInt(info.get(i+5)), Integer.parseInt(info.get(i+6)));
                long delay =  (Long.parseLong(info.get(i+4)) - Long.parseLong(info.get(i))) / 1000000;
                delay = (delay < 1 ? 1: delay);
                Dragged d = new Dragged(p1,p2,isPressed,(int) delay);
                dataset.add(d);
            }

            //handling mouse clicked
            if (actiontype.length() > 13 && actiontype.substring(0,13).equals("Mouse clicked")){
                Point p1 = new Point(Integer.parseInt(info.get(i+1)), Integer.parseInt(info.get(i+2)));
                String button = actiontype.substring(actiontype.length()-1,actiontype.length());
                long delay =  (Long.parseLong(info.get(i+4)) - Long.parseLong(info.get(i))) / 1000000;
                delay = (delay < 1 ? 1: delay);
                Click c = new Click(p1,button,(int) delay);
                dataset.add(c);
            }
        }
        //System.out.println(dataset);
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("MousePlaybackDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        File file = new File("C:\\Users\\kdle15\\Desktop\\Files\\Day1\\Try1\\1461532499846048_newCsvFile.csv");
        JComponent ContentPane = new Playbacktool(file);
        frame.setContentPane(ContentPane);

        frame.pack();
        frame.setVisible(true);
    }
}
