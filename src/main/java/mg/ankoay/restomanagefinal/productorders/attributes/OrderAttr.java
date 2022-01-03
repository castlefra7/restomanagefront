package mg.ankoay.restomanagefinal.productorders.attributes;

import java.sql.Timestamp;
import java.util.Set;

import mg.ankoay.restomanagefinal.commons.attributes.TableAttr;

public class OrderAttr {
	private Integer id_order;
	private Integer id_user;
	private Set<OrderDetailAttr> orderDetails;
	private Timestamp date_order;
	private TableAttr table;
	private Timestamp date_payment;

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
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
