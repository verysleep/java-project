package com.SedEx.order.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.SedEx.main.controller.Main;
import com.SedEx.order.db.OrderVO;
import com.SedEx.util.db.DAO;
import com.SedEx.util.db.DB;



public class OrderDAO extends DAO {

	// 주문 리스트를 가져오는 메서드
	public List<OrderVO> list() throws Exception {
		
		List<OrderVO> list = null;
		
		try {
			 // 데이터베이스 연결
			con = DB.getConnection();
		System.out.println(LIST);
		// 관리자일 경우
		if(Main.login.getGradeNo()==0) {
			pstmt = con.prepareStatement(LIST);
			
			rs = pstmt.executeQuery();
			
			
			if(rs != null) {
				while(rs.next()) {
					if (list == null) list = new ArrayList<OrderVO>();
					OrderVO vo = new OrderVO();
					vo.setOrdNo(rs.getLong("OrdNo")); // 주문번호
					vo.setMemberNo(rs.getLong("memberNo")); // 주문번호
					vo.setTitle(rs.getString("title")); // 상품명
					vo.setQuantity(rs.getLong("Quantity")); // 수량
					vo.setName(rs.getString("name")); // 주문자명
					vo.setOrderdate(rs.getString("orderDate")); // 주문일
					vo.setStatus(rs.getString("status")); // 배송상태
					vo.setTotalPrice(rs.getLong("TotalPrice")); // 총액
					
					list.add(vo);
				}
			}
		} else {
			// 일반 사용자일 경우
				pstmt = con.prepareStatement(LIST1);
				pstmt.setLong(1, Main.login.getMemberNo() );
						
				rs = pstmt.executeQuery();
				
				
				if(rs != null) {
					while(rs.next()) {
						if (list == null) list = new ArrayList<OrderVO>();
						OrderVO vo = new OrderVO();
						vo.setOrdNo(rs.getLong("OrdNo")); // 주문번호
						vo.setMemberNo(rs.getLong("memberNo")); // 주문번호
						vo.setTitle(rs.getString("title")); // 상품명
						vo.setQuantity(rs.getLong("Quantity")); // 수량
						vo.setName(rs.getString("name")); // 주문자명
						vo.setOrderdate(rs.getString("orderDate")); // 주문일
						vo.setStatus(rs.getString("status")); // 배송상태
						vo.setTotalPrice(rs.getLong("TotalPrice")); // 총액
						
						list.add(vo);
					} // while
				} // if
			} // else
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally {	
			// 리소스 정리
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally
		return list;
	} // end of public 
	
	
	public OrderVO view(Long ordNo) throws Exception { //관리자 view
		OrderVO vo = null;
		try {
			// DB연결
		con = DB.getConnection();
		// View sql문
		pstmt = con.prepareStatement(VIEW);
		pstmt.setLong(1, ordNo); // 첫 번째 인덱스에 주문번호 설정
		
		// SQL문을 실행하고 결과를 Resultset에 저장한다.
		rs = pstmt.executeQuery();
		
		if(rs != null && rs.next()) {
			// ResultSet이 비어있지 않으면	
			vo = new OrderVO();
			vo.setOrdNo(rs.getLong("OrdNo")); // 주문번호
			vo.setMemberNo(rs.getLong("MemberNo")); // 회원번호
			vo.setNo(rs.getLong("no")); // 상품번호
			vo.setTitle(rs.getString("Title")); // 상품명
			vo.setOrderdate(rs.getString("OrderDate")); // 주문일
			vo.setAdress(rs.getString("Adress")); // 배송지
			vo.setQuantity(rs.getLong("Quantity")); // 수량
			vo.setTel(rs.getString("Tel")); // 전화번호
			vo.setName(rs.getString("name")); // 수령인
			vo.setStatus(rs.getString("Status")); // 배송상태
			vo.setTotalPrice(rs.getLong("TotalPrice")); // 총액
			vo.setRequest(rs.getString("request")); // 요청사항
			vo.setReason(rs.getString("reason")); // 환불사유
		}
		} catch (Exception e) {
			// 예외가 발생하면 스택 트레이스를 출력한다.
			//e.printStackTrace();
			System.out.println("정보를 불러올 수 없습니다.");
			// 예외를 던진다.
//			throw e;
		}finally {
			// DB 닫기
			DB.close(con, pstmt, rs);
		}
		// OrderVo 객체를 반환.
		return vo;
		
	}// end view
	
	public OrderVO view1(Long ordNo) throws Exception {
		OrderVO vo = new OrderVO(); // 새로운 OrderVO 갹채 생성
		
		try {
			// DB연동
		con = DB.getConnection();
		// View1 SQL문 불러오기
		pstmt = con.prepareStatement(VIEW1);
		pstmt.setLong(1, ordNo); // 첫 번째 인덱스에 주문번호를 설정한다.
		pstmt.setLong(2, Main.login.getMemberNo()); // 두 번째 인덱스에 로그인한 회원의 번호를 설정한다.
		
		// SQL 문을 실행하고 결과를 ResultSet에 저장한다.
		rs = pstmt.executeQuery();
		
		  // ResultSet이 비어있지 않으면
		if(rs != null && rs.next()) {
			 // ResultSet에서 데이터를 가져와 OrderVO 객체에 설정한다.
			vo.setOrdNo(rs.getLong("OrdNo"));
			vo.setMemberNo(rs.getLong("MemberNo"));
			vo.setNo(rs.getLong("no"));
			vo.setTitle(rs.getString("title"));
			vo.setOrderdate(rs.getString("OrderDate"));
			vo.setAdress(rs.getString("Adress"));
			vo.setQuantity(rs.getLong("Quantity"));
			vo.setTel(rs.getString("Tel"));
			vo.setName(rs.getString("name"));
			vo.setStatus(rs.getString("Status"));
			vo.setTotalPrice(rs.getLong("TotalPrice"));
			vo.setRequest(rs.getString("request"));
			vo.setReason(rs.getString("reason"));
		}
		} catch (Exception e) {
			 // 예외가 발생하면 스택 트레이스를 출력한다.
			//e.printStackTrace();
			System.out.println("정보를 불러올 수 없습니다.");
			throw e;
		}finally {
			// DB 닫기
			DB.close(con, pstmt, rs);
		}
		// 객체 반환
		return vo;
		
	}// end view1
	
	public int write(OrderVO vo) throws Exception {
		int result = 0; // 결과를 저장할 변수 초기화
		
		try {
			// DB연동
		con = DB.getConnection();
		// Write SQL문
		pstmt = con.prepareStatement(WRITE);
		pstmt.setLong(1, vo.getNo()); // 상품번호
		pstmt.setLong(2, Main.login.getMemberNo()); // 회원번호
		pstmt.setString(3,  Main.login.getName()); // 수령인
		pstmt.setString(4,  vo.getTel()); // 전화번호
		pstmt.setString(5,  vo.getAdress()); // 배송지
		pstmt.setLong(6,  vo.getPayment()); // 결제수단
		pstmt.setLong(7,  vo.getQuantity()); // 수량
		pstmt.setString(8,  vo.getRequest()); // 요청사항
		pstmt.setLong(9,  vo.getTotalPrice()); // 총액
		
		
		// SQL문 실행하고 결과를 정수형 변수에 저장.
		result = pstmt.executeUpdate();
		System.out.println("주문리스트에 추가하였습니다.");		// 성공 메세지 출력				
		
		} catch (Exception e) {
			// 예외 발생
			//e.printStackTrace();
			System.out.println("주문 실패");
			throw e;
		} finally {
			// DB 닫기
			DB.close(con, pstmt);
		} // end of try ~ catch ~ finally 
		return result;
	}// end write

	public int update(OrderVO vo) throws Exception {
		int result = 0; // 결과를 저장할 변수 초기화
		Long ordNo = null; // 주문번호를 저장할 변수 초기화
		
		try {
			//관리자인지 확인
			if(Main.login.getGradeNo()==0) {
				// DB 연동
		con = DB.getConnection();
		
		// UPDATE SQL
		pstmt = con.prepareStatement(UPDATE);
		pstmt.setString(1, vo.getAdress()); // 배송지
		pstmt.setString(2, vo.getName()); // 수령인
		pstmt.setString(3, vo.getTel()); // 전화번호
		pstmt.setString(4, vo.getStatus()); // 배송상태
		pstmt.setString(5, vo.getRequest()); // 요청사항
		pstmt.setString(6, vo.getReason()); // 환불 사유
		pstmt.setLong(7, vo.getOrdNo()); // 주문번호

		// SQL문 실행
		result = pstmt.executeUpdate();
		System.out.println("수정");				// 성공 메세지 출력		
			}else { // 일반회원인 경우
				// DB연동
				con = DB.getConnection();
				// UPDATE1 SQL
				pstmt = con.prepareStatement(UPDATE1);
				pstmt.setString(1, vo.getAdress()); // 배송지
				pstmt.setString(2, vo.getName()); // 수령인
				pstmt.setString(3, vo.getTel()); // 전화번호
				pstmt.setString(4, vo.getStatus()); // 배송상태
				pstmt.setString(5, vo.getRequest()); // 요청사항
				pstmt.setString(6, vo.getReason()); // 환불사유
				pstmt.setLong(7, vo.getOrdNo()); // 주문번호
				pstmt.setLong(8, Main.login.getMemberNo()); //로그인한 회원번호
				
				// SQL문 실행하고 결과를 정수형 변수에 저장한다.
				result = pstmt.executeUpdate();
				System.out.println("수정완료 !");		// 성공 메세지 출력			
			} // end of 일반회원
		
		} catch (Exception e) {
			// 예외처리
			//e.printStackTrace();
			System.out.println("수정실패 !");			
		} finally {
			// DB닫기
			DB.close(con, pstmt);
		} // end of try ~ catch ~ finally 
		// 결과 반환
		return result;
	}// end update
	
	
	final String LIST = " select o.ordNo, o.memberNo, g.title, o.Quantity, o.name, to_char(o.orderdate, 'yyyy-mm-dd') orderdate, o.status, o.TotalPrice "
			+ " from ordertable o, member m, goods g "
			+ "  where (o.memberNo = m.memberNo) and (o.no = g.no) order by ordno desc";
	final String LIST1 = " select o.ordNo, o.memberNo, g.title, o.Quantity, o.name, to_char(o.orderdate, 'yyyy-mm-dd') orderdate, o.status, o.TotalPrice "
			+ " from ordertable o, member m, goods g "
			+ "  where o.memberNo = ? and (o.memberNo = m.memberNo) and (o.no = g.no) order by ordno desc";
	final String VIEW = " select o.ordNo, o.memberNo, o.name, o.no, g.title, TO_CHAR(o.orderDate, 'yyyy-mm-dd') orderDate, o.adress, o.Quantity, o.tel, o.status, o.totalPrice, o.request, o.reason "
			+ " from ordertable o, goods g "
			+ " WHERE o.ordNo = ? and (o.no = g.no)";
	final String VIEW1 = " select o.ordNo, o.memberNo, o.name, o.no, g.title, TO_CHAR(o.orderDate, 'yyyy-mm-dd') orderDate, o.adress, o.Quantity, o.tel, o.status, o.totalPrice, o.request, o.reason "
			+ " from ordertable o, goods g, member m  "
			+ " WHERE o.ordNo = ? and o.memberNo = ? and (o.no = g.no) and (o.memberNo = m.memberNo) ";
	final String UPDATE = " update ordertable set adress = ?, name = ?, tel = ?, status = ?, request = ?, reason = ? "
			+ "where ordNo = ? ";
	final String UPDATE1 = " update ordertable set adress = ?, name = ?, tel = ?, status = ?, request = ?, reason = ? "
			+ "where ordNo = ? and memberNo = ? ";
	
	final String WRITE = " insert into ordertable(ordNo, no, memberNo, name, tel, adress, payment, quantity, request, TotalPrice) "
			+ " values(ordertable_seq.nextval,?,?,?,?,?,?,?,?,?) ";
//			final String List = 
} // end of class
