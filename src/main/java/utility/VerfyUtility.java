package utility;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VerfyUtility {
	public static void showWarning(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
