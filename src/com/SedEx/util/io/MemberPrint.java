package com.SedEx.util.io;

import java.util.List;

import com.SedEx.member.db.MemberVO;

public class MemberPrint {
	
	// 1. 회원 List 출력 메소드
	public void print(List<MemberVO> list) {
		System.out.println();
		System.out.println("|| 회원 리스트 || ------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|       ID       |        Name      | Gen |    Birth    |       Tel       |             E-Mail          |   RegDate   |   ConDate   |Status|  No  |   Grade   |");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
		if (list == null) {
			System.out.println(" @@@@ 데이터가 존재하지 않습니다. @@@@");
		} else {
			for(MemberVO vo : list) {
				System.out.printf("%-15s  | ", vo.getId());
				System.out.printf("%-15s  | ", vo.getName());
				System.out.printf("%-1s  | ", vo.getGender());
				System.out.printf("%-2s  | ", vo.getBirth());
				System.out.printf("%-15s  | ", vo.getTel());
				System.out.printf("%-26s  | ", vo.getEmail());
				System.out.printf("%-2s  | ", vo.getRegDate());
				System.out.printf("%-2s  | ", vo.getConDate());
				System.out.printf("%-2s  | ", vo.getStatus());
				System.out.printf("%-3s  | ", vo.getMemberNo());
				System.out.printf("%-3s", vo.getGradeName());
				System.out.println();
			} // end of if
		} // end of if
	} // end of ListPrint
	
	// 2. 아이디 찾기 출력
	public void findID(MemberVO vo) {
		
		if (vo.getId() != null ) {
		System.out.println();
		System.out.println("+-----------------------------------------------------+");
		System.out.println(" 입력하신 이메일로 찾은 아이디는 " + vo.getId() + " 입니다. 다시 로그인해 주세요.");
		System.out.println("+-----------------------------------------------------+");
	}  else {
		System.out.println("+-----------------------------------------------------+");
		System.out.println("        입력하신 정보에 맞는 아이디가 존재하지 않습니다.");
		System.out.println("+-----------------------------------------------------+");
		} // end of if
	}
	// end of FindID
	
	// 3. 회원 상세보기 출력 메소드
	public void print(MemberVO vo) {
		System.out.println();
		System.out.println("||-------------- " + vo.getId() + " 님 상세보기 --------------||");
		System.out.println("  ID      → " + vo.getId());
		System.out.println("  PW      → " + vo.getPw());
		System.out.println("  Name    → " + vo.getName());
		System.out.println("  Gender  → " + vo.getGender());
		System.out.println("  Birth   → " + vo.getBirth());
		System.out.println("  Tel     → " + vo.getTel());
		System.out.println("  Email   → " + vo.getEmail());
		System.out.println("  Add     → " + vo.getAddress());
		System.out.println("  RegDate → " + vo.getRegDate());
		System.out.println("  ConDate → " + vo.getConDate());
		System.out.println("  Stauts  → " + vo.getStatus());
		System.out.println("  No      → " + vo.getMemberNo());
		System.out.println("  Photo   → " + vo.getPhoto());
		System.out.println("  Grade   → " + vo.getGradeName());
	}

} // end of class
