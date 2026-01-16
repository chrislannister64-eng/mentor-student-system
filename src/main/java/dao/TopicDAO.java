package dao;

import model.Topic;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDAO {

    // ADD TOPIC
    public void addTopic(int sessionId, String topicName) throws SQLException {
        String sql = "INSERT INTO topics (session_id, topic_name) VALUES (?, ?)";

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, sessionId);
            ps.setString(2, topicName);
            ps.executeUpdate();
        }
    }

    // GET TOPICS BY SESSION
    public List<Topic> getBySession(int sessionId) throws SQLException {
        List<Topic> list = new ArrayList<>();
        String sql = "SELECT * FROM topics WHERE session_id=?";

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, sessionId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Topic(
                        rs.getInt("id"),
                        rs.getInt("session_id"),
                        rs.getString("topic_name")
                ));
            }
        }
        return list;
    }
}
