package com.SedEx.member.db;

import java.util.ArrayList;
import java.util.List;

import com.SedEx.util.db.DAO;
import com.SedEx.util.db.DB;
import com.SedEx.member.service.MemberStatusUpdateService;
import com.SedEx.member.db.MemberVO;
import com.SedEx.util.exe.Execute;
import com.SedEx.util.io.In;
import com.SedEx.util.io.MemberPrint;
import com.SedEx.main.controller.Main;
import com.SedEx.member.db.LoginVO;

public class MemberDAO extends DAO {

	// 리스트 처리
	public List<MemberVO> list() throws Exception {

		List<MemberVO> list = null;

		try {
			// 1. DB = DAO 확인
			// 2. 드라이버 확인
			con = DB.getConnection();
			// 3. sql
			// System.out.println("sql : " + LIST);
			// 4. 실행객체
			pstmt = con.prepareStatement(LIST);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 데이터 표시
			if (rs != null) {
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<MemberVO>();
					MemberVO membervo = new MemberVO();
					membervo.setId(rs.getString("id"));
					membervo.setName(rs.getString("name"));
					membervo.setGender(rs.getString("gender"));
					membervo.setBirth(rs.getString("birth"));
					membervo.setTel(rs.getString("tel"));
					membervo.setEmail(rs.getString("email"));
					membervo.setAddress(rs.getString("address"));
					membervo.setRegDate(rs.getString("regDate"));
					membervo.setConDate(rs.getString("conDate"));
					membervo.setStatus(rs.getString("status"));
					membervo.setMemberNo(rs.getInt("memberNo"));
					membervo.setGradeName(rs.getString("gradeName"));

					list.add(membervo);
				} // end of while
			} // end of if

		} catch (Exception e) {
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			else
				throw new Exception("에외 발생 : 리스트 처리 중 오류가 발생했습니다.");
		} finally {
			DB.close(con, pstmt, rs);
		}

