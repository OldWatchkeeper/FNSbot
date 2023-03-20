package org.example.bot;

import org.example.Main;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.*;
import java.io.*;


public class MySAXParser {




    StringBuilder stringBuilder = new StringBuilder();

    private boolean isFound;
    public String INN;
    public String file3;
    String DATA = "";

    public static void setVsegozapisey(int vsegozapisey) {
        MySAXParser.vsegozapisey = vsegozapisey;
    }

    static int vsegozapisey = 0;
    static String vivodteksta;
    int otstup = 0;
    boolean HEAD;

    String probel = "  ";
    public MySAXParser (String file3) throws IOException {

        this.file3 = file3;

    }

    public static String getName2() {
        System.out.println ("переменная всего запиcей = " + vsegozapisey);
        return String.valueOf(vsegozapisey);
    }
    public static String getName3() {
        return vivodteksta;
    }
    String atr;
    public void vivod(String atr) throws ParserConfigurationException, SAXException, IOException {
        this.atr=atr;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SearchingXMLHandler handler = new SearchingXMLHandler("EGRUL");
        new File(file3);
        File x = new File (file3);
        System.out.println("создали нью файл x.toString() " + x.toString());
        System.out.println("создали нью файл file3        " + file3);
        System.out.println("создали нью файл x        " + x);
        System.out.println("INN        " + INN.toString());
        parser.parse(file3, handler);
        System.out.println("запустили парсинг ");

        if (!isFound)
            System.out.println("Элемент не был найден.");
    }

