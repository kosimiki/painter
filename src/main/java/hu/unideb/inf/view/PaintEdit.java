package hu.unideb.inf.view;

import hu.unideb.inf.model.Paint;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

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
	private Paint paint;

	public void setDialogStage(Stage stage) {
		this.dialogStage = stage;
	}
	
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
	
	public void setPaint(Paint paint){
		this.paint = paint;
		brandField.setText(paint.getBrand());
		nameField.setText(paint.getName());
		priceField.setText("" + paint.getPrice());
		sizeField.setText(""+ paint.getSize());
	}
	
	public boolean isOkClicked(){
		return okClicked;
	}

	public void handleOkClicked() {
		if(isInputValid()){
			paint.setBrand( brandField.getText());
			paint.setName(nameField.getText());
			paint.setColor(getHexCode(colorPicker));
			paint.setPrice(Double.parseDouble(priceField.getText()));
			paint.setSize(Double.parseDouble(sizeField.getText()));
			okClicked = true;
            dialogStage.close();
		}
		
	}
	public void handleCancel(){
		dialogStage.close();
	}

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
	private String getHexCode(ColorPicker colorPicker){
		if(Integer.toHexString(colorPicker.getValue().hashCode()).length()<6)
			return "000000";
		else
			
		return Integer.toHexString(colorPicker.getValue().hashCode()).substring(0, 6).toUpperCase();
		
	}
}
