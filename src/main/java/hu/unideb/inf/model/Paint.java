package hu.unideb.inf.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
/**
 * This class describe a simple Paint object.
 * The class uses Properties as fields so that JavaFX table features can be utilized.
 * @author Miklós Kosárkár
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Paint {
	/**
	 * This field holds the brand of the {@code Paint} object.
	 */
	private StringProperty brand;
	/**
	 * This field holds the name of the {@code Paint} object.
	 */
	private StringProperty name;
	/**
	 * This field holds the rgb color of the {@code Paint} object in hexadecimal format.
	 */
	private StringProperty color;
	/**
	 * This field holds the price per litre of the {@code Paint} object.
	 */
	private DoubleProperty price;
	/**
	 * This field hold the coverage of the {@code Paint} ( square meters / litre).
	 */
	private DoubleProperty size;
	
	/**
	 * This is a constructor to create a default Paint object.
	 */
	public Paint() {
		this("", "", "", 0.0, 0.0);
	}
	
	/**
	 * This Constructor creates a {@code Paint} object from non-Property parameters.
	 * @param brand of the {@code Paint}
	 * @param name of the {@code Paint}
	 * @param color of the {@code Paint}
	 * @param price of the {@code Paint}
	 * @param size of the {@code Paint}
	 */
	
	public Paint(String brand, String name, String color, Double price,
			Double size) {
		this.brand = new SimpleStringProperty(brand);
		this.name = new SimpleStringProperty(name);
		this.color = new SimpleStringProperty(color);
		this.price = new SimpleDoubleProperty(price);
		this.size = new SimpleDoubleProperty(size);
	}

	/**
	 * This Constructor creates a {@code Paint} object from Property parameters.
	 * @param brand of the {@code Paint}
	 * @param name of the {@code Paint}
	 * @param color of the {@code Paint}
	 * @param price of the {@code Paint}
	 * @param size of the {@code Paint}
	 */
	public Paint(StringProperty brand, StringProperty name,
			StringProperty color, DoubleProperty price, DoubleProperty size) {
		this.brand = brand;
		this.name = name;
		this.color = color;
		this.price = price;
		this.size = size;
	}
	/**
	 * This method returns the name as a {@code StringProperty}.
	 * @return the name as a {@code StringProperty}.
	 */
	public StringProperty getNameProperty() {
		return name;
	}
	/**
	 * This method returns the brand as a {@code StringProperty}.
	 * @return the brand as a {@code StringProperty}.
	 */
	public StringProperty getBrandProperty() {
		return brand;
	}
	/**
	 * This method returns the coverage as a {@code DoubleProperty}.
	 * @return the coverage as a {@code DoubleProperty}.
	 */
	public DoubleProperty getSizeProperty() {
		return size;
	}
	
	/**
	 * This method returns the color as a {@code StringProperty}.
	 * @return the color as a {@code StringProperty}.
	 */
	public StringProperty getColorProperty() {
		return color;
	}
	
	/**
	 * This method returns the price as a {@code DoubleProperty}.
	 * @return the price as a {@code DoubleProperty}.
	 */
	public DoubleProperty getPriceProperty() {
		return price;
	}
	/**
	 * This method returns the name as a {@code String}.
	 * @return the name as a {@code String}.
	 */
	public String getName() {
		return name.get();
	}
	
	/**
	 * This method returns the brand as a {@code String}.
	 * @return the brand as a {@code String}.
	 */
	public String getBrand() {
		return brand.get();
	}
	
	/**
	 * This method returns the coverage as a {@code Double}.
	 * @return the coverage as a {@code Double}.
	 */
	public Double getSize() {
		return size.get();
	}
	/**
	 * This method returns the color as a {@code String}.
	 * @return the color as a {@code String}.
	 */
	public String getColor() {
		return color.get();
	}
	/**
	 * This method returns the price as a {@code Double}.
	 * @return the price as a {@code Double}.
	 */
	public Double getPrice() {
		return price.get();
	}
	
	/**
	 * This method sets the brand field.
	 * @param brand new brand value.
	 */
	public void setBrand(String brand) {
		this.brand.set(brand);
	}
	/**
	 * This method sets the name field.
	 * @param name new name value.
	 */
	public void setName(String name) {
		this.name.set(name);
	}

	/**
	 * This method sets the color field.
	 * @param color new color value.
	 */
	public void setColor(String color) {
		this.color.set(color);
	}
	
	/**
	 * This method sets the price field.
	 * @param price new price value.
	 */
	public void setPrice(Double price) {
		this.price.set(price);
	}
	
	/**
	 * This method sets the coverage field.
	 * @param size new coverage value.
	 */
	public void setSize(Double size) {
		this.size.set(size);
	}
	
	/**
	 * This method converts the hexadecimal rgb color field into a
	 *  JavaFX {code Color} object.
	 * @return  JavaFX {code Color} object created from the {@code String} color field
	 */
	public Color getFXColor(){
		
		return  Color.valueOf(color.get());
	}
	/**
	 * This is the @Override of the {@code toString()} method.
	 */
	@Override
	public String toString() {
		return new StringBuilder().append("{").append("\"brand\":")
				.append(brand.get()).append(",").append("\"name\":")
				.append(name.get()).append(",").append("\"color\":")
				.append(color.get()).append(",").append("\"price\":")
				.append(price.get()).append(",").append("\"size\":")
				.append(size.get()).append("}").toString();
	}

	/**
	 * This is the @Override of the {@code hashCode()} method.
	 */
	@Override
	public int hashCode() {
		return (name.get() + color.get()).hashCode();
	}

	/**
	 * This is the @Override of the {@code equals} method. Returns true if two Paints has equal brand and color
	 * @return true if two Paints has equal brand and color.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paint other = (Paint) obj;
		
		if(other.getBrand().equals(this.brand.get()) && 
				other.getColor().equals(this.color.get())){
			return true;
		}else{
			return false;
		}
		
	}
	


}
