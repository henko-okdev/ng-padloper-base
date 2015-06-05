package ngdemo.dao.impl;

import ngdemo.dao.GenericDao;
import ngdemo.db.HikariConnPool;
import ngdemo.domain.Contact;
import ngdemo.domain.Donation;
import ngdemo.domain.Operator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static ngdemo.db.DataBaseUtils.closeSilently;

public class H2DonationDao implements GenericDao<Donation>{

    private final HikariConnPool pool;
    private final GenericDao<Operator> operatorDao;
    private final GenericDao<Contact> contactDao;

    public H2DonationDao() {
        this.pool = HikariConnPool.getInstance();
        this.operatorDao = new H2OperatorDao();
        this.contactDao = new H2ContactDao();
    }

    public Donation fetch(int id) {
        Connection conn = null;
        String query = "SELECT * FROM DONATIONS WHERE ID = ?";

        try {
            Donation donation = new Donation();
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Operator operator = operatorDao.fetch( rs.getInt("operator_id") );
                Contact contact = contactDao.fetch( rs.getInt("contact_id") );

                donation = new Donation(rs.getInt("id"), operator, contact, rs.getInt("amount"), new Date( rs.getLong("date") ));
            }

            return donation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public List<Donation> find() {
        Connection conn = null;
        String query = "SELECT * FROM DONATIONS;";

        try {
            List<Donation> donations = new ArrayList<Donation>();
            conn = pool.getConnection();
            ResultSet rs = conn.prepareStatement(query).executeQuery();

            while (rs.next()) {
                Operator operator = operatorDao.fetch( rs.getInt("operator_id") );
                Contact contact = contactDao.fetch( rs.getInt("contact_id") );

                donations.add(new Donation(rs.getInt("id"), operator, contact, rs.getInt("amount"), new Date( rs.getLong("date") )));
            }

            return donations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Donation create(Donation entity) {
        Connection conn = null;
        String query = "INSERT INTO DONATIONS (OPERATOR_ID, CONTACT_ID, AMOUNT, DATE)  VALUES (?, ?, ?, ?);";

        try {
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query, RETURN_GENERATED_KEYS);
            ps.setInt(1, entity.getOperator().getId());
            ps.setInt(2, entity.getContact().getId());
            ps.setInt(3, entity.getAmount());
            ps.setLong(4, entity.getDate().getTime());

            int generatedId = ps.executeUpdate();

            return fetch(generatedId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Donation update(Donation entity) {
        Connection conn = null;
        String query = "UPDATE DONATIONS SET OPERATOR_ID = ?, CONTACT_ID = ?, AMOUNT = ?, DATE = ? WHERE ID = ?";

        try {
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, entity.getOperator().getId());
            ps.setInt(2, entity.getContact().getId());
            ps.setInt(3, entity.getAmount());
            ps.setLong(4, entity.getDate().getTime());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();

            return fetch(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Donation delete(int id) {
        Connection conn = null;
        String query = "DELETE FROM DONATIONS WHERE ID = ?";

        try {
            Donation deletedDonation = fetch(id);

            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

            return deletedDonation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }
}
