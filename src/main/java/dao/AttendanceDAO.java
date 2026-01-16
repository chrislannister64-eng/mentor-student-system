package dao;
import model.Attendance;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    public void markAttendance(int sessionId,int studentId,String status) throws SQLException {
        String find = "SELECT id FROM Attendance WHERE session_id=? AND student_id=?";
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(find)){
            ps.setInt(1,sessionId); ps.setInt(2,studentId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                try(PreparedStatement upd = c.prepareStatement("UPDATE Attendance SET status=? WHERE id=?")){
                    upd.setString(1,status); upd.setInt(2,id); upd.executeUpdate();
                }
            } else {
                try(PreparedStatement ins = c.prepareStatement("INSERT INTO Attendance (session_id,student_id,status) VALUES (?,?,?)")){
                    ins.setInt(1,sessionId); ins.setInt(2,studentId); ins.setString(3,status); ins.executeUpdate();
                }
            }
        }
    }

    public List<Attendance> getBySession(int sessionId) throws SQLException {
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement("SELECT * FROM Attendance WHERE session_id=?")){
            ps.setInt(1,sessionId); ResultSet rs=ps.executeQuery(); List<Attendance> out=new ArrayList<>();
            while(rs.next()) out.add(new Attendance(rs.getInt("id"), rs.getInt("session_id"), rs.getInt("student_id"), rs.getString("status")));
            return out;
        }
    }

    public List<Attendance> getByStudent(int studentId) throws SQLException {
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement("SELECT * FROM Attendance WHERE student_id=?")){
            ps.setInt(1,studentId); ResultSet rs=ps.executeQuery(); List<Attendance> out=new ArrayList<>();
            while(rs.next()) out.add(new Attendance(rs.getInt("id"), rs.getInt("session_id"), rs.getInt("student_id"), rs.getString("status")));
            return out;
        }
    }
}
