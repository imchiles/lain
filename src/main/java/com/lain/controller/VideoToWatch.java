package com.lain.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class VideoToWatch {
	@RequestMapping("/videowatch")
	@ResponseBody
	public String Watch(HttpServletRequest request){
		try {
			test5();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "true";
	}
	public static void test4(String i){
		String all = "cmd /k ffplay.exe -i \"rtsp://admin:lain123456@192.168.1.65:554/h264/ch"+i+"/main/av_stream\"";
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(all);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void test5() throws IOException{
		List<String> command = new ArrayList<String>();
		command.add("cmd");
		command.add("/k");
		command.add("ffplay.exe");
		command.add("-i");
		command.add("\"rtsp://admin:lain123456@192.168.1.65:554/h264/ch35/main/av_stream\"");
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = null;
		try {
			process = builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);

		String line = "";
		while ((line = br.readLine()) != null) {
		}
		if (br != null) {
			br.close();
		}
		if (inputStreamReader != null) {
			inputStreamReader.close();
		}
		if (errorStream != null) {
			errorStream.close();
		}
	}
}
class t extends Thread{
	public void run(){
		String all = "cmd /k ffplay.exe -i \"rtsp://admin:lain123456@192.168.1.65:554/h264/ch34/main/av_stream\"";
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(all);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}