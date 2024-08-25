package com.SedEx.main.controller;

import com.SedEx.notice.controller.NoticeController;
import com.SedEx.util.exe.Execute;
import com.SedEx.cart.controller.CartController;
import com.SedEx.goods.controller.GoodsController;
import com.SedEx.member.controller.MemberController;
import com.SedEx.member.db.LoginVO;
import com.SedEx.order.controller.Ordercontroller;
import com.SedEx.util.io.In;

public class Main {
	
	// login 변수 안에 데이터가 있으면 (null 이 아니면) 로그인 한 상태
	// login 변수 안에 데이터가 없으면 (null) 로그인 하지 않은 상태
	public static LoginVO login = null;
	
	public static void loginInfo() {
		System.out.println();
		System.out.println("<<<------------- Login Information -------------->>>");
		if (login == null) { // 로그인이 안된 경우
			System.out.println("//\t\t로그인이 되어있지 않습니다.\t\t//");
			System.out.println("// 로그인을 하려면 5. 회원관리에서 6. 로그인 메뉴를 선택하세요. //");
		} else { // 로그인이 된 경우
			System.out.println("// " + login.getName() + "(" + login.getId() + " / " + login.getGradeName() + ")님 환영 합니다.");
			System.out.println("// 당신에게 온 새로운 메세지는 " + login.getNewMsgCnt() + "개 입니다.");
			
			
		} // end of if ~ else
		System.out.println("----------------------------------------------------");
	} // end of loginInfo
	
	
	public static void main(String[] args) { //주메뉴 선택
		loginInfo(); //로그인 처리
		
		//무한루프: 메뉴출력, 0~5번 입력, 메뉴처리
		while(true) {
			System.out.println();
			System.out.println("\t \t< S e d E x >\t \t");
			System.out.println();
			System.out.println("[\t 1. NOTICE  2. SHOP  3. CART \t  ]");
			System.out.println("[\t 4. ORDER  5. MEMBER  0. CLOSE \t  ]");
			
			String menu=In.getStr(" MENU");
			
			switch(menu) {
			case "1": System.out.println("...Moving to NOTICE...");
				new NoticeController().execute();
				break;
			case "2": System.out.println("...Moving to SHOP...");
				new GoodsController().execute();
				break;
			case "3": System.out.println("...Moving to CART...");
				if(Main.login!=null) new CartController().execute();
				else System.out.println("*** Wrong Access ***");
				break;
			case "4": System.out.println("...Moving to Order...");
				if(Main.login!=null) new Ordercontroller().execute();
				else System.out.println("*** Wrong Access ***");
				break;
			case "5": System.out.println("...Moving to MEMBER...");
				new MemberController().execute();
			break;
			case "0": System.out.println(" ~ THANK YOU FOR VISIT ~");
				System.exit(1);
			default: System.out.println("*** Wrong Input ***");
			}//end of switch
		}//end of while
	}//end of main
}