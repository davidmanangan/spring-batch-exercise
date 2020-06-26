package iam.davidmanangan.csvbatchprocessing.model;

import java.math.BigDecimal;
import java.util.Date;

public class StoreOrder {

	private Integer id;	
	private String orderId;
	private Date orderDate;
	private Date shipDate;
	private String shipMode;
	private Integer quantity;
	private BigDecimal discount;
	private BigDecimal profit;
	private String productId;
	private String customerName;
	private String category;
	private String customerId;
	private String productName;	
	
	
	
	public StoreOrder() {}
	

	/**
	 * 
	 * @param orderId - String
	 * @param orderDate - Date
	 * @param shipDate - Date
	 * @param shipMode - String
	 * @param quantity - Integer
	 * @param discount - BigDecimal
	 * @param profit - BigDecimal
	 * @param productId - String
	 * @param customerName - String
	 * @param category - String
	 * @param customerId - String
	 * @param productName - String
	 */
	public StoreOrder(String orderId, Date orderDate, Date shipDate, String shipMode, Integer quantity,
			BigDecimal discount, BigDecimal profit, String productId, String customerName, String category,
			String customerId, String productName) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.shipMode = shipMode;
		this.quantity = quantity;
		this.discount = discount;
		this.profit = profit;
		this.productId = productId;
		this.customerName = customerName;
		this.category = category;
		this.customerId = customerId;
		this.productName = productName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	public String getShipMode() {
		return shipMode;
	}
	public void setShipMode(String shipMode) {
		this.shipMode = shipMode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerNamew) {
		this.customerName = customerNamew;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
}
