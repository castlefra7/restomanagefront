package mg.ankoay.restomanagefinal.productorders.model;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mg.ankoay.restomanagefinal.commons.model.Product;
import mg.ankoay.restomanagefinal.commons.model.Table;
import mg.ankoay.restomanagefinal.commons.utils.ResponseBody;
import mg.ankoay.restomanagefinal.commons.utils.Utils;
import mg.ankoay.restomanagefinal.productorders.attributes.OrderAttr;
import mg.ankoay.restomanagefinal.productorders.attributes.OrderDetailAttr;

public class ProductOrderModel {
	private String URL = "http://localhost:8080/api/back";
	
	private ObservableList<ProductOrder> productOrders = FXCollections.observableArrayList();
	private ObjectProperty<ProductOrder> productOrderSelected = new SimpleObjectProperty<>();

	private static final ProductOrderModel INSTANCE = new ProductOrderModel();

	public static ProductOrderModel getInstance() {
		return INSTANCE;
	}
	
	public void clearData() {
		this.productOrderSelected.setValue(null);
		this.productOrders.clear();
		
	}

	public void loadData() {
// TODO: Load data from the database
		try {
			clearData();
			Gson gson = new Gson();

			Type type = new TypeToken<ResponseBody<OrderAttr>>() {
			}.getType();
			ResponseBody<OrderAttr> respCat = gson.fromJson(Utils.getJSON(URL + "/orders"), type);
			List<OrderAttr> orders = respCat.getData();
			for (OrderAttr order : orders) {
				ProductOrder prdOrd = new ProductOrder();
				prdOrd.setTable(new Table(String.valueOf(order.getId_table()), "Table temp"));
				prdOrd.setDate(order.getDate_order());
				
				for(OrderDetailAttr ordDetAttr: order.getOrderDetails()) {
					prdOrd.getProducts().add(new Product(
							String.valueOf(ordDetAttr.getProduct().getId()), 
							ordDetAttr.getProduct().getName(), 
							ordDetAttr.getUnit_price(), 
							String.valueOf(ordDetAttr.getProduct().getCategory().getId()),
							ordDetAttr.getQuantity()));
				}
				
				this.getProductOrders().add(prdOrd);
			}
		} catch (Exception ex) {
			//ex.printStackTrace();
		}
	}

// Getters
	public ObjectProperty<ProductOrder> getProducOrdertSelected() {
		return this.productOrderSelected;
	}

	public ObservableList<ProductOrder> getProductOrders() {
		return this.productOrders;
	}
}
