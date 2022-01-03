package mg.ankoay.restomanagefinal.commons.model;

import com.google.gson.Gson;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mg.ankoay.restomanagefinal.commons.attributes.UserAttr;
import mg.ankoay.restomanagefinal.commons.utils.ResponseBody;
import mg.ankoay.restomanagefinal.commons.utils.Utils;

public class User {
	private final StringProperty id = new SimpleStringProperty(this, "id", "1");
	private final StringProperty name = new SimpleStringProperty(this, "name", "user");
	private final StringProperty password = new SimpleStringProperty(this, "password", "FPO_12p)([]");
	private final StringProperty user_type = new SimpleStringProperty(this, "user_type", null);
	private String token;
	
	
	private static final User INSTANCE = new User();
	private String URL = "http://localhost:8080/api/login";
	
	public boolean login() throws Exception {
		boolean result = true;
		User connectedUser = User.getInstance();
		
		Gson gson = new Gson();
		UserAttr attr = new UserAttr();
		attr.setPassword(getPassword());
		attr.setName(getName());
		String entity = gson.toJson(attr);
		String tokens = Utils.postJSON(URL, entity);
		
		
		String userName = tokens.split(" ")[0];
		String token = tokens.split(" ")[1];
		
		connectedUser.setName(userName);
		connectedUser.setToken(token);
		
		
		return result;
	}

	public static User getInstance() {
		return INSTANCE;
	}

// Getters and setters
	
	
	
	public void setId(String _id) {
		this.id.set(_id);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
