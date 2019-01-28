package by.epam.kimbar.utils.parsers.dom;


import by.epam.kimbar.beans.Menu;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DomParser {
    private static DOMParser domParser = new DOMParser();
    private static Menu menu = null;
    private static List opt_desc = null;
    private static List<Menu> menuList = null;
    private static List<Menu> resultList = new ArrayList<>();


    public static List<Menu> parseByDomParser() throws IOException, SAXException {
        domParser.parse("C:\\Users\\Tim\\Desktop\\Unknown\\src\\by\\epam\\kimbar\\resources\\Menu.xml");
        Document document = domParser.getDocument();

        Element root = document.getDocumentElement();


        NodeList coldSnackNode = root.getElementsByTagName("cold_snack");
        NodeList hotSnackNode = root.getElementsByTagName("hot_snack");
        NodeList breakfastNode = root.getElementsByTagName("breakfast");


        resultList = new ArrayList<>();
        resultList.addAll(parseByTagName(coldSnackNode));
        resultList.addAll(parseByTagName(hotSnackNode));
        resultList.addAll(parseByTagName(breakfastNode));


        return resultList;

    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nList = element.getElementsByTagName(childName);
        Element child = (Element) nList.item(0);
        return child;
    }

    private static List getNestedChild(Element element, String childName) {
        NodeList nodes = element.getElementsByTagName(childName);
        ArrayList res = new ArrayList();
        ArrayList desc_e = new ArrayList();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node elem = nodes.item(i);
            Element elem2 = (Element) elem;

            NodeList desc = elem2.getElementsByTagName("opt_description");
            NodeList price = elem2.getElementsByTagName("price");
            desc_e.add(((Element) desc.item(0)).getTextContent().trim());
            Element price_e = (Element) price.item(0);
        }
        if(!desc_e.isEmpty()) {
            res.add("на выбор( " + desc_e + " )");
        }

        return res;
    }


    private static List parseByTagName(NodeList nameOfTheElement) {
        menuList = new ArrayList<>();
        ArrayList resultSet = new ArrayList();
        for (int i = 0; i < nameOfTheElement.getLength(); i++) {
            menu = new Menu();
            opt_desc = new ArrayList();
            Element element = (Element) nameOfTheElement.item(i);

            menu.setId(element.getAttribute("id"));
            menu.setPhotoURL(getSingleChild(element, "photo").getTextContent().trim());
            menu.setName(getSingleChild(element, "name").getTextContent().trim());
            //Merge description & optional description
            resultSet.add(getSingleChild(element, "description").getTextContent().trim());
            resultSet.add(getNestedChild(element, "desc_of_the_addition"));

            menu.setDescription(String.valueOf(resultSet).replace("[","").replace("]",""));

            menu.setPortion(getSingleChild(element, "portion").getTextContent().trim());
            menu.setPrice(Double.parseDouble(getSingleChild(element, "price").getTextContent().trim()));
            menu.setOptionalDescription(opt_desc);
            menuList.add(menu);
        }

        return menuList;
    }


}
