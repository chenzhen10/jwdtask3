package by.epam.kimbar.utils.parsers.sax_parser;


import by.epam.kimbar.beans.Menu;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser {

    public static List<Menu> parseBySaxAndWriteOutput() throws IOException, SAXException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        MenuSaxHandler menuSaxHandler = new MenuSaxHandler();

        reader.setContentHandler(menuSaxHandler);
        reader.parse("C:\\Users\\Tim\\Desktop\\Unknown\\src\\by\\epam\\kimbar\\resources\\Menu.xml");

        List<Menu> results = menuSaxHandler.getMenuList();



        return results;
    }
}
