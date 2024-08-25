package com.SedEx.goods.controller;

import java.util.List;

import com.SedEx.cart.db.CartVO;
import com.SedEx.cart.service.CartWriteService;
import com.SedEx.goods.db.GoodsVO;
import com.SedEx.goods.service.GoodsDeleteService;
import com.SedEx.goods.service.GoodsListService;
import com.SedEx.goods.service.GoodsUpdateService;
import com.SedEx.goods.service.GoodsViewService;
import com.SedEx.goods.service.GoodsWriteService;
import com.SedEx.main.controller.Main;
import com.SedEx.order.db.OrderVO;
import com.SedEx.order.service.OrderWriteService;
import com.SedEx.util.exe.Execute;
import com.SedEx.util.io.GoodsPrint;
import com.SedEx.util.io.In;

public class GoodsController {

	@SuppressWarnings("unchecked")
	public void execute() {
		
		while (true) {
			// 상품 관리 시작 화면
			System.out.println("--------------------    SHOP   ---------------------");
			System.out.println("-- 1. 상품 리스트          0. 이전 화면 ");
			// 관리자만 아래 메뉴를 볼 수 있다.
			if (Main.login != null && Main.login.getGradeNo() != 1) {
				System.out.println("-- 2. 상품 등록 3. 상품 수정 4. 상품 삭제 ");
			}
			System.out.println();
			
			String menu = In.getStr("항목 선택");
			System.out.println();
			
			Object result = null;
			Long no = 0L;
			
			try {
				// 관리자만 아래 기능을 실행시킬 수 있다.
				if (Main.login != null && Main.login.getGradeNo() != 1) { // 최종 관리자, 쇼핑몰 관리자
					switch (menu) {
					
					case "1": // 상품 리스트
						System.out.println("------------------- 1. 상품 리스트 --------------------");
						// GoodsListService를 호출하고 실행, 상품리스트 출력
						result = Execute.execute(new GoodsListService(), null);
						new GoodsPrint().print((List<GoodsVO>) result);
	
						// 상품 코드 입력 (이때 0을 입력하면 이전으로 돌아감)
						no = In.getLong("\n-- 이전 화면은 0번, 상품의 상세 보기는 상품 코드를 입력해 주세요.");
						if (no == 0) break;
						
						// GoodsViewService를 호출하고 실행, 넘어가는 데이터 : no (상품 코드)
						result = Execute.execute(new GoodsViewService(), no);
						
						// 상세 보기 출력
						new GoodsPrint().print((GoodsVO) result);
						break;
						
					case "2": // 상품 등록
						System.out.println("------------------- 2. 상품 등록  --------------------");
						// 필수로 입력해야 하는 내용 : 카테고리, 상품명, 상품 설명, 가격, 이미지
						String catagory = In.getStr("-- 상품 카테고리( 상의  /  하의  /  신발  /  기타 )");
						String title = In.getStr("-- 상품명");
						String content = In.getStr("-- 상품 설명");
						Long price = In.getLong("-- 가격");
						String image = In.getStr("-- 이미지");
	
						// 입력한 내용을 GoodsVO 에 setter() 를 이용해 저장
						GoodsVO vo = new GoodsVO();
						vo.setCatagory(catagory);
						vo.setTitle(title);
						vo.setContent(content);
						vo.setPrice(price);
						vo.setImage(image);
						
						// GoodsWriteService()를 호출하고 실행, 넘어가는 데이터 : vo (입력받은 데이터를 저장하는 개체)
						Execute.execute(new GoodsWriteService(), vo);
						break;
						
					case "3": // 상품 수정
						System.out.println("------------------- 3. 상품 수정  --------------------");
						// 상품 리스트 호출 및 출력
						result = Execute.execute(new GoodsListService(), null);
						new GoodsPrint().print((List<GoodsVO>) result);
						
						// 수정할 상품 코드로 상세보기
						no = In.getLong("-- 수정할 상품 코드 입력");
						result = Execute.execute(new GoodsViewService(), no);
						
						// 수정한 내용을 입력받고 저장할 새로운 변수(updateVO) 생성 
						GoodsVO updateVO = (GoodsVO) Execute.execute(new GoodsViewService(), no);
						
						// 상품 수정 무한 반복
						whileLoop:
						while (true) {
							
							new GoodsPrint().print(updateVO);
							// 수정할 항목 출력
							System.out.println("-- 1. 상품 설명    2. 이미지    3. 가격    4. 배송비 ");
							System.out.println("-- 0. 수정 완료    9. 수정 취소 ");
							System.out.println();
							
							String item = In.getStr("-- 수정 항목을 선택해 주세요.");
								
							switch (item) {
							
								case "1": // 상품 설명
									updateVO.setContent(In.getStr("-- 상품 설명"));
									break;
		
								case "2": // 이미지
									updateVO.setImage(In.getStr("-- 이미지"));
									break;
															
								case "3": // 가격
									updateVO.setPrice(In.getLong("-- 가격"));
									break;
									
								case "4": // 배송비
									updateVO.setDelivery_cost(In.getLong("-- 배송비"));
									break;
								
								case "0": // 수정 완료
									System.out.println();
									Execute.execute(new GoodsUpdateService(), updateVO);
									break whileLoop;
	
								case "9": // 수정 취소
									System.out.println();
									System.out.println("** 수정이 취소되었습니다. **");
									break whileLoop;
									
								default:
									break whileLoop;
								
								} // end of switch-case
						
						} // end of while
							
						break;
						
					case "4": // 상품 삭제
						System.out.println("------------------- 4. 상품 삭제  --------------------");
	
						// 상품 리스트 출력
						result = Execute.execute(new GoodsListService(), null);
						new GoodsPrint().print((List<GoodsVO>) result);
						
						// 삭제할 상품의 코드 입력
						no = In.getLong("\n-- 삭제할 상품 코드 입력");
						
						// 삭제 여부 확인
						System.out.println("-- 상품을 정말 삭제하시겠습니까? ");
						menu = In.getStr("-- 0. 삭제 완료    9. 삭제 취소");
						
						// switch-case
						switch (menu) {
							case "0": // 삭제 완료
								System.out.println();
								Execute.execute(new GoodsDeleteService(), no);
								break;
								
							case "9": // 삭제 취소
								System.out.println();
								System.out.println("** 삭제가 취소되었습니다. **");
								break;
							
							default:
								break;
									
						} // end of switch-case
						break;
						
					case "0": // 이전 화면
						return;
						
					default:
						System.out.println("--------------------------------------------------------");
						System.out.println("-- 잘못된 숫자가 입력되었습니다. ");
						System.out.println("-- [ 1 ~ 4 / 0 ] 중에서 선택해주세요.");
						System.out.println("--------------------------------------------------------");
						System.out.println();
						break;
					} // end of switch-case
				
				} else { // 일반 회원
				
					switch (menu) {
					
					case "1": // 상품 리스트 + 상품 상세보기(장바구니 / 구매)
						System.out.println("------------------- 1. 상품 리스트 --------------------");
						// GoodsListService를 호출하고 실행
						result = Execute.execute(new GoodsListService(), null);
						// 상품 리스트를 출력
						new GoodsPrint().print((List<GoodsVO>) result);
						System.out.println();
						
						// 상세 정보
						no = In.getLong("\n-- 이전 화면은 0을, 상세보기는 상품 코드를 입력하세요.");
						if (no == 0) break;
						
						// 입력 받은 상품 코드의 상세 정보를 result에 저장
						result = Execute.execute(new GoodsViewService(), no);
						
						// 상품 상태 확인
						if ("판매".equals(((GoodsVO)result).getStatus())) {
							
							// 상태가 "판매" 일 때 장바구니 / 구매 선택 
							new GoodsPrint().print((GoodsVO) result);
							
							System.out.println("-- 2. 장바구니  3. 구매  0. 이전 메뉴 ");
							String cart = In.getStr("-- 항목 입력");
							
							// 0 을 입력하면 이전 화면으로 이동
							if ("0".equals(cart)) break;
							
							// 로그인 여부
							if (Main.login != null) {
								
								switch (cart) {
								case "2": // 장바구니 (입력 데이터 : 상품코드, 수량, 회원번호)
									Long quantity = In.getLong("-- 수량");
									Long memberNo = Main.login.getMemberNo();
									
									// 입력 받은 데이터를 새로운 변수(cartVO)를 통해 CartVO 에 저장
									CartVO cartVO = new CartVO();
									cartVO.setNo(no);
									cartVO.setQuantity(quantity);
									cartVO.setMemberNo(memberNo);

									// CartWriteService()를 호출하고 실행, 넘어가는 데이터 : cartVO (입력받은 데이터를 저장하는 개체)
									Execute.execute(new CartWriteService(), cartVO);
									
									System.out.println("-- 장바구니에 상품을 추가하였습니다.\n");
									break;
	
								case "3": // 구매 (입력 데이터 : 수량, 회원 데이터, 연락처, 배송지, 요청사항, 결제 총액)
									quantity = In.getLong("-- 수량");
									memberNo = Main.login.getMemberNo();
									String tel = In.getStr("-- 연락처 입력 예시 ) 010-1234-1234\n");
									String adress = In.getStr("-- 배송지 입력 예시 ) xx도 xx시 xx동 우편번호 (000동 000호)\n");
									String request = In.getStr("-- 요청사항");
									Long totalPrice = ((GoodsVO) result).getPrice() * quantity + ((GoodsVO) result).getDelivery_cost();
	
									// 결제 여부 묻기
									Long payment = In.getLong("-- 결제를 하시겠습니까? (0. 결제 취소, 1. 결제 진행)");
									
									if (payment == 1) {
										
										Long card = In.getLong("-- 결제 방법을 선택하세요? (0. 무통장, 1. 카드 결제)");
										
										if (card == 0) {
											System.out.println("-- 무통장 입금 입니다.");
										} else {
											System.out.println("-- 카드 결제 입니다.");
										}
										
										OrderVO orderVO = new OrderVO();
										orderVO.setNo(no);
										orderVO.setQuantity(quantity);
										orderVO.setMemberNo(memberNo);
										orderVO.setPayment(payment);
										orderVO.setAdress(adress);
										orderVO.setTel(tel);
										orderVO.setRequest(request);
										orderVO.setTotalPrice(totalPrice);
										
										// OrderWriteService()를 호출하고 실행
										Execute.execute(new OrderWriteService(), orderVO);
										System.out.println("-- 해당 상품이 결제되었습니다.");
									} else {
										System.out.println("-- 결제가 취소되었습니다.");
									} // end of if-else : 결제
									break;
							
								default:
									System.out.println("--------------------------------------------------------");
									System.out.println("-- 잘못된 숫자를 입력하셨습니다. ");
									System.out.println("-- [ 2 ~ 3 / 0 ] 중에서 선택해주세요.");
									System.out.println("--------------------------------------------------------");
								} // end of switch-case - 장바구니 & 구매로 넘기기
							
							} else { // 로그인 안 한 상태
								System.out.println("-- 로그인이 필요한 서비스 입니다. 로그인 후 다시 이용해 주세요.");
								return;
							} // end of if-else : 회원의 로그인 여부
								
						} else { // 상품 상태가 품절일 때
							System.out.println("\n-- 해당 상품은 품절입니다. ");
						} // end of if-else : 상품 상태 확인
						break;
						
					case "0": // 이전 화면
						return;
						
					default:
						System.out.println("--------------------------------------------------------");
						System.out.println("-- 잘못된 숫자를 입력하셨습니다. ");
						System.out.println("-- [ 1 ~ 0 ] 중에서 선택해주세요.");
						System.out.println("--------------------------------------------------------\n");
					} // end of switch-case(일반 회원 시작 화면)
				
				} // end of if-else(권한 부여)
			
			} catch (Exception e) {
//				e.printStackTrace();
			} // end of try-catch
		
		} // end of while
		
	} // end of execute()

} // end of class
