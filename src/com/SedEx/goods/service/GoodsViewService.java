package com.SedEx.goods.service;

import com.SedEx.goods.db.GoodsDAO;
import com.SedEx.goods.db.GoodsVO;
import com.SedEx.main.service.Service;

public class GoodsViewService implements Service {

	@Override
	public GoodsVO service(Object obj) throws Exception {
		// Main.main() - GoodsController.execute() - GoodsViewService - GoodsDAO.view()
		// obj == no : 상품코드
		return new GoodsDAO().view((Long) obj);
	}

}
