package com.SedEx.cart.service;


import com.SedEx.cart.db.CartDAO;
import com.SedEx.main.service.Service;

//CartDeleteService 클래스를 정의하며, Service 인터페이스를 구현합니다.

public class CartDeleteService implements Service {

	//인터페이스 메서드를 구현
	@Override
	
	//service 메서드를 정의하며 이 메서드는 obj 매개변수를 받아 장바구니 항목을 삭제합니다.
	
	public Object service(Object obj) throws Exception {
		 
		//CartDAO 객체를 생성하고 delete 메서드를 호출하여 결과를 반환
		// obj를 long 타입으로 변환하여 delete 메서드에 전달
        
		return new CartDAO().delete((long) obj);
        
        //CartDAO 객체를 생성하고 delete 메서드를 호출하여 장바구니 항목을 삭제합니다.
        //obj를 long 타입으로 변환하여 delete 메서드에 전달합니다.
        //삭제 결과를 반환합니다.
    }

}