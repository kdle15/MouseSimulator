import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by kdle15 on 7/19/2017.
 */
public class Simulate {
    static FileWriter writer = null;
    public static void main(String args[]) throws IOException {
        File file = new File("C:\\Users\\kdle15\\Desktop\\" + System.nanoTime() + "_newCsvFile.csv");
        writer = new FileWriter(file);
        writer.append("time");
        writer.append(',');
        writer.append("x");
        writer.append(',');
        writer.append("y");
        writer.append(',');
        writer.append("event");
        writer.append('\n');
        writer.flush();

        double StartPositionX = 960;
        double StartPositionY = 540;
        int EndPositionX = 1030; // 212
        int EndPoistionY = 469; // 213
        int R = 10;
        long time = 1000000;//1millsec
                  //7077190
        double std = 0.52740941;
        double mean =-0.09360925;
        double dist = Math.sqrt((EndPositionX-960)*(EndPositionX-960) +
                (EndPoistionY - 540)*(EndPoistionY - 540)); //99.70456358662827
        //function for velocity~Distance: 1.159e-09*Dist +  - 7.782e-08 = v
        int count = 0;
        Random r = new Random();
        while (dist > 10 && count < 100000){
            double theta = r.nextGaussian()*std + mean;
            double v = 8.010e-10*dist - 2.156e-08;
            double D = v*1000000;
            //System.out.println(D);
            double angletoXasis = Math.asin((StartPositionY - EndPoistionY)/dist);
            double X, Y;

            if (theta > 0){
                 Y = StartPositionY - D*Math.sin(Math.toRadians(angletoXasis) + theta);
                 X = StartPositionX + D*Math.cos(Math.toRadians(angletoXasis) + theta);

            }
            else {
                X = StartPositionX + D * Math.cos(Math.toRadians(angletoXasis) - theta);
                Y = StartPositionY - D * Math.sin(Math.toRadians(angletoXasis) - theta);
            }
            writer.append(Long.toString(time));
            writer.append(',');
            writer.append((int)X + "");
            writer.append(',');
            writer.append((int)Y + "");
            writer.append(',');
            writer.append("0");
            writer.append('\n');
            count++;
            double olddist = dist;
            dist = Math.sqrt((EndPositionX-X)*(EndPositionX-X) +
                    (EndPoistionY - Y)*(EndPoistionY - Y));
            double t = Math.random();
            if(olddist > dist){
                StartPositionX = X;
                StartPositionY = Y;
                time += 1000000;
            }
            else{
                dist = olddist;
            }
            System.out.println(dist);
        }
        writer.flush();
        writer.close();
    }

}
