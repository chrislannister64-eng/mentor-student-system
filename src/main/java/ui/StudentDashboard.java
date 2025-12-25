package ui;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Attendance;
import model.SessionNote;
import model.User;
import service.SystemService;

import java.util.List;

public class StudentDashboard {
    public static void show(Stage stage, SystemService svc, User student) {
        stage.setTitle("Student Dashboard - " + student.getName());
        VBox root = new VBox(10); root.setPadding(new Insets(10));

        Label attLbl = new Label("Attendance:");
        ListView<String> attList = new ListView<>();
        try {
            List<Attendance> at = svc.getAttendanceForStudent(student.getId());
            for(Attendance a: at) attList.getItems().add("Session " + a.getSessionId() + " -> " + a.getStatus());
        } catch (Exception e){ e.printStackTrace(); }

        Label notesLbl = new Label("Mentor Notes:");
        ListView<String> noteList = new ListView<>();
        try {
            List<SessionNote> notes = svc.getNotesForStudent(student.getId());
            for(SessionNote n: notes) noteList.getItems().add("Session " + n.getSessionId() + ": " + n.getNotes() + " | FollowUp: " + n.getFollowUp());
        } catch (Exception e){ e.printStackTrace(); }

        root.getChildren().addAll(attLbl, attList, notesLbl, noteList);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
