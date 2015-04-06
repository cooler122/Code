package com.zs.controller;

import com.zs.service._DubboService;
import com.zs.vo.MsgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/dubbo")
public class DubboController {

    @Autowired
	private _DubboService dubboService;
	
	@ResponseBody
	@RequestMapping(value = "/json")
    public ModelMap json(ModelMap map) {
        System.out.println("TestController -- json()");
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/testDubbo")
	public String testDubbo(ModelMap map) {
        System.out.println("TestController -- testDubbo()");

		if (dubboService != null) {
            MsgInfo msgInfoParam = new MsgInfo();        //  输入参数
            msgInfoParam.setId(1);
            msgInfoParam.setName("name");
            List<String> msgs = new ArrayList<String>();
            msgs.add("I want com.zs.service -> ");
            msgInfoParam.setMsgs(msgs);

            MsgInfo msgInfoResult = dubboService.returnMsgInfo(msgInfoParam);    //输出参数
            List<String> msgList = msgInfoResult.getMsgs();
            for(String msg : msgList){
                System.out.println(msg);
            }
            map.addAttribute("msg", msgInfoResult);
			return "pages/dubboPage";
		}
		return "pages/error";
	}
}