    public class SearchingXMLHandler extends DefaultHandler {
        private String element;
        private boolean isEntered;
        private boolean isIDDOK;
        public SearchingXMLHandler(String element) {
            this.element = element;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            otstup ++;
            if (qName == "EGRUL") {System.out.println("qName равно EGRUL");}
            if (qName == "EGRUL") {DATA = attributes.getValue(0); System.out.println("DATA = " + DATA);}
            if (qName == "СвЮЛ") {System.out.println("qName равно СвЮЛ");}
            if (qName == "СвЮЛ" && attributes.getValue(3).equals(INN)) {isIDDOK = true; vsegozapisey++; try  (FileWriter writer = new FileWriter("D:\\resultFNS.txt", true))
            {
                System.out.println ("DATA = " + DATA);
                System.out.println ("atr = " + atr);
                String lineSeparator = System.getProperty("line.separator");
                writer.write(lineSeparator + String.format("СВЕДЕНИЯ ПО СОСТОЯНИЮ НА: %.10s ", DATA));
                stringBuilder.append(lineSeparator + String.format("СВЕДЕНИЯ ПО СОСТОЯНИЮ НА: %.10s ", DATA));
                // writer.write(String.format(lineSeparator + "Найден элемент <%s>, его атрибуты:", qName) + lineSeparator);
                // writer.write(String.format("local Name: ", localName) + lineSeparator);
                writer.append("\n");
                stringBuilder.append("\n");
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            } }
            if (isEntered && isIDDOK) {

                if (isIDDOK&&!(qName.equals("ГРНДата")||qName.equals("СвУчетНО")||qName.equals("СвНО")||qName.equals("СвПолФЛ")||qName.equals("СвГраждФЛ")||qName.equals("СвОКВЭДДоп")||qName.equals("СведПредДок")||qName.equals("СвЗапЕГРЮЛ")||qName.equals("ВидЗап")||qName.equals("СвМНЮЛ")||qName.equals("Регион")||qName.equals("СвТипУстав")||qName.equals("СвРегПФ")||qName.equals("СвРегФССЭ")||qName.equals("СвОргПФ")||qName.equals("СвРегФСС")||qName.equals("СвОргФСС")||qName.equals("ГРНДатаПерв")||qName.equals("РазмерДоли")||qName.equals("Процент")||qName.equals("СвОКВЭД")||qName.equals("НаимДок")||qName.equals("ДатаДокЭ")||qName.equals("НаимРегион")||qName.equals("ДатаДок")||qName.equals("СвРегОрг")||qName.equals("НомДок") ||qName.equals("ГРНДатаИспрПред") ||qName.equals("СвСтатусЗап") ||qName.equals("СвЛицензия") ||qName.equals("ЛицОргВыдЛиц") ||qName.equals("НаимЛицВидДеят") ||qName.equals("ГРНДатаИспр")||qName.equals("СвСвид"))) {

                    try  (FileWriter writer = new FileWriter("D:\\resultFNS.txt", true))
                    {
                        String lineSeparator = System.getProperty("line.separator");

                        // writer.write(lineSeparator + Integer.toString(otstup));
                        if (otstup==3) {writer.write(lineSeparator); stringBuilder.append(lineSeparator);}
                        String repeated = new String(new char[otstup]). replace("\0", probel);

                        // if (qName == "UL") {writer.write(lineSeparator + "ИНН ЮРЛИЦА:");} else
                        writer.write(lineSeparator + repeated + String.format("<%s>: ", qName));
                        stringBuilder.append(lineSeparator + repeated + String.format("<%s>: ", qName));
                        // writer.write(String.format(lineSeparator + "Найден элемент <%s>, его атрибуты:", qName) + lineSeparator);
                        // writer.write(String.format("local Name: ", localName) + lineSeparator);
                        writer.append("\n");
                        stringBuilder.append("\n");
                        writer.flush();
                    }
                    catch(IOException ex){

                        System.out.println(ex.getMessage());
                    }


                    int i;
                    int length = attributes.getLength();
                    for(i = 0; i < length; i++) {

                        if (attributes.getQName(i).equals("ДатаЗап")) { continue; }
                        if (attributes.getQName(i).equals("ДатаЗаписи")) { continue; }
                        if (attributes.getQName(i).equals("КодОКВЭД")) { continue; }
                        if (attributes.getQName(i).equals("ПрВерсОКВЭД")) { continue; }
                        if (attributes.getQName(i).equals("ГРН")) { continue; }
                        if (attributes.getQName(i).equals("ГРНДата")) { continue; }
                        if (attributes.getQName(i).equals("ПолнНаимОПФ")) { continue; }
                        if (attributes.getQName(i).equals("ИдНом")) { continue; }
                        if (attributes.getQName(i).equals("ВидКод")) { continue; }
                        if (attributes.getQName(i).equals("СпрОПФ")) { continue; }
                        if (attributes.getQName(i).equals("КодОПФ")) { continue; }
                        if (attributes.getQName(i).equals("КодСпОбрЮЛ")) { continue; }
                        if (attributes.getQName(i).equals("КодНО")) { continue; }
                        if (attributes.getQName(i).equals("ВидДолжн")) { continue; }
                        if (attributes.getQName(i).equals("ДатаЛиц")) { continue; }
                        if (attributes.getQName(i).equals("ДатаВып")) { continue; }
                        if (attributes.getQName(i).equals("ОГРН")) { continue; }
                        if (attributes.getQName(i).equals("ДатаОГРН")) { continue; }
                        if (attributes.getQName(i).equals("КПП")) { continue; }




                        try (FileWriter writer = new FileWriter("D:\\resultFNS.txt", true))
                        {
                           // String vivodTekstaVStroku;

                            String lineSeparator2 = System.getProperty("line.separator");

                            writer.write(String.format("%s : %s", attributes.getQName(i), attributes.getValue(i) + lineSeparator2));

                            stringBuilder.append(String.format("%s : %s", attributes.getQName(i), attributes.getValue(i)));
                                //writer.write(String.format("%s", attributes.getValue(i) + " "));
                            writer.append("\n");
                            stringBuilder.append("\n");
                                //writer.write(String.valueOf(HEAD));
                            writer.flush();

                        }
                        catch(IOException ex){

                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
            if (qName.equals(element)) {
                isEntered = true;
                isFound = true;
            }
            //System.out.println("здесь должна происхдить передача текста в телеграм");
            vivodteksta = stringBuilder.toString();
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            otstup --;

            if (qName.equals(element)) {
                isEntered = false;}

            if (qName.equals("СвЮЛ")) {
                isIDDOK = false;
                System.out.print("***");
                System.out.println(" ");

                //System.out.println("конец юридического лица qName: " + qName);
                try (FileWriter writer2 = new FileWriter("D:\\resultFNS.txt", true))
                {

                    //	String lineSeparator3 = System.getProperty("line.separator");
                    //	writer2.write(lineSeparator3);
                    //writer2.write(" видимо, конец юридического лица, qName: " + qName);
                    //временно закоментирую 	writer2.write("***");
                    // writer2.append("\n");
                    writer2.flush();
                }
                catch(IOException ex){System.out.println(ex.getMessage());
                }

            }
        }
    }


}
