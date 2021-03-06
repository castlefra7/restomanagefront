package mg.ankoay.restomanagefinal.opencashier.model;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mg.ankoay.restomanagefinal.commons.model.User;
import mg.ankoay.restomanagefinal.commons.utils.ResponseBody;
import mg.ankoay.restomanagefinal.commons.utils.Utils;
import mg.ankoay.restomanagefinal.opencashier.attributes.OpenCashierAttr;

public class OpenCashierModel {
	private String URL = "http://localhost:8080/api/back";
	
	private SimpleDoubleProperty fund = new SimpleDoubleProperty(this, "fund");
	private static final OpenCashierModel INSTANCE = new OpenCashierModel();
	


// Functions
	public boolean open() throws Exception {
		boolean result = true;
// TODO: Send a POST to database
		User connectedUser = User.getInstance();
		System.out.println(connectedUser.getId());
		Gson gson = new Gson();
		Type type = new TypeToken<ResponseBody<Object>>() {
		}.getType();
		
		OpenCashierAttr attr = new OpenCashierAttr();
		attr.setFund(this.getFund());
		attr.setId_user(Integer.valueOf(connectedUser.getId()));
		
		String entity = gson.toJson(attr);
		ResponseBody<Object> respOrd = gson.fromJson(Utils.postJSON(URL + "/open-cashier/", entity), type);
		System.out.println(respOrd.getStatut().getMessage());
		

		return result;
	}

	public static OpenCashierModel getInstance() {
		return INSTANCE;
	}

// Getters and setters
	public Double getFund() {
		return this.fund.get();
	}

	public void setFund(Double _fund) {
		this.fund.set(_fund);
	}

// Properties
	public SimpleDoubleProperty fundProperty() {
		return this.fund;
	}
}
