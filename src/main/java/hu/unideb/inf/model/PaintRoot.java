package hu.unideb.inf.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Paints")
public class PaintRoot {
	
	@XmlElement(name ="paint")
	List<Paint> paints;

	public PaintRoot(List<Paint> paints) {
		this.paints = paints;
	}
	
	public PaintRoot() {
	}

	public List<Paint> getPaints() {
		return paints;
	}

	public void setPaints(List<Paint> paints) {
		this.paints = paints;
	}
	
	
	
}
