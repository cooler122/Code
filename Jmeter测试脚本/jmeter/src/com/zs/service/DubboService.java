package com.zs.service;

import com.zs.model.MsgInfo;

public interface DubboService {
	
    public void sayHello();
    
    public String returnHello();
    
    public MsgInfo returnMsgInfo(MsgInfo info);
    
}