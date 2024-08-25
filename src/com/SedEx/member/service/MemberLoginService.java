package com.SedEx.member.service;

import com.SedEx.member.db.MemberDAO;
import com.SedEx.member.db.LoginVO;
import com.SedEx.main.service.Service;

public class MemberLoginService implements Service {

	@Override
	public LoginVO service(Object obj) throws Exception {
		
		MemberDAO dao = new MemberDAO();
		// 로그인을 먼저 실행
		LoginVO result = dao.login((LoginVO)obj);
		dao.conUpdate((LoginVO) obj);
		
		
		
		
		// DB 처리는 DAO에서 처리 - MemberDAO.login() : obj == LoginVO
		// MemberController - (Execute) - [MemberListService] - MemberDAO.login()
		return dao.login((LoginVO) obj);
	}

}