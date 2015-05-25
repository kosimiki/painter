package hu.unideb.inf.model;

import javafx.scene.paint.Color;

/**
 * This class hold information about a custom color.
 * @author kosimiki
 *
 */
public class CustomColor {
	
	/**
	 * This field holds the rgb code of the color.
	 */
	private String color;
	/**
	 * This field hold the cost of the red dye stuff.
	 */
	private double costRed = 3;
	/**
	 * This field hold the cost of the blue dye stuff.
	 */
	private double costBlue = 1.1;
	/**
	 * This field hold the cost of the green dye stuff.
	 */
	private double costGreen = 1.7;
	/**
	 * This field holds the area which 1 litre of paint can cover.
	 */
	private double coverage = 8.0;
	
	/**
	 * This method returns the value of coverage.
	 * @return  value of coverage
	 */
	public double getCoverage() {
		return coverage;
	}
	/**
	 * This method sets the coverage.
	 * @param coverage of the paint
	 */
	public void setCoverage(double coverage) {
		this.coverage = coverage;
	}

	/**
	 * This method returns the value of color.
	 * @return  value of color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * This method sets the color
	 * @param color rbg color in hexadecimal format
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * This method returns the price of red dye stuff.
	 * @return  the price of red dye stuff
	 */
	public double getCostRed() {
		return costRed;
	}
	/**
	 * This method sets the price of the red dye stuff.
	 * @param costRed price of the red  dye stuff
	 */
	public void setCostRed(double costRed) {
		this.costRed = costRed;
	}
	
	/**
	 * This method returns the price of blue dye stuff.
	 * @return  the price of blue dye stuff
	 */
	public double getCostBlue() {
		return costBlue;
	}

	/**
	 * This method sets the price of the blue dye stuff.
	 * @param costBlue price of the blue dye stuff
	 */
	
	public void setCostBlue(double costBlue) {
		this.costBlue = costBlue;
	}
	
	/**
	 * This method returns the cost of red green stuff.
	 * @return  the cost of green dye stuff
	 */
	public double getCostGreen() {
		return costGreen;
	}
	/**
	 * This method sets the price of the blue dye stuff.
	 * @param costGreen price of the blue dye stuff
	 */
	public void setCostGreen(double costGreen) {
		this.costGreen = costGreen;
	}
	
	/**
	 * Constructor to create CustomColor object.
	 * @param color rgb value of a color in hexadecimal format
	 */
	public CustomColor(String color) {
		this.color = color;
	}
	/**
	 * This method calculates the cost of the given litre paint.
	 * @param litres the quantity of paint
	 * @return the cost
	 */
	public double calculateCost(double litres){
		if(litres<1)
			return 0.0;
		
		Color c = Color.valueOf(color);
		return ((1.5 - c.getRed() )  * costRed + (1.5 - c.getBlue() ) * costBlue + (1.5 - c.getGreen())* costGreen) * litres;
	}
}
