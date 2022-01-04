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
import mg.ankoay.restomanagefinal.commons.attributes.TableAttr;
import mg.ankoay.restomanagefinal.commons.model.Product;
import mg.ankoay.restomanagefinal.commons.model.Table;
import mg.ankoay.restomanagefinal.commons.model.User;
import mg.ankoay.restomanagefinal.commons.utils.ResponseBody;
import mg.ankoay.restomanagefinal.commons.utils.Utils;
import mg.ankoay.restomanagefinal.productorders.attributes.OrderAttr;
import mg.ankoay.restomanagefinal.productorders.attributes.OrderDetailAttr;

public class ProductOrder {
	private String URL = "http://localhost:8080/api/back";

	private Integer id_order;
	private ObservableList<Product> products = FXCollections
			.observableArrayList(product -> new Observable[] { product.quantityProperty() });
	private ObjectProperty<Timestamp> date = new SimpleObjectProperty<Timestamp>();
	private ObjectProperty<Timestamp> datePayment = new SimpleObjectProperty<Timestamp>();
	private ObjectProperty<Table> table = new SimpleObjectProperty<>();

	public ProductOrder() {

	}

	public Integer getId_order() {
		return id_order;
	}

	public void setId_order(Integer id_order) {
		this.id_order = id_order;
	}

	public void update() throws Exception {
		Gson gson = new Gson();
		Type type = new TypeToken<ResponseBody<Object>>() {
		}.getType();
		OrderAttr attr = new OrderAttr();
// TODO: WHAT TO DO WITH THE USER ID
		Integer idUser = Integer.valueOf(User.getInstance().getId());
		attr.setId_user(idUser);
		attr.setId_order(getId_order());
		attr.setTable(new TableAttr(Integer.valueOf(this.getTable().getId())));
		
		Set<OrderDetailAttr> orderDetails = new HashSet<>();
		for (Product product : products) {
			OrderDetailAttr ordDet = new OrderDetailAttr();
			ordDet.setAmount(product.getTotal());
			ordDet.setQuantity(product.getQuantity());
			ordDet.setUnit_price(product.getPrice());
			ordDet.setProduct(new ProductAttr(Integer.valueOf(product.getId())));
			orderDetails.add(ordDet);
		}
		attr.setOrderDetails(orderDetails);

		String entity = gson.toJson(attr);
		ResponseBody<Object> respOrd = gson.fromJson(Utils.putJSON(URL + "/orders", entity), type);
		if (respOrd.getStatut().getCode() != 200) {
			throw new Exception(respOrd.getStatut().getMessage());
		}


	}

	public void pay() throws Exception {
		Gson gson = new Gson();
		Type type = new TypeToken<ResponseBody<Object>>() {
		}.getType();
		OrderAttr attr = new OrderAttr();
		attr.setId_order(this.getId_order());

		String entity = gson.toJson(attr);
		ResponseBody<Object> respOrd = gson.fromJson(Utils.putJSON(URL + "/orders/pay", entity), type);
		if (respOrd.getStatut().getCode() != 200) {
			throw new Exception(respOrd.getStatut().getMessage());
		}
	}

	public double getTotal() {
		double total = 0.0;
		for (Product prod : products) {
// TODO: Maybe need to implement prod.getTotal();			
			total += (prod.getQuantity() * prod.getPrice());
		}
		return total;
	}

	public String sendToDB(boolean paid) throws Exception {
		String result = "";
		Gson gson = new Gson();
		Type type = new TypeToken<ResponseBody<Object>>() {
		}.getType();

		OrderAttr attr = new OrderAttr();
		attr.setTable(new TableAttr(Integer.valueOf(this.getTable().getId())));

		Set<OrderDetailAttr> orderDetails = new HashSet<>();
		for (Product product : products) {
			OrderDetailAttr ordDet = new OrderDetailAttr();
			ordDet.setAmount(product.getTotal());
			ordDet.setQuantity(product.getQuantity());
			ordDet.setUnit_price(product.getPrice());
			ordDet.setProduct(new ProductAttr(Integer.valueOf(product.getId())));
			orderDetails.add(ordDet);
		}
		attr.setOrderDetails(orderDetails);

		String entity = gson.toJson(attr);
		String endpoint = "/orders/";
		if(paid) endpoint = "/orders-pay/";
		ResponseBody<Object> respOrd = gson.fromJson(Utils.postJSON(URL + endpoint, entity), type);
		result = respOrd.getStatut().getMessage();

		return result;
	}

// Properties
	public ObjectProperty<Timestamp> datePaymentProperty() {
		return datePayment;
	}

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

	public Timestamp getDatePayment() {
		return this.datePayment.get();
	}

	public void setDatePayment(Timestamp _date) {
		this.datePayment.set(_date);
	}

	public Timestamp getDate() {
		return this.date.get();
	}

	public void setDate(Timestamp _date) {
		this.date.set(_date);
	}
}
