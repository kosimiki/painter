package hu.unideb.inf.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StringPropertyAdapter extends XmlAdapter<String, StringProperty> {

	@Override
	public String marshal(StringProperty stringProperty) throws Exception {
		
		return stringProperty.get();
	}

	@Override
	public StringProperty unmarshal(String string) throws Exception {
		return new SimpleStringProperty(string);
	}

}
