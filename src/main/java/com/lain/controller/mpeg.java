package com.lain.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class mpeg {
	public static void main(String[] args) throws InterruptedException {
		test4("35");
	}
	public static void test(){
		String command = "ipconfig -all";
		String ip = "IPv4";
		String line = null;
		StringBuilder sb = new StringBuilder();
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run.exec("cmd.exe /k"+command);
			BufferedReader bufferReader=new BufferedReader(new InputStreamReader(process.getInputStream()));
			while((line = bufferReader.readLine()) != null){
				sb.append(line+"/n");
				if(line.contains(ip)){
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void test2(){
		String all = "cmd /k ffmpeg -re -rtsp_transport tcp -i \"rtsp://admin:lain123456@192.168.1.65:554/h264/ch35/main/av_stream\" -f flv -vcodec libx264 -vprofile baseline -acodec aac -ar 44100 -strict -2 -ac 1 -f flv -s 1280x720 -q 10 \"rtmp://localhost:1935/live/test2\"";
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(all);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void test3(){
		String stop = "taskkill /im ffmpeg.exe /f ";
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(stop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void test4(String i){
		String all = "cmd /c ffplay.exe -i \"rtsp://admin:lain123456@192.168.1.65:554/h264/ch"+i+"/main/av_stream\"";
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(all);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
class liu implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		String all = "cmd /k ffmpeg -re -rtsp_transport tcp -i \"rtsp://admin:lain123456@192.168.1.65:554/h264/ch35/main/av_stream\" -f flv -vcodec libx264 -vprofile baseline -acodec aac -ar 44100 -strict -2 -ac 1 -f flv -s 1280x720 -q 10 \"rtmp://localhost:1935/live/test2\"";
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(all);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
