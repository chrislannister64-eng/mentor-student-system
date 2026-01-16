package model;

public class SessionNote {

    private int id;
    private int sessionId;
    private int studentId;
    private String noteText;

    public SessionNote(int id, int sessionId, int studentId, String noteText) {
        this.id = id;
        this.sessionId = sessionId;
        this.studentId = studentId;
        this.noteText = noteText;
    }

    // getters only (enough for now)
    public int getId() {
        return id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getNoteText() {
        return noteText;
    }
}
