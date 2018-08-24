package filetools;

import java.io.File;
import java.util.ArrayList;

public class FilesCollector {

    public static ArrayList<String> getAllFiles(String dirName) {
        ArrayList<String> files = new ArrayList<String>();

        File diectory = new File(dirName);

        if(diectory.isDirectory()) {
            for(File item:diectory.listFiles())
                files.add(item.toString());
        }

        return files;
    }
}
