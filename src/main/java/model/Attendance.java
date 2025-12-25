package model;
public class Attendance {
    private int id;
    private int sessionId;
    private int studentId;
    private String status;
    public Attendance(int id,int sessionId,int studentId,String status){
        this.id=id;this.sessionId=sessionId;this.studentId=studentId;this.status=status;
    }
    public int getId(){return id;}
    public int getSessionId(){return sessionId;}
    public int getStudentId(){return studentId;}
    public String getStatus(){return status;}
}
