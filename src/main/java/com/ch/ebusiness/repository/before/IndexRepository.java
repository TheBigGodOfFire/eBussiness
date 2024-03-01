package com.ch.ebusiness.repository.before;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ch.ebusiness.entity.Goods;
import com.ch.ebusiness.entity.GoodsType;

@Repository
public interface IndexRepository {
	public List<Goods> selectAdvertisementGoods();
	public List<GoodsType> selectGoodsType();
	public List<Goods> selectRecommendGoods(Integer tid);
	public List<Goods> selectLastedGoods(Integer tid);
	public Goods selectAGoods(Integer id);
	public List<Goods> search(String mykey);
}
