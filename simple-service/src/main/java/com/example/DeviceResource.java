package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.dao.DeviceDao;
import com.po.Device;

@Path("device")
public class DeviceResource {
	// 关注点1：注入Dao实例
	private final DeviceDao deviceDao;

	public DeviceResource() {
		deviceDao = new DeviceDao();
	}

	// 关注点2：GET方法
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Device get(@QueryParam("ip") final String deviceIp) {
		Device result = null;
		if (deviceIp != null) {
			result = deviceDao.getDevice(deviceIp);
		}
		return result;
	}

	// 关注点3：PUT方法
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Device put(final Device device) {
		Device result = null;
		if (device != null) {
			result = deviceDao.updateDevice(device);
		}
		return result;
	}
}