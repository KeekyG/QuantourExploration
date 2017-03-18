package presentation.controller;

import java.time.LocalDate;

import javafx.scene.control.DateCell;

public class ForbidDateCell extends DateCell {
	@Override

	public void updateItem(LocalDate item, boolean empty) {
		super.updateItem(item, empty);

		if ((item.isBefore(LocalDate.of(2005, 2, 2))) || item.isAfter(LocalDate.of(2014, 4, 29))) {
			setDisable(true);
			setStyle("-fx-background-color: #ffc0cb;");
		}
	}
}
