package com.SedEx.member.service;

import com.SedEx.member.db.MemberDAO;
import com.SedEx.member.db.MemberVO;
import com.SedEx.main.service.Service;

public class MemberFindPWService implements Service {

	@Override
	public Integer service(Object obj) throws Exception {
		
		// MemberController = (Execute) - [MemberLoginService] - MemberDAO.login()
		return new MemberDAO().findPW((MemberVO)obj);
	}

}
