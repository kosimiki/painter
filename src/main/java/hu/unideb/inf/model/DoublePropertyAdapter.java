package hu.unideb.inf.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DoublePropertyAdapter extends XmlAdapter<Double, DoubleProperty> {

	@Override
	public Double marshal(DoubleProperty v) throws Exception {
		return v.doubleValue();
	}

	@Override
	public DoubleProperty unmarshal(Double v) throws Exception {
		return new SimpleDoubleProperty(v.doubleValue());
	}

}
