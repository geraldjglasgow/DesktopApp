import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Stats {

    private File[] file_list;
    List<String> files[]; // array of ArrayLists that contain all values in output files
    Set<String> unique[]; // obtains all unique elements in each file
    List<String> matches; // all matches between each file
    Set<Integer>[][] times;
    int match;

    public Stats(File[] list){
        this.file_list = list;
        start();
    }
    private void start(){
        files = new ArrayList[file_list.length];
        unique = new LinkedHashSet[file_list.length];
        read_data();
        matches = new ArrayList<>(unique[0]);
        matches.retainAll(unique[1]);
        match = matches.size();
        times = new LinkedHashSet[file_list.length][matches.size()];
        get_times();
        print_times();
    }

    private void read_data(){
        try {
            int i=0;
            for (File file : file_list) {
                String f = file.toString() + "/output.log";
                BufferedReader Buffone = new BufferedReader(new FileReader(new File(f)));
                List<String> unique = new ArrayList<String>();
                String temp;
                files[i] = new ArrayList<>();
                while ((temp = Buffone.readLine()) != null) {
                    try {
                        files[i].add(temp);
                        String[] temp1 = temp.split("\t");
                        unique.add(temp1[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("ERROR: " + e);
                    }
                }
                this.unique[i] = new LinkedHashSet<>(unique);
                i+=1;
            }
        } catch(java.io.IOException e){
            System.out.println("ERROR: " + e);
        }
    }
    private void get_times(){
        ArrayList<Integer> array = new ArrayList<>();
        int[] arr = new int[2]; arr[0] = 0; arr[1] = 1;
        int k;
        String temp[];
        for(int i  : arr) {
            k = -1;
            for (String ele1 : matches) {
                k += 1;
                for (String ele2 : files[i]) {
                    temp = ele2.split("\t");
                    if (ele1.compareTo(temp[1]) == 0) {
                        array.add(Integer.parseInt(temp[0]));
                    }
                }
                times[i][k] = new LinkedHashSet<>(array);
                array.clear();
            }
        }
    }
    private void print_times(){
        int k,z;
        int one, two;
        Iterator<Integer> itr;
        for(int l=0;l<file_list.length;l++) {                  // number of files
            for (k = 0; k < matches.size(); k++) {             // number of matches
                itr = times[l][k].iterator();
                one = itr.next();
                if(!itr.hasNext()){
                    System.out.print(one);
                }
                z=0;
                while(itr.hasNext()){
                    if(itr.hasNext()) {
                        two = itr.next();
                        if(convert_time(two, one)){
                            one = two;
                            if(!itr.hasNext())
                                System.out.print(one + " ");
                        } else {
                            if (!itr.hasNext()) {
                                System.out.print(one + " " + two + " ");
                            } else {
                                System.out.print(one + " ");
                                one = two;
                            }
                        }
                    }
                    z++;
                }
                System.out.println();
            }
            System.out.println("##################################################################");
        }
        System.out.println(times[0].length + " " + times[1].length);
    }

    private boolean convert_time(int two, int one){
        boolean value = false;
        two = to_seconds(two);
        one = to_seconds(one);
        if(two - one <= 300){
            value = true;
        }
        return value;
    }

    private int to_seconds(int num){
        int seconds = 0;
        seconds += (num / 10000) * 3600;
        seconds += ((num % 10000)/100) * 60;
        seconds += (num % 100);
        return seconds;
    }

    public void time_difference(){

    }
}
