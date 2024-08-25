package com.SedEx.goods.service;

import com.SedEx.goods.db.GoodsDAO;
import com.SedEx.goods.db.GoodsVO;
import com.SedEx.main.service.Service;

public class GoodsUpdateService implements Service {

	@Override
	public Integer service(Object obj) throws Exception {
		// Main.main() - GoodsController.execute() - GoodsUpdateService - GoodsDAO.update()
		// obj == updateVO : 수정한 데이터를 저장하는 변수	
		return new GoodsDAO().update((GoodsVO)obj);
	}

}
