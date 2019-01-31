package by.epam.kimbar.model;


public enum MenuTagName {
    PHOTO, NAME, DESCRIPTION, DESC_OF_THE_ADDITION, PORTION, PRICE, MENU, COLD_SNACK, HOT_SNACK, BREAKFAST, ID, OPT_DESCRIPTION, OPT_DESCRIPTIONS ;


    public static MenuTagName getElementTagName(String element) {
        switch (element) {
            case "photo":
                return PHOTO;
            case "name":
                return NAME;
            case "description":
                return DESCRIPTION;
            case "desc_of_the_addition":
                return DESC_OF_THE_ADDITION;
            case "portion":
                return PORTION;
            case "price":
                return PRICE;
            case "menu":
                return MENU;
            case "cold_snack":
                return COLD_SNACK;
            case "hot_snack":
                return HOT_SNACK;
            case "breakfast":
                return BREAKFAST;
            case "id":
                return ID;
            case "opt_descriptions":
                return OPT_DESCRIPTIONS;
            case "opt_description":
                return OPT_DESCRIPTION;
            default:
                throw new EnumConstantNotPresentException(MenuTagName.class, element);
        }
    }
    }
