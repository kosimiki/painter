package hu.unideb.inf;

import hu.unideb.inf.model.IDAO;
import hu.unideb.inf.model.JAXBDAO;
import hu.unideb.inf.model.Paint;
import hu.unideb.inf.model.Surface;
import hu.unideb.inf.view.MainViewController;
import hu.unideb.inf.view.PaintEdit;
import hu.unideb.inf.view.SurfaceEdit;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is the main controller which holds together the models and the view.
 * It extedns the Application class which is the entry point for JavaFX applications.
 * @author Miklós Kosárkár
 */

public class MainApp extends Application {
	/**
	 * Holds the primary {@code Stage} of the application. 
	 */
	private Stage primaryStage;
	/**
	 * Holds the root layout  of the application which is a {@code BorderPane}. 
	 */
	private BorderPane rootLayout;
	/**
	 * This field holds the DAO (data access object). 
	 * The application will be able to get and set data with this object. 
	 */
	
	private IDAO dao;
	/**
	 * This is an {@code ObservableList} which holds {@code Paint} objects. Most of the calculations will
	 * use this object. It is empty if no xml files were present at the start of the application.
	 */
	private ObservableList<Paint> paintData;
	/**
	 * This is an {@code ObservableList} which holds {@code Surfaces} objects.
	 * These are the surfaces that will be painted. {@code notToPaintSurfaceData} may contain 
	 * surfaces that are sub-surfaces of these surfaces so you need here to
	 *  add ALL the surfaces you want to paint.
	 * This List will can only be filled by the user using the GUI.
	 */
	
	private ObservableList<Surface> allSurfaceData;
	
	/**
	 * This is an {@code ObservableList} which holds {@code Surfaces} objects.
	 * This List will can only be filled by the user using the GUI.
	 * These are the surfaces you don't want to paint, they will be subtracted
	 * from the sum surface value of {@code allSurfaceData}.
	 */
	private ObservableList<Surface> notToPaintSurfaceData;
	
	/**
	 * This object which will refer to the controller of the main view.
	 */
	private MainViewController controller;
	
