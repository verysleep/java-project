package com.SedEx.member.service;

import com.SedEx.member.db.MemberDAO;
import com.SedEx.member.db.MemberVO;
import com.SedEx.main.service.Service;

public class MemberUserUpdateService implements Service {

	@Override
	public Integer service(Object obj) throws Exception {
		
		// DB member에서 조회수 1증가 하고 글보기 데이터 가져와서 리턴
		
		
		return new MemberDAO().userUpdate((MemberVO) obj);

	}
}
