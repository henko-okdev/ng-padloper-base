package ngdemo.dao.impl;

import ngdemo.dao.GenericDao;
import ngdemo.db.HikariConnPool;
import ngdemo.domain.Territory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static ngdemo.db.DataBaseUtils.closeSilently;

public class H2TerritoryDao implements GenericDao<Territory> {

    private final HikariConnPool pool;

    public H2TerritoryDao() {
        this.pool = HikariConnPool.getInstance();
    }

    public Territory fetch(int id) {
        Connection conn = null;
        String query = "SELECT * FROM TERRITORIES WHERE ID = ?";

        try {
            Territory territory = new Territory();
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Territory parent = fetch(rs.getInt("parent_id"));
                territory = new Territory(rs.getInt("id"), rs.getString("name"), parent,
                        rs.getString("type"), rs.getString("zip"));
            }

            return territory;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public List<Territory> find() {
        Connection conn = null;
        String query = "SELECT * FROM TERRITORIES";

        try {
            List<Territory> territories = new ArrayList<Territory>();
            conn = pool.getConnection();
            ResultSet rs = conn.prepareStatement(query).executeQuery();

            while (rs.next()) {
                Territory parent = fetch( rs.getInt("parent_id") );
                territories.add( new Territory(rs.getInt("id"), rs.getString("name"), parent, rs.getString("type"), rs.getString("zip")) );
            }

            return territories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Territory create(Territory entity) {
        Connection conn = null;
        String query = "INSERT INTO TERRITORIES (NAME, PARENT_ID, TYPE, ZIP) VALUES (?, ?, ?, ?)";

        try {
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query, RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getParent().getId());
            ps.setString(3, entity.getType());
            ps.setString(4, entity.getZip());

            int generatedId = ps.executeUpdate();

            return fetch(generatedId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Territory update(Territory entity) {
        Connection conn = null;
        String query = "UPDATE TERRITORIES SET NAME = ?, TYPE = ?, ZIP = ?, PARENT_ID = ? WHERE ID = ?";

        try {
            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getType());
            ps.setString(3, entity.getZip());
            ps.setInt(4, entity.getParent().getId());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();

            return fetch(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

    public Territory delete(int id) {
        Connection conn = null;
        String query = "DELETE FROM TERRITORIES WHERE ID = ?";

        try {
            Territory deletedTerritory = fetch(id);

            conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

            return deletedTerritory;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeSilently(conn);
        }
    }

}
