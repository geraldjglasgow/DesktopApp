import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Process {

    private File filelist[];
    private String PATH;
    private boolean DEBUG = true;

    public Process(String PATH){
        this.PATH = PATH;
        get_folder_list(PATH);
        create_temp_folder();
    }

    /**
     * Function takes a PATH and counts sub-directory's
     */
    private void get_folder_list(String PATH){
        try {
            File dir = new File(PATH);
            int sub_dirs = 0;
            File listDir[] = dir.listFiles();
            for (File aListDir1 : listDir) {
                if (aListDir1.isDirectory() && aListDir1.toString().compareTo(PATH + "tempfiles") < 0) {
                    sub_dirs++;
                }
            }
            File filelist[] = new File[sub_dirs];
            sub_dirs = 0;
            for (File aListDir : listDir) {
                if (aListDir.isDirectory() && aListDir.toString().compareTo(PATH + "tempfiles") < 0) {
                    filelist[sub_dirs] = aListDir;
                    sub_dirs++;
                }
            }
            this.filelist = filelist;
        } catch (NullPointerException e){
            if(DEBUG)
                System.out.println(e);
        }
    }

    /**
     * Create a folder called tempfiles
     * @return success or failure of creating folder
     */
    private boolean create_temp_folder(){
        File theDir = new File(PATH + "tempfiles");
        boolean result = false;
        // if the directory does not exist, create it
        if (!theDir.exists()) {
            try{
                theDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                if(DEBUG)
                    System.out.println("Error creating directory");
            }
            if(result) {
                if(DEBUG)
                    System.out.println("Process Complete");
            }
        }
        return result;
    }


    public File[] getFilelist(){
        return this.filelist;
    }

}
