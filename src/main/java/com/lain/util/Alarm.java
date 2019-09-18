package com.lain.util;

import java.util.List;

import com.lain.analysis.Analysis;
import com.lain.analysis.Switch;
import com.lain.dao.Ktr8060Mapper;
import com.lain.entity.Ktr8060;

public class Alarm {
	/**收发通讯的SOCKET类*/
	private static SocketUtil SOCKET = new SocketUtil();
	/**需要发送指令的实体类*/
	public static Ktr8060 KTR8060 = null;;
	public static Ktr8060Mapper ktr8060Mapper;
	
	private static byte[] OPEN = null;
	private static byte[] CLOSE = null;
	
	/**控制单个开关*/
	public static void SwitchOne (int diId, String ip, int port, int id) {
		/**找到需要开哪个8060开关*/
		KTR8060 = ktr8060Mapper.findKtrAddress(diId, id);
		/**打开通讯*/
		SOCKET.openSocket(ip, port);
		OPEN = Analysis.getKtr8060OpenOrder(KTR8060.getAddress(), KTR8060.getGallery());
		CLOSE = Analysis.getKtr8060CloseOrder(KTR8060.getAddress(), KTR8060.getGallery());
		Switch.soundSwitch(ip + ":" + port, SOCKET, OPEN, CLOSE);
		SOCKET.closeSocket(ip, port);
	}
	
	/**控制同一中类型全部开关*/
	public static void SwitchAllById(int kId){
		List<Ktr8060> list = ktr8060Mapper.findKtrAddressById(kId);
		for (Ktr8060 ktr8060 : list) {
			SOCKET.openSocket(ktr8060.getDiAddress(), ktr8060.getDiPort());
			OPEN = Analysis.getKtr8060OpenOrder(ktr8060.getAddress(), ktr8060.getGallery());
			CLOSE = Analysis.getKtr8060CloseOrder(ktr8060.getAddress(), ktr8060.getGallery());
			SOCKET.closeSocket(ktr8060.getDiAddress(), ktr8060.getDiPort());
		}
	}

	public static void setKtr8060Mapper(Ktr8060Mapper ktr8060Mapper) {
		Alarm.ktr8060Mapper = ktr8060Mapper;
	}
	
}
