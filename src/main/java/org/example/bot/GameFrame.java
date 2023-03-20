package org.example.bot;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.example.Main;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.xml.sax.SAXException;


public class GameFrame  {
    public String textFieldText13;
    public  String vivodTekstaIzGameFrame;
    public String ZNACHENIE13;
    GameFrame(String prislali) throws IOException, ClassNotFoundException {

        System.out.println("textFieldText13 = " + prislali);
        int j = 0;

                // читаем в mapFromFile из файла F:\ReadingXML\tempMAP

                String newPath = Main.getMapFromFile(prislali);
                System.out.println( " ***************************  newPath = " + newPath);
                if (newPath == null) { MySAXParser.setVsegozapisey(0);
                    System.out.println( "такого ИНН не существует, вызываю execute" );
                    newPath = "nothing.XML";


                }


        System.out.println ("поехали");
        String [] vozvrat3;
    //    File file2 = new File ("C:\\testFNS");                      D:\эксперимент с распаковкой\01.01.2022_FULL\egrul full 2022
        File file2 = new File ("D:\\эксперимент с распаковкой\\01.01.2022_FULL\\egrul full 2022");
        System.out.println( "************************ важно file2 = " + file2);
        Scan scan = new Scan();
        ArrayList<String> l = scan.processFilesFromFolder(file2);

            System.out.println("String file = vozvrat3[j]");
            String [] listvivoda;

            System.out.println("String [] listvivoda");
            String fileFromDB = "file:/"+ "D:/эксперимент с распаковкой/01.01.2022_FULL/egrul full 2022/" + newPath;

            System.out.println("fileFromDB = " + fileFromDB);


            MyXPath myxpath = new MyXPath (fileFromDB,  prislali.toString());

            listvivoda = myxpath.poisk(fileFromDB);
            System.out.println( " listvivoda = " + myxpath.poisk(fileFromDB));


                String atr2 = listvivoda [0];
                System.out.println("atr2 = " + atr2);
                MySAXParser mysaxpparser = new MySAXParser (fileFromDB);
                mysaxpparser.INN = prislali;
                System.out.println (" MySAXParser mysaxpparser = new MySAXParser (file) прошли");
                try {
                    System.out.println (" 	mysaxpparser.vivod(atr2); щас будет");
                    mysaxpparser.vivod(atr2);
                } catch (ParserConfigurationException e2) {
                    e2.printStackTrace();
                } catch (SAXException e2) {
                    e2.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }


        System.out.println("готово, найдено " + MySAXParser.getName2() + " записей");

        if (Integer.parseInt(MySAXParser.getName2())<1) {
            vivodTekstaIzGameFrame = "записей не найдено";
        }
        else vivodTekstaIzGameFrame = MySAXParser.getName3();

    }

    public String getINN() {
        return textFieldText13;
    }

}








