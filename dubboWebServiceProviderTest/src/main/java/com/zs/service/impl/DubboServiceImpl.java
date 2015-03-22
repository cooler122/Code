package com.zs.service.impl;

import com.zs.model.MsgInfo;
import com.zs.service.DubboService;
import org.springframework.stereotype.Component;

@Component(value = "dubboServiceImpl")
public class DubboServiceImpl implements DubboService {
	
	public void sayHello() {
		System.out.println("hello world!");
	}

	public String returnHello() {
		return "hello world!";
	}

	public MsgInfo returnMsgInfo(MsgInfo info) {
        System.out.println("DubboServiceImpl -- returnMsgInfo()");
		info.getMsgs().add("处理完毕");
		return info;
	}
}