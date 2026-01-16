package service;

import dao.*;
import model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SystemService {

    private final UserDAO userDAO = new UserDAO();
    private final CourseDAO courseDAO = new CourseDAO();
    private final SessionDAO sessionDAO = new SessionDAO();
    private final AttendanceDAO attendanceDAO = new AttendanceDAO();
    private final TopicDAO topicDAO = new TopicDAO();
    private final SessionNotesDAO sessionNotesDAO = new SessionNotesDAO();

    // ------------------ USER ------------------

    public User login(String email, String password) {
        try {
            return userDAO.login(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createUser(User user) {
        try {
            userDAO.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        try {
            return userDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<User> getStudentsForMentor(int mentorId) {
        try {
            return userDAO.getStudentsByMentor(mentorId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<User> getAllStudents() {
        try {
            return userDAO.getAllStudents();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<User> getAllMentors() {
        try {
            return userDAO.getAllMentors();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public void assignMentor(int studentId, int mentorId) {
        assignMentor(studentId,mentorId);
    }

    // ------------------ COURSES ------------------

    public List<Course> getAllCourses() {
        try {
            return courseDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Course getCourseById(int id) {
        try {
            return courseDAO.getCourseById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createCourse(String name, String description) {
        try {
            courseDAO.create(new Course(0, name, description));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createCourse(Course c) {
        createCourse(c.getName(), c.getDescription());
    }

    // ------------------ SESSIONS ------------------

    public int createSession(int mentorId, int courseId, LocalDate date) {
        try {
            return sessionDAO.createSession(mentorId, courseId, date);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<SessionModel> getSessionsByMentor(int mentorId) {
        try {
            return sessionDAO.getSessionsByMentor(mentorId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public SessionModel getSessionById(int id) {
        try {
            return sessionDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ------------------ ATTENDANCE ------------------

    public void markAttendance(int sessionId, int studentId, String status) {
        try {
            attendanceDAO.markAttendance(sessionId, studentId, status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Attendance> getAttendanceForStudent(int studentId) {
        try {
            return attendanceDAO.getByStudent(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public List<Attendance> getAttendanceBySession(int sessionId) {
        try {
            return attendanceDAO.getBySession(sessionId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Attendance> getAttendanceByStudent(int studentId) {
        try {
            return attendanceDAO.getByStudent(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // ------------------ TOPICS ------------------

    public void addTopic(int sessionId, String topicName) {
        try {
            topicDAO.addTopic(sessionId, topicName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Topic> getTopicsBySession(int sessionId) {
        try {
            return topicDAO.getBySession(sessionId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    // ------------------ SESSION NOTES ------------------

    public void addNote(int sessionId, int studentId, String noteText) {
        try {
            sessionNotesDAO.addNote(sessionId, studentId, noteText);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<SessionNote> getBySession(int sessionId) {
        try {
            return sessionNotesDAO.getBySession(sessionId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<SessionNote> getByStudent(int studentId) {
        try {
            return sessionNotesDAO.getByStudent(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
