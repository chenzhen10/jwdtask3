package by.epam.kimbar.dao.parser.stax_parser;


import by.epam.kimbar.model.entity.Menu;
import by.epam.kimbar.model.MenuTagName;

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
    private static List resultSet = null;
    private static List optPrice = null;



    public static List<Menu> parseByStaxParser() throws XMLStreamException, FileNotFoundException {

        InputStream inputStream = new FileInputStream("C:\\Users\\Tim\\Desktop\\Unknown\\src\\by\\epam\\kimbar\\view\\Menu.xml");
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        List<Menu> menus = process(reader);


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
                            optional_desc = new ArrayList();
                            optPrice = new ArrayList();
                            break;
                        case BREAKFAST:
                            menu = new Menu();
                            menu.setId(reader.getAttributeValue(null, "id"));
                            resultSet = new ArrayList();
                            optional_desc = new ArrayList();
                            optPrice = new ArrayList();
                            break;
                        case HOT_SNACK:
                            menu = new Menu();
                            menu.setId(reader.getAttributeValue(null, "id"));
                            resultSet = new ArrayList();
                            optional_desc = new ArrayList();
                            optPrice = new ArrayList();
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
                            optional_desc.add(text);
                            break;
                        case PORTION:
                            menu.setPortion(text);
                            break;
                        case PRICE:
                            optPrice.add(text);
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    menuTagName = MenuTagName.getElementTagName(reader.getLocalName());

                    switch (menuTagName) {
                        case COLD_SNACK:
                            if (optional_desc.size() != 0) {
                                resultSet.add("на выбор ( " + optional_desc + " )");
                            }
                            menu.setOptPrice(String.valueOf(optPrice).replace("[","").replace("]",""));
                            menu.setDescription(String.valueOf(resultSet).replace("[", "").replace("]", ""));
                            menus.add(menu);
                            break;
                        case HOT_SNACK:
                            if (optional_desc.size() != 0) {
                                resultSet.add("на выбор ( " + optional_desc + " )");
                            }
                            menu.setOptPrice(String.valueOf(optPrice).replace("[","").replace("]",""));
                            menu.setDescription(String.valueOf(resultSet).replace("[", "").replace("]", ""));
                            menus.add(menu);
                            break;
                        case BREAKFAST:
                            if (optional_desc.size() != 0) {
                                resultSet.add("на выбор ( " + optional_desc + " )");
                            }
                            menu.setOptPrice(String.valueOf(optPrice).replace("[","").replace("]",""));
                            menu.setDescription(String.valueOf(resultSet).replace("[", "").replace("]", ""));
                            menus.add(menu);
                            break;
                    }
            }
        }
        return menus;
    }

}
