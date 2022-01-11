package mg.ankoay.restomanagefinal.productorders.attributes;

import java.sql.Timestamp;
import java.util.Set;

import mg.ankoay.restomanagefinal.commons.attributes.TableAttr;
import mg.ankoay.restomanagefinal.productorders.model.UserOrder;

public class OrderAttr {
	private Integer id_order;

	private Set<OrderDetailAttr> orderDetails;
	private Timestamp date_order;
	private TableAttr table;
	private Timestamp date_payment;
	private UserOrder user;
	private String later_payment;

	public String getLater_payment() {
		return later_payment;
	}

	public void setLater_payment(String later_payment) {
		this.later_payment = later_payment;
	}

	public UserOrder getUser() {
		return user;
	}

	public void setUser(UserOrder user) {
		this.user = user;
	}

	public Timestamp getDate_payment() {
		return date_payment;
	}

	public void setDate_payment(Timestamp date_payment) {
		this.date_payment = date_payment;
	}

	public Integer getId_order() {
		return id_order;
	}

	public void setId_order(Integer id_order) {
		this.id_order = id_order;
	}

	public TableAttr getTable() {
		return table;
	}

	public void setTable(TableAttr table) {
		this.table = table;
	}

	public Timestamp getDate_order() {
		return date_order;
	}

	public void setDate_order(Timestamp date_order) {
		this.date_order = date_order;
	}

	public Set<OrderDetailAttr> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetailAttr> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
