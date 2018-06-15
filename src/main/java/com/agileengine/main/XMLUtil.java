package com.agileengine.main;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLUtil {

    public static Node getSpecificNode(String id, Document originalDoc, XPath originalXPath){
        String idRegex = String.format("//*[@id=\"%s\"]", id);
        XPathExpression everythingOKExpr = null;
        try {
            everythingOKExpr = originalXPath.compile(idRegex);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Node node = null;
        try {
            node = ((NodeList) everythingOKExpr.evaluate(originalDoc, XPathConstants.NODESET)).item(0);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return node;
    }
    
    public static Document getNormalizedDocFromFile(File file) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        try {
             dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = dBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc.getDocumentElement().normalize();

        return doc;
    }

    public static List<Map<String, String>> getDiffsAttributesMaps(Document diffDoc, XPath diffXPath, String nodeName) {

        List<Map<String, String>> diffsAttributesMap = new ArrayList<Map<String, String>>();

        String regex = String.format("//%s", nodeName);

        XPathExpression expr = null;
        NodeList nList = null;

        try {
            expr = diffXPath.compile(regex);
            nList = (NodeList) expr.evaluate(diffDoc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                diffsAttributesMap.add(getAttributesMap(nNode));
            }
        }

        return diffsAttributesMap;
    }

    /**
     * Gets the map of attributes, including also the value of the node, and its path.
     * @param node
     * @return
     */
    public static Map<String, String> getAttributesMap(Node node) {

        Map<String, String> attrMap = new HashMap<String, String>();

        Element everythingOKElement = (Element) node;

        String path = "";
        while (node != null) {
            path = node.getNodeName() + '/' + path;
            node = node.getParentNode();
        }

        attrMap.put("path", path);
        attrMap.put("value", everythingOKElement.getTextContent());

        NamedNodeMap namedNodeMap = everythingOKElement.getAttributes();
        for (int i = 0; i < namedNodeMap.getLength(); i++) {
            Node n = namedNodeMap.item(i);
            attrMap.put(n.getNodeName(), n.getNodeValue());
        }

        return attrMap;
    }

}
