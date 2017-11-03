import getdata.Get_Info;
import com.codebind.*;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        String path = "/Users/SSLGhost/Desktop/ProbemonTests/Test19/";
        Process p = new Process(path);
        Stats s = new Stats(p.getFilelist());
        //Get_Info data = new Get_Info();
        //data.conn();
        System.exit(1);
//        JFrame frame = new JFrame("APP");
//        frame.setContentPane(new App().mainPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
    }
}
