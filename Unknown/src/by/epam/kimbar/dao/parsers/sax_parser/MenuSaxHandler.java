package by.epam.kimbar.dao.parsers.sax_parser;


import by.epam.kimbar.model.entity.Menu;
import by.epam.kimbar.model.MenuTagName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MenuSaxHandler extends DefaultHandler {
    private List<Menu> menuList = new ArrayList<>();
    private Menu menu = new Menu();
    private StringBuilder text;
    private List resOfOptionalDescription = null;
    private List resultSet = null;
    private List optPrice = null;

    public List<Menu> getMenuList(){
        return menuList;
    }


    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if(qName.equals("cold_snack") || qName.equals("hot_snack") || qName.equals("breakfast")){
            menu = new Menu();
            resultSet = new ArrayList();
            resOfOptionalDescription = new ArrayList<>();
            optPrice = new ArrayList();
            menu.setId(attributes.getValue("id"));
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      MenuTagName tags = MenuTagName.valueOf(qName.toUpperCase());
      switch (tags){
          case ID:
              menu.setId(text.toString());
              break;
          case PHOTO:
              menu.setPhotoURL(text.toString());
              break;
          case NAME:
              menu.setName(text.toString());
              break;
          case DESCRIPTION:
              resultSet.add(text.toString());
              break;
          case OPT_DESCRIPTION:
              resOfOptionalDescription.add(text.toString());
              break;
          case PORTION:
              menu.setPortion(text.toString());
              break;
          case PRICE:
              optPrice.add(text.toString());
              break;
          case COLD_SNACK:
              if(resOfOptionalDescription.size() != 0 ) {
                  resultSet.add("на выбор ( " + resOfOptionalDescription + " )");
              }
              menu.setOptPrice(String.valueOf(optPrice).replace("[","").replace("]",""));
              menu.setDescription(String.valueOf(resultSet).replace("[","").replace("]",""));
              menuList.add(menu);
              break;
          case HOT_SNACK:
              if(resOfOptionalDescription.size() != 0 ) {
                  resultSet.add("на выбор ( " + resOfOptionalDescription + " )");
              }
              menu.setOptPrice(String.valueOf(optPrice).replace("[","").replace("]",""));
              menu.setDescription(String.valueOf(resultSet).replace("[","").replace("]",""));
              menuList.add(menu);
              break;
          case BREAKFAST:
              if(resOfOptionalDescription.size() != 0 ) {
                  resultSet.add("на выбор ( " + resOfOptionalDescription + " )");
              }
              menu.setOptPrice(String.valueOf(optPrice).replace("[","").replace("]",""));
              menu.setDescription(String.valueOf(resultSet).replace("[","").replace("]",""));
              menuList.add(menu);
              break;
      }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }
}
