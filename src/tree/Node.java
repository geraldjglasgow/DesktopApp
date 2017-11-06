package tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
    Node left, right;
    private String MAC="";
    ArrayList<ArrayList> list = new ArrayList<ArrayList>();



    public Node(String MAC, int time, int index){
        this.MAC = MAC;
        if(index >= list.size()){
            expandList(index);
        }
        list.get(index).add(time);
        left = null;
        right = null;
    }

    public Node(){
        left = null;
        right = null;
    }

    public void AddTime(int time, int index){
        if(index >= list.size()){
            expandList(index);
        }
        list.get(index).add(time);
    }

    public String getMAC(){
        return this.MAC;
    }

    private void expandList(int i){
        if(i == list.size())
            i+=1;
        for(int k=0;k<i;k++){
            list.add(new ArrayList());
        }
    }

    public void printTimes(){
        for(ArrayList<Integer> arr : list){
            for(int i : arr){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
