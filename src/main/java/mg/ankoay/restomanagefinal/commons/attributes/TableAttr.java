package mg.ankoay.restomanagefinal.commons.attributes;

public class TableAttr {
	private Integer id;
	private String name;
	
	public TableAttr(Integer _id ) {
		this.id = _id;
	}
	
	public TableAttr() {
		
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
