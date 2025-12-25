package dao;
import model.SessionNote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionNotesDAO {
    public void addNote(int sessionId,int studentId,String notes,String followUp) throws SQLException {
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(
                "INSERT INTO SessionNotes (session_id,student_id,notes,follow_up_tasks) VALUES (?,?,?,?)")){
            ps.setInt(1,sessionId); ps.setInt(2,studentId); ps.setString(3,notes); ps.setString(4,followUp); ps.executeUpdate();
        }
    }
    public List<SessionNote> getForStudent(int studentId) throws SQLException {
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement("SELECT * FROM SessionNotes WHERE student_id=?")){
            ps.setInt(1,studentId); ResultSet rs=ps.executeQuery(); List<SessionNote> out=new ArrayList<>();
            while(rs.next()) out.add(new SessionNote(rs.getInt("id"), rs.getInt("session_id"), rs.getInt("student_id"), rs.getString("notes"), rs.getString("follow_up_tasks")));
            return out;
        }
    }
}
