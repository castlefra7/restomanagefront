package mg.ankoay.restomanagefinal.commons.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
	private final StringProperty id = new SimpleStringProperty(this, "id", null);
	private final StringProperty name = new SimpleStringProperty(this, "name", null);
	
// Constructors
	public Category() {}
	public Category(String _id, String _name) {
		this.id.set(_id);
		this.name.set(_name);
	}

// Properties
	public StringProperty idProperty() {
		return this.id;
	}

	public StringProperty nameProperty() {
		return this.name;
	}

// Getters and Setters
	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

}
