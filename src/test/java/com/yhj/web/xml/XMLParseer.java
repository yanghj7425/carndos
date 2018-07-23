package com.yhj.web.xml;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLParseer {


    @Test
    public void xMLParseerTest() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = createDocumentBuliderFactory();


        DocumentBuilder builder = createDocumentBulider(factory);

        Document doc = builder.parse("/opt/coder/java/security/src/main/resources/generatorConfig.xml");
        Element root = doc.getDocumentElement();

        NodeList nodes = root.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(node.getAttributes().getLength());

            }
        }


    }


    private DocumentBuilderFactory createDocumentBuliderFactory() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        return factory;
    }

    private DocumentBuilder createDocumentBulider(DocumentBuilderFactory factory) throws ParserConfigurationException {
        return factory.newDocumentBuilder();
    }

}
