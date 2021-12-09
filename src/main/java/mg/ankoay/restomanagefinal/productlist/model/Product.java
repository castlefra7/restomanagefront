package mg.ankoay.restomanagefinal.productlist.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
	private StringProperty id = new SimpleStringProperty(this, "id", null);
	private StringProperty name = new SimpleStringProperty(this, "name", null);
	private DoubleProperty price = new SimpleDoubleProperty(this, "price");
	private StringProperty idCategory = new SimpleStringProperty(this, "idCategory", null);
	private IntegerProperty quantity = new SimpleIntegerProperty(this, "quantity");
	private DoubleProperty total = new SimpleDoubleProperty(this, "total");

// Constructors
	public Product() {
	}

	public Product(String _id, String _name, double _price, String _idCateg) {
		this.id.set(_id);
		this.name.set(_name);
		this.price.set(_price);
		this.idCategory.set(_idCateg);
	}

	public Product(String _id, String _name, double _price, String _idCateg, int _quantity) {
		this.id.set(_id);
		this.name.set(_name);
		this.price.set(_price);
		this.idCategory.set(_idCateg);
		this.quantity.set(_quantity);
		this.total.set(_quantity * _price);
	}

// Properties	
	public StringProperty idProperty() {
		return this.id;
	}

	public StringProperty nameProperty() {
		return this.name;
	}

	public DoubleProperty priceProperty() {
		return this.price;
	}

	public StringProperty idCategoryProperty() {
		return this.idCategory;
	}

	public DoubleProperty totalProperty() {
		return this.total;
	}

	public IntegerProperty quantityProperty() {
		return this.quantity;
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

	public double getPrice() {
		return price.get();
	}

	public void setPrice(double price) {
		this.price.set(price);
	}

	public String getIdCategory() {
		return idCategory.get();
	}

	public void setIdCategory(String idCategory) {
		this.idCategory.set(idCategory);
	}

	public int getQuantity() {
		return quantity.get();
	}

	public void setQuantity(int quantity) {
		this.quantity.set(quantity);
	}

	public double getTotal() {
		return total.get();
	}

	public void setTotal(double total) {
		this.total.set(total);
	}

}
