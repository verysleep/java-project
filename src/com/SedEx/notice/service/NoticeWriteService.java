package com.SedEx.notice.service;

import com.SedEx.main.service.Service;
import com.SedEx.notice.db.NoticeDAO;
import com.SedEx.notice.db.NoticeVO;

public class NoticeWriteService implements Service {
	public Integer service(Object obj) throws Exception {
		return new NoticeDAO().write((NoticeVO)obj);
	}
}