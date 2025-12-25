package ui;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.User;
import model.SessionModel;
import service.SystemService;

import java.time.LocalDate;
import java.util.List;

public class MentorDashboard {
    public static void show(Stage stage, SystemService svc, User mentor) {
        stage.setTitle("Mentor Dashboard - " + mentor.getName());
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Left: students
        ListView<User> studentsList = new ListView<>();
        try { studentsList.setItems(FXCollections.observableArrayList(svc.getStudentsForMentor(mentor.getId()))); } catch (Exception e){ e.printStackTrace(); }
        VBox left = new VBox(new Label("Assigned Students"), studentsList);
        left.setSpacing(8); left.setPadding(new Insets(5));

        // Center: session controls + attendance
        VBox center = new VBox(8);
        HBox newSessionBox = new HBox(8);
        DatePicker datePicker = new DatePicker(LocalDate.now());
        TextField courseIdFld = new TextField(); courseIdFld.setPromptText("Course ID");
        Button createSessionBtn = new Button("Create Session");
        newSessionBox.getChildren().addAll(new Label("Date:"), datePicker, new Label("CourseID:"), courseIdFld, createSessionBtn);

        Label sessionIdLbl = new Label("Session ID: (create a session)");
        ListView<String> attendanceView = new ListView<>();
        HBox markBox = new HBox(8);
        ComboBox<String> statusBox = new ComboBox<>(FXCollections.observableArrayList("Present","Absent","Late"));
        Button markBtn = new Button("Mark Attendance");
        Button noteBtn = new Button("Add Note");
        TextField sessionIdField = new TextField(); sessionIdField.setPromptText("sessionId");
        markBox.getChildren().addAll(new Label("Student:"), new Label("select from left"), new Label("Status:"), statusBox, sessionIdField, markBtn);

        center.getChildren().addAll(new Label("Create Session"), newSessionBox, sessionIdLbl, new Label("Attendance list:"), attendanceView, markBox, noteBtn);
        center.setPadding(new Insets(5));

        root.setLeft(left); root.setCenter(center);

        createSessionBtn.setOnAction(e -> {
            try {
                int courseId = Integer.parseInt(courseIdFld.getText().trim());
                LocalDate d = datePicker.getValue();
                int sid = svc.createSession(mentor.getId(), courseId, d);
                sessionIdLbl.setText("Session ID: " + sid);
            } catch (Exception ex) { ex.printStackTrace(); showAlert("Error", ex.getMessage()); }
        });

        markBtn.setOnAction(e -> {
            User selected = studentsList.getSelectionModel().getSelectedItem();
            if(selected == null) { showAlert("Select", "Please select a student from left"); return; }
            String status = statusBox.getValue();
            if(status==null){ showAlert("Select", "Choose status"); return; }
            try {
                int sid = Integer.parseInt(sessionIdField.getText().trim());
                svc.markAttendance(sid, selected.getId(), status);
                showAlert("OK","Marked " + selected.getName() + " as " + status);
            } catch (Exception ex){ ex.printStackTrace(); showAlert("Error", ex.getMessage()); }
        });

        noteBtn.setOnAction(e -> {
            User sel = studentsList.getSelectionModel().getSelectedItem();
            if(sel==null){ showAlert("Select", "Select student"); return; }
            TextInputDialog d = new TextInputDialog();
            d.setTitle("Add Note"); d.setHeaderText("Add note and follow up (format: note||followup)");
            d.showAndWait().ifPresent(text -> {
                String[] parts = text.split("\\|\\|",2);
                String notes = parts.length>0?parts[0]:"";
                String fu = parts.length>1?parts[1]:"";
                try {
                    int sid = Integer.parseInt(sessionIdField.getText().trim());
                    svc.addNote(sid, sel.getId(), notes, fu);
                    showAlert("Saved","Note saved");
                } catch (Exception ex){ ex.printStackTrace(); showAlert("Error", ex.getMessage()); }
            });
        });

        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }

    private static void showAlert(String t,String m){ Alert a=new Alert(Alert.AlertType.INFORMATION); a.setTitle(t); a.setContentText(m); a.showAndWait(); }
}
