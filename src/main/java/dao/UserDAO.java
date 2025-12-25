package dao;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public User getByEmailAndPassword(String email,String password) throws SQLException {
        String q = "SELECT * FROM Users WHERE email=? AND password=?";
        try(Connection c= DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(q)){
            ps.setString(1,email); ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return map(rs);
            return null;
        }
    }

    public User getById(int id) throws SQLException {
        String q="SELECT * FROM Users WHERE id=?";
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(q)){
            ps.setInt(1,id); ResultSet rs=ps.executeQuery();
            if(rs.next()) return map(rs);
            return null;
        }
    }

    public List<User> getStudentsByMentor(int mentorId) throws SQLException {
        String q="SELECT * FROM Users WHERE role='Student' AND assigned_mentor_id=?";
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(q)){
            ps.setInt(1,mentorId);
            ResultSet rs=ps.executeQuery();
            List<User> out=new ArrayList<>();
            while(rs.next()) out.add(map(rs));
            return out;
        }
    }

    public List<User> getAllMentors() throws SQLException {
        String q="SELECT * FROM Users WHERE role='Mentor'";
        try(Connection c=DBUtil.getConnection(); Statement s=c.createStatement()){
            ResultSet rs=s.executeQuery(q); List<User> list=new ArrayList<>();
            while(rs.next()) list.add(map(rs));
            return list;
        }
    }

    public List<User> getAllStudents() throws SQLException {
        String q="SELECT * FROM Users WHERE role='Student'";
        try(Connection c=DBUtil.getConnection(); Statement s=c.createStatement()){
            ResultSet rs=s.executeQuery(q); List<User> list=new ArrayList<>();
            while(rs.next()) list.add(map(rs));
            return list;
        }
    }

    public void createUser(User u) throws SQLException {
        String q="INSERT INTO Users (name,role,email,password,assigned_mentor_id) VALUES (?,?,?,?,?)";
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(q)){
            ps.setString(1,u.getName()); ps.setString(2,u.getRole()); ps.setString(3,u.getEmail());
            ps.setString(4,u.getPassword()); if(u.getAssignedMentorId()==null) ps.setNull(5,Types.INTEGER);
            else ps.setInt(5,u.getAssignedMentorId());
            ps.executeUpdate();
        }
    }

    public void assignMentorToStudent(int studentId, Integer mentorId) throws SQLException {
        String q="UPDATE Users SET assigned_mentor_id=? WHERE id=?";
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(q)){
            if(mentorId==null) ps.setNull(1,Types.INTEGER); else ps.setInt(1,mentorId);
            ps.setInt(2,studentId); ps.executeUpdate();
        }
    }

    private User map(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("name"), rs.getString("role"),
                rs.getString("email"), rs.getString("password"),
                (Integer) rs.getObject("assigned_mentor_id"));
    }
}
