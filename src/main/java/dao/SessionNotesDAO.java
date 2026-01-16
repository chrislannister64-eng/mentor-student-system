package dao;

import model.SessionNote;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionNotesDAO {

    // ADD NOTE
    public void addNote(int sessionId, int studentId, String noteText) throws SQLException {
        String sql = "INSERT INTO notes (session_id, student_id, note_text) VALUES (?, ?, ?)";

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, sessionId);
            ps.setInt(2, studentId);
            ps.setString(3, noteText);

            ps.executeUpdate();
        }
    }

    // GET NOTES BY SESSION
    public List<SessionNote> getBySession(int sessionId) throws SQLException {
        List<SessionNote> list = new ArrayList<>();

        String sql = "SELECT * FROM notes WHERE session_id=?";

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, sessionId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new SessionNote(
                        rs.getInt("id"),
                        rs.getInt("session_id"),
                        rs.getInt("student_id"),
                        rs.getString("note_text")
                ));
            }
        }
        return list;
    }

    // GET NOTES BY STUDENT
    public List<SessionNote> getByStudent(int studentId) throws SQLException {
        List<SessionNote> list = new ArrayList<>();

        String sql = "SELECT * FROM notes WHERE student_id=?";

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new SessionNote(
                        rs.getInt("id"),
                        rs.getInt("session_id"),
                        rs.getInt("student_id"),
                        rs.getString("note_text")
                ));
            }
        }
        return list;
    }
}
