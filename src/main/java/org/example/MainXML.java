package org.example;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainXML {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        List<Employee> list = parseXML("src/main/resources/data.xml");
    }

    public static List<Employee> parseXML(String fileName) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory. newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse( new File(fileName));
        Node root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (Node.ELEMENT_NODE == item.getNodeType()) {
                System. out.println( "Текущий узел: " + item.getNodeName() + item.getTextContent());
                List<String> list = Arrays.asList(item.getTextContent().split("\n"));
                List<String> collect = list.stream().map(String::strip).collect(Collectors.toList());
                System.out.println(collect);
            }
        }
//        System.out.println( "Корневой элемент: " + root.getNodeName());
//        read(root);



        return null;
    }

    private static void read(Node node) {
//        NodeList nodeList = node.getChildNodes();
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node_ = nodeList.item(i);
//            if (Node.ELEMENT_NODE == node_.getNodeType()) {
//                System. out.println( "Текущий узел: " + node_.getNodeName() + node_.getTextContent());
//                read(node_);
//            }
//        }
    }
}
