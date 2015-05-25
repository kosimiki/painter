package hu.unideb.inf.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

@XmlAccessorType(XmlAccessType.FIELD)
public class Paint {
	private StringProperty brand;
	private StringProperty name;
	private StringProperty color;
	private DoubleProperty price;
	private DoubleProperty size;

	public Paint() {
		this("", "", "", 0.0, 0.0);
	}
	

	public Paint(String brand, String name, String color, Double price,
			Double size) {
		this.brand = new SimpleStringProperty(brand);
		this.name = new SimpleStringProperty(name);
		this.color = new SimpleStringProperty(color);
		this.price = new SimpleDoubleProperty(price);
		this.size = new SimpleDoubleProperty(size);
	}

	public Paint(StringProperty brand, StringProperty name,
			StringProperty color, DoubleProperty price, DoubleProperty size) {
		this.brand = brand;
		this.name = name;
		this.color = color;
		this.price = price;
		this.size = size;
	}

	public StringProperty getNameProperty() {
		return name;
	}

	public StringProperty getBrandProperty() {
		return brand;
	}

	public DoubleProperty getSizeProperty() {
		return size;
	}

	public StringProperty getColorProperty() {
		return color;
	}

	public DoubleProperty getPriceProperty() {
		return price;
	}

	public String getName() {
		return name.get();
	}

	public String getBrand() {
		return brand.get();
	}

	public Double getSize() {
		return size.get();
	}

	public String getColor() {
		return color.get();
	}

	public Double getPrice() {
		return price.get();
	}

	public void setBrand(String brand) {
		this.brand.set(brand);
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public void setColor(String color) {
		this.color.set(color);
	}

	public void setPrice(Double price) {
		this.price.set(price);
	}

	public void setSize(Double size) {
		this.size.set(size);
	}
	
	public Color getFXColor(){
		
		return  Color.valueOf(color.get());
	}
	@Override
	public String toString() {
		return new StringBuilder().append("{").append("\"brand\":")
				.append(brand.get()).append(",").append("\"name\":")
				.append(name.get()).append(",").append("\"color\":")
				.append(color.get()).append(",").append("\"price\":")
				.append(price.get()).append(",").append("\"size\":")
				.append(size.get()).append("}").toString();
	}


	@Override
	public int hashCode() {
		return (name.get() + color.get()).hashCode();
	}


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
