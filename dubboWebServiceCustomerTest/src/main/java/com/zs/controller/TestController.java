package com.zs.controller;

import com.zs.model.MsgInfo;
import com.zs.service.DubboService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Resource(name="dubboServiceImpl")
	private DubboService dubboServiceImpl;
	
	@ResponseBody
	@RequestMapping(value = "/json")
	Object json(HttpServletRequest request, Model view) {
        System.out.println("TestController -- json()");
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/testDubbo")
	Object testDubbo(HttpServletRequest request, Model view) {
        System.out.println("TestController -- testDubbo()");

		if (dubboServiceImpl != null) {
            MsgInfo msgInfoParam =new MsgInfo();        //  输入参数
            msgInfoParam.setId(1);
            msgInfoParam.setName("ruisheh");
            List<String> msgs=new ArrayList<String>();
            msgs.add("I");
            msgs.add("am");
            msgs.add("service");
            msgInfoParam.setMsgs(msgs);

            MsgInfo msgInfoResult = dubboServiceImpl.returnMsgInfo(msgInfoParam);    //输出参数
			return msgInfoResult;
		}
		return null;
	}
}