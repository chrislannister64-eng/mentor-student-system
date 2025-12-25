package model;
public class SessionNote {
    private int id;
    private int sessionId;
    private int studentId;
    private String notes;
    private String followUp;
    public SessionNote(int id,int sessionId,int studentId,String notes,String followUp){
        this.id=id;this.sessionId=sessionId;this.studentId=studentId;this.notes=notes;this.followUp=followUp;
    }
    public int getId(){return id;}
    public int getSessionId(){return sessionId;}
    public int getStudentId(){return studentId;}
    public String getNotes(){return notes;}
    public String getFollowUp(){return followUp;}
}
