package com.SedEx.member.service;

import com.SedEx.member.db.MemberDAO;
import com.SedEx.member.db.MemberVO;
import com.SedEx.main.service.Service;

public class MemberConUpdateService implements Service {

	@Override
	public MemberVO service(Object obj) throws Exception {
		
		// MemberController = (Execute) - [MemberLoginService] - MemberDAO.login()
		return new MemberDAO().view((String)obj);
	}

}
