import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;


import java.sql.*;

public class Main extends Application {

    String url = "jdbc:postgresql://Nikita-MSI:5432/AuntificationFormDataBase";
    String user = "postgres";
    String pass = "123";
    @FXML
    private TextField nameUser; // Поле для ввода логина

    @FXML
    private PasswordField passUser; // Поле для ввода пароля

    @FXML
    private CheckBox INTO;

    @FXML
    private TextField visiblePasswordField = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Загружаем FXML файл
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceOfAunteficationWindow.fxml"));
        loader.setController(this); // Устанавливаем контроллером текущий класс (Main)
        Parent root = loader.load(); // Загружаем интерфейс

        // Создаём сцену
        Scene scene = new Scene(root, 1280, 820);
        // Устанавливаем сцену и показываем окно
        primaryStage.setTitle("Авторизация пользователя");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void ChekInfo() {
        // Получаем данные из текстовых полей
        Alert alert = new Alert(AlertType.ERROR);
        Alert alert2 = new Alert(AlertType.CONFIRMATION);
        String login = nameUser.getText();
        String password = passUser.getText();

        // Проверка на пустые поля
        if (login.isEmpty() || password.isEmpty()) {
            alert.setTitle("Ошибка");
            alert.setContentText("Проверь поля с именем пользователя или паролем, видимо ты ввел что-то не то..");
            alert.showAndWait();
            return; // Выход из метода
        }

        // Проверка на недопустимые символы
        if (login.matches(".*[./\\-_@|;].*")) {
            alert.setTitle("Ошибка");
            alert.setContentText("В поле логина введены недопустимые символы: '.', '/', '-', '_', ';', '|', '@'");
            alert.showAndWait();
            return; // Выход из метода
        }

        System.out.println("Логин: " + login);
        System.out.println("Пароль: " + password);

        // Проверка логина и пароля в базе данных
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String query = "SELECT * FROM public.\"InfoUsers\" WHERE \"Login\" = ? AND \"Password\" = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, login);
                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Успешный вход!");
                        alert2.setTitle("Уведомление");
                        alert2.setContentText("Данные корректны, успешный вход)");
                        alert2.showAndWait();
                        // Дальнейшая логика при успешном входе
                    } else {
                        alert.setTitle("Ошибка");
                        alert.setContentText("Неверный логин или пароль!");
                        alert.showAndWait();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.setTitle("Ошибка");
            alert.setContentText("Ошибка подключения к базе данных: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void passwordVisible() {
        if (INTO.isSelected()) {
            // Если чекбокс выбран, показываем текст пароля
            visiblePasswordField.setText(passUser.getText());
            visiblePasswordField.setLayoutX(passUser.getLayoutX());
            visiblePasswordField.setLayoutY(passUser.getLayoutY());
            visiblePasswordField.setPrefSize(passUser.getPrefWidth(), passUser.getPrefHeight());

            // Получаем родителя passUser и добавляем туда visiblePasswordField
            AnchorPane parent = (AnchorPane) passUser.getParent();
            parent.getChildren().add(visiblePasswordField);  // Добавляем видимое поле
            passUser.setVisible(false);  // Скрываем PasswordField
        } else {
            // Если чекбокс не выбран, возвращаем PasswordField
            passUser.setText(visiblePasswordField.getText());
            passUser.setVisible(true);  // Показываем PasswordField
            AnchorPane parent = (AnchorPane) passUser.getParent();
            parent.getChildren().remove(visiblePasswordField);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}