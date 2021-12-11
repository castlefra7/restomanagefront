package mg.ankoay.restomanagefinal.commons.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Table {
	private StringProperty id = new SimpleStringProperty();
	private StringProperty name = new SimpleStringProperty();
	
	public Table(String _id, String _name) {
		this.setId(_id);
		this.setName(_name);
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
	public StringProperty idProperty() {
		return this.id;
	}

	public StringProperty nameProperty() {
		return this.name;
	}

	public String getId() {
		return this.id.get();
	}

	public void setId(String _id) {
		this.id.set(_id);
	}

	public String getName() {
		return this.name.get();
	}

	public void setName(String _name) {
		this.name.set(_name);
	}

}
