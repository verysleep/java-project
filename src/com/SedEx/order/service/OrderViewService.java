package com.SedEx.order.service;

import java.util.List;

import com.SedEx.main.controller.Main;
import com.SedEx.main.service.Service;
import com.SedEx.order.db.OrderDAO;
import com.SedEx.order.db.OrderVO;

public class OrderViewService implements Service {

	// 주문 정보를 조회하는 서비스 메소드
	public OrderVO service(Object obj) throws Exception {
		// 관리자인지 확인
		if(Main.login.getGradeNo()==0) return new OrderDAO().view((Long) obj);
		// 관리자인 경우, 주문번호를 사용하여 주문 정보를 조회
		else return new OrderDAO().view1((Long) obj);
		// 일반 회원인 경우, 주문번호를 사용하여 주문 정보를 조회
	}
}
