package com.po;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

//关注点1：JAXB 根元素
@XmlRootElement(name = "device")
public class Device {
	private String deviceIp;
	private int deviceStatus;

	public Device() {
	}

	public Device(String deviceIp) {
		super();
		this.deviceIp = deviceIp;
	}

	// 关注点2：JAXB属性
	@XmlAttribute
	public String getIp() {
		return deviceIp;
	}

	public void setIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	@XmlAttribute
	public int getStatus() {
		return deviceStatus;
	}

	public void setStatus(int deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
}