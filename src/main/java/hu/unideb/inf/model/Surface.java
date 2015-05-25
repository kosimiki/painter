package hu.unideb.inf.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Surface {
	private StringProperty name;
	private DoubleProperty height;
	private DoubleProperty width;
	private IntegerProperty numberOfItems;
	
	public Surface(){
		this("",0.0,0.0,0);
	}
	
	public Surface(String name, Double height, Double width, Integer numberOfItems) {
		this.name = new SimpleStringProperty(name);
		this.height =new SimpleDoubleProperty (height);
		this.width = new SimpleDoubleProperty (width);
		this.numberOfItems =new SimpleIntegerProperty( numberOfItems);
	}
	
	
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public Double getHeight() {
		return height.get();
	}
	public void setHeight(Double height) {
		this.height.set(height);
	}
	public Double getWidth() {
		return width.get();
	}
	public void setWidth(Double width) {
		this.width.set(width);
	}
	public Integer getNumberOfItems() {
		return numberOfItems.get();
	}
	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems.set(numberOfItems);
	}
	
	public StringProperty getNameProperty() {
		return name;
	}

	public DoubleProperty getHeightProperty() {
		return height;
	}

	public DoubleProperty getWidthProperty() {
		return width;
	}

	public IntegerProperty getNumberOfItemsProperty() {
		return numberOfItems;
	}
	
	public Double getSurface(){
		return this.getHeight()*this.getWidth();
	}

	

	
	
	
	

}
