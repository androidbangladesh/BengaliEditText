package bengali.language.support;

public class BengaliUnicodeString {

	
	public static String getBengaliUTF(String text){
		GSUB.text = text.toCharArray();
		GSUB.newlength = text.length();
//		int newlength=count;
		int ll = 0,ret=2;
//		__android_log_print(ANDROID_LOG_VERBOSE, "REORDER", "%s",reorder);
		while ((ret=GSUB.mygsub(GSUB.newlength))==2 &&
				ll < 10) {
		}
		text = new String(GSUB.text,0,GSUB.newlength);
		text = new String(Shape.reorder(text.toCharArray()));
		
		return text;
	}
}
