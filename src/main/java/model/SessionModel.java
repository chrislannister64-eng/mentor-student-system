package model;
import java.time.LocalDate;
public class SessionModel {
    private int id;
    private int mentorId;
    private int courseId;
    private LocalDate sessionDate;
    public SessionModel(int id,int mentorId,int courseId,LocalDate sessionDate){
        this.id=id;this.mentorId=mentorId;this.courseId=courseId;this.sessionDate=sessionDate;
    }
    public int getId(){return id;}
    public int getMentorId(){return mentorId;}
    public int getCourseId(){return courseId;}
    public LocalDate getSessionDate(){return sessionDate;}
}
