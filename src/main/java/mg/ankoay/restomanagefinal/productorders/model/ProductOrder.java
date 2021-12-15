package mg.ankoay.restomanagefinal.productorders.model;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mg.ankoay.restomanagefinal.commons.attributes.ProductAttr;
import mg.ankoay.restomanagefinal.commons.model.Product;
import mg.ankoay.restomanagefinal.commons.model.Table;
import mg.ankoay.restomanagefinal.commons.utils.ResponseBody;
import mg.ankoay.restomanagefinal.commons.utils.Utils;
import mg.ankoay.restomanagefinal.productorders.attributes.OrderAttr;
import mg.ankoay.restomanagefinal.productorders.attributes.OrderDetailAttr;

public class ProductOrder {
	private String URL = "http://localhost:8080/api/back";

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

	public String sendToDB() throws Exception {
		String result = "";
		Gson gson = new Gson();
		Type type = new TypeToken<ResponseBody<Object>>() {
		}.getType();
		
		OrderAttr attr = new OrderAttr();
		attr.setId_table(Integer.valueOf(this.getTable().getId()));
		Set<OrderDetailAttr> orderDetails = new HashSet<>();
		for(Product product: products) {
			OrderDetailAttr ordDet = new OrderDetailAttr();
			ordDet.setAmount(product.getTotal());
			ordDet.setQuantity(product.getQuantity());
			ordDet.setUnit_price(product.getPrice());
			ordDet.setProduct(new ProductAttr(Integer.valueOf(product.getId())));
			orderDetails.add(ordDet);
		}
		attr.setOrderDetails(orderDetails);
		
		String entity = gson.toJson(attr);
		ResponseBody<Object> respOrd = gson.fromJson(Utils.postJSON(URL + "/orders/", entity), type);
		result = respOrd.getStatut().getMessage();
		
		return result;
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
