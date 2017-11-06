package getdata;

import com.jcraft.jsch.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;


public class Mac extends Get_Info implements GetData{
    private String directory = "/Users/SSLGhost/Desktop";
    private String ip;
    private String[] addr = {"10.0.0.200", "10.0.0.201"};

    public Mac(){
        start();
    }

    public void start(){
        Session session=null;
        for(String IP : addr){
            session = super.getConnRSA(IP);
            if(session != null)
                ip = IP;
                break;
        }
        p.setValue(25);
        if(session != null){
            for(String command : super.cmd){
                send_command(command, session);
            }
            p.setValue(50);
            SendCommand("scp -r pi@"+ip+":" + "/home/pi/Desktop/*" + " " + directory); //+dir+"/*.log
            p.setValue(75);
            send_command("trash /home/pi/Desktop/"+dir, session);
            p.setValue(100);
            session.disconnect();
        }
    }

    public void SendCommand(String cmd) {
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
    }

    public Session getConnPWD(){
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession("pi", "10.0.0.221", 22);
            session.setPassword("raspberry");
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
        } catch(JSchException e){
            System.out.println("getConnPWD JSchExcpetion: " + e);
        }
        return session;
    }
}
