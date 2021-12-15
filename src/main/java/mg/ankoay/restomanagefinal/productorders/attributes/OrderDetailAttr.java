package mg.ankoay.restomanagefinal.productorders.attributes;

import mg.ankoay.restomanagefinal.commons.attributes.CategoryAttr;
import mg.ankoay.restomanagefinal.commons.attributes.ProductAttr;

public class OrderDetailAttr {
	private Integer id;

	private Integer quantity;
	private Double unit_price;
	private Double amount;
	private ProductAttr product;
	private CategoryAttr category;

	public CategoryAttr getCategory() {
		return category;
	}

	public void setCategory(CategoryAttr category) {
		this.category = category;
	}

	public ProductAttr getProduct() {
		return product;
	}

	public void setProduct(ProductAttr product) {
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
