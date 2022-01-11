package mg.ankoay.restomanagefinal.productorders.model;

import java.sql.Timestamp;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mg.ankoay.restomanagefinal.commons.attributes.UserAttr;
import mg.ankoay.restomanagefinal.commons.model.User;

public class Expense {
	private Integer id;
	private ObjectProperty<Timestamp> date = new SimpleObjectProperty<Timestamp>();
	private DoubleProperty amount = new SimpleDoubleProperty(this, "amount");
	private StringProperty reason = new SimpleStringProperty(this, "reason", null);
	private UserOrder user;

// Properties
	public ObjectProperty<Timestamp> dateProperty() {
		return date;
	}

// Getters and Setters

	public void setReason(String _value) {
		this.reason.set(_value);
	}

	public UserOrder getUser() {
		return user;
	}

	public void setUser(UserOrder user) {
		this.user = user;
	}

	public String getReason() {
		return this.reason.get();
	}

	public Double getAmount() {
		return this.amount.get();
	}

	public void setAmount(Double _value) {
		this.amount.set(_value);
	}

	public Timestamp getDate() {
		return this.date.get();
	}

	public void setDate(Timestamp _date) {
		this.date.set(_date);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
