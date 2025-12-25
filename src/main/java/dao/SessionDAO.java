package dao;
import model.SessionModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {
    public int createSession(int mentorId,int courseId, LocalDate date) throws SQLException {
        String q="INSERT INTO Sessions (mentor_id,course_id,session_date) VALUES (?,?,?)";
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(q, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,mentorId); ps.setInt(2,courseId); ps.setDate(3, Date.valueOf(date)); ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys(); if(rs.next()) return rs.getInt(1);
            return -1;
        }
    }
    public List<SessionModel> getSessionsByMentor(int mentorId) throws SQLException {
        String q="SELECT * FROM Sessions WHERE mentor_id=?";
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(q)){
            ps.setInt(1,mentorId); ResultSet rs=ps.executeQuery(); List<SessionModel> out=new ArrayList<>();
            while(rs.next()) out.add(new SessionModel(rs.getInt("id"), rs.getInt("mentor_id"), rs.getInt("course_id"), rs.getDate("session_date").toLocalDate()));
            return out;
        }
    }
    public SessionModel getById(int id) throws SQLException {
        String q="SELECT * FROM Sessions WHERE id=?";
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement(q)){
            ps.setInt(1,id); ResultSet rs=ps.executeQuery();
            if(rs.next()) return new SessionModel(rs.getInt("id"), rs.getInt("mentor_id"), rs.getInt("course_id"), rs.getDate("session_date").toLocalDate());
            return null;
        }
    }
}
