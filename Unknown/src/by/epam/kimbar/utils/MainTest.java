package by.epam.kimbar.utils;

import by.epam.kimbar.dao.parsers.dom.DomParser;
import org.xml.sax.SAXException;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException, SAXException {
        System.out.println(DomParser.parseByDomParser());
    }
}
