package ngdemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Territory {

    private int id;
    private String name;
    private Territory parent;
    private String type;
    private String zip;

    public Territory() {

    }

    public Territory(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Territory(String name, String type, Territory parent) {
        this.name = name;
        this.parent = parent;
        this.type = type;
    }

    public Territory(String name, Territory parent, String type, String zip) {
        this.name = name;
        this.parent = parent;
        this.type = type;
        this.zip = zip;
    }

    public Territory(int id, String name, String type, Territory parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.type = type;
    }

    public Territory(int id, String name, Territory parent, String type, String zip) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.type = type;
        this.zip = zip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Territory getParent() {
        return parent;
    }

    public void setParent(Territory parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Territory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", type='" + type + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
