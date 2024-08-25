package com.SedEx.notice.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SedEx.util.db.DAO;
import com.SedEx.util.db.DB;

public class NoticeDAO extends DAO {
		//각 기능의 sql 저장: private final
		//일반 게시판의 각 기능 구현 public
		private final String LIST="select no, title, to_char(startdate, 'yy-mm-dd') startdate,"
				+ " to_char(enddate, 'yy-mm-dd')enddate, to_char(updatedate, 'yy-mm-dd')updatedate"
				+ " from notice order by no desc";
		private final String VIEW="select no, title, content, to_char(startdate, 'yy-mm-dd') startdate,"
				+ " to_char(enddate, 'yy-mm-dd')enddate, to_char(updatedate, 'yy-mm-dd')updatedate"
				+ " from notice where no=?";	
		private final String WRITE="insert into notice(no, title, content, startdate, enddate)"
				+ " values(Notice_seq.nextval, ?, ?, ?, ?)";
		private final String UPDATE="update notice set title=?, content=?, startdate=?, "
				+ " enddate=?, updatedate=sysdate where no=?";
		private final String DELETE="delete from notice where no=?";
		
		public List<NoticeVO> list() throws Exception{
			List<NoticeVO> list=new ArrayList<NoticeVO>();
			
			//DAO: 객체 선언
			//DB: db 접근 정보, getConnection(), close()
			
			try {
				//드라이버 연결부터
				con=DB.getConnection();
				pstmt=con.prepareStatement(LIST);
				rs=pstmt.executeQuery();
				if(rs!=null) {
					while(rs.next()) {
						NoticeVO vo=new NoticeVO();
						vo.setNo(rs.getLong("no"));
						vo.setTitle(rs.getString("title"));
						vo.setStartDate(rs.getString("startdate"));
						vo.setEndDate(rs.getString("enddate"));
						vo.setUpdateDate(rs.getString("updatedate"));
						
						list.add(vo);
					}
				}//end of if
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("오류내용: "+e.getClass().getSimpleName());
			}finally {
				DB.close(con, pstmt, rs);
			}
			
			return list;
		}
	
		//2. 상세보기 -> 찐 상세보기
		public NoticeVO view(long no) throws Exception{
			NoticeVO vo=new NoticeVO();
			
			//DAO: 객체 선언
			//DB: db 접근 정보, getConnection(), close()
			
			try {
				//드라이버 연결부터
				con=DB.getConnection();
				pstmt=con.prepareStatement(VIEW);
				pstmt.setLong(1, no);
				rs=pstmt.executeQuery();
				if(rs!=null&&rs.next()) {
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setStartDate(rs.getString("startdate"));
					vo.setEndDate(rs.getString("enddate"));
					vo.setUpdateDate(rs.getString("updatedate"));
				}//end of if
			}catch(Exception e) {
				e.printStackTrace();
				//특별한 예외: 단순 전달
				throw e;
				//처리 중 발생한 오류는 사용자가 원인 파악할 수 있도록 전달
			}finally {
				DB.close(con, pstmt);
			}
			
			return vo;
		}//end of view()

		//3. 등록
		public int write(NoticeVO vo) throws Exception{
			int result=0;
			
			try {
				//드라이버 연결부터
				con=DB.getConnection();
				pstmt=con.prepareStatement(WRITE);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getStartDate());
				pstmt.setString(4, vo.getEndDate());
				
				result=pstmt.executeUpdate();
				System.out.println("글 등록이 완료되었습니다.");
				
				return result;
			}catch(Exception e) {
				e.printStackTrace();
				//특별한 예외: 단순 전달
				throw e;
				//처리 중 발생한 오류는 사용자가 원인 파악할 수 있도록 전달
			}finally {
				DB.close(con, pstmt);
			}
		}

		//4. 수정
		public int update(NoticeVO vo) throws Exception{
			int result=0;
			
			try {
				//드라이버 연결부터
				con=DB.getConnection();
				pstmt=con.prepareStatement(UPDATE);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, vo.getStartDate());
				pstmt.setString(4, vo.getEndDate());
				pstmt.setLong(5, vo.getNo());
				
				result=pstmt.executeUpdate();
				if(result==0) System.out.println("존재하지 않는 글이거나 잘못된 비밀번호입니다.");
				
				return result;
			}catch(Exception e) {
				e.printStackTrace();
				//특별한 예외: 단순 전달
				throw e;
				//처리 중 발생한 오류는 사용자가 원인 파악할 수 있도록 전달
			}finally {
				DB.close(con, pstmt);
			}
		}
		
		//5. 삭제
		public int delete(NoticeVO vo) throws Exception{
			int result=0;
			
			//DAO: 객체 선언
			//DB: db 접근 정보, getConnection(), close()
			try {
				//드라이버 연결부터
				con=DB.getConnection();
				pstmt=con.prepareStatement(DELETE);
				pstmt.setLong(1, vo.getNo());
				//update: executeUpdate
				result=pstmt.executeUpdate();
				if(result==0) {//글번호 존재하지 않을 경우
					throw new Exception("예외 발생: 존재하지 않는 글이거나 잘못된 비밀번호입니다.");
				}
			}catch(Exception e) {
				e.printStackTrace();
				//특별한 예외: 단순 전달
				if(e.getMessage().indexOf("예외 발생")>=0) throw e;
				//처리 중 발생한 오류는 사용자가 원인 파악할 수 있도록 전달
				else throw new Exception("예외 발생: 게시판 DB 처리 중 예외 발생");
			}finally {
				DB.close(con, pstmt);
			}
			
			return result;
		}//end of delete
}