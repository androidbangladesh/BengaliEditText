package androidbangladesh;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView.BufferType;
import bengali.language.support.BengaliUnicodeString;

import com.omicronlab.avro.PhoneticParser;
import com.omicronlab.avro.PhoneticXmlLoader;

public class BengaliEditText extends EditText{

	public BengaliEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		Typeface tf = Typeface.createFromAsset(context.getAssets(), "solaimanlipinormal.ttf");
		setTypeface(tf);
		avro = PhoneticParser.getInstance();    
		avro.setLoader(new PhoneticXmlLoader(context));
		tw = new TextWatcher() {
			public void afterTextChanged(Editable s){
				setSelection(text.length());
			}
			public void  beforeTextChanged(CharSequence s, int start, int count, int after){
				// you can check for enter key here

			}
			public void  onTextChanged (CharSequence s, int start, int before,int count) {

				if(before == 0){
					in_text += s.toString().charAt(start);
				}
				else{
					in_text = in_text.substring(0, in_text.length()-1);
				}
				if(!in_text.equals(null)){
				out_text = avro.parse(in_text);
				removeTextChangedListener(tw);
				
				setText(text = BengaliUnicodeString.getBengaliUTF(out_text));
				addTextChangedListener(tw);
				}
			} 
		};

		addTextChangedListener(tw);
	}





	String in_text = "";
	//String eng_token="";
	String out_text;
	PhoneticParser avro;
	TextWatcher tw;
	//TextView tv;
	String text;
	//String temp[];
	Context ctx;
	Typeface tf;
	
	


	public String getInputText(){
		return in_text;
	}
 

}
