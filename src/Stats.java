import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Stats {

    private File[] file_list;     // paths to all .log files from devices
    private List<String> matches; // all matching mac addresses between each file
    private tree.BTree tree = new tree.BTree();

    public Stats(File[] list){
        this.file_list = list;
        start();
    }
    private void start(){
        read_data();
        System.out.println(matches.size());
        getMatchesTimes();
    }

    private void read_data(){
        try {
            Set<String> unique[] = new HashSet[file_list.length];
            int i=0;
            for (File file : file_list) {
                String f = file.toString() + "/output.log";
                BufferedReader Buffone = new BufferedReader(new FileReader(new File(f)));
                String temp;
                unique[i] = new HashSet<>();
                while ((temp = Buffone.readLine()) != null) {
                    try {

                        String[] temp1 = temp.split("\t");
                        tree.insert(temp1[1], Integer.parseInt(temp1[0]));
                        unique[i].add(temp1[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("ERROR: " + e);
                    }
                }
                i+=1;
            }
            matches = new ArrayList<>(unique[0]);
            matches.retainAll(unique[1]);
        } catch(java.io.IOException e){
            System.out.println("ERROR: " + e);
        }
    }

    public void getMatchesTimes(){
        for(String temp : matches){
            tree.Node node = tree.find(temp);
            if(node != null){
                System.out.print(node.getMAC() +  " ");
                //node.printTimes();
                System.out.println();
            }
        }
    }
}
