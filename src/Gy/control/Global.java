package Gy.control;

public class Global {
	static String hexString = "0123456789ABCDEF";
	public enum STATUS {
        starting,runing,finished,idle;
    }
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = (byte) Global.hexString.indexOf(Character.toUpperCase(src0));
		_b0 = (byte) (_b0 << 4);
		byte _b1 = (byte) Global.hexString.indexOf(Character.toUpperCase(src1));
		byte ret = (byte) (_b0 | _b1);
		return ret;
	}

	public static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[src.length() / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < src.length() / 2; ++i) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}
}
