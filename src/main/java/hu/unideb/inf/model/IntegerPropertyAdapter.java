package hu.unideb.inf.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class IntegerPropertyAdapter extends
		XmlAdapter<Integer, IntegerProperty> {

	@Override
	public Integer marshal(IntegerProperty v) throws Exception {
		return v.get();
	}

	@Override
	public IntegerProperty unmarshal(Integer v) throws Exception {
		return new SimpleIntegerProperty(v.intValue());
	}

}
