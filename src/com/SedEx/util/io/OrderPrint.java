package com.SedEx.util.io;

import java.util.List;

import com.SedEx.order.db.OrderVO;

public class OrderPrint {
	// 주문 리스트 출력하는 메소드
	public void print(List<OrderVO>list) {
		// 출력 구분선과 헤더 출력
		System.out.println();
		System.out.println("============================ [ 주문 관리 ] ==============================");
		System.out.println("----------------------------------------------------------------------");
		System.out.println(" 주문번호 /  회원번호  /  상품명  /  수량  / 주문자명 /     주문일     / 배송상태 /   총액  / ");
		
		// 주문 리스트가 없는 경우 메세지 출력
		if(list == null) {
			System.out.println("  ****** 주문목록이 조회 되지 않습니다. ******");
		} else {
			// 주문 리스트가 있는 경우 각 주문 출력
			for(OrderVO vo : list) {
				System.out.print("    " + vo.getOrdNo());
				System.out.print("  /   " + vo.getMemberNo());
				System.out.print("  / " + vo.getTitle());
				System.out.print("  /   " + vo.getQuantity());
				System.out.print(" /  " + vo.getName());
				System.out.print(" /  " + vo.getOrderdate());
				System.out.print(" /  " + vo.getStatus());
				System.out.print(" /  " + vo.getTotalPrice());
				System.out.println();
			}
		}
		System.out.println("-------------------------------------------");
	}
	// 주문 상세보기
	public void print(OrderVO vo) {
		// 주문번호 없는 경우 메세지 출력
		if(vo.getOrdNo()==null) {
			System.out.println("@##@$#@$@#$@#$@##$@#$$@#$@#$@#$@#$#@$#@#$@$#@$#@$#@$");
			System.out.println("@#$ 입력하신 주문번호는 없는 번호입니다. 다시 확인 해주세요. ^_^ $#@");
			System.out.println("@##@$#@$@#$@#$@##$@#$$@#$@#$@@#$#$@#$#@$#@$#@$#@$#@$");
			return;
		}
		// 주문 상세 정보 출력
		System.out.println("-------->>>>> [ 주문 상세보기 ] <<<<<--------");
		System.out.println("------------------------------------------");
		System.out.println(" + 주문번호 : " + vo.getOrdNo());
		System.out.println(" + 회원번호 : " + vo.getMemberNo());
		System.out.println(" + 상품번호 : " + vo.getNo());
		System.out.println(" + 상품명 : " + vo.getTitle());		
		System.out.println(" + 주문일 : " + vo.getOrderdate());
		System.out.println(" + 배송지 : " + vo.getAdress());
		System.out.println(" + 주문자 : " + vo.getName());
		System.out.println(" + 주문수량 : " + vo.getQuantity());
		System.out.println(" + TEL : " + vo.getTel());
		System.out.println(" + 배송현황 : " + vo.getStatus());
		System.out.println(" + 총액 : " + vo.getTotalPrice());
		System.out.println(" + 요청사항 : " + vo.getRequest());
		System.out.println(" + 환불사유 : " + vo.getReason());
		System.out.println("------------------------------------------");
	}

}
