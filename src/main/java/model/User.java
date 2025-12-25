package model;

public class User {
    private int id;
    private String name;
    private String role;
    private String email;
    private String password;
    private Integer assignedMentorId;

    public User(int id, String name, String role, String email, String password, Integer assignedMentorId) {
        this.id = id; this.name = name; this.role = role; this.email = email;
        this.password = password; this.assignedMentorId = assignedMentorId;
    }

    public User(String name, String role, String email, String password) {
        this(0,name,role,email,password,null);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Integer getAssignedMentorId() { return assignedMentorId; }
    public void setAssignedMentorId(Integer id) { this.assignedMentorId = id; }
}
