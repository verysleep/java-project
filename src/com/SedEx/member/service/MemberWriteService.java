package com.SedEx.member.service;

import com.SedEx.member.db.MemberDAO;
import com.SedEx.member.db.MemberVO;
import com.SedEx.main.service.Service;

public class MemberWriteService implements Service {

	@Override
	public Integer service(Object obj) throws Exception {
		
		
		return new MemberDAO().write((MemberVO)obj);

	}
}