		return list;
	} // end of list

	// 상세보기 처리
	public MemberVO view(Object obj) throws Exception {
		MemberVO vo = null;

		try {
			con = DB.getConnection();
			pstmt = con.prepareStatement(VIEW);
			pstmt.setString(1, (String) obj);

			rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPw(rs.getString("pw"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setAddress(rs.getString("address"));
				vo.setRegDate(rs.getString("regDate"));
				vo.setConDate(rs.getString("conDate"));
				vo.setStatus(rs.getString("status"));
				vo.setMemberNo(rs.getInt("memberNo"));
				vo.setPhoto(rs.getString("photo"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			else
				throw new Exception("에외 발생 : 회원 등록 처리 중 예외가 발생했습니다.");
		} finally {
			DB.close(con, pstmt, rs);
		}
		return vo;

	} // end of view

	// 로그인 처리
	public LoginVO login(LoginVO loginvo) throws Exception {
		LoginVO logvo = null;

		try {
			// 1. DB 확인 DAO
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. sql 문 - 아래 LIST
			// System.out.println("sql : " + LOGIN);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(LOGIN);
			pstmt.setString(1, loginvo.getId());
			pstmt.setString(2, loginvo.getPw());
			// 5. 실행 객체 실행
			rs = pstmt.executeQuery();
			// 6. 데이터 표시 또는 담기
			if (rs != null && rs.next()) {
				// rs -> VO
				logvo = new LoginVO();
				logvo.setId(rs.getString("id"));
				logvo.setName(rs.getString("name"));
				logvo.setGradeNo(rs.getInt("gradeNo"));
				logvo.setGradeName(rs.getString("gradeName"));
				logvo.setPhoto(rs.getString("photo"));
				logvo.setNewMsgCnt(rs.getInt("newMsgCnt"));
				logvo.setMemberNo(rs.getInt("memberNo"));

			} // end of if
			if (logvo == null) {
				throw new Exception("예외 발생 : ID나 PW가 맞지 않습니다. 다시 확인해 주세요.");
			} //
		} catch (Exception e) {
			// e.printStackTrace();
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			else
				throw new Exception("에외 발생 : 로그인 처리 중 예외가 발생했습니다.");
		} finally {
			DB.close(con, pstmt, rs);
		}
		return logvo;
	} // end of login

	// 로그인 - 최종 접속일 업데이트
	public int conUpdate(LoginVO vo) throws Exception {

		int result = 0;

		try {
			con = DB.getConnection();
//			System.out.println("sql : " + CONUPDATE);

			pstmt = con.prepareStatement(CONUPDATE);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());

			result = pstmt.executeUpdate();

			if (result == 0) {
				throw new Exception("예외 발생 : 로그인 정보가 없습니다.");
			} // end of if

		} catch (Exception e) {
			// e.printStackTrace();
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			else
				throw new Exception("예외 발생 : 최근 접속일 DB 처리 중 예외가 발생했습니다.");
		} finally {
			DB.close(con, pstmt);
		} // end of try~finally
		return result;

	} // end of conUpdate

	// 회원 등록 처리
	public int write(MemberVO memberVO) throws Exception {
		int result = 0;

		try {
			con = DB.getConnection();

//			System.out.println("sql : " + WRITE);

			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPw());
			pstmt.setString(3, memberVO.getName());
			pstmt.setString(4, memberVO.getGender());
			pstmt.setString(5, memberVO.getBirth());
			pstmt.setString(6, memberVO.getTel());
			pstmt.setString(7, memberVO.getEmail());
			pstmt.setString(8, memberVO.getAddress());

			result = pstmt.executeUpdate();

			System.out.println();
			System.out.println(" ▶ 회원가입이 완료되었습니다.");

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			throw new Exception("예외 발생 : 회원등록 DB 처리 중 예외가 발생했습니다.");
		} finally {
			DB.close(con, pstmt);
		}
		return result;
	} // end of write

	// 아이디 찾기
	public MemberVO findID(Object obj) throws Exception {

		MemberVO vo = (MemberVO) obj;
		try {
			con = DB.getConnection();

			pstmt = con.prepareStatement(FINDID);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());

			rs = pstmt.executeQuery();

//			if (rs.next()) {
//				vo = new MemberVO();
//				id = rs.getString("id");
//			} 
			if (rs.next() && rs != null) {
				vo.setId(rs.getString("id"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			throw new Exception("예외 발생 : 입력하신 이메일이 존재하지 않거나 비밀번호가 맞지 않습니다.");
		} finally {
			DB.close(con, pstmt, rs);
		}
		return vo;
	} // end of findID

	// 비밀번호 찾기 - 변경
	public int findPW(MemberVO vo) throws Exception {

		int result = 0;

		try {
			con = DB.getConnection();
			// System.out.println("sql : " + CONUPDATE);

			pstmt = con.prepareStatement(FINDPW);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getEmail());

			result = pstmt.executeUpdate();

			if (result == 0) {
				throw new Exception("예외 발생 : 아이디와 이메일이 존재하지 않습니다.");
			} // end of if

		} catch (Exception e) {
			// e.printStackTrace();
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			else
				throw new Exception("예외 발생 : 비밀번호 변경 중 오류가 발생했습니다.");
		} finally {
			DB.close(con, pstmt);
		} // end of try~finally
		return result;
	} // end of findPW

	// 관리자 - 회원 정보 수정
	public int adminUpdate(MemberVO vo) throws Exception {

		int result = 0;

		try {
			con = DB.getConnection();
			// System.out.println("sql : " + CONUPDATE);

			pstmt = con.prepareStatement(ADMINUPDATE);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getBirth());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getPhoto());
			pstmt.setString(7, vo.getStatus());
			pstmt.setString(8, (String) vo.getId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// e.printStackTrace();
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			else
				throw new Exception("예외 발생 : 회원정보 수정 중 오류가 발생했습니다.");
		} finally {
			DB.close(con, pstmt, rs);
		} // end of try~finally

		return result;
	} // end of adminUpdate

	// 관리자 - 회원 등급 변경
	public int authUpdate(MemberVO vo) throws Exception {

		int result = 0;

		try {
			con = DB.getConnection();
			// System.out.println("sql : " + CONUPDATE);

			pstmt = con.prepareStatement(GRADEUPDATE);
			pstmt.setInt(1, vo.getGradeNo());
			pstmt.setString(2, vo.getId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// e.printStackTrace();
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			else
				throw new Exception("예외 발생 : 회원 등급 수정 중 오류가 발생했습니다.");
		} finally {
			DB.close(con, pstmt, rs);
		} // end of try~finally

		return result;
	} // end of authUpdate

	// 관리자 - 회원 삭제
	public int delete(MemberVO vo) throws Exception {

		int result = 0;

		try {
			con = DB.getConnection();
			// System.out.println("sql : " + CONUPDATE);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, vo.getId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// e.printStackTrace();
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			else
				throw new Exception("예외 발생 : 회원 등급 수정 중 오류가 발생했습니다.");
		} finally {
			DB.close(con, pstmt, rs);
		} // end of try~finally

		return result;
	} // end of delete

	// 일반회원 - 회원 정보 수정
	public int userUpdate(MemberVO vo) throws Exception {

		int result = 0;

		try {
			con = DB.getConnection();
			// System.out.println("sql : " + CONUPDATE);

			pstmt = con.prepareStatement(USERUPDATE);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getBirth());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getPhoto());
			pstmt.setString(7, vo.getId());
//			pstmt.setString(8, vo.getPw());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// e.printStackTrace();
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			else
				throw new Exception("예외 발생 : 회원정보 수정 중 오류가 발생했습니다.");
		} finally {
			DB.close(con, pstmt, rs);
		} // end of try~finally

		return result;
	} // end of adminUpdate

	// 회원 탈퇴 처리
	public int statusUpdate(MemberVO vo) throws Exception {

		int result = 0;

		try {
			con = DB.getConnection();
			// System.out.println("sql : " + CONUPDATE);

			pstmt = con.prepareStatement(STATUSUPDATE);
			pstmt.setString(1, Main.login.getId());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPw());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			if (result == 0)
				throw e;
			else
				throw new Exception("예외 발생 : 회원정보 수정 중 오류가 발생했습니다.");
		} finally {
			DB.close(con, pstmt);
		} // end of try~finally

		return result;
	} // end of statusUpdate
	
	
	

	// 실행할 쿼리를 정의해 놓은 변수 선언
	final String LIST = "select m.id, m.name, m.gender, to_char(m.birth, 'yyyy-mm-dd') birth, m.tel, m.email, m.address, "
			+ " to_char(m.regDate, 'yyyy-mm-dd') regDate, to_char(m.conDate, 'yyyy-mm-dd') conDate, m.status, "
			+ " m.memberNo, m.gradeNo, g.gradeName " + "from member m, grade g where m.gradeNo = g.gradeNo";

	final String VIEW = "select m.id, m.pw, m.name, m.gender, to_char(m.birth, 'yyyy-mm-dd') birth, m.tel, m.email, m.address, "
			+ " to_char(m.regDate, 'yyyy-mm-dd') regDate, to_char(m.conDate, 'yyyy-mm-dd') conDate, m.status, m.memberNo, m.photo, m.gradeNo, g.gradeName "
			+ " from member m, grade g where (id = ?) and (m.gradeNo = g.gradeNo)";

	final String WRITE = "insert into member(id, pw, name, gender, birth, tel, email, address, memberNo) "
			+ " values (?, ?, ?, ?, ?, ?, ?, ?, memberNo_seq.nextval)";

	final String ADMINUPDATE = "update member set pw =?, name = ?, birth = ?, tel = ?, address = ?, photo = ?, status = ?"
			+ " where id = ?";

	final String USERUPDATE = "update member set pw =?, name = ?, birth = ?, tel = ?, address = ?, photo = ? "
			+ " where id = ? ";

	final String DELETE = "delete from member where id = ?";

	final String LOGIN = "select m.id, m.name, m.gradeNo, g.gradeName, m.photo, m.newMsgCnt, m.memberNo "
			+ " from member m, grade g " + " where (id = ? and pw = ? and status = '정상') and (g.gradeNo = m.gradeNo)";

	final String CONUPDATE = "update member set conDate = sysDate where id = ? and pw = ?";

	final String FINDID = "select id from member where name = ? and email = ?";

	final String FINDPW = "update member set pw = ? where id = ? and email = ?";

	final String GRADEUPDATE = "update member set gradeNo = ? where id = ? ";

	final String STATUSUPDATE = "update member set status = '탈퇴' " + " where id = ? and (email = ? and pw = ?)";

} // end of class
