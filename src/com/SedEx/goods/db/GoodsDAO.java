package com.SedEx.goods.db;

import java.util.ArrayList;
import java.util.List;

import com.SedEx.util.db.DAO;
import com.SedEx.util.db.DB;

public class GoodsDAO extends DAO{

	// 처리할 DB 쿼리
	// 1. 상품 리스트 보기
	final String LIST = "select no, catagory, title, image, price, status "
			+ " from goods "
			+ " order by no desc";
	
	// 2. 상품 상세 보기
	final String VIEW = "select no, catagory, title, image, price, content, delivery_cost, "
			+ "to_char(writeDate, 'yyyy-mm-dd') writeDate, status "
			+ " from goods "
			+ " where no = ?";
	
	// 3. 상품 등록
	final String WRITE = "insert into goods(no, catagory, title, content, price, image) "
			+ " values(goods_seq.nextval, ?, ?, ?, ?, ?)";
	
	// 4. 상품 수정
	final String UPDATE = "update goods "
			+ " set content = ?, price = ?, image = ?, delivery_cost = ? "
			+ " where no = ?";
	
	// 5. 상품 삭제
	final String DELETE = "update goods "
			+ " set status = '품절' "
			+ " where no = ?";
	
	// DB 쿼리를 처리하는 메서드
	// 1. 상품 리스트 보기
	public List<GoodsVO> list() throws Exception {
		
		List<GoodsVO> list = null;
		
		try {
			
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			
			// 3. sql Query
			// 4. 실행 객체 생성
			pstmt = con.prepareStatement(LIST);
			
			// 5. 실행
			rs = pstmt.executeQuery();
			
			// 6. 데이터 담기 & 출력
			// 담을 데이터 : no, catagory, title, image, price
			if (rs != null) {
				
				while (rs.next()) {
					
					if (list == null) {
						list = new ArrayList<GoodsVO>();
					} // end of if-2
					
					GoodsVO vo = new GoodsVO();
					vo.setNo(rs.getLong("no")); // 상품코드
					vo.setCatagory(rs.getString("catagory")); // 카테고리
					vo.setTitle(rs.getString("title")); // 상품명
					vo.setImage(rs.getString("image")); // 이미지
					vo.setPrice(rs.getLong("price")); // 가격
					vo.setStatus(rs.getString("status")); // 상태
					list.add(vo);
					
				} // end of while
				
			} // end of if-1
			
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			
			try {
				
				// 7. 개체 닫기
				DB.close(con, pstmt, rs);
				
			} catch (Exception e2) {
//				e2.printStackTrace();
			} // end of try-catch
			
		} // end of try-catch-finally
		
		return list;
		
	} // end of list()
	
	// 2. 상품 상세 보기
	public GoodsVO view(Long no) throws Exception {
		
		GoodsVO vo = null;
		
		try {
			
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			
			// 3. sql Query
			// 4. 실행 객체 생성
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, no);
			
			// 5. 실행
			rs = pstmt.executeQuery();
			
			// 6. 데이터 담기 & 출력
			// 담을 데이터 : no, catagory, title, image, price, content, delivery_cost, writeDate
			if (rs != null && rs.next()) {
				
					vo = new GoodsVO();
					vo.setNo(rs.getLong("no")); // 상품 코드
					vo.setCatagory(rs.getString("catagory")); // 카테고리
					vo.setTitle(rs.getString("title")); // 상품명
					vo.setImage(rs.getString("image")); // 이미지
					vo.setPrice(rs.getLong("price")); // 가격
					vo.setContent(rs.getString("content")); // 상품 설명
					vo.setDelivery_cost(rs.getLong("delivery_cost")); // 배송비
					vo.setWriteDate(rs.getString("writeDate")); // 등록일
					vo.setStatus(rs.getString("status")); // 상태
					
			} // end of if-1
			
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			
			try {
				
				// 7. 개체 닫기
				DB.close(con, pstmt, rs);
				
			} catch (Exception e2) {
//				e2.printStackTrace();
			} // end of try-catch
			
		} // end of try-catch-finally
		
		return vo;
		
	} // end of view()
	
	// 3. 상품 등록
	public int write(GoodsVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			
			// 3. sql Query
			// 4. 실행 객체 생성
			// catagory, title, content, price, image
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getCatagory());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setLong(4, vo.getPrice());
			pstmt.setString(5, vo.getImage());
			
			// 5. 실행
			result = pstmt.executeUpdate();
			
			// 6. 데이터 담기 & 출력
			if (result == 0) {
				System.out.println(" -- 상품 등록을 실패하였습니다. 다시 시도해 주세요.");
			} else {
				System.out.println(" -- 상품 등록이 완료되었습니다.");
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			
			try {
				
				// 7. 개체 닫기
				DB.close(con, pstmt);
				
			} catch (Exception e2) {
//				e2.printStackTrace();
			} // end of try-catch
			
		} // end of try-catch-finally
		
		return result;
		
	} // end of write()
	
	// 4. 상품 수정
	public int update(GoodsVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			
			// 3. sql Query
			// 4. 실행 객체 생성
			// content, price, image, delivery_cost
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getContent());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setString(3, vo.getImage());
			pstmt.setLong(4, vo.getDelivery_cost());
			pstmt.setLong(5, vo.getNo());
			
			// 5. 실행
			result = pstmt.executeUpdate();
			
			// 6. 데이터 담기 & 출력
			if (result == 0) {
				throw new Exception(" ** 상품 수정을 실패하였습니다. 다시 시도해 주세요. ** ");
			} else {
				System.out.println(" ** 수정이 완료되었습니다. ** ");	
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			
			try {
				// 7. 개체 닫기
				DB.close(con, pstmt);
				
			} catch (Exception e2) {
//				e2.printStackTrace();
			} // end of try-catch
			
		} // end of try-catch-finally
		
		return result;
		
	} // end of update()
	
	// 5. 상품 삭제 >> 상태 > 품절
	public int delete(Long no) throws Exception {
		
		int result = 0;
		
		try {
			
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			
			// 3. sql Query
			// 4. 실행 객체 생성
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, no);
			
			// 5. 실행
			result = pstmt.executeUpdate();
			
			// 6. 데이터 담기 & 출력
			// 담을 데이터 : no, catagory, title, image, price, content, delivery_cost, writeDate
			if (result == 0) { // 실패
				System.out.println(" ** 상품 삭제를 실패하였습니다. 다시 시도해 주세요. ** ");
			} else { // 성공
				System.out.println(" ** 삭제가 완료되었습니다. ** ");
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			
			try {
				
				// 7. 개체 닫기
				DB.close(con, pstmt);
				
			} catch (Exception e2) {
//				e2.printStackTrace();
			} // end of try-catch
			
		} // end of try-catch-finally
		
		return result;
		
	} // end of delete()
	
} // end of class
