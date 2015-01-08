package com.kang.batch2.processor;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.kang.batch2.model.Goods;

public class XmlItemProcessor  implements ItemProcessor<Goods, Goods> {

	public Goods process(Goods goods) throws Exception {
		 // 购入日期变更
        goods.setBuyDay(new Date());
        // 顾客信息变更
        goods.setCustomer(goods.getCustomer() + "顾客!");
        // ISIN变更
        goods.setIsin(goods.getIsin() + "IsIn");
        // 价格变更
        goods.setPrice(goods.getPrice() + 1000.112);
        // 数量变更
        goods.setQuantity(goods.getQuantity() + 100);
		
		return goods;
	}

}
