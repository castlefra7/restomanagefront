package mg.ankoay.restomanagefinal.commons.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private final StringProperty id = new SimpleStringProperty(this, "id", null);
	private final StringProperty name = new SimpleStringProperty(this, "name", null);
	private final StringProperty password = new SimpleStringProperty(this, "password", null);
	private final StringProperty user_type = new SimpleStringProperty(this, "user_type", null);

	private static final User INSTANCE = new User();

	public boolean login() throws Exception {
		boolean result = false;
// TODO: Connect to database		

		return result;
	}

	public static User getInstance() {
		return INSTANCE;
	}

// Getters and setters
	public void setId(String _id) {
		this.id.set(_id);
	}

	public void setName(String _name) {
		this.name.set(_name);
	}

	public void setPassword(String _pass) {
		this.password.set(_pass);
	}

	public void setUser_type(String _type) {
		this.user_type.set(_type);
	}

	public String getId() {
		return this.id.get();
	}

	public String getName() {
		return this.name.get();
	}

	public String getPassword() {
		return this.password.get();
	}

	public String getUser_type() {
		return this.user_type.get();
	}

// Properties	
	public StringProperty idProperty() {
		return this.id;
	}

	public StringProperty nameProperty() {
		return this.name;
	}

	public StringProperty passwordProperty() {
		return this.password;
	}

	public StringProperty user_typeProperty() {
		return this.user_type;
	}
}
