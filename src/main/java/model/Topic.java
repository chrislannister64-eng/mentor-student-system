package model;
public class Topic {
    private int id;
    private int courseId;
    private String name;
    private String description;
    public Topic(int id,int courseId,String name){
        this.id=id;this.courseId=courseId;this.name=name;this.description=description;
    }
    public int getId(){return id;}
    public int getCourseId(){return courseId;}
    public String getName(){return name;}
    public String getDescription(){return description;}
}
