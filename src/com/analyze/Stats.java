package com.analyze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import tree.*;

public class Stats {

    private File[] file_list;     // paths to all .log files from devices
    private BTree tree;
    private HashSet<String> matches = new HashSet<>();

    public Stats(File[] list){
        this.file_list = list;
        start();
    }
    private void start(){
        tree = new BTree();
        read_data();
    }

    private void read_data(){
        try {
            int i=0;
            for (File file : file_list) {
                String f = file.toString() + "/output.log";
                BufferedReader Buffone = new BufferedReader(new FileReader(new File(f)));
                String temp;
                while ((temp = Buffone.readLine()) != null) {
                    try {
                        String[] temp1 = temp.split("\t");
                        if(i==0) {
                            tree.insert(temp1[1], Integer.parseInt(temp1[0]),i);
                        } else {
                            Node node;
                            if((node = tree.find(temp1[1])) != null){
                                node.AddTime(Integer.parseInt(temp1[0]), i);
                                matches.add(temp1[1]);
                            }
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("ERROR: " + e);
                    }
                }
                Buffone.close();
                i+=1;
            }
        } catch(java.io.IOException e){
            System.out.println("ERROR: " + e);
        }
    }

    public String getMatches(){
        return Integer.toString(matches.size());
    }

    public void getMatchesTimes(){
        for (String temp : matches) {
            Node node = tree.find(temp);
            if (node != null) {
                System.out.println(node.getMAC());
                node.printTimes();
                System.out.println();
            }
        }
    }
}
