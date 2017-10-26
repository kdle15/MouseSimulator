import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by kdle15 on 6/20/2017.
 */
public class Sessions {
    private int[] targetsize = {20,50};
    private int[] distance = {200,450};
    private int[] direction = {90,135,180,225,270,315,0,45};
    //{"N,90","NW,135","W,180","SW,225","S,270","SE,315","E,0","NE,45"};
    private Map<Integer, String> dict = null;
    private FileWriter[] WRITERS = new FileWriter[32];

    public Sessions() throws IOException {
        dict = new HashMap<>();
        addtodict();
        List keys = new ArrayList(dict.keySet());
        Collections.shuffle(keys);

        for (int i = 0; i < dict.size(); i++) {
            JFrame frame = new JFrame("Task number: " + i);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            //Acess to a random key
            String value = dict.get(keys.get(i));
            int k = 0;
            int direction = 0;
            int targetsize = 0;
            int distance = 0;
            for (int j = 0; j < value.length(); j++){
                if (value.charAt(j) == '_'){
                    direction = Integer.parseInt(value.substring(0,j));
                    k = j;
                }
                else if(value.charAt(j) == '^'){
                    distance = Integer.parseInt(value.substring(k+1,j));
                    targetsize = Integer.parseInt(value.substring(j+1));
                }
            }
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
            int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
            Point startpoint = new Point(x,y);

            String location = "C:\\Users\\kdle15\\Desktop\\Files\\Day1\\Try1\\"+
                    System.nanoTime() +"_"+ direction + "#_" +  distance
                    + "_" + targetsize + "_" + "_newCsvFile.csv";
            File file = new File(location);
            WRITERS[i] = new FileWriter(file,true);
            WRITERS[i].append("TimeStart");
            WRITERS[i].append(',');
            WRITERS[i].append("X-cor");
            WRITERS[i].append(',');
            WRITERS[i].append("Y-cor");
            WRITERS[i].append(',');
            WRITERS[i].append("Action");
            WRITERS[i].append('\n');
            WRITERS[i].flush();

            JComponent ContentPane = new Tasks(startpoint, direction, distance, targetsize, WRITERS[i]);
            frame.setContentPane(ContentPane);

            frame.pack();
            frame.setVisible(true);
            //frame.dispose();
        }
    }

    //add all options inside a dictionary.
    private void addtodict(){
        int index = 0;
        for (int i = 0; i < 1; i++){
        //for (int i = 0; i < direction.length; i++){
            for(int j = 0; j < 1; j++){
            //for(int j = 0; j < distance.length; j++){
                //for (int k = 0; k < targetsize.length; k++) {
                for (int k = 0; k < 2; k++) {
                    dict.put(index, Integer.toString(direction[i]) + "_" + Integer.toString(distance[j])
                            + "^" + Integer.toString(targetsize[k]));
                    index++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Sessions t = new Sessions();
    }
}
