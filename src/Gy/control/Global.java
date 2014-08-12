package Gy.control;

public class Global {
	static String hexString = "0123456789ABCDEF";
	public enum STATUS {
        starting,runing,finished,idle;
    }
	/**
	 * 
	 *  函数名称 : uniteBytes<p>
	 *  功能描述 :  将两个十六进制的字节数合并成一个字节（'a','b'='0xab'）<p>
	 *  参数说明：
	 *  	@param src0
	 *  	@param src1
	 *  	@return<p>
	 *  返回值：
	 *  	byte<p>
	 *  修改记录：
	 *  日期：2012-3-15 上午11:27:22	修改人：黄镇军<p>
	 *  描述	：
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
	 *  函数名称 : bytesToHexString<p>
	 *  功能描述 :  讲字节数组转换成十六进制字符串(如：“10,11,12,13,14,15”=="0a,0b,0d,0c,0e,0f")<p>
	 *  参数说明：
	 *  	@param src
	 *  	@return<p>
	 *  返回值：
	 *  	String<p>
	 *  修改记录：
	 *  日期：2012-3-15 上午11:14:12	修改人：黄镇军<p>
	 *  描述	：
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
}
