package mx.edu.utng.ws;

public class Product {
	private int id;
	private String productName;
	private String supplierId;
	private String categoryId;
	private String quantityPerUnit;
	private String unitPrice;
	private String unitsInStock;
	private String unitsOnOrder;
	private String reorderLevel;
	private String discontinued;
	
	
	
	public Product(int id, String productName, String supplierId, String categoryId, String quantityPerUnit,
			String unitPrice, String unitsInStock, String unitsOnOrder, String reorderLevel, String discontinued) {
		super();
		this.id = id;
		this.productName = productName;
		this.supplierId = supplierId;
		this.categoryId = categoryId;
		this.quantityPerUnit = quantityPerUnit;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.unitsOnOrder = unitsOnOrder;
		this.reorderLevel = reorderLevel;
		this.discontinued = discontinued;
	}

	public Product(){
		this(0, "","","","","","","","","");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(String unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public String getUnitsOnOrder() {
		return unitsOnOrder;
	}

	public void setUnitsOnOrder(String unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}

	public String getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(String reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", supplierId=" + supplierId + ", categoryId="
				+ categoryId + ", quantityPerUnit=" + quantityPerUnit + ", unitPrice=" + unitPrice + ", unitsInStock="
				+ unitsInStock + ", unitsOnOrder=" + unitsOnOrder + ", reorderLevel=" + reorderLevel + ", discontinued="
				+ discontinued + "]";
	}

}
