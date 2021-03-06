package hu.unideb.inf.view;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.CustomColor;
import hu.unideb.inf.model.Paint;
import hu.unideb.inf.model.Surface;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;


/**
 * This is the controller of the main view. This class manages
 * the communication between the view described by the .fxml file and the
 * mainApp controller.
 * @author Miklós Kosárkár
 *
 */
public class MainViewController {
	/**
	 *  Color picker object for color inputs.
	 */
	@FXML
	private ColorPicker customColorPicker;
	/**
	 *  Button object is required for the controller only to be able to set it disabled.
	 */
	@FXML
	private Button saveToFileButton;
	
	@FXML
	private TableColumn<Paint, String> brandCol;
	@FXML
	private Rectangle coloredRec;
	@FXML
	private Rectangle coloredRectangle;
	@FXML
	private TableColumn<Surface, Number> widthCol;
	@FXML
	private TableColumn<Surface, String> nameColNotToPaint;
	@FXML
	private TableView<Paint> brandTable;
	@FXML
	private TableView<Paint> editionTable;
	@FXML
	private TableColumn<Paint, String> et_Brand;
	@FXML
	private TableColumn<Paint, String> et_Name;
	@FXML
	private TableColumn<Paint, String> et_Color;
	@FXML
	private TableColumn<Paint, Number> et_Price;
	@FXML
	private TableColumn<Paint, Number> et_Size;
	@FXML
	private TableView<Paint> colorTable;
	@FXML
	private TableColumn<Surface, String> nameCol;
	@FXML
	private TableColumn<Paint, String> colorCol;
	@FXML
	private TableColumn<Surface, Number> heightCol;
	@FXML
	private TableColumn<Surface, Number> widthColNotToPaint;
	@FXML
	private TableView<Surface> allSurfacesTable;
	@FXML
	private TableView<Surface> surfacesNotToPaintTable;
	@FXML
	private Label colorLable;
	@FXML
	private Label requiredPaintsLabel;
	@FXML
	private Label cheapestLabel;
	@FXML
	private TableColumn<Surface, Number> heightColNotToPaint;
	@FXML
	private Tab custonColorTab;
	@FXML
	private Tab databaseTab;
	@FXML
	private Label costLabel;
	@FXML
	private TableColumn<Surface, Number> nOfItemsColNotToPaint;
	@FXML
	private Label surfaceLabel;
	@FXML
	private Label brandLabel;
	@FXML
	private Tab presetColorTab;
	@FXML
	private TableColumn<Surface, Number> numberOfItemsCol;
	
	/**
	 * This objects holds the main application. 
	 * Through this lists and methods can be accessed.
	 */
	private MainApp mainApp;
	/**
	 * This boolean field is required to trace if the custom color is used or not.
	 */
	private boolean isCustomColor = false;
	/**
	 * Logger object for logging.
	 */
	private static Logger	logger = LoggerFactory.getLogger(MainViewController.class);
	
