package com.SedEx.order.controller;

import java.util.List;
import com.SedEx.order.service.OrderListService;
import com.SedEx.order.service.OrderUpdateService;
import com.SedEx.order.service.OrderViewService;
import com.SedEx.main.controller.Main;
import com.SedEx.order.db.OrderVO;
import com.SedEx.util.exe.Execute;
import com.SedEx.util.io.In;
import com.SedEx.util.io.OrderPrint;



public class Ordercontroller {

	@SuppressWarnings("unchecked")
	public void execute() {

		// 게시판 기능 무한 반복
		while(true) { // 반복문
			// 모듈 이름 출력
			System.out.println();
			System.out.println("<<------------------- [ Welcome to order ] --------------------->");
			// 메뉴 출력
			// 메뉴 출력 - 리스트, 글보기, 글등록, 글수정, 글삭제
			System.out.println("****************************************************************");
			System.out.println("** 1. 주문 리스트, 2. 주문내역 상세보기,  3. 주문상태 수정, 0. 이전 메뉴   **");
			System.out.println("****************************************************************");
			
			// 메뉴 선택하기
			String menu = In.getStr("메뉴");
			
			Object result = null;
			
			// 입력받는 데이터 선언
			Long ordNo = 0L;
			
			try {
				switch (menu) {
				case "1":
					System.out.println("1. 주문 리스트");
					result = Execute.execute(new OrderListService(), null); // 주문리스트 처리하는 서비스를 result 변수에 저장
					new OrderPrint().print((List<OrderVO>) result);// orderprint 클래스의 print를 호출하여 result 변수를 출력한다.
					break;
					
				case "2":
					System.out.println("2. 주문내역 상세보기");
					
					ordNo = In.getLong("주문번호"); // 주문번호를 입력 받아서 long타입으로 변환 후 ordNo변수에 저장
					
					result = Execute.execute(new OrderViewService(), ordNo); // 주문상세보기 처리하는 서비스를 result 변수에 저장
					
					new OrderPrint().print((OrderVO)result); // orderprint 클래스의 print를 호출하여 OrderVo를 캐스팅하고 변수를 출력한다.
					break;
					
				case "3":
					System.out.println("주문현황 수정");
					// 주문목록을 조회하고 출력
					result = Execute.execute(new OrderListService(), null);
					new OrderPrint().print((List<OrderVO>) result);
					
					// 수정할 주문번호 입력 받음
					ordNo = In.getLong(" 수정할 주문번호");
					
					OrderVO updateVO = 	(OrderVO)Execute.execute(new OrderViewService(), ordNo);	
					whileLoop:
					while(true) {
						// 주문 정보 출력
						new OrderPrint().print(updateVO);
						System.out.println();
						System.out.println("----------------------------------------------------------");
						System.out.println("----------  [ 1.배송지  2.이름  3.TEL  4.주문상태 ] ------------");
						System.out.println("---------- [ 5.요청사항 6.환불사유 9.수정취소 0.수정완료 ] ----------");
						System.out.println("----------------------------------------------------------");
						// 수정할 항목 선택
						String item = In.getStr("수정 항목 선택");
						switch (item) {
						case "1": {
							// 배송지 수정
							updateVO.setAdress(In.getStr("배송지"));
							break;
						}
						case "2": {
							// 이름 수정
							updateVO.setName(In.getStr("이름"));
							break;
						}
						case "3": {
							 // 전화번호 수정
							updateVO.setTel(In.getStr("TEL"));
							break;
						}
						case "4": {
							// 주문 상태 수정
							if(Main.login.getGradeNo() == 0) {
								 // 관리자일 경우
								updateVO.setStatus(In.getStr("주문상태(배송준비중, 배송중, 배송완료)"));
							}else {
								// 일반 사용자일 경우
								updateVO.setStatus(In.getStr("주문상태(구매확정, 환불처리, 반품처리)"));
							}
							break;
						}
						case "5": {
							// 요청사항 수정
							updateVO.setRequest(In.getStr("요청사항"));
							break;
						}
						case "6": {
							// 환불사유 수정
							updateVO.setReason(In.getStr("환불사유"));
							break;
						}
						case "9": {
							// 수정 취소
							System.out.println();
							System.out.println("*** 수정이 취소 되었습니다. ***");
							break whileLoop;
						}
						case "0": {
							// 수정 완료, DB에 적용
							Execute.execute(new OrderUpdateService(), updateVO);
							break whileLoop;
						}
						default:
							 // 잘못된 항목 번호를 입력했을 경우
							System.out.println("**************************************");
							System.out.println("******잘못된 항목 번호를 선택하셨습니다.******");
							System.out.println("****[1~6, 9, 0] 번호를 선택하셔야 합니다****");
							System.out.println("**************************************");
					} // switch
					
					} // while
					break;
					
					
				case "0":
					 // 종료
					return;
					
					
				default:
					 // 잘못된 메뉴 번호를 입력했을 경우
					System.out.println("**************************************");
					System.out.println("******잘못된 항목 번호를 선택하셨습니다.******");
					System.out.println("** [1~3, 0] 번호를 선택하셔야 합니다. **");
					System.out.println("**************************************");
					break;
				}// try
				// 예외 처리
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}// finally 블록
		} // end of while
	} // end of public void
} // end of class
