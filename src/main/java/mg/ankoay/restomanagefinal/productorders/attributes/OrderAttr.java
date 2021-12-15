package mg.ankoay.restomanagefinal.productorders.attributes;

import java.sql.Timestamp;
import java.util.Set;

public class OrderAttr {
	private Integer id_table;
	private Set<OrderDetailAttr> orderDetails;
	private Timestamp date_order;

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

	public Integer getId_table() {
		return id_table;
	}

	public void setId_table(Integer id_table) {
		this.id_table = id_table;
	}

}
