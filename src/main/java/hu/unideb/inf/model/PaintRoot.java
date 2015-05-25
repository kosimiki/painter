package hu.unideb.inf.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * This is class is the root of our xml file.
 * @author Miklós Kosárkár
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Paints")
public class PaintRoot {
	/**
	 * This list hold all the paints and used to create from or read into by the DAO.
	 */
	@XmlElement(name ="paint")
	List<Paint> paints;
	
	/**
	 * Constructor to create a PaintRoot object.
	 * @param paints the list to be used
	 */
	public PaintRoot(List<Paint> paints) {
		this.paints = paints;
	}
	
	/**
	 * Empty constructor
	 */
	public PaintRoot() {
	}
	
	/**
	 * This method returns the list of {@code Paint}s.
	 * @return the list of {@code Paint}s.
	 */
	public List<Paint> getPaints() {
		return paints;
	}
	
	/**
	 * This method sets the list of {@code Paint}s.
	 * @param paints to set as our list
	 */
	public void setPaints(List<Paint> paints) {
		this.paints = paints;
	}
	
	
	
}
