package com.SedEx.member.service;

import java.util.List;

import com.SedEx.member.db.MemberDAO;
import com.SedEx.member.db.MemberVO;
import com.SedEx.main.service.Service;

public class MemberListService implements Service {

	@Override
	public List<MemberVO> service(Object obj) throws Exception {
		// DB member에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		// DB 처리는 DAO에서 처리 - MemberDAO.list()
		// MemberController - (Execute) - [MemberListService] - MemberDAO.list()
		return new MemberDAO().list();
	}

}
