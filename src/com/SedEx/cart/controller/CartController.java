package com.SedEx.cart.controller;

import java.util.List;

import com.SedEx.cart.db.CartVO;
import com.SedEx.cart.service.CartDeleteService;
import com.SedEx.cart.service.CartListService;
import com.SedEx.cart.service.CartUpdateService;
import com.SedEx.cart.service.CartWriteService;
import com.SedEx.main.controller.Main;
import com.SedEx.order.db.OrderVO;
import com.SedEx.order.service.OrderWriteService;
import com.SedEx.util.exe.Execute;
import com.SedEx.util.io.CartPrint;
import com.SedEx.util.io.In;

//CartController 클래스를 정의합니다.
public class CartController {


	//실행메서드
	//execute 메서드를 정의합니다. 이 메서드는 장바구니 기능을 제공합니다.
	public void execute() {
		
		//장바구니 기능을 반복적으로 제공합니다
		while(true) {
			System.out.println();
			//장바구니 라는 제목을 출력합니다
			System.out.println("장바구니 ");
			//장바구니 기능 메뉴 출력 - 리스트, 수정, 삭제, 담기, 이전메뉴
			System.out.print("|   1.리스트   |   2.수정   |   3.삭제   |   4.담기   |   0.이전메뉴   |");
			//줄바꿈 처리 출력
			System.out.println();
			
			//결과를 저장할 변수를 초기화합니다.
			Object result = null;
			//총액을 저장할 변수를 초기화합니다
			Long total = 0L;
			//회원이 입력한 메뉴번호를 받습니다
			String  menu = In.getStr("번호");
			
			//입력받는 데이터 초기화
			Long no = 0L;
			
			//예외처리 시작
			try {
				switch (menu) { //사용자가 선택한 메뉴에 따라 분기합니다
				
				//장바구니 리스트 출력
				case "1":
				    System.out.println("1.장바구니 리스트");
				    //CartListService를 실행하여 장바구니 리스트를 가져옵니다
				    result = Execute.execute(new CartListService(), null);
				    //결과를 cartList에 저장합니다
				    List<CartVO> cartList = (List<CartVO>) result;
				    //CartPrint를 사용허요 장바구니 리스트를 출력합니다
				    new CartPrint().print(cartList);
				    //장바구니에 비어있는지 확인합니다
				    if (cartList.isEmpty()) {
				    	//장바구니 비어 있는경우 메시지를 출력합니다
				        System.out.println("장바구니가 비어 있습니다.");
				    //장바구니에 상품이있는 경우 처리
				    } else {
				    	//장바구니에 상품이 있는 경우 처리
				        System.out.println();
				        //구매 여부를 묻습니다
				        String order = In.getStr("구매하시겠습니까? (y/n)");
				        //사용자가 입력한 구매 여부에 따라 분기합니다
				        switch (order) {
				        	//y를 선택한 경우 : 구매 처리
				            case "y":
				            	//로그인한 회원 번호를 가져옵니다
				                Long memberNo = Main.login.getMemberNo();
				                //연락처 입력 예시를 출력합니다
				                System.out.println("연락처 입력 예시 ) 010-1234-1234");
				                //회원의 연락처를 입력받습니다
				                String tel = In.getStr("연락처");
				                //배송지 입력 예시를 출력합니다
				                System.out.println("배송지 입력 예시 ) xx도 xx시 xx동 우편번호 (000동 000호)");
				                //회원의 배송지를 입력받습니다
				                String address = In.getStr("배송지");
				                //회원의 요성사항을 입력받습니다
				                String request = In.getStr("요청사항");
				                //입력받은 정보를 배열에 저장합니다
				                String[] info = {tel, address, request};
				                
				                //주문을 추가합니다
				                addOrder(info, cartList);

				                break;
				            //n을 선택한 경우: 구매취소
				            case "n":
				            	//구매를 취소한 경우 구매취소 메시지를 출력합니다
				                System.out.println("구매가 취소되었습니다.");
				                break;
				            default: //잘못된 입력 처리
				            	// 잘못된 입력 처리 경유 잘못된 입력이라고 메시지를 출력합니다
				                System.out.println("잘못된 입력입니다.");
				                break;
				        }//end of switch (order)
				    }//if (cartList.isEmpty()
				    break;
					
				    
				    
				    
				    
				    
				//메뉴 2번은 장바구니 수정 
				case"2":
					//장바구니 수정 제목을 출력합니다
					System.out.println("2.장바구니 수정");
					// 리스트 출력
					//CartPrint를 사용하여 장바구니 리스트를 출력합니다 
					new CartPrint().print((List<CartVO>) Execute.execute(new CartListService(), null));
					
					// 수정할 장바구니 상세 보기(대기)
					// 수정하기 (1.수량, 2.수정완료 0.수정최소)
					// 수정할 장바구니 코드를 입력받습니다
					no = In.getLong("장바구니 코드");
					//CartVO 객체를 생성합니다
					CartVO vo = new CartVO();
					
					//while 루프에 라벨을 붙입니다
					whileLoop:
					//무한루프 시작
					while(true) {
						
						System.out.println();
						System.out.println("=======================");
						System.out.println("1.수량, 2.수정완료 0.수정최소"); // 수정 항목 선택 메뉴를 출력합니다
						System.out.println("=======================");
						String item = In.getStr("수정 항목 선택"); // 선택한 항목에 따라선택합니다
						//선택한 항목에 따라 분기합니다
						switch(item) {
						//상품의 수량 수정
							case "1" : { //1을 선택한 경우 : 수량 수정
								//수량을 입력 받습니다
								long cnt=In.getLong("수량");
								//CartVO 객체 수량을 설정합니다
								vo.setQuantity(cnt);
								break;
							}
						
							case "2" : { //"2"를 선택한 경우: 수정 완료
								//상품의 수정 완료
								//CartVO 객체에 장바구니 코드를 설정합니다.
								vo.setCartCode(no);
								//CartUpdateService를 실행하여 장바구니 항목을 업데이트합니다.
								Execute.execute(new CartUpdateService(), vo);
								//수정 완료 메시지를 출력합니다.
								System.out.println("수정이 완료되었습니다.");
								//무한 루프를 종료합니다.
								break whileLoop;
							}
						
							case "0" : {//"0"을 선택한 경우: 수정 취소
								//상품의 수정 취소
								//수정 취소 메시지를 출력합니다.
								System.out.println("수정이 취소되었습니다.");
								//무한 루프를 종료합니다.
								break whileLoop;
							}
							default: //잘못된 입력 처리
								//상품 수정 중 예외처리
								//잘못된 항목 번호 메시지를 출력합니다.
								System.out.println(" 잘못된 항목 번호 선택하셨습니다. ");
								//올바른 번호 선택 메시지를 출력합니다.
								System.out.println(" [1, 2, 0]번호를 선택하세요. ");
						}//end of switch
					}//end of while
					break;
					
				//장바구니 상품삭제 
				case "3":	//메뉴 3: 장바구니 상품 삭제
					
					//장바구니 상품 삭제 제목을 출력합니다.
				    System.out.println("3.장바구니 상품삭제");
				    
				    // 장바구니 리스트 출력
				    //CartPrint를 사용하여 장바구니 리스트를 출력합니다.
				    new CartPrint().print((List<CartVO>) Execute.execute(new CartListService(), null));
				   
				    //CartVO 객체를 생성합니다.
				    vo = new CartVO();
				    
				    // 메뉴 출력
				    System.out.println("=======================");
				    System.out.println("1. 선택 삭제"); //선택 삭제 메뉴를 출력합니다.
				    System.out.println("2. 전체 삭제"); //전체 삭제 메뉴를 출력합니다.
				    System.out.println("0. 삭제 취소"); //삭제 취소 메뉴를 출력합니다.
				    System.out.println("=======================");
				    
				    // 삭제할 방법 선택
				    //삭제 방법을 입력받습니다.
				    long deleteOption = In.getLong("번호를 입력해주세요.");

				    //선택한 상품 삭제
				    if (deleteOption == 1) {
				        
				    	// 선택한 상품 삭제
				    	//삭제할 장바구니 번호를 입력받습니다.
				        vo.setCartCode(In.getLong("삭제할 장바구니 번호 입력하세요."));
				        //CartDeleteService를 실행하여 선택한 상품을 삭제합니다.
				        result = Execute.execute(new CartDeleteService(), vo.getCartCode());
				      //- 전체 상품 삭제
				    } else if (deleteOption == 2) {
				        // 전체 삭제를 위한 코드를 설정합니다.
				        vo.setCartCode(-1L);
				        //CartDeleteService를 실행하여 전체 상품을 삭제합니다.
				        result = Execute.execute(new CartDeleteService(), vo.getCartCode());
				      //삭제 취소
				    } else if (deleteOption == 0) {
				        // 삭제 취소 메시지를 출력합니다.
				        System.out.println("삭제가 취소되었습니다.");
				      //잘못된 선택 처리
				    } else {
				        // 잘못된 선택 메시지를 출력합니다.
				        System.out.println("잘못된 선택입니다. [1, 2, 0] 중에서 선택하세요.");
				    }
				    break;

				    
					
				//장바구니에 상품담기
				case"4":  //메뉴 4: 장바구니에 상품 담기
					//상품번호와 상품수량을 
					System.out.println("4.상품담기"); //상품 담기 제목을 출력합니다.
					no = In.getLong("상품번호"); //상품 번호를 입력받습니다.
					long Quantity = In.getLong("상품수량"); //상품 수량을 입력받습니다.
					
					//CartVO 객체를 생성합니다.
					CartVO vo1 = new CartVO();
					//CartVO 객체에 상품 번호를 설정합니다.
					vo1.setNo(no);
					//CartVO 객체에 상품 수량을 설정합니다.
					vo1.setQuantity(Quantity);
					//CartWriteService를 실행하여 장바구니에 상품을 추가합니다.
					result=Execute.execute(new CartWriteService(), vo1);
					break;
					
			// 이전메뉴로 돌아가기 
				case"0": //메뉴 0: 이전 메뉴로 돌아가기
					//이전 메뉴로 돌아가는 메시지를 출력합니다.
					System.out.println("이전메뉴로 돌아갑니다");
					return;	//메서드 종료
				}//end of switch
			
			
			//예외처리 출력 
			}catch (Exception e) {
				//- 예외 스택 트레이스를 출력합니다.
				e.printStackTrace();
				System.out.println("====  오류출력  ===="); //오류 출력 메시지를 출력합니다.
				System.out.println("====  타입: " + e.getClass().getSimpleName()); //예외 타입을 출력합니다.
				System.out.println("====  내용: " + e.getMessage()); //예외 내용을 출력합니다.
				System.out.println("====  조치: 데이터를 확인 후 다시 실행해 보세요"); //예외 처리 조치를 출력합니다.
			}//end of catch
		}//end of while
	}//end of public void excute

//주문 추가 메서드
//주문 추가 메서드를 정의합니다. arr은 연락처, 배송지, 요청사항 정보를 담고 있으며, cart_list는 장바구니 목록입니다.
static int addOrder(String[] arr, List<CartVO> cart_list) throws Exception {
		// 장바구니 목록을 순회하며 주문 처리
		for(CartVO vo : cart_list) {
			
			//OrderVO 객체를 생성합니다.
			OrderVO ovo = new OrderVO();
			ovo.setNo(vo.getNo());  // 주문 항목에 상품 번호를 설정합니다.
			ovo.setQuantity(vo.getQuantity());  //주문 항목에 수량을 설정합니다.
			ovo.setMemberNo(vo.getMemberNo());  //주문 항목에 회원 번호를 설정합니다.
			ovo.setName(Main.login.getName());  //주문 항목에 회원 이름을 설정합니다.
			ovo.setTotalPrice(vo.getSum());   //주문 항목에 총 가격을 설정합니다.

			ovo.setTel(arr[0]);  //주문 항목에 연락처를 설정합니다.
			ovo.setAdress(arr[1]);  //주문 항목에 배송지를 설정합니다.
			ovo.setRequest(arr[2]); //주문 항목에 요청사항을 설정합니다.
			ovo.setPayment((long) 1);  //결제 방식을 설정합니다.
			
			//주문처리
			//OrderWriteService를 실행하여 주문을 처리합니다.
			int result=(int) Execute.execute(new OrderWriteService(), ovo);
			//주문이 성공적으로 처리된 경우
			if(result==1) {
				System.out.println("주문이 성공적으로 처리되었습니다.");  //주문 성공 메시지를 출력합니다.
				long cartcode=vo.getCartCode();  //장바구니 코드를 가져옵니다.
				//장바구니에서 구매가 끝나면 CartDeleteService를 실행하여 장바구니에서 상품을 삭제합니다.
				int flag=(int)Execute.execute(new CartDeleteService(), cartcode); 
				//장바구니 삭제가 성공한 경우
				//장바구니 삭제 성공 메시지를 출력합니다.
				if(flag==1) System.out.println("장바구니 삭제 성공"); 
				//- 장바구니 삭제가 실패한 경우
				//- 장바구니 삭제 실패 메시지를 출력합니다.
				else System.out.println("장바구니 삭제 실패");
			} else {//- 주문 처리 중 오류가 발생한 경우
				//- 주문 처리 오류 메시지를 출력합니다.
				System.out.println("주문 처리 중 오류가 발생했습니다.");
			}
			
		}//end of for
//}end of if-else
	// 메서드를 종료하고 0을 반환합니다.
	return 0;
	
}//end of public
}//end of class
