package tree;

import java.util.ArrayList;

public class Node {
    Node left, right;
    private String MAC="";
    private ArrayList<Integer> times = new ArrayList<>();

    public Node(String MAC, int time){
        this.MAC = MAC;
        this.times.add(time);
        left = null;
        right = null;
    }

    public Node(){
        left = null;
        right = null;
    }

    public void AddTime(int time){
        this.times.add(time);
    }

    public String getMAC(){
        return this.MAC;
    }

    public void printTimes(){
        for(int i : times){
            System.out.print(i + " ");
        }
    }

}
