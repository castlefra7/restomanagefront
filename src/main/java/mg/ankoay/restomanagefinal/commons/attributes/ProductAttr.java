package mg.ankoay.restomanagefinal.commons.attributes;

public class ProductAttr {
	private Integer id;
	private String name;
	private Integer id_affiliate;
	private Integer id_category;
	private Double price;

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

	public Integer getId_category() {
		return id_category;
	}

	public void setId_category(Integer id_category) {
		this.id_category = id_category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
