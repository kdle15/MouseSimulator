import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by kdle15 on 6/26/2017.
 */
public class Displayprogram extends JPanel {
    private JPanel Screen;
    private Playbacktool playbackscreen;
    private JButton Pause, Start, Foward, PlayBack, Browse;
    private static File file;

    public Displayprogram(){
        setLayout(null);
        Screen = new JPanel();
        Screen.setBounds(new Rectangle(0,0,1440,810));
        Screen.setBorder(BorderFactory.createLineBorder(Color.black));
        //Screen.setBackground(Color.RED);
        add(Screen);
        //add(playbackscreen);
        JPanel buttonspanel = new JPanel();
        buttonspanel.setBounds(new Rectangle(0,810,1440,40));
        buttonspanel.setBorder(BorderFactory.createLineBorder(Color.black));
        //buttonspanel.setBackground(Color.GREEN);
        Pause = new JButton("Pause");
        Start = new JButton("Play");
        Foward = new JButton("Foward");
        PlayBack = new JButton("PlayBack");
        Browse  = new JButton("Browse");
        Browse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("Browse the folder to process");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                    System.out.println(file.getPath());
                    playbackscreen = new Playbacktool(file);
                    addp(playbackscreen);
                } else {
                    System.out.println("No Selection ");
                }
            }
        });

        buttonspanel.add(Foward);
        buttonspanel.add(Start);
        buttonspanel.add(Pause);
        buttonspanel.add(PlayBack);
        buttonspanel.add(Browse);
        add(buttonspanel);
    }

    public void addp(JPanel k){
        Screen.add(k);
    }
    public static void main(String[] args){
        JFrame frame = new JFrame("DisplayDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JComponent ContentPane = new Displayprogram();
        frame.setContentPane(ContentPane);

        frame.pack();
        frame.setVisible(true);
    }
}