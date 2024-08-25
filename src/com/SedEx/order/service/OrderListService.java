package com.SedEx.order.service;

import java.util.List;

import com.SedEx.main.service.Service;
import com.SedEx.order.db.OrderDAO;
import com.SedEx.order.db.OrderVO;

public class OrderListService implements Service {

	// 주문 목록을 조회하는 서비스 메소드
	public List<OrderVO> service(Object obj) throws Exception {
		// OrderDAO 객체를 생성하고 list() 메소드를 호출하여 주문 목록을 반환
		return new OrderDAO().list();
	}
}
