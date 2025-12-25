package service;
import dao.*;
import model.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SystemService {
    private final UserDAO userDAO = new UserDAO();
    private final CourseDAO courseDAO = new CourseDAO();
    private final TopicDAO topicDAO = new TopicDAO();
    private final SessionDAO sessionDAO = new SessionDAO();
    private final AttendanceDAO attendanceDAO = new AttendanceDAO();
    private final SessionNotesDAO notesDAO = new SessionNotesDAO();

    // Auth
    public User login(String email,String password) throws SQLException { return userDAO.getByEmailAndPassword(email,password); }

    // Mentors
    public List<User> getStudentsForMentor(int mentorId) throws SQLException { return userDAO.getStudentsByMentor(mentorId); }
    public int createSession(int mentorId,int courseId, LocalDate date) throws SQLException { return sessionDAO.createSession(mentorId,courseId,date); }
    public void markAttendance(int sessionId,int studentId,String status) throws SQLException { attendanceDAO.markAttendance(sessionId,studentId,status); }
    public List<Attendance> getAttendanceForSession(int sessionId) throws SQLException { return attendanceDAO.getBySession(sessionId); }
    public void addNote(int sessionId,int studentId,String notes,String followUp) throws SQLException { notesDAO.addNote(sessionId,studentId,notes,followUp); }

    // Students
    public List<Attendance> getAttendanceForStudent(int studentId) throws SQLException { return attendanceDAO.getByStudent(studentId); }
    public List<SessionNote> getNotesForStudent(int studentId) throws SQLException { return notesDAO.getForStudent(studentId); }

    // Admin
    public void createUser(User u) throws SQLException { userDAO.createUser(u); }
    public void assignMentor(int studentId,Integer mentorId) throws SQLException { userDAO.assignMentorToStudent(studentId,mentorId); }
    public List<User> listMentors() throws SQLException { return userDAO.getAllMentors(); }
    public List<User> listStudents() throws SQLException { return userDAO.getAllStudents(); }
    public List<Course> listCourses() throws SQLException { return courseDAO.getAll(); }
    public void createCourse(Course c) throws SQLException { courseDAO.create(c); }
    public void createTopic(Topic t) throws SQLException { topicDAO.create(t); }
    public List<Topic> getTopicsByCourse(int courseId) throws SQLException { return topicDAO.getByCourse(courseId); }
}
