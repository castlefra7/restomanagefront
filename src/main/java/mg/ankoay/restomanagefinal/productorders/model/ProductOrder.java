package mg.ankoay.restomanagefinal.productorders.model;

import java.sql.Timestamp;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mg.ankoay.restomanagefinal.commons.model.Product;
import mg.ankoay.restomanagefinal.commons.model.Table;

public class ProductOrder {
	private ObservableList<Product> products = FXCollections
			.observableArrayList(product -> new Observable[] { product.quantityProperty() });
	private ObjectProperty<Timestamp> date = new SimpleObjectProperty<Timestamp>();
	private ObjectProperty<Table> table = new SimpleObjectProperty<>();

	public ProductOrder() {

	}
	
	public double getTotal() {
		double total = 0.0;
		for (Product prod : products) {
// TODO: Maybe need to implement prod.getTotal();			
			total += (prod.getQuantity() * prod.getPrice());
		}
		return total;
	}

// Properties
	public ObjectProperty<Timestamp> dateProperty() {
		return date;
	}

	public ObservableList<Product> getProducts() {
		return products;
	}

// Getters and Setters
	public void setTable(Table _table) {
		this.table.set(_table);
	}

	public Table getTable() {
		return this.table.get();
	}

	public Timestamp getDate() {
		return this.date.get();
	}

	public void setDate(Timestamp _date) {
		this.date.set(_date);
	}
}
