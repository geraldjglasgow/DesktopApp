package getdata;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.SystemUtils;
import java.util.Properties;

public class Get_Info {
    protected String dir = "Output";
    protected String [] cmd = {
            "mkdir /home/pi/Desktop/Test",
            "mv /home/pi/Project/probe-finder/*.log /home/pi/Desktop/"+dir+"/output.log",
            "mv /home/pi/Project/Battery/*.log /home/pi/Desktop/"+dir+"/battery.log",
            "mv /home/pi/Project/Buttons/*.log /home/pi/Desktop/"+dir+"/button.log",
            "mv /home/pi/Project/Temperature/*.log /home/pi/Desktop/"+dir+"/cputemp.log"};

    public void conn(){

        if(SystemUtils.IS_OS_WINDOWS){
            System.out.println("WINDOWS");
        } else if(SystemUtils.IS_OS_MAC_OSX){
            new Mac();
        } else if(SystemUtils.IS_OS_UNIX) {
            System.out.println("UNIX");
        } else {
            System.out.println("OS NOT KNOWN");
        }
    }

    /**
     * Creates SSH connection with RSA key
     * @return
     */
    protected Session getConnRSA(String ip){
        JSch jsch = new JSch();
        String privatekey = "/Users/SSLGhost/.ssh/id_rsa";

        Session session = null;
        try {
            jsch.addIdentity(privatekey);
            session = jsch.getSession("pi", ip, 22);
            session.setConfig("PrefferedAuthentications", "publickey");
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect(3000);
        } catch(JSchException e){
            System.out.println("getConnRSA JSchExcpetion: " + e);
        }
        if(!session.isConnected())
            session=null;
        return session;
    }

    /**
     * Creates SSH connection using password (for development)
     * @return
     */
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

    protected void send_command(String cmd, Session session){
        try {
            Channel chan = session.openChannel("exec");
            ((ChannelExec) chan).setCommand(cmd);
            ((ChannelExec) chan).setPty(false);
            chan.connect();
            chan.disconnect();
        } catch(JSchException e){
            System.out.println("Get_Info->send_command " + e);
        }
    }
}