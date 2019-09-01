package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyWebServices {
	
	
	@RequestMapping(value = "/webapi/coupon", method = RequestMethod.GET)
	public List<MyMessage> getCoupons(){
		//return "{ \"name\" : \"itay\" }";
		MyMessage msg1 = new MyMessage();
		msg1.setId(1);
		msg1.setValue("Hello from java spring!");
		List<MyMessage> messages = new ArrayList<>();
		messages.add(msg1);
		MyMessage msg2 = new MyMessage();
		msg2.setId(2);
		msg2.setValue("Hello AGAIN from java spring!");
		messages.add(msg2);
		return messages;
		
	}

	@RequestMapping(value = "/webapi/coupon", method = RequestMethod.POST)
	public String updateCoupons(){
		return "Coupon was Added!";
	}
	
	@RequestMapping(value = "/webapi/coupon", method = RequestMethod.PUT)
	public String addCoupon(){
		return "Coupon was updated!";
	}
	
	@RequestMapping(value = "/webapi/coupon", method = RequestMethod.DELETE)
	public String deleteCoupon(){
		return "Coupon was deleted!";
	}	
	

}
