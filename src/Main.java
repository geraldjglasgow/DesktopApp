import getdata.Get_Info;

public class Main {
    public static void main(String[] args) {
        String path = "/Users/SSLGhost/Desktop/ProbemonTests/Test17/";
        //Process p = new Process(path);
        //Stats s = new Stats(p.getFilelist());
        Get_Info data = new Get_Info();
        data.conn();
        System.exit(1);
    }
}
