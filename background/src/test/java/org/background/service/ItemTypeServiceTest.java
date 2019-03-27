package org.background.service;

import org.business.product.ItemTypeService;
import org.domain.product.ItemType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages={"org.dao.hibernate","org.business"})
@EntityScan(basePackages={"org.domain"})
@EnableTransactionManagement
public class ItemTypeServiceTest {
	
	@Autowired
	private ItemTypeService itemTypeService;
	
	@Test
	public void testSave(){
		ItemType itemType = new ItemType();
		itemType.setItemTypeCd("200000");
		itemType.setItemTypeNm("逾期利息");
		itemType.setBiller(1);
		itemType.setCharger(2);
		itemType.init();
		itemType.setFeeType("200");
		itemType.setNode(4);
		itemType.setRateReferened(6);
		itemType.setCalOnlineFlag(1);
		itemTypeService.saveOrUpdate(itemType);
	
	}
}
