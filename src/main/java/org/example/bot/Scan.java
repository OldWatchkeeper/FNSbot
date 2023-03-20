package org.example.bot;

import java.io.File;
import java.util.ArrayList;

public class Scan {

    public ArrayList<String> listWithFileNames = new ArrayList<>();
    public ArrayList<String> processFilesFromFolder(File folder) {
        System.out.println ("зашли в класс Scan");
        // формируем список файлова, которые вообще есть
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries)
        {
            if (entry.isDirectory())
            {
                System.out.println ("ПАПКА " + entry.getName());
                processFilesFromFolder(entry);
                continue;
            }
            // иначе нам попался файл, обрабатываем его!
            else if
            (entry.isFile() && entry.getName().endsWith(".XML")){

                listWithFileNames.add(entry.toURI().toASCIIString());
                //listWithFileNames.add(entry.getAbsolutePath());
                //    listWithFileNames.add(entry.getName());
         // пока уберем на время       System.out.println ("entry.getName(): " + entry.getName());
                //String absolutePath = file.getAbsolutePath();
            }
        }

        return listWithFileNames;
    }
}
