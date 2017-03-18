package presentation.controller;

import java.util.ArrayList;

import org.controlsfx.control.textfield.TextFields;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import vo.StockCodesVO;

public class MenuPaneController {

	@FXML
	private AnchorPane pane;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private VBox listBox;

	@FXML
	private VBox stockBox;

	@FXML
	private VBox compareBox;

	@FXML
	private VBox thermoBox;

	@FXML
	private ToggleButton stockToggleButton;

	@FXML
	private ToggleButton compareToggleButton;

	@FXML
	private ToggleButton thermoToggleButton;

	@FXML
	private ToggleGroup toggleGroup;

	@FXML
	private TextField stockTextField;

	@FXML
	private DatePicker stockBeginDatePicker;

	@FXML
	private DatePicker stockEndDatePicker;

	@FXML
	private Button stockButton;

	@FXML
	private TextField compareFirstTextField;

	@FXML
	private TextField compareSecondTextField;

	@FXML
	private DatePicker compareBeginDatePicker;

	@FXML
	private DatePicker compareEndDatePicker;

	@FXML
	private Button compareButton;

	@FXML
	private DatePicker thermoDatePicker;

	@FXML
	private Button thermoButton;

	private ArrayList<Node> stockNodeList;

	private ArrayList<Node> compareNodeList;

	private ArrayList<Node> thermoNodeList;

	@FXML
	private void initialize() {
		stockNodeList = new ArrayList<>();
		compareNodeList = new ArrayList<>();
		thermoNodeList = new ArrayList<>();
		stockNodeList.addAll(stockBox.getChildren());
		compareNodeList.addAll(compareBox.getChildren());
		thermoNodeList.addAll(thermoBox.getChildren());
		stockBox.getChildren().clear();
		compareBox.getChildren().clear();
		thermoBox.getChildren().clear();
		bind(stockTextField);
		bind(compareFirstTextField);
		bind(compareSecondTextField);
		forbidDate(stockBeginDatePicker);
		forbidDate(stockEndDatePicker);
		forbidDate(compareBeginDatePicker);
		forbidDate(compareEndDatePicker);
		forbidDate(thermoDatePicker);
	}

	@FXML
	private void handleStockToggleButton() {
		showContents(stockBox, stockNodeList);
	}

	@FXML
	private void handleCompareToggleButton() {
		showContents(compareBox, compareNodeList);
	}

	@FXML
	private void handleThermoToggleButton() {
		showContents(thermoBox, thermoNodeList);
	}

	private void showContents(VBox box, ArrayList<Node> nodes) {
		if (box.getChildren().isEmpty()) {
			hide();
			box.getChildren().addAll(nodes);
			fade(box);
		} else {
			box.getChildren().clear();
		}
	}

	private void hide() {
		stockBox.getChildren().clear();
		compareBox.getChildren().clear();
		thermoBox.getChildren().clear();
	}

	private void fade(Node no) {
		FadeTransition fade = new FadeTransition(Duration.seconds(1), no);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.setCycleCount(1);
		fade.setAutoReverse(true);
		fade.play();
	}

	private void bind(TextField textField) {
		TextFields.bindAutoCompletion(textField, StockCodesVO.getInstance().getCodeList());
		TextFields.bindAutoCompletion(textField, StockCodesVO.getInstance().getNameMap().keySet());
	}
	
	private void forbidDate(DatePicker datePicker) {
		datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {

			@Override
			public DateCell call(DatePicker param) {
				return new ForbidDateCell();
			}
		});
			
	}				
}
