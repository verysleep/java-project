package com.SedEx.notice.service;

import java.util.List;

import com.SedEx.main.service.Service;
import com.SedEx.notice.db.NoticeDAO;
import com.SedEx.notice.db.NoticeVO;


public class NoticeListService implements Service{
	@Override
	public List<NoticeVO> service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return new NoticeDAO().list();
	}
}