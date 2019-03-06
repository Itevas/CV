package com.lelek.cv;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParseXML {

    private static final String INPATH = "src/cv.xml";
    private static Document document;
    private static Map<String, String> nodeMap = new HashMap<>();

    public void parse() {

        File xmlFile = new File(INPATH);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            try {
                document = db.parse(xmlFile);
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Element mainNode = document.getDocumentElement();
        fillNodeMap(mainNode.getFirstChild());
        System.out.println(nodeMap);

    }

    void fillNodeMap(Node node) {
        nodeMap.put(node.getNodeName(), node.getNodeValue());
        for (Node child = node.getFirstChild();
             child != null;
             child = child.getNextSibling()) {
            fillNodeMap(child);

        }
    }

}
