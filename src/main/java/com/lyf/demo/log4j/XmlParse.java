package com.lyf.demo.log4j;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * 解析帶xsd的xml
 */
public class XmlParse {
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
        String configFileLocation = "/myconfig.xml";
        String xsdFileLocation = "/myxsd.xsd";
        InputStream configInputStream = XmlParse.class.getResourceAsStream(configFileLocation);
        if (configInputStream == null) {
            throw new IllegalArgumentException("can not find resource[" + configFileLocation + "]");
        }

        InputStream xsdInputStream = XmlParse.class.getResourceAsStream(xsdFileLocation);
        if (xsdInputStream == null) {
            throw new IllegalArgumentException("can not find resource[" + xsdFileLocation + "]");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new SAXSource(new InputSource(xsdInputStream)));
        factory.setSchema(schema);

        DocumentBuilder builder = factory.newDocumentBuilder();

        builder.setErrorHandler(new ErrorHandler() {

            @Override
            public void warning(SAXParseException exception) throws SAXException {
                throw new RuntimeException(exception);
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                throw new RuntimeException(exception);
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                throw new RuntimeException(exception);
            }
        });

        Document document = builder.parse(configInputStream);

        System.out.println(document.getXmlVersion());                           // 1.0
        System.out.println(document);                                           // [#document: null]
        System.out.println(document.getElementsByTagName("note").getLength());  // 1
    }
}
