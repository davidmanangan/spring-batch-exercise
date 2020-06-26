package iam.davidmanangan.csvbatchprocessing.model;

public class Sales {

	private String rowid;
	private String orderid;
	private String orderdate;
	private String shipdate;
	private String shipmode;
	private String customerid;
	private String customername;
	private String segment;
	private String country;
	private String city;
	private String state;
	private String postalcode;
	private String region;
	private String productid;
	private String category;
	private String subCategory;
	private String productname;
	private String sales;
	private String quantity;
	private String discount;
	private String profit;	
	
	
	public Sales() {}
	
	public Sales(String rowid, String orderid, String orderdate, String shipdate, String shipmode, String customerid,
			String customername, String segment, String country, String city, String state, String postalcode,
			String region, String productid, String category, String subCategory, String productname, String sales,
			String quantity, String discount, String profit) {
		super();
		this.rowid = rowid;
		this.orderid = orderid;
		this.orderdate = orderdate;
		this.shipdate = shipdate;
		this.shipmode = shipmode;
		this.customerid = customerid;
		this.customername = customername;
		this.segment = segment;
		this.country = country;
		this.city = city;
		this.state = state;
		this.postalcode = postalcode;
		this.region = region;
		this.productid = productid;
		this.category = category;
		this.subCategory = subCategory;
		this.productname = productname;
		this.sales = sales;
		this.quantity = quantity;
		this.discount = discount;
		this.profit = profit;
	}
	
	public String getRowid() {
		return rowid;
	}
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getShipdate() {
		return shipdate;
	}
	public void setShipdate(String shipdate) {
		this.shipdate = shipdate;
	}
	public String getShipmode() {
		return shipmode;
	}
	public void setShipmode(String shipmode) {
		this.shipmode = shipmode;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}	
}
