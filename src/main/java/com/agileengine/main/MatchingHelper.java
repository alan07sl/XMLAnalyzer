package com.agileengine.main;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.List;
import java.util.Map;

public class MatchingHelper {

    private static final String NO_MATCHES = "No matches.";

    private File originalFile;
    private File diffFile;
    private String id;

    private Map<String, String> originalAttributesMap;
    private List<Map<String, String>> diffsAttributesMap;

    public MatchingHelper(String originalFile, String diffFile, String id) {
        this.originalFile = new File(originalFile);
        this.diffFile = new File(diffFile);
        this.id = id;
    }

    public void printBestMatchingPath() {

        String bestMatchingPath = NO_MATCHES;
        Document originalDoc = XMLUtil.getNormalizedDocFromFile(originalFile);
        Document diffDoc = XMLUtil.getNormalizedDocFromFile(diffFile);

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath diffXPath = xPathfactory.newXPath();
        XPath originalXPath = xPathfactory.newXPath();

        Node everythingOKNode = XMLUtil.getSpecificNode(id, originalDoc, originalXPath);

        String nodeName = everythingOKNode.getNodeName();

        if (everythingOKNode != null && everythingOKNode.getNodeType() == Node.ELEMENT_NODE) {
            originalAttributesMap = XMLUtil.getAttributesMap(everythingOKNode);
            diffsAttributesMap = XMLUtil.getDiffsAttributesMaps(diffDoc, diffXPath, nodeName);
            bestMatchingPath = getBestMatchingPath(bestMatchingPath);
        }

        System.out.println(bestMatchingPath);
    }

    private String getBestMatchingPath(String bestMatchingPath) {
        Integer maxMatches = 0;

        for (Map<String, String> attrMap : diffsAttributesMap) {
            Integer matches = 0;
            for (String key : attrMap.keySet()) {
                if (attrMap.get(key).equals(originalAttributesMap.get(key)))
                    matches++;
            }
            if (maxMatches < matches) {
                maxMatches = matches;
                bestMatchingPath = attrMap.get("path");
            }
        }
        return bestMatchingPath;
    }


}
