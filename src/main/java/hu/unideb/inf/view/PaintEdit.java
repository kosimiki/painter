package hu.unideb.inf.view;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.Paint;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
 * This class is the controller of the editor window.
 * This controller handles inputs and outputs ensures that the input is correct.
 * @author Milós Kosárkár
 *
 */
public class PaintEdit {

	@FXML
	private TextField brandField;

	@FXML
	private TextField priceField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField sizeField;
	
	@FXML
	private ColorPicker colorPicker;

	@FXML
	private void initialize() {
		
	}
	
	private Stage dialogStage;
	private boolean okClicked = false;
	private MainApp mainApp;
	/**
	 * This object holds the Paint to be edited.
	 */
	private Paint paint;

	/**
	 * This method sets the stage.
	 * @param stage the container which holds the gui elements
	 */
	public void setDialogStage(Stage stage) {
		this.dialogStage = stage;
	}
	
	/**
	 * This method clears the field is is focused.
	 */
	public void resetValue(){
		if(brandField.focusedProperty().getValue()){
			brandField.clear();
		}
		if(sizeField.focusedProperty().getValue()){
			sizeField.clear();
		}
		if(priceField.focusedProperty().getValue()){
			priceField.clear();
		}
		if(nameField.focusedProperty().getValue()){
			nameField.clear();
		}
		
	}
	/**
	 * This method sets the values for the paint labels.
	 * @param paint object to be edited
	 */
	public void setPaint(Paint paint){
		this.paint = paint;
		brandField.setText(paint.getBrand());
		nameField.setText(paint.getName());
		priceField.setText("" + paint.getPrice());
		sizeField.setText(""+ paint.getSize());
	}
	/**
	 * This method returs the boolean value of the field {@code okClicked}
	 * @return the boolean value of the field {@code okClicked}
	 */
	public boolean isOkClicked(){
		return okClicked;
	}
	/**
	 * This method is called when the OK button is pressed checks if the input is 
	 * valid and if it is then sets the paint object accordign to the input.
	 * At the end the window is closed.
	 */
	public void handleOkClicked() {
		if(isInputValid()){
			paint.setBrand( brandField.getText());
			paint.setName(nameField.getText());
			paint.setColor(mainApp.getHexCode(colorPicker));
			paint.setPrice(Double.parseDouble(priceField.getText()));
			paint.setSize(Double.parseDouble(sizeField.getText()));
			okClicked = true;
            dialogStage.close();
		}
		
	}
	/**
	 * This method is called when the CANCEL button is clicked.
	 * It closes the window.
	 */
	public void handleCancel(){
		dialogStage.close();
	}
	/**
	 * This method checks if the input is valid.
	 * @return true if the input is valid, if not a warning is showed to the user then returns with false
	 */
	public boolean isInputValid() {
		String errorMsg = "";
		double price = 0;
		double size = 0;
		
		if (nameField.getText().isEmpty() || nameField.getText() == null) {
			errorMsg += "Name field cannot be empty. ";
		}
		
		if (brandField.getText().isEmpty() || brandField.getText() == null) {
			errorMsg += "Brand field cannot be empty. ";
		}
		
		try {
			price = Double.parseDouble(priceField.getText());
		} catch (NumberFormatException e) {
			errorMsg += "Please fill the price field with numeric value.";
		}
		
		try {
			size = Double.parseDouble(sizeField.getText());
		} catch (NumberFormatException e) {
			errorMsg += "Please fill the covarage field with numeric value.";
		}

		
		if(price<0.0001 || size<0.0001){
			errorMsg +="Price and size fields cannot be 0 or negative.";
		}
		if (errorMsg.isEmpty()) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMsg);
			alert.showAndWait();
			return false;

		}
		
	}

	/**
	 * This method sets the {@code MainApp}.
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}
	

}
