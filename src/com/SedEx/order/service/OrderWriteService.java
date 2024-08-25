package com.SedEx.order.service;

import java.util.List;


import com.SedEx.main.service.Service;
import com.SedEx.order.db.OrderDAO;
import com.SedEx.order.db.OrderVO;

public class OrderWriteService implements Service {
	// 주문 정보를 작성하는 서비스 메소드
	public Integer service(Object obj) throws Exception {
		
		// OrderDAO 객체를 생성하고 write() 메소드를 호출하여 주문 정보를 저장
		return new OrderDAO().write((OrderVO) obj);
	}
}
