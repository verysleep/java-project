package com.SedEx.cart.db;

public class CartVO {

	private Long cartCode; //상품코드
	private Long no; //상품번호
	private Long quantity;	//상품수량
	private Long memberNo; //회원번호
	private String title; //상품명
	private Long price; //상품가격
	private Long sum; //총액
	
	
	 // 상품 코드를 가져오는 메서드
    public Long getCartCode() {
        return cartCode;
    }

    // 상품 코드를 설정하는 메서드
    public void setCartCode(Long cartCode) {
        this.cartCode = cartCode;
    }

    // 상품 번호를 가져오는 메서드
    public Long getNo() {
        return no;
    }

    // 상품 번호를 설정하는 메서드
    public void setNo(Long no) {
        this.no = no;
    }

    // 상품 수량을 가져오는 메서드
    public Long getQuantity() {
        return quantity;
    }

    // 상품 수량을 설정하는 메서드
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    // 회원 번호를 가져오는 메서드
    public Long getMemberNo() {
        return memberNo;
    }

    // 회원 번호를 설정하는 메서드
    public void setMemberNo(Long memberNo) {
        this.memberNo = memberNo;
    }

    // 상품명을 가져오는 메서드
    public String getTitle() {
        return title;
    }

    // 상품명을 설정하는 메서드
    public void setTitle(String title) {
        this.title = title;
    }

    // 상품 가격을 가져오는 메서드
    public Long getPrice() {
        return price;
    }

    // 상품 가격을 설정하는 메서드
    public void setPrice(Long price) {
        this.price = price;
    }

    // 총액을 가져오는 메서드
    public Long getSum() {
        return sum;
    }

    // 총액을 설정하는 메서드
    public void setSum(Long sum) {
        this.sum = sum;
    }

    // 객체의 문자열 표현을 반환하는 메서드 (디버깅용)
    @Override
    public String toString() {
        return "CartVO [cartCode=" + cartCode + ", no=" + no + ", quantity=" + quantity + ", memberNo=" + memberNo
                + ", title=" + title + ", price=" + price + ", sum=" + sum + "]";
	}
}//end of class
