package getdata;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Mac {
    private String[] files = {
            "/home/pi/Project/Battery/*.log",
            "/home/pi/Project/Buttons/*.log",
            "/home/pi/Project/probe-finder/*.log",
            "/home/pi/Project/Temperature/*.log"};

    private String directory = "/Users/SSLGhost/Desktop";

    public Mac(){
        start();
    }

    private void start(){
        for(String file : files){
            mac_command("scp pi@10.0.0.200:" + file + " " + directory);
            mac_command("scp pi@10.0.0.201:" + file + " " + directory);
        }


    }

    private String mac_command(String cmd) {

        try {
            Process p = Runtime.getRuntime().exec(cmd);
        } catch(java.io.IOException e){

        }
        return cmd;
    }
}

