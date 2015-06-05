package ngdemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contact {

    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Territory city;
    private Territory country;

    public Contact() { }

    public Contact(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public Contact(String firstName, String lastName, String phone, String email, Territory city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.country = city.getParent();
    }

    public Contact(int id, String firstName, String lastName, String phone, String email, Territory city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Territory getCity() {
        return city;
    }

    public void setCity(Territory city) {
        this.city = city;
    }

    public Territory getCountry() {
        return country;
    }

    public void setCountry(Territory country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", city=" + city +
                ", country=" + country +
                '}';
    }
}
