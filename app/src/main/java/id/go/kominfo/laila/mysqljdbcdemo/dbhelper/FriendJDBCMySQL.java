package id.go.kominfo.laila.mysqljdbcdemo.dbhelper;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import id.go.kominfo.laila.mysqljdbcdemo.dao.FriendDao;
import id.go.kominfo.laila.mysqljdbcdemo.model.Friend;

public class FriendJDBCMySQL implements FriendDao {
    private Connection con;

    public FriendJDBCMySQL(String url, String username, String password) {
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            Log.e("Friend JDBC", "Error Create Connection", e);
        }
    }

    @Override
    public void insert(Friend f) {
        String sql = "insert into friend values (?,?,?,?)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            if (f.getId() == 0) {
                pst.setNull(1, f.getId());
            } else {
                pst.setInt(1, f.getId());
            }
            pst.setString(2, f.getName());
            pst.setString(3, f.getAddress());
            pst.setString(4, f.getPhone());
            pst.executeUpdate();

        } catch (SQLException e) {
            Log.e("Friend JDBC", "Error in Insert", e);
        }

    }

    @Override
    public void update(Friend f) {
        String sql = "update friend set name=?, address=?, phone=? where id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, f.getName());
            pst.setString(2, f.getAddress());
            pst.setString(3, f.getPhone());
            pst.setInt(4, f.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            Log.e("Friend JDBC", "Error in Update", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement pst = con.prepareStatement("delete from friend where id=?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            Log.e("Friend JDBC", "Error in Delete", e);
        }
    }

    @Override
    public void deleteAllFriends() {
        try (PreparedStatement pst = con.prepareStatement("delete from friend")) {
            pst.executeUpdate();
        } catch (SQLException e) {
            Log.e("Friend JDBC", "Error in Delete All", e);
        }
        try (PreparedStatement pst = con.prepareStatement("alter table friend auto_increment = 0")) {
            pst.executeUpdate();
        } catch (SQLException e) {
            Log.e("Friend JDBC", "Error in Alter table", e);
        }
    }

    @Override
    public Friend getAFriendById(int id) {
        Friend result = null;
        try (PreparedStatement pst = con.prepareStatement("select * from friend where id=?")) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.first())
                    result = new Friend(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("phone")
                    );
            }
        } catch (SQLException e) {
            Log.e("Friend JDBC", "Error in getAFriendById()", e);
        }
        return result;
    }

    @Override
    public List<Friend> getAllFriends() {
        List<Friend> result = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement("select * from friend");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next())
                result.add(new Friend(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                ));

        } catch (SQLException e) {
            Log.e("Friend JDBC", "Error in getAllFriends()", e);
        }
        return result;
    }
}
