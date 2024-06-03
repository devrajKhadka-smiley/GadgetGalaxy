package model;

public class Product {

	private int modelNumber;
	private String productName;
	private int releasedYear;
	private String productCategory;
	private String productDistributer;
	private String productDescription;
	private int stockQuantity;
	private int unitPrice;
	private byte[] image_data;
	private String image_name;
	private String base64ImageData;

	public int getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductDistributer() {
		return productDistributer;
	}

	public void setProductDistributer(String productDistributer) {
		this.productDistributer = productDistributer;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public byte[] getImage_data() {
		return image_data;
	}

	public void setImage_data(byte[] image_data) {
		this.image_data = image_data;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getBase64ImageData() {
		return base64ImageData;
	}

	public void setBase64ImageData(String base64ImageData) {
		this.base64ImageData = base64ImageData;
	}

}
