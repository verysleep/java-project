package com.SedEx.util.io;

import java.util.List;

import com.SedEx.goods.db.GoodsVO;

public class GoodsPrint {

	// 상품 리스트 보기
	public void print(List<GoodsVO>list) {
		System.out.println();
		System.out.println("--------------------------------------------------------");
		System.out.println(" [  상품 코드  |  상품 카테고리  |  상품명  |  상품 이미지  |  상품 가격  |  상태  ] ");
		if (list == null) {
			System.out.println("데이터가 존재하지 않습니다.");
		} else {
			for (GoodsVO vo : list) {
				System.out.println();
				System.out.print(" [ " + vo.getNo());
				System.out.print("  |  " + vo.getCatagory());
				System.out.print("  |  " + vo.getTitle());
				System.out.print("  |  " + vo.getImage());
				System.out.print("  |  " + vo.getPrice());
				System.out.println("  |  " + vo.getStatus() + " ] ");
			}
			
		}
		
	} // end of print()-1
	
	// 상품 상세 보기
	public void print(GoodsVO vo) {
		System.out.println();
		System.out.println("--------------------------------------------------------");
		System.out.println("-------------------    상품 상세 정보    -------------------");
		System.out.println("+ 상품 코드 : " + vo.getNo());
		System.out.println("+ 상품 카테고리 : " + vo.getCatagory());
		System.out.println("+ 상품명 : " + vo.getTitle());
		System.out.println("+ 상품 설명 : " + vo.getContent());
		System.out.println("+ 상품 이미지 : " + vo.getImage());
		System.out.println("+ 상품 가격 : " + vo.getPrice());
		System.out.println("+ 상품 배송비 : " + vo.getDelivery_cost());
		System.out.println("+ 상품 등록일 : " + vo.getWriteDate());
		System.out.println("+ 판매 상태 : " + vo.getStatus());
		System.out.println("--------------------------------------------------------");
			
	} // end of print()-2
	
} // end of main()
