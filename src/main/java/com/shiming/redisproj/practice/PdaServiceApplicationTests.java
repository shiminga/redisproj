package com.shiming.redisproj.practice;

import com.fs.pdaservice.repository.pojo.InStockPojo;
import com.fs.pdaservice.repository.pojo.JsonResult;
import com.fs.pdaservice.service.StartShipmentService;
import com.fs.pdaservice.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdaServiceApplicationTests {

	@Autowired
	StartShipmentService  service;
	@Test
	public void contextLoads() {
		InStockPojo stockPojo=new InStockPojo();
		stockPojo.setShowMessageBox(1);
		stockPojo.setOrderNo("SH200600035");
		service.inStock(stockPojo,"8fa0d551-eabc-4b1b-b3bb-ad2531050be3\n");
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void dsfe(){

    }


	@Test
	public void reviewTest() {

//		Map map=JsonUtils.jsonToMap("{\"CartonNo\":\"24664162\",\"CargoName\":\"YKH-G D6070\",\"type\":1,\"cbModel\":\"HISILICON\"}");

		  Map map=JsonUtils.jsonToMap("{\"CartonNo\":\"\",\"CargoName\":\"\",\"type\":1,\"cbModel\":\"HISILICON\"}");

		try {
			JsonResult jsonResult=service.BoxNoModelReview(map,"");

			System.out.println(jsonResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
