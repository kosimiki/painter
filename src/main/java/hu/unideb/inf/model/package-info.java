@XmlJavaTypeAdapters({
		@XmlJavaTypeAdapter(type = javafx.beans.property.DoubleProperty.class, value = hu.unideb.inf.model.DoublePropertyAdapter.class),
		 @XmlJavaTypeAdapter(type = javafx.beans.property.StringProperty.class, value = hu.unideb.inf.model.StringPropertyAdapter.class),
		 @XmlJavaTypeAdapter(type = javafx.beans.property.IntegerProperty.class, value = hu.unideb.inf.model.IntegerPropertyAdapter.class)})
package hu.unideb.inf.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;