package by.epam.kimbar.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String photoURL;
    private String name;
    private String description;
    private List<String> optionalDescription;
    private String portion;
    private double price;
    private String optPrice;


    public Menu() {
    }

    public Menu(String id, String photoURL, String name, String description, List<String> optionalDescription, String portion, double price, String optPrice) {
        this.id = id;
        this.photoURL = photoURL;
        this.name = name;
        this.description = description;
        this.optionalDescription = optionalDescription;
        this.portion = portion;
        this.price = price;
        this.optPrice = optPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List getOptionalDescription() {
        return optionalDescription;
    }

    public void setOptionalDescription(List optionalDescription) {
        this.optionalDescription = optionalDescription;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOptPrice() {
        return optPrice;
    }

    public void setOptPrice(String optPrice) {
        this.optPrice = optPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Double.compare(menu.price, price) == 0 &&
                Objects.equals(id, menu.id) &&
                Objects.equals(photoURL, menu.photoURL) &&
                Objects.equals(name, menu.name) &&
                Objects.equals(description, menu.description) &&
                Objects.equals(optionalDescription, menu.optionalDescription) &&
                Objects.equals(portion, menu.portion) &&
                Objects.equals(optPrice, menu.optPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, photoURL, name, description, optionalDescription, portion, price, optPrice);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", photoURL='" + photoURL + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", optionalDescription=" + optionalDescription +
                ", portion='" + portion + '\'' +
                ", price=" + price +
                ", optPrice=" + optPrice +
                '}';
    }
}
