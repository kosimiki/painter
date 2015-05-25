package hu.unideb.inf.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Surface {
	/**
	 * {@code StringProperty} object holds the name of the surface.
	 */
	private StringProperty name;
	/**
	 * {@code DoubleProperty} object holds the height of the surface.
	 */
	private DoubleProperty height;
	/**
	 * {@code DoubleProperty} object holds the height of the surface.
	 */
	private DoubleProperty width;
	/**
	 * {@code IntgerProperty} object  holds the  number of the same surfaces.
	 */
	private IntegerProperty numberOfItems;
	
	/**
	 * Constructor to create empty {@code Surface} object.
	 */
	public Surface(){
		this("",0.0,0.0,0);
	}
	/**
	 * Constructor to create a {@code Surface} object.
	 * @param name {@code String} object the name of the surface.
	 * @param height {@code Double} object the height of the surface.
	 * @param width {@code Double} object the width of the surface.
	 * @param numberOfItems {@code Intger} object the number of the same surfaces.
	 */
	public Surface(String name, Double height, Double width, Integer numberOfItems) {
		this.name = new SimpleStringProperty(name);
		this.height =new SimpleDoubleProperty (height);
		this.width = new SimpleDoubleProperty (width);
		this.numberOfItems =new SimpleIntegerProperty( numberOfItems);
	}
	
	/**
	 * This method returns the name {@code String}.
	 * @return the Name {@code String}
	 */
	
	public String getName() {
		return name.get();
	}
	/**
	 * This method sets the Name field.
	 * @param name of the Surface
	 */
	public void setName(String name) {
		this.name.set(name);
	}
	/**
	 * This method returns the height of the surface.
	 * @return the height  of the surface.
	 */
	public Double getHeight() {
		return height.get();
	}
	/**
	 * This method sets the height of the surface.
	 * @param height of the surface
	 */
	public void setHeight(Double height) {
		this.height.set(height);
	}
	/**
	 * This method returns the width of the surface.
	 * @return the width of the surface.
	 */
	public Double getWidth() {
		return width.get();
	}
	/**
	 * This method sets the width of the surface.
	 * @param width of the surface
	 */
	public void setWidth(Double width) {
		this.width.set(width);
	}
	/**
	 * This method returns the number of the same surfaces.
	 * @return the number of the same surfaces
	 */
	public Integer getNumberOfItems() {
		return numberOfItems.get();
	}
	/**
	 * This method sets the number of the same surfaces.
	 * @param numberOfItems the number of the same surfaces
	 */
	
	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems.set(numberOfItems);
	}
	/**
	 * This method returs the name {@code StringProperty}.
	 * @return the name {@code StringProperty}.
	 */
	public StringProperty getNameProperty() {
		return name;
	}
	
	/**
	 * This method returns the height {@code DoubleProperty}.
	 * @return the height {@code DoubleProperty}.
	 */
	public DoubleProperty getHeightProperty() {
		return height;
	}

	/**
	 * This method returns the width {@code DoubleProperty}
	 * 
	 * @return width {@code Double Property}
	 */
	public DoubleProperty getWidthProperty() {
		return width;
	}

	/**
	 * This method returns the number of the same surface {@code IntegerProperty}
	 * @return the number of the same surface {@code IntegerProperty}
	 */
	public IntegerProperty getNumberOfItemsProperty() {
		return numberOfItems;
	}
	/**
	 * This method returns how many square meters the surface area is.
	 * @return surface area in square meters
	 */
	public Double getSurface(){
		return this.getHeight()*this.getWidth();
	}

	

	
	
	
	

}
