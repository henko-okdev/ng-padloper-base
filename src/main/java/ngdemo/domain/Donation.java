package ngdemo.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Donation {

    private int id;
    private Operator operator;
    private Contact contact;
    private int amount;
    private Date date;

    public Donation(){

    }

    public Donation(Operator operator, Contact contact, int amount, Date date) {
        this.operator = operator;
        this.contact = contact;
        this.amount = amount;
        this.date = date;
    }

    public Donation(int id, Operator operator, Contact contact, int amount, Date date) {
        this.id = id;
        this.operator = operator;
        this.contact = contact;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", operator=" + operator +
                ", contact=" + contact +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
