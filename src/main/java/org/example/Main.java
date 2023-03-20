package org.example;

//import org.example.bot.A;
import org.example.bot.GameFrame;
import org.example.bot.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.xml.sax.SAXException;

import java.io.IOException;

public class Main {

    public static HashMap<String, String> mapFromFile;

    public static String getMapFromFile(String INNizChata) {

        String PathIzMain =  mapFromFile.get(INNizChata);
        return PathIzMain;
    }

    public static void main(String[] args) throws TelegramApiException, IOException {


        File toRead = new File("C:\\new HashMap file EGRUL 2022");
        FileInputStream fis = new FileInputStream(toRead);
        System.out.println("чтение из файла в мапу HashMap в GameFrame");
        ObjectInputStream ois = new ObjectInputStream(fis);
       // HashMap<String, String> mapFromFile;
        try {
            mapFromFile = (HashMap<String, String>) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ois.close();
        fis.close();
        System.out.println( "Закончили читать правильный файл в правильную мапу");

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramBot());
        System.out.println("Hello world!");
    }
}