package by.epam.kimbar.dao.parser.dom;


import by.epam.kimbar.model.entity.Menu;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    private static DOMParser domParser = new DOMParser();
    private static Menu menu = null;
    private static List<Menu> menuList = null;
    private static List<Menu> resultList = new ArrayList<>();


    public static List<Menu> parseByDomParser() throws IOException, SAXException {
        domParser.parse("C:\\Users\\Tim\\Desktop\\Unknown\\src\\by\\epam\\kimbar\\view\\Menu.xml");
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

    private static List getNestedDescription(Element element, String childName) {
        NodeList nodes = element.getElementsByTagName(childName);
        ArrayList res = new ArrayList();
        ArrayList desc_e = new ArrayList();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node elem = nodes.item(i);
            Element elem2 = (Element) elem;

            NodeList desc = elem2.getElementsByTagName("opt_description");
            desc_e.add(((Element) desc.item(0)).getTextContent().trim());

        }
        if (!desc_e.isEmpty()) {
            res.add("на выбор ( " + desc_e + " )");
        }
        return res;
    }

    private static List getInnerPrice(Element element, String childName) {
        ArrayList innerPrices = new ArrayList();
        NodeList nodes = element.getElementsByTagName(childName);
        int counter = 0;
        for (int i = 0; i < nodes.getLength(); i++) {
            Node elem = nodes.item(i);
            Element elem2 = (Element) elem;
            NodeList price = elem2.getElementsByTagName("price");
            Element price_e = (Element) price.item(0);
            ++counter;
            if(price_e != null) {
                innerPrices.add(counter + ")" + price_e.getTextContent().trim());
            }
        }
        return innerPrices;
    }


    private static List parseByTagName(NodeList nameOfTheElement) {
        menuList = new ArrayList<>();

        for (int i = 0; i < nameOfTheElement.getLength(); i++) {
            menu = new Menu();
            ArrayList resultSet = new ArrayList();
            Element element = (Element) nameOfTheElement.item(i);

            menu.setId(element.getAttribute("id"));
            menu.setPhotoURL(getSingleChild(element, "photo").getTextContent().trim());
            menu.setName(getSingleChild(element, "name").getTextContent().trim());
            //Merge description & optional description
            if ((getSingleChild(element, "description") != null)) {
                resultSet.add(getSingleChild(element, "description").getTextContent().trim());
            }
            if (getNestedDescription(element, "desc_of_the_addition").size() != 0) {
                resultSet.add(getNestedDescription(element, "desc_of_the_addition"));
            }
            menu.setDescription(String.valueOf(resultSet).replace("[", "").replace("]", ""));

            menu.setPortion(getSingleChild(element, "portion").getTextContent().trim());
            menu.setOptPrice(String.valueOf(getInnerPrice(element, "desc_of_the_addition"))
                    .replace("[", "").replace("]", ""));

            if (getSingleChild(element, "price") != null && menu.getOptPrice().isEmpty()) {
                menu.setOptPrice(getSingleChild(element, "price").getTextContent().trim());
            }

            menuList.add(menu);
        }

        return menuList;
    }


}
