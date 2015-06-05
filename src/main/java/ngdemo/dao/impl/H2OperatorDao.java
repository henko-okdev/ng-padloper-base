package ngdemo.dao.impl;

import ngdemo.dao.GenericDao;
import ngdemo.db.HikariConnPool;
import ngdemo.domain.Operator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static ngdemo.db.DataBaseUtils.closeSilently;

public class H2OperatorDao implements GenericDao<Operator> {

    private final HikariConnPool pool;

    public H2OperatorDao() {
        this.pool = HikariConnPool.getInstance();
    }

    public Operator fetch(int id) {
        Connection conn = null;
        String query = "SELECT * FROM OPERATORS WHERE ID = ?";

        try {
            Operator operator = new Operator();
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                operator = new Operator(rs.getInt("id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("phone"), rs.getString("email"));
            }

            return operator;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public List<Operator> find() {
        Connection conn = null;
        String query = "SELECT * FROM OPERATORS;";

        try {
            List<Operator> operators = new ArrayList<Operator>();
            conn = pool.getConnection();
            ResultSet rs = conn.prepareStatement(query).executeQuery();

            while (rs.next()) {
                operators.add(new Operator(rs.getInt("id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("phone"), rs.getString("email")));
            }

            return operators;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Operator create(Operator entity) {
        Connection conn = null;
        String query = "INSERT INTO OPERATORS (FIRST_NAME, LAST_NAME, PHONE, EMAIL) VALUES (?, ?, ?, ?);";

        try {
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query, RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getEmail());

            int generatedId = ps.executeUpdate();

            return fetch(generatedId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Operator update(Operator entity) {
        Connection conn = null;
        String query = "UPDATE OPERATORS SET FIRST_NAME = ?, LAST_NAME = ?, PHONE = ?, EMAIL = ? WHERE ID = ?";

        try {
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query, RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getEmail());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();

            return fetch(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Operator delete(int id) {
        Connection conn = null;
        String query = "DELETE FROM OPERATORS WHERE ID = ?";

        try {
            Operator deletedOperator = fetch(id);

            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

            return deletedOperator;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }


}
