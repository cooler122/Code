package com.zs.service;

import com.zs.vo.MsgInfo;

public interface _DubboService {
	
    public void sayHello();
    
    public String returnHello();
    
    public MsgInfo returnMsgInfo(MsgInfo info);
    
}