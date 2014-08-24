package Gy.control;

import org.apache.log4j.Logger;

public class Global {
	static String hexString = "0123456789ABCDEF";
	static Logger log;
	public enum STATUS {
        starting,runing,finished,idle;
    }
	static{
		log = Logger.getLogger("--GYLogger--");
//		System.err.println("new logger");
	}
	public static void debug(String str){
//		System.err.println("debug() :"+log);
		log.debug(str);
	}
	public static void error(String str){
		log.error(str);
	}	
	public static void info(String str){
//		System.err.println("debug() :"+log);
		log.info(str);
	}		
	/**
	 * 
	 *  �������� : uniteBytes<p>
	 *  �������� :  ������ʮ�����Ƶ��ֽ����ϲ���һ���ֽڣ�'a','b'='0xab'��<p>
	 *  ����˵����
	 *  	@param src0
	 *  	@param src1
	 *  	@return<p>
	 *  ����ֵ��
	 *  	byte<p>
	 *  �޸ļ�¼��
	 *  ���ڣ�2012-3-15 ����11:27:22	�޸��ˣ������<p>
	 *  ����	��
	 *
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = (byte) Global.hexString.indexOf(Character.toUpperCase(src0));
		_b0 = (byte) (_b0 << 4);
		byte _b1 = (byte) Global.hexString.indexOf(Character.toUpperCase(src1));
		byte ret = (byte) (_b0 | _b1);
		return ret;
	}

	/**
	 * 
	 *  �������� : bytesToHexString<p>
	 *  �������� :  ���ֽ�����ת����ʮ�������ַ���(�磺��10,11,12,13,14,15��=="0a,0b,0d,0c,0e,0f")<p>
	 *  ����˵����
	 *  	@param src
	 *  	@return<p>
	 *  ����ֵ��
	 *  	String<p>
	 *  �޸ļ�¼��
	 *  ���ڣ�2012-3-15 ����11:14:12	�޸��ˣ������<p>
	 *  ����	��
	 *
	 */
	public static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[src.length() / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < src.length() / 2; ++i) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}
	/**
	 * ��ȡУ����: ����У����Ϊ�����Ϣ�󷵻���ȷУ�������Ϣ
	 * 
	 * ***/

	public static void main(String[] args) {
//		Global.debug("debug");
//		Global.debug("debug"); 
		Global.debug("debug");
		Global.info("info11");
		Global.error("info11");
		Global.debug("debug");
	}
}
