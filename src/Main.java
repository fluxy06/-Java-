import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        BorderPane borderPane = new BorderPane();

        GridPane gridPane = new GridPane();

// Добавляем элементы в сетку с указанием строки и столбца
        gridPane.add(new Label("Label 1"), 0, 5);  // Колонка 0, строка 0
        gridPane.add(new Button("Button 1"), 1, 5);  // Колонка 1, строка 0
        gridPane.add(new Button("Button 2"), 2, 5);  // Колонка 0, строка 1

// Устанавливаем отступы между столбцами и строками
        gridPane.setHgap(10); // Горизонтальный отступ
        gridPane.setVgap(10); // Вертикальный отступ


        Scene scene = new Scene(gridPane, 600, 600);
        stage.setScene(scene);
        stage.setTitle("JavaFX");

        stage.show();
    }
}