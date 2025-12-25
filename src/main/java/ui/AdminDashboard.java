package ui;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Course;
import model.User;
import service.SystemService;

public class AdminDashboard {
    public static void show(Stage stage, SystemService svc, User admin) {
        stage.setTitle("Admin Dashboard - " + admin.getName());
        BorderPane root = new BorderPane(); root.setPadding(new Insets(10));

        VBox left = new VBox(8);
        Button refreshBtn = new Button("Refresh");
        ListView<User> mentorsList = new ListView<>();
        ListView<User> studentsList = new ListView<>();
        left.getChildren().addAll(new Label("Mentors"), mentorsList, new Label("Students"), studentsList, refreshBtn);

        VBox center = new VBox(8);
        TextField nameFld = new TextField(); nameFld.setPromptText("Name");
        ComboBox<String> roleBox = new ComboBox<>(FXCollections.observableArrayList("Mentor","Student","Admin"));
        TextField emailFld = new TextField(); emailFld.setPromptText("Email");
        PasswordField passFld = new PasswordField(); passFld.setPromptText("Password");
        Button createBtn = new Button("Create User");
        HBox assignBox = new HBox(8);
        Button assignBtn = new Button("Assign selected mentor to selected student");
        assignBox.getChildren().add(assignBtn);
        center.getChildren().addAll(new Label("Create User"), nameFld, roleBox, emailFld, passFld, createBtn, assignBox);

        VBox right = new VBox(8);
        TextField courseName = new TextField(); courseName.setPromptText("Course name");
        TextField courseDesc = new TextField(); courseDesc.setPromptText("Course desc");
        Button createCourseBtn = new Button("Create Course");
        right.getChildren().addAll(new Label("Courses"), courseName, courseDesc, createCourseBtn);

        root.setLeft(left); root.setCenter(center); root.setRight(right);

        refreshBtn.setOnAction(e -> {
            try {
                mentorsList.setItems(FXCollections.observableArrayList(svc.listMentors()));
                studentsList.setItems(FXCollections.observableArrayList(svc.listStudents()));
            } catch (Exception ex){ ex.printStackTrace(); }
        });
        createBtn.setOnAction(e -> {
            try {
                User u = new User(nameFld.getText(), roleBox.getValue(), emailFld.getText(), passFld.getText());
                svc.createUser(u); showAlert("OK","User created"); refreshBtn.fire();
            } catch (Exception ex){ ex.printStackTrace(); showAlert("Error",ex.getMessage()); }
        });
        assignBtn.setOnAction(e -> {
            User mentor = mentorsList.getSelectionModel().getSelectedItem();
            User student = studentsList.getSelectionModel().getSelectedItem();
            if(mentor==null || student==null){ showAlert("Select", "Choose mentor & student"); return; }
            try { svc.assignMentor(student.getId(), mentor.getId()); showAlert("OK","Assigned"); refreshBtn.fire(); } catch (Exception ex){ ex.printStackTrace(); }
        });

        createCourseBtn.setOnAction(e -> {
            try { svc.createCourse(new Course(0, courseName.getText(), courseDesc.getText())); showAlert("OK","Course created"); } catch (Exception ex){ ex.printStackTrace(); }
        });

        stage.setScene(new Scene(root, 900, 500));
        stage.show();
        refreshBtn.fire();
    }

    private static void showAlert(String t,String m){ Alert a=new Alert(Alert.AlertType.INFORMATION); a.setTitle(t); a.setContentText(m); a.showAndWait(); }
}
