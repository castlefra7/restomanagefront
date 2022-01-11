package mg.ankoay.restomanagefinal.productorders.model;

public class UserOrder {
	private String name;
	private Integer id;
	
	public UserOrder() {
		
	}
	
	public UserOrder(Integer _id) {
		this.id = _id;
	}
	
	@Override
	public String toString() {
		return this.getName();
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
	
}
