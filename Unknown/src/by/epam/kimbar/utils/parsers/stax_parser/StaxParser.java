package by.epam.kimbar.utils.parsers.stax_parser;


import by.epam.kimbar.beans.Menu;
import by.epam.kimbar.utils.MenuTagName;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StaxParser {
    private static XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    private static ArrayList resultSet = null;


    public static List<Menu> parseByStaxParser() {
        List<Menu> menus = null;
        try {
            InputStream inputStream = new FileInputStream("C:\\Users\\Tim\\Desktop\\Unknown\\src\\by\\epam\\kimbar\\resources\\Menu.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

            menus = process(reader);


        } catch (XMLStreamException | FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return menus;
    }

    private static List<Menu> process(XMLStreamReader reader) throws XMLStreamException {

        List<Menu> menus = new ArrayList<>();
        List optional_desc = null;

        Menu menu = null;
        MenuTagName menuTagName = null;

        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    menuTagName = MenuTagName.getElementTagName(reader.getLocalName());

                    switch (menuTagName) {
                        case COLD_SNACK:
                            menu = new Menu();
                            menu.setId(reader.getAttributeValue(null, "id"));
                            resultSet = new ArrayList();
                            break;
                        case BREAKFAST:
                            menu = new Menu();
                            menu.setId(reader.getAttributeValue(null, "id"));
                            resultSet = new ArrayList();
                            break;
                        case HOT_SNACK:
                            menu = new Menu();
                            menu.setId(reader.getAttributeValue(null, "id"));
                            resultSet = new ArrayList();
                            break;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (menuTagName) {
                        case ID:
                            menu.setId(text);
                            break;
                        case PHOTO:
                            menu.setPhotoURL(text);
                            break;
                        case NAME:
                            menu.setName(text);
                            break;
                        case DESCRIPTION:
                            resultSet.add(text);
                            break;
                        case OPT_DESCRIPTION:
                            if(!text.isEmpty()) {
                                resultSet.add(text);
                            }
                            break;
                        case PORTION:
                            menu.setPortion(text);
                            break;
                        case PRICE:
                            menu.setPrice(Double.parseDouble(text));
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    menuTagName = MenuTagName.getElementTagName(reader.getLocalName());

                    switch (menuTagName) {
                        case COLD_SNACK:
                            menu.setDescription(String.valueOf(resultSet).replace("[", "").replace("]", ""));
                            menus.add(menu);
                            break;
                        case HOT_SNACK:
                            menu.setDescription(String.valueOf(resultSet).replace("[", "").replace("]", ""));
                            menus.add(menu);
                            break;
                        case BREAKFAST:
                            menu.setDescription(String.valueOf(resultSet).replace("[", "").replace("]", ""));
                            menus.add(menu);
                            break;
                    }
            }
        }
        return menus;
    }

}
