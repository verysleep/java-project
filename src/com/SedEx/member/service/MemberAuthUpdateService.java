package com.SedEx.member.service;

import com.SedEx.member.db.MemberDAO;
import com.SedEx.member.db.MemberVO;
import com.SedEx.main.service.Service;

public class MemberAuthUpdateService implements Service {

	@Override
	public Integer service(Object obj) throws Exception {
		
		
		
		return new MemberDAO().authUpdate((MemberVO) obj);

	}
}
