package com.SedEx.cart.service;

import java.util.List;

import com.SedEx.cart.db.CartDAO;
import com.SedEx.cart.db.CartVO;
import com.SedEx.main.service.Service;


//CartListService 클래스를 정의하며, Service 인터페이스를 구현합니다.
public class CartListService implements Service {

	// Service 인터페이스의 service 메서드를 구현합니다.
	@Override
	//service 메서드를 정의합니다. 이 메서드는 obj 매개변수를 받아 장바구니 항목 리스트를 반환합니다.
	public List<CartVO> service (Object obj) throws Exception {
		
		// CartDAO 객체를 생성하고 list 메서드를 호출하여 장바구니 리스트를 반환합니다.
		//CartDAO 객체를 생성하고 list 메서드를 호출하여 장바구니 항목 리스트를 가져옵니다.
		//가져온 리스트를 반환합니다.
		return new CartDAO().list();
	}//end of public
}//end of class 