	/**
	 * The main entry point of the application. 
	 * This will set the primary {@code Stage},
	 * initialize the {@code rootLayout} and show the main view.
	 * @param primaryStage of the application
	 */

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Painter");
		initRootLayout();
		showMainView();
	}
	
	
	/**
	 * This constructor ensures that all lists are initialized.
	 *
	 */
	public MainApp() {

		paintData = FXCollections.observableArrayList();
		allSurfaceData = FXCollections.observableArrayList();
		notToPaintSurfaceData = FXCollections.observableArrayList();
	}
	
	/**
	 * Give the DAO object a new file to work with.
	 * 
	 * @param file  that contains {@code Paint} objects.
	 */
	public void setFile(File file) {
		dao = new JAXBDAO(file);
		if (!dao.isEmpty()) {
			paintData = FXCollections.observableArrayList(dao.getAllPaint());
		} else {
			paintData = FXCollections.observableArrayList();
		}
	}

	/**
	 * This method calls the defaul constructor of the DAO.
	 */
	public void setFile() {
		if (dao == null) {
			dao = new JAXBDAO();
			if (!dao.isEmpty()) {
				paintData = FXCollections
						.observableArrayList(dao.getAllPaint());
			} else {
				paintData = FXCollections.observableArrayList();
			}
		}

	}
	
	/**
	 * This method gets the path of the source which is used by the DAO.
	 * @return {@code String} path of the source for the {@code Paint} list.
	 */
	public String getPath() {
		return dao.getPath();
	}

	/**
	 *This method initiates the root layout by loading 
	 *fxml which describes the root.
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/rootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *This method loads the main view and adds it to the root layout.
	 *Also the main view controller gets a reference to this class.
	 */
	public void showMainView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/mainView.fxml"));
			AnchorPane mainView = (AnchorPane) loader.load();
			controller = loader.getController();
			controller.setMainApp(this);
			rootLayout.setCenter(mainView);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method saves the current sate of a {@code List<Paint>}.
	 * @param list of {@code Paint}s
	 * @return true if the save was successful false otherwise
	 */
	public boolean saveToFile(List<Paint> list) {
		return dao.updatePaints(list);
	}
	/**
	 * This method loads a dialogue window to edit a {@code Surface} object.
	 * @param surface to be edited
	 * @return true of OK button was clicked false if the CANCEL button was clicked
	 */
	public boolean showEditDialog(Surface surface) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/SurfaceEdit.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit surface");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			SurfaceEdit controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setSurface(surface);
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method loads a dialogue window to edit a {@code Paint} object.
	 * @param paint to be edited
	 * @return true of OK button was clicked false if the CANCEL button was clicked
	 */
	public boolean showPaintEditDialog(Paint paint) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PaintEdit.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit paint");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			PaintEdit controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPaint(paint);
			controller.setMainApp(this);
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method creates a list of {@code Paint}s with unique brand names.
	 * @return list of {@code Paint}s with unique brand names
	 */
	public List<Paint> getUniqueBrands() {
		List<Paint> list = new ArrayList<>();
		boolean alreadyHave = false;

		for (Paint p : paintData) {
			for (Paint l : list) {
				if (p.getBrand().equals(l.getBrand())) {
					alreadyHave = true;
				}
			}
			if (!alreadyHave) {
				list.add(p);
			} else {
				alreadyHave = false;
			}
		}
		return list;
	}
	/**
	 * This method takes a {@code Paint} list and returns another list 
	 * with only unique elements.
	 * @param paints list of {@code Paint}s 
	 * @return list of {@code Paint}s with unique colors in it
	 */
	public List<Paint> getUniqueColors(List<Paint> paints) {
		return paints.stream().distinct().collect(Collectors.toList());
	}
	
	/**
	 * This method calculates the required litres 
	 * of paint to cover a give quantity of surface.
	 * @param coverage square metre / litre
	 * @return required litres to cover a surface
	 */
	public double calculateRequredLitres(double coverage){
		return Math.ceil(calculateSurface() / coverage);
	}
	
	/**
	 * This method calculates the cost to paint a given
	 * surface.
	 * @param paint which will be used to paint the surface
	 * @return cost to paint the surface given by the GUI
	 */
	public double calculateCost(Paint paint) {
		if (calculateSurface() > 0) {
			return paint.getPrice()
					* calculateRequredLitres(paint.getSize());
		} else {
			return 0.0;
		}
	}

	/**
	 *This method calculates the required number of paints to paint a given surface.
	 * @param surface surface to paint
	 * @param coverage square metre / litre
	 * @return number of paints to use
	 */
	public String calcRequiredNumberOfPaints(double surface, double coverage) {
		if (surface <= 0.0 || coverage == 0)
			return "Add surface to paint.";
		surface = surface / coverage;
		StringBuilder result = new StringBuilder();
		NumberFormat formatter = new DecimalFormat("#0");
		double temp6 = surface % 10;
		double temp = (surface - temp6) / 10;
		
		
			if(temp>0)
			result.append(formatter.format(temp)).append(" of 10l ");
		
			if(temp6>=5){
				result.append("1 of 5l ");
				temp6 = temp6%5;
			}
			
			switch ((int) temp6) {
			case 1:
				result.append("1 of 1l ");
				break;
			case 2:
				result.append("1 of 2l ");
				break;
			case 3:
				result.append("1 of 2l 1 of 1l");
				break;
			case 4:
				result.append("2 of 2l ");
				break;
			}

		
		return result.toString();
	}
	
	/**
	 * This method calculates the final sum of surfaces.
	 * @return the final sum of surfaces
	 */
	public double calculateSurface() {
		double sumSurfaces = 0;
		double sumSurfacesNTP = 0;
		for (Surface s : allSurfaceData) {
			sumSurfaces += s.getSurface() * s.getNumberOfItems();
		}
		for (Surface s : notToPaintSurfaceData) {
			sumSurfacesNTP += s.getSurface() * s.getNumberOfItems();	

		}
		return sumSurfaces - sumSurfacesNTP;

	}
	/**
	 * This method returns the cheapest paint which has the same color
	 * as the paint in the parameter.
	 * @param selected the paint which's color you need
	 * @return the paint witch has the same color has the lowest price
	 */
	public Paint cheapestOfThisColor(Paint selected){
		Paint min = selected;
		for(Paint paint: paintData){
			if( paint.getColor().equals(selected.getColor() )
					&& (paint.getPrice()/paint.getSize()) < (selected.getPrice()/selected.getSize())){
				min = paint;
			}
		}
			
		return min;
	}
	/**This method returns the primary {@code Stage} of this application application.
	 * @return primary {@code Stage} of this application application
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	/**This method returns the  {@code Paint} list.
	 * @return {@code Paint} list.
	 */
	public ObservableList<Paint> getPaintData() {
		return paintData;
	}

	/**This method returns the  {@code Surface} list of all surfaces.
	 * @return {@code Paint} list.
	 */
	public ObservableList<Surface> getAllSurfaceData() {
		return allSurfaceData;
	}
	/**This method returns the  {@code Paint} list of surfaces you don't want to paint.
	 * @return {@code Paint} list.
	 */
	public ObservableList<Surface> getNotToPaintSurfaceData() {
		return notToPaintSurfaceData;
	}
	
	
	/**
	 * This method converts the color of a {@code ColorPicker} into a hex{@code String} ("FFFFFF").
	 * 
	 * @param colorPicker the {@code ColorPicker} object
	 * @return {@code String} that represents the hexadecimal version of an rgb color.
	 */
	public String getHexCode(ColorPicker colorPicker){
		if(Integer.toHexString(colorPicker.getValue().hashCode()).length()<6)
			return "000000";
		else
			
		return Integer.toHexString(colorPicker.getValue().hashCode()).substring(0, 6).toUpperCase();
		
	}
	/**
	 * This is the main method.
	 * 
	 * @param args the argument list
	 */

	public static void main(String[] args) {
		launch(args);
	}
}
