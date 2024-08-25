package com.SedEx.goods.service;

import java.util.List;

import com.SedEx.goods.db.GoodsDAO;
import com.SedEx.goods.db.GoodsVO;
import com.SedEx.main.service.Service;

public class GoodsListService implements Service {

	@Override
	public List<GoodsVO> service(Object obj) throws Exception {
		// Main.main() - GoodsController.execute() - GoodsListService - GoodsDAO.list()
		return new GoodsDAO().list();
	}

}
