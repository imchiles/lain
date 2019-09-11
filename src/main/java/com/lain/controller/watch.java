package com.lain.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class watch {
	public static void main(String[] args) {
		int i = 34;
		String line = null;
		String all = "cmd /k ffplay.exe -i \"rtsp://admin:lain123456@192.168.1.65:554/h264/ch"+i+"/main/av_stream\"";
		StringBuilder sb = new StringBuilder();
		System.out.println(all);
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(all);
			BufferedReader bufferReader=new BufferedReader(new InputStreamReader(process.getInputStream()));
			while((line = bufferReader.readLine()) != null){
				sb.append(line+"/n");
				System.out.println(line);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
