package com.SedEx.goods.service;

import com.SedEx.goods.db.GoodsDAO;
import com.SedEx.main.service.Service;

public class GoodsDeleteService implements Service {

	@Override
	public Integer service(Object obj) throws Exception {
		// Main.main() - GoodsController.execute() - GoodsWriteService - GoodsDAO.write()
		// obj == vo : 데이터 저장소	
		return new GoodsDAO().delete((Long)obj);
	}

}
