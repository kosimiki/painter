package hu.unideb.inf.model;

import javafx.scene.paint.Color;

public class CustomColor {

	private String color;
	private double costRed = 3;
	private double costBlue = 1.1;
	private double costGreen = 1.7;
	private double coverage = 8.0;

	public double getCoverage() {
		return coverage;
	}

	public void setCoverage(double coverage) {
		this.coverage = coverage;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getCostRed() {
		return costRed;
	}

	public void setCostRed(double costRed) {
		this.costRed = costRed;
	}

	public double getCostBlue() {
		return costBlue;
	}

	public void setCostBlue(double costBlue) {
		this.costBlue = costBlue;
	}

	public double getCostGreen() {
		return costGreen;
	}

	public void setCostGreen(double costGreen) {
		this.costGreen = costGreen;
	}

	public CustomColor(String color) {
		this.color = color;
	}

	public double calculateCost(double litres){
		if(litres<1)
			return 0.0;
		
		Color c = Color.valueOf(color);
		return ((1.5 - c.getRed() )  * costRed + (1.5 - c.getBlue() ) * costBlue + (1.5 - c.getGreen())* costGreen) * litres;
	}
}
