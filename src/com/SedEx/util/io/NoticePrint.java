package com.SedEx.util.io;

import java.util.List;

import com.SedEx.notice.db.NoticeVO;

public class NoticePrint {
	//게시판 리스트 출력 처리
	public static void print(List<NoticeVO> list) {
		System.out.println();
		System.out.println("=========================== NOTICE LIST ============================");
		System.out.println(" 번호 /\t\t제목\t\t/   시작일   /   종료일   /  수정일");
		System.out.println("====================================================================");
		if(list==null) System.out.println(" no data");
		else {
			for(NoticeVO vo:list) {
				System.out.println(String.format(" %-2s / %-20s / %-8s / %-8s / %-8s", 
						vo.getNo(), vo.getTitle(),
						vo.getStartDate(), vo.getEndDate(), vo.getUpdateDate()
						));
//				System.out.print(vo.getTitle()+" / ");
//				System.out.print(vo.getStartDate()+" / ");
//				System.out.print(vo.getEndDate()+" / ");
//				System.out.println(vo.getUpdateDate());
				System.out.println("====================================================================");
			}
		}
		System.out.println();
	}
	
	//게시판 상세보기 출력 처리
	public static void print(NoticeVO vo) {
		System.out.println();
		if(vo.getNo()==null) {
			System.out.println(" * * * 존재하지 않는 공지입니다. * * *");
			return;
		}
		System.out.println(" - - - - VIEW - - - -");
		System.out.println(" + 번호: "+vo.getNo());
		System.out.println(" + 제목: "+vo.getTitle());
		System.out.println(" + 내용: "+vo.getContent());
		System.out.println(" + 시작일: "+vo.getStartDate());
		System.out.println(" + 종료일: "+vo.getEndDate());
		System.out.println(" + 수정일: "+vo.getUpdateDate());
	}
}