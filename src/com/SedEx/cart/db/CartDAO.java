package com.SedEx.cart.db;

import java.util.ArrayList;
import java.util.List;

import com.SedEx.main.controller.Main;
//import com.SedEx.goods.db.GoodsVO;
import com.SedEx.util.db.DAO;
import com.SedEx.util.db.DB;

public class CartDAO extends DAO{

	//결과를 저장할 변수를 초기화합니다
	int result = 0;
	
	//1.장바구니 리스트 처리
	//CartController -> (Execute로그 출력) -> CartListService -> [CartDAO.list()]
	//CartDAO 클래스를 정의하며 DAO 클래스를 상속받습니다
	public List<CartVO> list() throws Exception{
		
		//결과를 저장할 수 있는 변수 선언
		//결과를 저장할 리스트를 초기화합니다
		List<CartVO> list=new ArrayList<CartVO>();
		
		try {  //예외 처리를 시작합니다
			//1.드라이버 확인
			
			//2.연결
			//데이터벵스 연결울 설정합니다 
			con = DB.getConnection();
			
			//3. sql
			
			//4. 실행객체
			//SQL문을 준비합니다
			pstmt = con.prepareStatement(LIST);
			//로그인한 사회원 번호를 sql문에 설정
			pstmt.setLong(1, Main.login.getMemberNo());
			
			//5. 실행
			//SQL문을 실행
			rs = pstmt.executeQuery();
			
			//6.표시 또는 담기 
			//결과가 존재하는지 확인합니다.
			if(rs != null) {
				//결과를 순회하다
				while(rs.next()) {
					//CartVO객체를 생성합니다
					CartVO vo = new CartVO();
					vo.setCartCode(rs.getLong("CartCode"));  //장바구니 코드를 설정합니다
					vo.setNo(rs.getLong("no"));  //상품 번호를 설정합니다.
					vo.setQuantity(rs.getLong("quantity"));  //상품 수량을 설정합니다.
					vo.setMemberNo(rs.getLong("memberNo"));  //회원 번호를 설정합니다.
					vo.setTitle(rs.getString("title"));  //상품명을 설정합니다.
					vo.setPrice(rs.getLong("price"));  //상품 가격을 설정합니다.
					vo.setSum(vo.getPrice()*vo.getQuantity());  //총액을 설정합니다.

					list.add(vo);  //리스트에 CartVO 객체를 추가합니다.
				}//end of while
			}//end of if
		 //예외 처리를 시작합니다.
		}catch(Exception e) {
			e.printStackTrace(); //예외 스택 트레이스를 출력합니다.
			throw e;  //예외를 던집니다.	
		}finally {	//finally 블록을 시작합니다.
			//7. 닫기
			DB.close(con, pstmt, rs);  //데이터베이스 연결을 닫습니다.
		}//end of finally
		//결과값 리턴
		return list;  //결과 리스트를 반환합니다.
	}//end of Public List
	
	

	//2.상품의 수량
	//CartController -> (Execute로그 출력) -> CartUpdateService -> [CartDAO.update()]
	//상품의 수량을 업데이트하는 메서드를 정의합니다.
	public int update (CartVO vo)throws Exception{
			
        // 결과를 저장할 수 있는 변수 선언

		//예외 처리를 시작합니다.
		try {
			//1.드라이버 확인
				
			//2.연결
			//데이터베이스 연결을 설정합니다.
			con = DB.getConnection();
				
			//3. sql
				
			//4. 실행객체 
			pstmt = con.prepareStatement(UPDATE);  //SQL 문을 준비합니다.
			pstmt.setLong(1, vo.getQuantity());  //상품 수량을 설정합니다.
			pstmt.setLong(2, vo.getCartCode());  //장바구니 코드를 설정합니다.
				
			//5. 실행
			//SQL 문을 실행합니다.
			result = pstmt.executeUpdate();
				
			//6.표시 또는 담기
			//수량이 0이하일 경우 예외 발생시킵니다
			if (vo.getQuantity() <= 0) {
				   //예외 메시지를 출력합니다.
	               throw new Exception("1개 이상부터 구매하실 수 있습니다.");
	           }
				
			 //예외 처리를 시작합니다.
			}catch(Exception e) {
				e.printStackTrace();  //예외 스택 트레이스를 출력합니다.
				throw e;  //예외를 던집니다.
			}finally {	//finally 블록을 시작합니다.
				//7. 데이터베이스 닫기
				DB.close(con, pstmt);
			}//end of finally
			//결과값 리턴
			return result;
		}//public int update
	
		
		
