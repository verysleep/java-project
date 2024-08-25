package com.SedEx.notice.service;

import java.util.List;

import com.SedEx.main.service.Service;
import com.SedEx.notice.db.NoticeDAO;
import com.SedEx.notice.db.NoticeVO;

public class NoticeViewService implements Service{
	@Override
	public NoticeVO service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return new NoticeDAO().view((long)obj);
	}
}