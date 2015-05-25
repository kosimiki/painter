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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private IDAO dao;
	private ObservableList<Paint> paintData;
	private ObservableList<Surface> allSurfaceData;
	private ObservableList<Surface> notToPaintSurfaceData;
	private MainViewController controller;

	public void setPaintData(ObservableList<Paint> paintData) {
		this.paintData = paintData;
	}

	public void setAllSurfaceData(ObservableList<Surface> allSurfaceData) {
		this.allSurfaceData = allSurfaceData;
	}

	public void setNotToPaintSurfaceData(
			ObservableList<Surface> notToPaintSurfaceData) {
		this.notToPaintSurfaceData = notToPaintSurfaceData;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Painter");
		initRootLayout();
		showMainView();
	}

	public MainApp() {

		paintData = FXCollections.observableArrayList();
		allSurfaceData = FXCollections.observableArrayList();
		notToPaintSurfaceData = FXCollections.observableArrayList();
	}

	public void setFile(File file) {
		dao = new JAXBDAO(file);
		if (!dao.isFileEmpty()) {
			paintData = FXCollections.observableArrayList(dao.getAllPaint());
		} else {
			paintData = FXCollections.observableArrayList();
		}
	}

	public void setFile() {
		if (dao == null) {
			dao = new JAXBDAO();
			if (!dao.isFileEmpty()) {
				paintData = FXCollections
						.observableArrayList(dao.getAllPaint());
			} else {
				paintData = FXCollections.observableArrayList();
			}
		}

	}

	public String getPath() {
		return dao.getPath();
	}

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

	public boolean saveToFile(List<Paint> list) {
		return dao.updatePaints(list);
	}

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
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

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

	public List<Paint> getUniqueColors(List<Paint> paints) {
		return paints.stream().distinct().collect(Collectors.toList());
	}
	public double calculateRequredLitres(double coverage){
		return Math.ceil(calculateSurface() / coverage);
	}
	public double calculateCost(Paint paint) {
		if (calculateSurface() > 0) {
			return paint.getPrice()
					* calculateRequredLitres(paint.getSize());
		} else {
			return 0.0;
		}
	}

	public String calcRequiredNumberOfPaints(double surface, double coverage) {
		if (surface <= 0.0 || coverage == 0)
			return "Add surface to paint.";
		surface = surface / coverage;
		StringBuilder result = new StringBuilder();
		NumberFormat formatter = new DecimalFormat("#0");
		double temp6 = surface % 10;
		double temp = (surface - temp6) / 10;

		if (temp6 == 1)
			result.append(formatter.format(temp + 1)).append(" of 10l ");
		else if (!(temp < 1) && temp > 1)
			result.append(formatter.format(temp)).append(" of 10l ");
		else {
			if (temp6 >= 5) {
				result.append("1 of 5l ");
				temp6 %= 5;
			}
			switch ((int) temp6) {
			case 0:
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

		}
		return result.toString();
	}

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

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<Paint> getPaintData() {
		return paintData;
	}

	public ObservableList<Surface> getAllSurfaceData() {
		return allSurfaceData;
	}

	public ObservableList<Surface> getNotToPaintSurfaceData() {
		return notToPaintSurfaceData;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
