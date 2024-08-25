package com.SedEx.goods.db;

public class GoodsVO {

	// DAO 처리할 변수
	private Long no;
	private String catagory;
	private String title;
	private String content;
	private Long price;
	private String image;
	private String writeDate;
	private Long delivery_cost;
	private String status;
	
	// getter & setter
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
	public Long getDelivery_cost() {
		return delivery_cost;
	}
	public void setDelivery_cost(Long delivery_cost) {
		this.delivery_cost = delivery_cost;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	// toString()
	@Override
	public String toString() {
		return "GoodsVO [no=" + no + ", catagory=" + catagory + ", title=" + title + ", content=" + content + ", price="
				+ price + ", image=" + image + ", writeDate=" + writeDate + ", delivery_cost=" + delivery_cost
				+ ", status=" + status + "]";
	}
	
}
