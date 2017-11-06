package getdata;

import com.jcraft.jsch.Session;

public interface GetData {

    void start();

    void SendCommand(String cmd);

    Session getConnPWD();

}
