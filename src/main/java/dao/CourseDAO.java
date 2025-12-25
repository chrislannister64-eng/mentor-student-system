package dao;
import model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public List<Course> getAll() throws SQLException {
        try(Connection c=DBUtil.getConnection(); Statement s=c.createStatement()){
            List<Course> out=new ArrayList<>();
            ResultSet rs=s.executeQuery("SELECT * FROM Courses");
            while(rs.next()) out.add(new Course(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            return out;
        }
    }
    public void create(Course cObj) throws SQLException {
        try(Connection c=DBUtil.getConnection(); PreparedStatement ps=c.prepareStatement("INSERT INTO Courses (name,description) VALUES (?,?)")){
            ps.setString(1,cObj.getName()); ps.setString(2,cObj.getDescription()); ps.executeUpdate();
        }
    }
}