	/**
	 * This method sets some listeners and values for table cells.
	 */
	@FXML
	private void initialize() {

		brandCol.setCellValueFactory(cellData -> cellData.getValue()
				.getBrandProperty());
		colorCol.setCellValueFactory(cellData -> cellData.getValue()
				.getNameProperty());

		nameCol.setCellValueFactory(cellData -> cellData.getValue()
				.getNameProperty());
		widthCol.setCellValueFactory(cellData -> cellData.getValue()
				.getWidthProperty());
		heightCol.setCellValueFactory(cellData -> cellData.getValue()
				.getHeightProperty());
		numberOfItemsCol.setCellValueFactory(cellData -> cellData.getValue()
				.getNumberOfItemsProperty());

		nameColNotToPaint.setCellValueFactory(cellData -> cellData.getValue()
				.getNameProperty());
		widthColNotToPaint.setCellValueFactory(cellData -> cellData.getValue()
				.getWidthProperty());
		heightColNotToPaint.setCellValueFactory(cellData -> cellData.getValue()
				.getHeightProperty());
		nOfItemsColNotToPaint.setCellValueFactory(cellData -> cellData
				.getValue().getNumberOfItemsProperty());

		et_Brand.setCellValueFactory(cellData -> cellData.getValue()
				.getBrandProperty());
		et_Name.setCellValueFactory(cellData -> cellData.getValue()
				.getNameProperty());

		et_Price.setCellValueFactory(cellData -> cellData.getValue()
				.getPriceProperty());
		et_Size.setCellValueFactory(cellData -> cellData.getValue()
				.getSizeProperty());
	
		
		saveToFileButton.setDisable(true);

		brandTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					updateColorTable(newValue);
					colorLable.setText("");
					coloredRec.setFill(Color.valueOf("FFFFFF"));
					requiredPaintsLabel.setText("");
					cheapestLabel.setText("");
				});

		colorTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> {
							if (newValue != null) {
								isCustomColor = false;
								updateSurfaceLabel();
								cheapestLabel.setText(mainApp
										.cheapestOfThisColor(newValue)
										.getBrand());
								colorLable.setText(newValue.getName());
								coloredRec.setFill(newValue.getFXColor());
								updateCost();
							}
						});
		editionTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						coloredRectangle.setFill(newValue.getFXColor());

					}
				});

	}
	
	/**
	 * This method returns true if a color is selected from the color table.
	 * @return true if a color is selected from the color table.
	 */
	private boolean isColorSelected() {
		if (!colorLable.getText().isEmpty())
			return true;
		else
			return false;
	}
	/**
	 * This methods updates the color table. It is called when any change happen in the Paint list
	 * or another Brand is selected.
	 * @param paint which is selected in the brand table
	 */
	private void updateColorTable(Paint paint) {
		if (paint == null) {
			colorTable.setItems(null);
		} else {
			brandLabel.setText(paint.getBrand());
			List<Paint> list = mainApp.getUniqueColors(mainApp.getPaintData())
					.stream()
					.filter(p -> p.getBrand().equals(paint.getBrand()))
					.collect(Collectors.toList());
			colorTable.setItems(FXCollections.observableArrayList(list));
		}
	}
	/**
	 * This method sets the main application from the controller and 
	 * also sets the tables to their initial state.
	 * @param mainApp the {@code MainApp} object
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		mainApp.setFile();

		mainApp.getPaintData().addListener(new ListChangeListener<Paint>() {

			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends Paint> c) {
				saveToFileButton.setDisable(false);
				updateCost();
			}

		});

		brandTable.setItems(FXCollections.observableArrayList(mainApp
				.getUniqueBrands()));
		allSurfacesTable.setItems(mainApp.getAllSurfaceData());
		surfacesNotToPaintTable.setItems(mainApp.getNotToPaintSurfaceData());
		editionTable.setItems(mainApp.getPaintData());

	}
	
	/**
	 * This method is responsible for handling new {@code Surface}s from the GUI. 
	 */
	@FXML
	private void handleNewSurface() {
		Surface temp = new Surface();
		boolean okClicked = mainApp.showEditDialog(temp);
		if (okClicked) {
			mainApp.getAllSurfaceData().add(temp);
			allSurfacesTable.setItems(mainApp.getAllSurfaceData());
			updateSurfaceLabel();
			if (isColorSelected()) {
				updateCost();

			}

		}
	}
	
	/**
	 * This method is responsible for handling new {@code Paint}s from the GUI.
	 * Only unique paints are accepted.
	 */
	@FXML
	private void handleNewPaint() {
		Paint temp = new Paint();
		boolean okClicked = mainApp.showPaintEditDialog(temp);
		if (okClicked) {
			if (!mainApp.getPaintData().contains(temp))
				mainApp.getPaintData().add(temp);
			else {
				Alert alert = new Alert(AlertType.WARNING);
				logger.warn("Alrady in table!");
				alert.initOwner(mainApp.getPrimaryStage());
				alert.setHeaderText("Paint already in table!");
				alert.setContentText("You can only store unique paints.");
				alert.showAndWait();
			}
			editionTable.setItems(mainApp.getPaintData());
			updateBrandTable();
		}
	}
	
	/**
	 * This method is responsible for calling the file saving method of the MainApp;
	 * A window will show up if the operation failed or succeeded.
	 */
	@FXML
	private void handleSaveToFile() {
		saveToFileButton.setDisable(true);
		if (mainApp.saveToFile(mainApp.getPaintData())) {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setHeaderText("Successful!");
			alert.setContentText("The changes you made were successfully saved to: "
					+ mainApp.getPath());
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setHeaderText("Save Failed!");
			alert.setContentText("The changes you made were failed saved to: "
					+ mainApp.getPath());
			logger.debug("failed to save to " + mainApp.getPath());
			alert.showAndWait();
		}

	}
	/**
	 * This method is responsible for handling new {@code Surface}s from the GUI. 
	 */
	@FXML
	private void handleNewSurfaceNTP() {
		Surface temp = new Surface();
		boolean okClicked = mainApp.showEditDialog(temp);
		if (okClicked) {
			mainApp.getNotToPaintSurfaceData().add(temp);
			surfacesNotToPaintTable
					.setItems(mainApp.getNotToPaintSurfaceData());
			updateSurfaceLabel();
			if (isColorSelected())
				updateCost();
		}

	}
	/**
	 * This method is responsible for handling the edition of a {@code Surface}
	 */
	@FXML
	private void handleEdit() {
		Surface selected = allSurfacesTable.getSelectionModel()
				.getSelectedItem();
		Surface selectedNTP = surfacesNotToPaintTable.getSelectionModel()
				.getSelectedItem();
		if (selected != null && selectedNTP == null) {
			boolean okClicked = mainApp.showEditDialog(selected);
			if (okClicked) {
				allSurfacesTable.setItems(mainApp.getAllSurfaceData());
				updateSurfaceLabel();
				if (isColorSelected())
					updateCost();
			}
		} else {
			if (selected == null && selectedNTP != null) {
				boolean okClicked = mainApp.showEditDialog(selectedNTP);
				if (okClicked) {
					surfacesNotToPaintTable.setItems(mainApp
							.getNotToPaintSurfaceData());
					updateSurfaceLabel();
					if (isColorSelected())
						updateCost();
				}
			} else {

				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(mainApp.getPrimaryStage());
				alert.setTitle("No Selection");
				alert.setHeaderText("No surface selected");
				alert.setContentText("Please select a surface in the table.");
				alert.showAndWait();
			}
		}
	}
	/**
	 * This method is responsible for handling the edition of a {@code Paint}
	 */
	@FXML
	private void handleEditPaint() {
		Paint paint = editionTable.getSelectionModel().getSelectedItem();

		if (paint != null) {
			boolean okClicked = mainApp.showPaintEditDialog(paint);
			if (okClicked) {
				editionTable.setItems(mainApp.getPaintData());
				updateBrandTable();

			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No paint selected");
			alert.setContentText("Please select a paint in the table.");
			alert.showAndWait();
		}
	}
	/**
	 * This method is responsible for handling the removal of a  {@code Surface}.
	 * Warnings are ready to inform the user if they failed to select a surface before
	 * trying to remove one.
	 */
	@FXML
	public void handleRemove() {
		Surface selected = allSurfacesTable.getSelectionModel()
				.getSelectedItem();
		Surface selectedNTP = surfacesNotToPaintTable.getSelectionModel()
				.getSelectedItem();
		if (selected != null && selectedNTP == null) {
			mainApp.getAllSurfaceData().remove(selected);
			allSurfacesTable.setItems(mainApp.getAllSurfaceData());
			updateSurfaceLabel();
			if (isColorSelected())
				updateCost();

		} else {
			if (selected == null && selectedNTP != null) {
				mainApp.getNotToPaintSurfaceData().remove(selectedNTP);
				surfacesNotToPaintTable.setItems(mainApp
						.getNotToPaintSurfaceData());
				updateSurfaceLabel();
				if (isColorSelected())
					updateCost();
			} else {

				// Nothing selected.
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(mainApp.getPrimaryStage());
				alert.setTitle("No Selection");
				alert.setHeaderText("No surface selected");
				alert.setContentText("Please select a surface in the table.");
				alert.showAndWait();
			}
		}

	}
	/**
	 * This method is responsible for handling the removal of a  {@code Paint}.
	 * Warnings are ready to inform the user if they failed to select a paint before
	 * trying to remove one.
	 */

	@FXML
	public void handleRemovePaint() {
		Paint paint = editionTable.getSelectionModel().getSelectedItem();
		if (paint != null) {
			mainApp.getPaintData().remove(paint);
			editionTable.setItems(mainApp.getPaintData());
			updateBrandTable();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No paint selected");
			alert.setContentText("Please select a paint in the table.");
			alert.showAndWait();
		}
	}
	/**
	 * This method returns the selected paint from the color table.
	 * @return selected paint from the color table.
	 */
	public Paint selectedPaint() {
		return colorTable.getSelectionModel().getSelectedItem();
	}
	/**
	 * This method updates the surfaces label.
	 * Called by various listeners in order to ensure the GUI is up-to-date.
	 */
	public void updateSurfaceLabel() {
		double surface = mainApp.calculateSurface();
		logger.info("updateSurface label surface:" + surface);
		if (surface < 0)
			surfaceLabel.setText("Not enough suraface to paint");
		else {
			NumberFormat formatter = new DecimalFormat("#0.00");
			surfaceLabel.setText(formatter.format(surface));
		}
	}
	/**
	 * This method updates the cost label.
	 * Called by various listeners in order to ensure the GUI is up-to-date.
	 */
	private void updateCost() {
		double cost = mainApp.calculateCost( selectedPaint());
		double coverage = 0;
		if(selectedPaint()!=null)
			coverage = selectedPaint().getSize();
		if (cost < 0)
			costLabel.setText("Not enough surface to paint.");
		else {
			if (isCustomColor) {
				customColorCost();
			} else {
				NumberFormat formatter = new DecimalFormat("#0.00");
				costLabel.setText("" + formatter.format(cost));
				requiredPaintsLabel.setText(mainApp.calcRequiredNumberOfPaints(
						mainApp.calculateSurface(),coverage ));
			}

		}

	}
	/**
	 * This method handles the file chose input.
	 */
	@FXML
	public void fileChooser() {
		FileChooser fc = new FileChooser();
		fc.setTitle("choose a file");
		File f;
		if ((f = fc.showOpenDialog(mainApp.getPrimaryStage())) != null)
			mainApp.setFile(f);
		logger.debug(f.getPath());
		setMainApp(mainApp);
	}
	/**
	 * This method updates the brand table.
	 */
	public void updateBrandTable() {
		brandTable.setItems(FXCollections.observableArrayList(mainApp
				.getUniqueBrands()));
	}
	/**
	 * This method updates the color cost label if a custom color is selected.
	 * 
	 */
	@FXML
	public void customColorCost() {
		isCustomColor = true;
		CustomColor color = new CustomColor(mainApp.getHexCode(customColorPicker));
		NumberFormat formatter = new DecimalFormat("#0.00");
		brandLabel.setText("Custom");
		colorLable.setText("");
		if(mainApp.calculateSurface()>0){
			costLabel.setText(""
					+ formatter.format(color.calculateCost(mainApp
							.calculateRequredLitres(color.getCoverage()))));
			requiredPaintsLabel.setText(mainApp.calcRequiredNumberOfPaints(
							mainApp.calculateSurface(), color.getCoverage()));
		}
		

	}

}
