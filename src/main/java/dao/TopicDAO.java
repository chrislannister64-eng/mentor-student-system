package dao;
import model.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDAO {
    public List<Topic> getByCourse(int courseId) throws SQLException {
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement("SELECT * FROM Topics WHERE course_id=?")){
            ps.setInt(1,courseId); ResultSet rs=ps.executeQuery(); List<Topic> out=new ArrayList<>();
            while(rs.next()) out.add(new Topic(rs.getInt("id"),rs.getInt("course_id"),rs.getString("name"),rs.getString("description")));
            return out;
        }
    }
    public void create(Topic t) throws SQLException {
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement("INSERT INTO Topics (course_id,name,description) VALUES (?,?,?)")){
            ps.setInt(1,t.getCourseId()); ps.setString(2,t.getName()); ps.setString(3,t.getDescription()); ps.executeUpdate();
        }
    }
}
