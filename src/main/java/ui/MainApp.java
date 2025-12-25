package ui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;
import service.SystemService;

public class MainApp extends Application {
    private final SystemService svc = new SystemService();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mentor-Student Manager - Login");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20)); grid.setVgap(8); grid.setHgap(10);

        Label emailLbl = new Label("Email:"); TextField emailFld = new TextField();
        Label passLbl = new Label("Password:"); PasswordField passFld = new PasswordField();
        Button loginBtn = new Button("Login");
        grid.add(emailLbl,0,0); grid.add(emailFld,1,0);
        grid.add(passLbl,0,1); grid.add(passFld,1,1);
        grid.add(loginBtn,1,2);

        loginBtn.setOnAction(ev -> {
            String email = emailFld.getText().trim();
            String pass = passFld.getText().trim();
            try {
                User user = svc.login(email, pass);
                if (user == null) { showAlert("Login failed", "Invalid credentials"); return; }
                switch(user.getRole()){
                    case "Mentor": MentorDashboard.show(primaryStage, svc, user); break;
                    case "Student": StudentDashboard.show(primaryStage, svc, user); break;
                    case "Admin": AdminDashboard.show(primaryStage, svc, user); break;
                }
            } catch (Exception ex) { ex.printStackTrace(); showAlert("Error", ex.getMessage()); }
        });

        primaryStage.setScene(new Scene(grid, 420, 200));
        primaryStage.show();
    }

    private void showAlert(String t,String m){ Alert a=new Alert(Alert.AlertType.INFORMATION); a.setTitle(t); a.setContentText(m); a.showAndWait(); }

    public static void main(String[] args){ launch(); }
}
