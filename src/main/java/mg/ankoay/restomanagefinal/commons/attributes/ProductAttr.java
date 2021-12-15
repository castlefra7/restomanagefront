package mg.ankoay.restomanagefinal.commons.attributes;

public class ProductAttr {
	private Integer id;
	private String name;
	private Integer id_affiliate;
	private Double price;
	private CategoryAttr category;
	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductAttr() {

	}

	public ProductAttr(Integer _id) {
		this.id = _id;
	}

	public CategoryAttr getCategory() {
		return category;
	}

	public void setCategory(CategoryAttr category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId_affiliate() {
		return id_affiliate;
	}

	public void setId_affiliate(Integer id_affiliate) {
		this.id_affiliate = id_affiliate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
