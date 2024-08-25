package com.SedEx.cart.service;


import com.SedEx.cart.db.CartDAO;
import com.SedEx.cart.db.CartVO;
import com.SedEx.main.service.Service;

//CartUpdateService 클래스를 정의하며, Service 인터페이스를 구현합니다.
public class CartUpdateService implements Service {

	@Override
	//service 메서드를 정의합니다. 이 메서드는 obj 매개변수를 받아 장바구니 항목을 업데이트합니다.
	public Integer service(Object obj) throws Exception {
		
		//CartDAO 객체를 생성하고 update 메서드를 호출하여 장바구니 항목을 업데이트합니다.
		//obj를 CartVO 타입으로 변환하여 update 메서드에 전달합니다.
		//업데이트 결과를 반환합니다.
		return new CartDAO().update((CartVO)obj);
	}

}
