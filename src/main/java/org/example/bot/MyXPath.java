package org.example.bot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class MyXPath {
    public String file2;
    String [] vozvrat;
   /* public String zn1;
    public String zn2;
    public String zn3;
    public String zn4;
    public String zn5;
    public String zn6;
    public String zn7;
    public String zn8;
    public String zn9;
    public String zn10;
    public String zn11;
    public String zn12;*/
    public String zn13;


    public MyXPath(String file2, String zn13) {

        this.zn13 = zn13;
        this.file2 = file2;
    }
    public String [] poisk (String file) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        //  String vozvrat;
        try {
            builder = builderFactory.newDocumentBuilder();
            doc = builder.parse(file);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            // Получаем экзмепляр XPath для создания
            // XPathExpression выражений
            XPath xpath = xpathFactory.newXPath();
            List<String> getMatchesInFile = GetMatchesInFile(doc, xpath);
            System.out.println("Совпадения в файле на позициях: " +
                    getMatchesInFile.toString());
            vozvrat = getMatchesInFile.stream().toArray(String[]::new);
            System.out.println("vozvrat = getMatchesInFile.stream().toArray(String[]::new): " + vozvrat);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return vozvrat;
    }

    public List<String> GetMatchesInFile(Document doc, XPath xpath) {
        List<String> list = new ArrayList<>();

        String ZNACHENIE13 = this.zn13;

        int vibortest = 0;
        String put = "//СвЮЛ[contains(@ИНН,'" + ZNACHENIE13 + "')]";
        System.out.println("put =  " + put);


        try {
            //создаем объект XPathExpression
            // XPathExpression xPathExpression = xpath.compile(

            XPathExpression xPathExpression = xpath.compile(put);
            // получаем список всех тегов, которые отвечают условию
            NodeList nodes = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            // String nodes2 = xpath.evaluate("//UL[.//*[contains(@Фамилия,'А')]]/@IDDOK", doc);
            // проходим по списку и получаем значение с помощью getNodeValue()
            System.out.println("длина nodes " + nodes.getLength());
            System.out.println ("nodes = " + nodes.toString());
            //  System.out.print("nodes2 = " + nodes2+ "\n");
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }










}
