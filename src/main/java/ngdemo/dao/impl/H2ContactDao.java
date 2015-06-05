package ngdemo.dao.impl;

import ngdemo.dao.GenericDao;
import ngdemo.db.HikariConnPool;
import ngdemo.domain.Contact;
import ngdemo.domain.Territory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static ngdemo.db.DataBaseUtils.closeSilently;

public class H2ContactDao implements GenericDao<Contact> {

    private final HikariConnPool pool;
    private final GenericDao<Territory> territoryDao;

    public H2ContactDao() {
        this.pool = HikariConnPool.getInstance();
        this.territoryDao = new H2TerritoryDao();
    }

    public Contact fetch(int id) {
        Connection conn = null;
        String query = "SELECT * FROM CONTACTS WHERE ID = ?";

        try {
            Contact contact = new Contact();
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Territory city = territoryDao.fetch(rs.getInt("city_id"));
                contact = new Contact(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("phone"), rs.getString("email"), city);
            }

            return contact;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public List<Contact> find() {
        Connection conn = null;
        String query = "SELECT * FROM CONTACTS";

        try {
            List<Contact> contacts = new ArrayList<Contact>();
            conn = pool.getConnection();
            ResultSet rs = conn.prepareStatement(query).executeQuery();

            while (rs.next()) {
                Territory city = territoryDao.fetch(rs.getInt("city_id"));
                contacts.add( new Contact(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("phone"), rs.getString("email"), city));
            }

            return contacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Contact create(Contact entity) {
        Connection conn = null;
        String query = "INSERT INTO CONTACTS (FIRST_NAME, LAST_NAME, PHONE, EMAIL, CITY_ID)  VALUES (?, ?, ?, ?, ?)";

        try {
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query, RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getEmail());
            ps.setInt(5, entity.getCity().getId());

            int generatedId = ps.executeUpdate();

            return fetch(generatedId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Contact update(Contact entity) {
        Connection conn = null;
        String query = "UPDATE CONTACTS SET FIRST_NAME = ?, LAST_NAME = ?, PHONE = ?, EMAIL = ?, CITY_ID = ? WHERE ID = ?";

        try {
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query, RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getEmail());
            ps.setInt(5, entity.getCity().getId());
            ps.setInt(6, entity.getId());
            ps.executeUpdate();

            return fetch(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Contact delete(int id) {
        Connection conn = null;
        String query = "DELETE FROM CONTACTS WHERE ID = ?";

        try {
            Contact deletedContact = fetch(id);

            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

            return deletedContact;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }
}
