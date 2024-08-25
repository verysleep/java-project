package com.SedEx.order.db;

public class OrderVO {

	private Long ordNo; // 주문번호
	private Long no; // 상품코드
	private Long memberNo; //회원번호
	private Long quantity; // 수량
	private Long order_detailNo; // 주문 상세보기 번호
	private String title; // 상품명
	private String orderdate; // 주문일
	private String adress; // 배송지
	private String name; // 수령인 이름
	private String tel; // 수령인 번호
	private String status; // 배송상태
	private Long totalPrice; // 총액
	private Long payment; // 결제수단
	private String request; // 요청사항
	private String reason; // 환불사유
	
	public Long getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(Long ordNo) {
		this.ordNo = ordNo;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getOrder_detailNo() {
		return order_detailNo;
	}
	public void setOrder_detailNo(Long order_detailNo) {
		this.order_detailNo = order_detailNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Long getPayment() {
		return payment;
	}
	public void setPayment(Long payment) {
		this.payment = payment;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		return "OrderVO [ordNo=" + ordNo + ", no=" + no + ", memberNo=" + memberNo + ", quantity=" + quantity
				+ ", order_detailNo=" + order_detailNo + ", title=" + title + ", orderdate=" + orderdate + ", adress="
				+ adress + ", name=" + name + ", tel=" + tel + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", payment=" + payment + ", request=" + request + ", reason=" + reason + "]";
	}
	
	

}