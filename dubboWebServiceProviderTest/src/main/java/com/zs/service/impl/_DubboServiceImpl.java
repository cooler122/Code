package com.zs.service.impl;

import com.zs.vo.MsgInfo;
import com.zs.service._DubboService;
import org.springframework.stereotype.Service;

@Service(value = "_dubboServiceImpl")
public class _DubboServiceImpl implements _DubboService {
	
	public void sayHello() {
		System.out.println("hello world!");
	}

	public String returnHello() {
		return "hello world!";
	}

	public MsgInfo returnMsgInfo(MsgInfo info) {
        System.out.println("_DubboServiceImpl -- returnMsgInfo()");
        info.getMsgs().add("dubboServiceImpl");
		info.getMsgs().add("处理完毕");
		return info;
	}
}