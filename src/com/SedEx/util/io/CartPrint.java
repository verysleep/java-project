package com.SedEx.util.io;

import java.util.List;

import com.SedEx.cart.db.CartVO;
//import com.SedEx.order.db.OrderVO;

//CartPrint 클래스를 정의합니다.
public class CartPrint {

	//print 메서드를 정의하며. 이 메서드는 장바구니 항목 리스트를 받아서 출력하고 총액을 반환합니다.
	public long print(List<CartVO> list) {
		
		//출력 포맷 설정
		//빈 줄을 출력합니다.
		System.out.println();
		
		//장바구니 리스트 헤더를 출력합니다.
		System.out.println("<=====================================================  장바구니 리스트  ==========================================>");
		
		//장바구니 항목의 각 속성에 대한 헤더를 출력합니다. 
		//(장바구니번호, 회운번호, 상품코드, 상품명, 상품수량, 상품가격, 합) 이렇게 있습니다
		System.out.println("[        장바구니 번호        ][        회원번호        ][        상품코드        ][         상품명         ][        상품수량        ][        상품가격        ][          합          ]");
		
		//총액을 저장할 변수입니다.
		long total=0;
		
		//리스트가 null인지 확인합니다.
		if(list == null) {
			
			//데이터가 없을 때 메시지를 출력합니다.
			System.out.println("데이터가 존재하지 않습니다");
		
			//리스트가 null이 아닐 때의 처리를 시작합니다.
		}else {
			
			//리스트의 각 항목에 대해 출력 반복합니다.
			for(CartVO vo : list) {
					System.out.print("|"  + vo.getCartCode()); //장바구니 번호를 출력합니다
					System.out.print("                         | "  + vo.getMemberNo()); //회원번호를 출력합니다
					System.out.print("                    | " + vo.getNo()); //상품번호를 출력합니다
					System.out.print("                    | " + vo.getTitle()); //제품명을 출력합니다
					System.out.print("                    | " + vo.getQuantity( )); //상품수량을 출력합니다
					System.out.print("                    |" + vo.getPrice( )); //상품가격을 출력합니다
					System.out.print("                |" + vo.getSum( )); //합계를 출력합니다
					System.out.print("                |");
				
				//자바구니의 총금액을 계산
				total+=vo.getSum(); //각 항목의 합계를 총액에 더합니다		
				System.out.println(); // 각 항목의 출력을 완료 후 줄바꿈
			}//end of for
			
			//총액을 출력합니다
			System.out.println("\t \t \t \t -> 총금액: "+total+"원");
			System.out.println();
		}//end of else
		
		return total; //총액을 반환합니다
		
	}//end of public
}//end of class
