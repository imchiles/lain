package com.lain.master;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class ElectricmeterTypeConvert {
	/**
	 * charתbyte
	 * 
	 * @param chars����
	 * @return byte����
	 */
	public static byte[] charArrayToByteArray(char[] chars) {
		Charset cs = Charset.forName("ISO-8859-1");
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);

		return bb.array();
	}

	/**
	 * byteתchar
	 * 
	 * @param bytes����
	 * @return char����
	 */
	public static char[] ByteArrayToCharArray(byte[] bytes) {
		Charset cs = Charset.forName("ISO-8859-1");
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);

		return cb.array();
	}

	/**
	 * byteתString
	 * 
	 * @param byte����
	 * @return String
	 */
	public static String byteArrayToString(byte[] b) {
		int len = b.length;
		int[] x = new int[len];
		String[] y = new String[len];
		StringBuilder str = new StringBuilder();
		// ת����Int����,Ȼ��ת����String����
		for (int j = 0; j < len; j++) {
			x[j] = b[j] & 0xff;
			y[j] = Integer.toHexString(x[j]);
			while (y[j].length() < 2) {
				y[j] = "0" + y[j];
			}
			str.append(y[j]);
		}
		// //�������"0"��ͷ��������"0"
		// while(str.indexOf("0")==0){
		// str = str.delete(0, 1);
		// }
		return new String(str).toUpperCase();// toUpperCase()���� ת���ɴ�д
	}

	/**
	 * ���ַ���ȥ�ո��ת����byte���顣��"37   5a"ת��[0x37][0x5A]
	 * 
	 * @param String
	 * @return byte����
	 */
	public static byte[] stringToByteArray(String s) {
		String ss = s.replace(" ", "");
		int string_len = ss.length();
		int len = string_len / 2;
		if (string_len % 2 == 1) {
			ss = "0" + ss;
			string_len++;
			len++;
		}
		byte[] a = new byte[len];
		for (int i = 0; i < len; i++) {
			a[i] = (byte) Integer.parseInt(ss.substring(2 * i, 2 * i + 2), 16);
		}
		return a;
	}

	/**
	 * ASCII��תʮ������
	 * 
	 * @param hex
	 * @return
	 */
	public static String convertHexToString(String hex) {

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < hex.length() - 1; i += 2) {
			String output = hex.substring(i, (i + 2));
			int decimal = Integer.parseInt(output, 16);
			sb.append((char) decimal);
			temp.append(decimal);
		}
		return sb.toString();
	}

	/**
	 * ʮ������תASCII��
	 * 
	 * @param str
	 * @return
	 */
	public static String convertStringToHex(String str) {

		char[] chars = str.toCharArray();
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString();
	}
}
