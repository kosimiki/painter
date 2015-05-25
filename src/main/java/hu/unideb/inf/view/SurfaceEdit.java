package hu.unideb.inf.view;

import hu.unideb.inf.model.Surface;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
 * This class is the controller of the surface editor window.
 * This controller handles inputs and outputs ensures that the input is correct.
 * @author Milós Kosárkár
 *
 */
public class SurfaceEdit {

	@FXML
	private TextField heightField;

	@FXML
	private TextField widthField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField numberOfItemsField;

	/**
	 * Empty initialize method().
	 */
	@FXML
	private void initialize() {
		
	}
	/**
	 * Container of this window.
	 */
	private Stage dialogStage;
	/**
	 * This field tracks if OK button is clicked.
	 */
	private boolean okClicked = false;
	/**
	 * Surface object to be edited.
	 */
	private Surface surface;
	
	
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
		if(heightField.focusedProperty().getValue()){
			heightField.clear();
		}
		if(numberOfItemsField.focusedProperty().getValue()){
			numberOfItemsField.clear();
		}
		if(widthField.focusedProperty().getValue()){
			widthField.clear();
			widthField.setText("");
		}
		if(nameField.focusedProperty().getValue()){
			nameField.clear();
		}
		
	}
	
	/**
	 * This method sets the values for the surface labels.
	 * @param surface object to be edited
	 */
	public void setSurface(Surface surface) {
		this.surface = surface;
		nameField.setText(this.surface.getName());
		heightField.setText(this.surface.getHeight() + "");
		widthField.setText(this.surface.getWidth() + "");
		numberOfItemsField.setText(this.surface.getNumberOfItems() + "");
	}
	
	/**
	 * This method returs the boolean value of the field {@code okClicked}.
	 * @return the boolean value of the field {@code okClicked}
	 */
	public boolean isOkClicked(){
		return okClicked;
	}
	
	/**
	 * This method is called when the OK button is pressed checks if the input is 
	 * valid and if it is then sets the surface object accordign to the input.
	 * At the end the window is closed.
	 */
	public void handleOkClicked() {
		if(isInputValid()){
			surface.setName(nameField.getText());
			surface.setHeight(Double.parseDouble(heightField.getText()));
			surface.setWidth(Double.parseDouble(widthField.getText()));
			surface.setNumberOfItems(Integer.parseInt(numberOfItemsField.getText()));
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
		double height = 0;
		double width = 0;
		int nOfItems = 0;
		if (nameField.getText().isEmpty() || nameField.getText() == null) {
			errorMsg += "Please give a name for the surface. ";
		}
		try {
			width = Double.parseDouble(widthField.getText());
			height = Double.parseDouble(heightField.getText());
		} catch (NumberFormatException e) {
			errorMsg += "Please fill the heigh and width fields with numeric values ( in meters). ";
		}

		try {
			nOfItems = Integer.parseInt(numberOfItemsField.getText());
			
		} catch (NumberFormatException e) {
			errorMsg += "Please fill the number of  surfaces field with a non negative integer value. ";
		}
		if(width<0.0001 || height<0.0001 || nOfItems<1){
			errorMsg +="Height, Width and Number of surfaces fields cannot be 0 or negative.";
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

}
