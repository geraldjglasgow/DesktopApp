package getdata;

import com.jcraft.jsch.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Mac extends Get_Info{
    private String directory = "/Users/SSLGhost/Desktop";

    public Mac(){
        start();

    }

    private void start(){
        Session session;
        session = super.getConnRSA();
        if(session != null){
            for(String command : super.cmd){
                send_command(command, session);
            }
            mac_command("scp pi@10.0.0.221:" + "/home/pi/Desktop/"+dir+"" + " " + directory);
            send_command("trash /home/pi/Desktop/"+dir, session);
            session.disconnect();
        }
    }

    private String mac_command(String cmd) {

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader read = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String s;
            while((s=read.readLine()) != null){
                System.out.println(s);
            }
            while((s=err.readLine()) != null){
                System.out.println(s);
            }
            read.close();
            err.close();
        } catch(java.io.IOException e){

        }
        return cmd;
    }
}
