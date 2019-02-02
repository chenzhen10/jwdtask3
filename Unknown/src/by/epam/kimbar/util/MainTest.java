package by.epam.kimbar.util;

import by.epam.kimbar.dao.parser.dom.DomParser;
import org.xml.sax.SAXException;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException, SAXException {
        System.out.println(DomParser.parseByDomParser());
    }
}
