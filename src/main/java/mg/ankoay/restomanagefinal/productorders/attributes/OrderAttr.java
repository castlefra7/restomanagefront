package mg.ankoay.restomanagefinal.productorders.attributes;

import java.sql.Timestamp;
import java.util.Set;

import mg.ankoay.restomanagefinal.commons.attributes.TableAttr;

public class OrderAttr {
	private Integer id_order;
	private Integer id_table;
	private Set<OrderDetailAttr> orderDetails;
	private Timestamp date_order;
	private TableAttr table;

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

	public Integer getId_table() {
		return id_table;
	}

	public void setId_table(Integer id_table) {
		this.id_table = id_table;
	}

}
