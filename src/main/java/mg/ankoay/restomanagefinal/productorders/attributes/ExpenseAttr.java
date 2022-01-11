package mg.ankoay.restomanagefinal.productorders.attributes;

import java.sql.Timestamp;

import mg.ankoay.restomanagefinal.commons.attributes.UserAttr;
import mg.ankoay.restomanagefinal.productorders.model.UserOrder;

/**
 * @author lacha
 *
 */
public class ExpenseAttr {
	private Integer id;
	private Timestamp date_expense;
	private Double amount;
	private String reason;
	private UserOrder user;

	public UserOrder getUser() {
		return user;
	}

	public void setUser(UserOrder user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDate_expense() {
		return date_expense;
	}

	public void setDate_expense(Timestamp date_expense) {
		this.date_expense = date_expense;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
