package getdata;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.SystemUtils;

import javax.swing.*;
import java.util.Properties;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Get_Info {
    protected String dir = getDate();
    protected String file;
    protected String [] cmd = {
            "mkdir /home/pi/Desktop/" + dir,
            "mv /home/pi/Project/probe-finder/*.log /home/pi/Desktop/"+dir+"/output.log",
            "mv /home/pi/Project/Battery/*.log /home/pi/Desktop/"+dir+"/battery.log",
            "mv /home/pi/Project/Buttons/*.log /home/pi/Desktop/"+dir+"/button.log",
            "mv /home/pi/Project/Temperature/*.log /home/pi/Desktop/"+dir+"/cputemp.log"};
    public JProgressBar p;

    public void conn(JProgressBar p, String file){
        this.p = p;
        this.file =file;
        if(SystemUtils.IS_OS_WINDOWS){
            System.out.println("WINDOWS");
            new Mac();
        } else if(SystemUtils.IS_OS_MAC_OSX){
            System.out.println("MAC");
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

    private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH");
        Date date = new Date();
        return dateFormat.format(date).toString();

    }
}