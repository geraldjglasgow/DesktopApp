package getdata;
import com.oracle.tools.packager.IOUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.InputStream;

public class Get_Info {



    public Get_Info(){
        conn();
    }

    private void conn(){
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

}