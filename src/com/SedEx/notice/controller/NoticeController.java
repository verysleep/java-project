package com.SedEx.notice.controller;

import java.util.List;

import com.SedEx.main.controller.Main;
import com.SedEx.notice.db.NoticeVO;
import com.SedEx.notice.service.NoticeDeleteService;
import com.SedEx.notice.service.NoticeListService;
import com.SedEx.notice.service.NoticeUpdateService;
import com.SedEx.notice.service.NoticeViewService;
import com.SedEx.notice.service.NoticeWriteService;
import com.SedEx.util.exe.Execute;
import com.SedEx.util.io.In;
import com.SedEx.util.io.NoticePrint;

public class NoticeController {
	public static void execute() {
		while(true) {
			System.out.println();
			System.out.println("\t \t[ N O T I C E ]\t \t");
			System.out.println();
			
			Object result;
			
			try {
				result=Execute.execute(new NoticeListService(), null);
				new NoticePrint().print((List<NoticeVO>)result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("[\t    0. Return  1. View    \t]");
			if(Main.login!=null&&Main.login.getGradeNo()==0) System.out.println("[\t2. write  3. Update  4.Delete  \t]");
			
			String menu=In.getStr(" Menu");
			
			//입력 받는 데이터 선언
			long no=0;
			long flag=0;
			
			try {
				switch(menu) {
				case "0": System.out.println("... Return to MAIN ...");
					return;
//				case "1": System.out.println("...List...");
//					result=Execute.execute(new NoticeListService(), null);
//					new NoticePrint().print((List<NoticeVO>)result);
//					break;
				case "1": System.out.println("...View...");
					no=In.getLong("no");	
					result=Execute.execute(new NoticeViewService(), no);
					new NoticePrint().print((NoticeVO) result);
					System.out.println();
					
					//상세보기에서 이전 메뉴, 수정 출력 및 처리
					if(((NoticeVO)result).getNo()!=null) {
						System.out.println(" [ 0 for list ]");
						if(Main.login!=null&&Main.login.getGradeNo()==0) System.out.println(" [ 1 for update ]");
						flag=In.getLong(" input");
						if(flag==0) System.out.println("...return to list...");
						else if((flag==1)&&(Main.login!=null&&Main.login.getGradeNo()==0)) {//관리자 수정
							update(no);
						} else System.out.println("*** Wrong input: return to list ***");
					}
					
					break;
				case "2": if(Main.login!=null&&Main.login.getGradeNo()==0) {
					System.out.println("...Write...");
					//사용자 입력: 제목, 내용, 작성자, 비밀번호
					String title=In.getStr("제목");
					String content=In.getStr("내용");
					String start=In.getStr("시작일");
					String end=In.getStr("종료일");
					
					do {
						flag=In.getLong(" [ 0. 등록 취소, 1. 등록 ]");
						if(flag==1) {
							NoticeVO vo=new NoticeVO();
							vo.setTitle(title);
							vo.setContent(content);
							vo.setStartDate(start);
							vo.setEndDate(end);
							//BoardWriteService로 전달
							result=Execute.execute(new NoticeWriteService(), vo);
						}
					}while(flag!=0&&flag!=1);
					//입력데이터를 vo에 저장하여 service로 전달
					break;
				} else{
					System.out.println("*** Wrong Input ***");
					break;
				}
				case "3": if(Main.login!=null&&Main.login.getGradeNo()==0) {
					no=In.getLong(" 수정할 공지");
					update(no);
					break;
				} else {
					System.out.println("*** Wrong Input ***");
					break;
				}
				case "4": if(Main.login!=null&&Main.login.getGradeNo()==0) {
					System.out.println("...Delete...");
					NoticeVO vo=new NoticeVO();
					vo.setNo(In.getLong("번호"));
					result=Execute.execute(new NoticeDeleteService(), vo);
					System.out.println(" === "+vo.getNo()+"번 글 삭제 완료 ===");
					break;
				} else {
					System.out.println("*** Wrong Input ***");
					break;
				}
				default: System.out.println("*** Wrong Input ***");
				}//end of switch
			}catch(Exception e) {
				//e.printStackTrace();
				System.out.println("###오류 발생: "+e.getClass().getSimpleName()+"###");
			}//end of try-catch
		}//end of while
	}//end of execute()
	
	
	static void update(long no) throws Exception {
		System.out.println("...Update...");
		//수정할 데이터 보여주기
		NoticeVO vo=(NoticeVO)Execute.execute(new NoticeViewService(), no);
		new NoticePrint().print(vo);
		
		whileLoop: //while문의 루프
		while(true) {
			System.out.println();
			System.out.println("1. 제목 2. 내용 3. 시작일 4.종료일 9. 수정 취소 0. 수정 완료");
			String input=In.getStr("수정 항목");
			switch(input) {
			case "1": vo.setTitle(In.getStr("제목"));
				break;
			case "2": vo.setContent(In.getStr("내용"));
				break;
			case "3": vo.setStartDate(In.getStr("시작일"));
				break;
			case "4": vo.setEndDate(In.getStr("종료일"));
				break;
			case "9":
				break whileLoop;
			case "0":
				//DB 적용하는 처리문 작성
				int result=(int)Execute.execute(new NoticeUpdateService(), vo);
				System.out.println((result==1)?" : : : 수정 완료 : : :":" : : : 수정 실패 : : :");
				break whileLoop;
			default:
				System.out.println();
				System.out.println(" * * * * * 잘못된 입력 * * * * *");
				System.out.println(" * * [1~4, 9, 0] 중 선택하시오 * *");
			}//end of switch
			new NoticePrint().print(vo);
		}//end of while
	}// end of update
}