	//3.상품삭제 : 선택삭제,전체삭제
	//CartController -> (Execute로그 출력) -> CartDeleteService -> [CartDAO.delete()]
		//상품을 삭제하는 메서드를 정의합니다.
		public int delete (long obj) throws Exception{
			
			//결과를 저장할 변수를 초기화합니다.
			int result = 0;
			
			try {
				//1. 드라이버 확인

				//2. 연결
				//데이터베이스 연결을 설정합니다.
				con = DB.getConnection();
				
				//3. sql
				//SQL 문을 저장할 변수를 선언합니다.
				String sql;
	            
				//전체삭제
				//전체 삭제인지 확인합니다.
				if (obj == -1) {
					//전체 삭제 SQL 문을 설정합니다.
					sql = DELETE_ALL;
	              //선택 삭제일 경우
				} else {
					//선택 삭제 SQL 문을 설정합니다.
	                sql = DELETE;
	            }
				
				//4. 실행객체
				//SQL 문을 준비합니다.
				pstmt = con.prepareStatement(sql);
				//선택 삭제일 경우
				if (obj != -1) {
					//장바구니 번호를 설정합니다.
					pstmt.setLong(1, obj);
				}
				
				//5. 실행
				//SQL 문을 실행합니다.
				result = pstmt.executeUpdate();
				
				//6. 결과 확인 및 메시지 출력
				//결과가 없을 경우 예외를 발생시킵니다.
				if (result == 0) {
	                throw new Exception("장바구니 번호가 맞지 않습니다."); //예외 메시지를 출력합니다.
	              //전체 삭제일 경우
				} else if (obj == -1) {
	                System.out.println("전체 상품이 삭제되었습니다."); //전체 삭제 메시지를 출력합니다.
	              //선택 삭제일 경우
				} else {
	                System.out.println(obj + "번 상품이 삭제되었습니다."); //선택 삭제 메시지를 출력합니다.
	            } 
			  //예외 처리를 시작합니다.
	        } catch (Exception e) { 
	            e.printStackTrace(); //예외 스택 트레이스를 출력합니다.
	            if (e.getMessage().indexOf("오류") >= 0) throw e; //예외 메시지에 "오류"가 포함되어 있을 경우 예외를 던집니다.
	            else throw new Exception("오류 : 장바구니의 상품 삭제 처리 중 오류가 발생했습니다."); //그렇지 않을 경우 일반 예외를 던집니다.
	        } finally { //finally 블록을 시작합니다.
	            DB.close(con, pstmt); //데이터베이스 연결을 닫습니다.
	        }
	        return result; //결과를 반환합니다.
		}// end of public int deletec
		
		
		
	//4.상품담기
	//CartController -> (Execute로그 출력) -> CartWriteService -> [CartDAO.write()]
		//상품을 장바구니에 추가하는 메서드를 정의합니다.
		public int write (CartVO vo) throws Exception{
			//결과를 저장할 변수를 초기화합니다.
			int result = 0;
			
			//예외 처리를 시작합니다.
			try {
				//1. 드라이버 확인
				
				//2. 연결
				//데이터베이스 연결을 설정합니다.
				con = DB.getConnection();
				
				//3. sql
				 
				
				//4. 실행객체
				//SQL 문을 준비합니다.
				pstmt = con.prepareStatement(WRITE);
				pstmt.setLong(1, vo.getNo()); // 상품 번호 설정
				pstmt.setLong(2, vo.getQuantity()); // 상품 수량 설정
				pstmt.setLong(3, Main.login.getMemberNo()); // 회원 번호 설정
				
				//5. 실행
				//SQL 문을 실행합니다.
				result = pstmt.executeUpdate();
				
				//6. 결과 확인 및 메시지 출력
				//실행 결과가 있을 경우
				if(result > 0) {
					//성공 메시지를 출력합니다.
					System.out.println("상품등록이 성공했습니다.");
				  //실행 결과가 없을 경우
				} else {
					//실패 메시지를 출력합니다.
					throw new Exception("상품등록에 실패했습니다.");	
				}
			 //예외 처리를 시작합니다.
			}catch(Exception e) {
				e.printStackTrace(); //예외 스택 트레이스를 출력합니다.
				if(e.getMessage().indexOf("오류") >= 0) throw e; //예외 메시지에 "오류"가 포함되어 있을 경우 예외를 던집니다.
				else throw new Exception("오류 : 상품담기 중 오류가 발생했습니다."); //그렇지 않을 경우 일반 예외를 던집니다.
			}finally {	//finally 블록을 시작합니다.
				//연결닫기
				DB.close(con, pstmt); //데이터베이스 연결을 닫습니다.
			}
			return result; //결과를 반환합니다.
		}// end of 상품담기public
		
	
	 

	
		
		//실행할 쿼리를 정의해 놓은 변수 선언
		
		//장바구니 리스트를 가져오는 SQL 문을 정의합니다.
		final String LIST = " select c.cartCode, g.no, c.quantity, c.memberNo, g.title, g.price, c.sum "  
				+ " from cart c, goods g "
				+ " where (memberNo = ?) and (c.no = g.no) "
				+ " order by c.cartCode desc";
		
		
		
		//상품 수량을 업데이트하는 SQL 문을 정의합니다.
		final String UPDATE = " UPDATE cart SET "
				+ "  quantity = ? WHERE cartCode = ?";
		
		
		 
		//상품을 삭제하는 SQL 문을 정의합니다.
		final String DELETE = " delete from cart "
				+ " where cartCode = ? ";
		
		
		
		//전체 상품을 삭제하는 SQL 문을 정의합니다.
		final String DELETE_ALL = "DELETE FROM cart";
		
		
		
		//상품을 장바구니에 추가하는 SQL 문을 정의합니다.
		final String WRITE = "insert into cart(cartCode, no, quantity, memberNo) "
				+ " values(cart_seq.nextval, ?, ?, ?)";


}//end of class
