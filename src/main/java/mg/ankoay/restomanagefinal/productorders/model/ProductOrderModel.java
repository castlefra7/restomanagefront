package mg.ankoay.restomanagefinal.productorders.model;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import mg.ankoay.restomanagefinal.commons.model.Product;
import mg.ankoay.restomanagefinal.commons.model.Table;
import mg.ankoay.restomanagefinal.commons.utils.ResponseBody;
import mg.ankoay.restomanagefinal.commons.utils.Utils;
import mg.ankoay.restomanagefinal.productorders.attributes.ExpenseAttr;
import mg.ankoay.restomanagefinal.productorders.attributes.OrderAttr;
import mg.ankoay.restomanagefinal.productorders.attributes.OrderDetailAttr;

public class ProductOrderModel {
	private String URL = "http://localhost:8080/api/back";

	private ObservableList<ProductOrder> productOrders = FXCollections.observableArrayList();
	private ObjectProperty<ProductOrder> productOrderSelected = new SimpleObjectProperty<>();
	private ObservableList<ProductOrder> productOrdersPaid = FXCollections.observableArrayList();
	private FilteredList<ProductOrder> filteredProductOrdersUnpaid = new FilteredList<>(this.productOrders);
	
	private ObservableList<Expense> expenses = FXCollections.observableArrayList();

	private static final ProductOrderModel INSTANCE = new ProductOrderModel();

	public static ProductOrderModel getInstance() {
		return INSTANCE;
	}

	private void clearData() {
		this.productOrderSelected.setValue(null);
		this.productOrders.clear();
		this.productOrdersPaid.clear();
		this.filteredProductOrdersUnpaid.clear();
		this.expenses.clear();
	}

	public void pay() throws Exception {
		if(productOrderSelected.get() != null) {
			productOrderSelected.get().pay();
		}
	}

	public void loadData(String dt) throws Exception {

		clearData();
		Gson gson = new Gson();
		Type type = new TypeToken<ResponseBody<OrderAttr>>() {
		}.getType();
		
		if(dt == null) {
			SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
			dt = sdt.format(new Date());
		}
		
		ResponseBody<OrderAttr> respCat = gson.fromJson(Utils.getJSON(URL + "/orders/unpaid?date=" + dt), type);
		if(respCat.getStatut().getCode() != 200) {
			throw new Exception(respCat.getStatut().getMessage());
		} else {
			List<OrderAttr> orders = respCat.getData();
			copyList(this.getProductOrders(), orders);
		}
		
		respCat = gson.fromJson(Utils.getJSON(URL + "/orders/paid?date=" + dt), type);
		if(respCat.getStatut().getCode() != 200) {
			throw new Exception(respCat.getStatut().getMessage());
		} else {
			List<OrderAttr> orders = respCat.getData();
			copyList(this.getProductOrdersPaid(), orders);
		}
		
		type = new TypeToken<ResponseBody<ExpenseAttr>>() {
		}.getType();
		ResponseBody<ExpenseAttr> respExp = gson.fromJson(Utils.getJSON(URL + "/expenses?date=" + dt), type);
		if(respExp.getStatut().getCode() != 200) {
			throw new Exception(respExp.getStatut().getMessage());
		} else {
			List<ExpenseAttr> _expenses = respExp.getData();
			copyExpense(_expenses);
		}
	}
	
	private void copyExpense(List<ExpenseAttr> src) {
		for(ExpenseAttr exp: src) {
			Expense e = new Expense();
			e.setUser(exp.getUser());
			e.setDate(exp.getDate_expense());
			e.setAmount(exp.getAmount());
			e.setId(exp.getId());
			e.setReason(exp.getReason());
			this.expenses.add(e);
		}
	}
	
	private void copyList(ObservableList<ProductOrder> dest, List<OrderAttr> src) {
		
		for (OrderAttr order : src) {
			ProductOrder prdOrd = new ProductOrder();
			prdOrd.setUser(order.getUser());
			prdOrd.setDatePayment(order.getDate_payment());
			prdOrd.setId_order(order.getId_order());
			prdOrd.setLaterPayment(order.getLater_payment());
			
			prdOrd.setTable(new Table(String.valueOf(order.getTable().getId()), order.getTable().getName()));
			prdOrd.setDate(order.getDate_order());

			for (OrderDetailAttr ordDetAttr : order.getOrderDetails()) {
				prdOrd.getProducts().add(new Product(String.valueOf(ordDetAttr.getProduct().getId()),
						ordDetAttr.getProduct().getName(), ordDetAttr.getUnit_price(),
						String.valueOf(ordDetAttr.getProduct().getCategory().getId()), ordDetAttr.getQuantity()));
			}
			dest.add(prdOrd);
		}
	}

// Getters

	public FilteredList<ProductOrder> getFilteredProductOrdersUnpaid() {
		return this.filteredProductOrdersUnpaid;
	}
	
	public ObservableList<ProductOrder> getProductOrdersPaid() {
		return this.productOrdersPaid;
	}
	
	public ObservableList<Expense> getExpenses() {
		return this.expenses;
	}

	public ObjectProperty<ProductOrder> getProducOrdertSelected() {
		return this.productOrderSelected;
	}

	public ObservableList<ProductOrder> getProductOrders() {
		return this.productOrders;
	}
}
