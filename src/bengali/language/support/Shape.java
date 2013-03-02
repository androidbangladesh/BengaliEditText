package bengali.language.support;

import java.util.ArrayList;


public class Shape {

	static char  temp;
	static ArrayList<Character>split_matra;// = new ArrayList<Character>();
	public static char[] reorder(char text[]){

		for(int i=0;i<text.length;i++){


			if(text[i] == 0x09c7 || text[i] == 0x09c8 || text[i] == 0x09bf){
				//				if(i>0&&text[i]>=0x0995&&text[i]<=0x09b9){
				text = swap(text,i);
				if(text[i] == 0xf54a){
					text = swap(text,i-1);
//					Log.d("","");
				}
				//				}
				//				else{
				//					text = swap(text,i);
				//					text = swap(text,i-1);
				//				}
			}
			if(text[i] == 0x09cb || text[i] == 0x09cc){
				text = split(text, i);
				if(i-1>0&&text[i] != 0xf54a)
					text = swap(text,i-1);
			}
			
//			if(text[i] == 0xf54d)
//				if(i+1<text.length)
//				text = swap(text,i+1);

		}
		return text;
	}
	public static char[] split(char text[],int i){
		split_matra = new ArrayList<Character>();
		for(int j =0;j<text.length;j++){
			split_matra.add(text[j]);
		}
		if(split_matra.get(i) == (char)0x09cb){
			if(i-2>=0){
				split_matra.add(i-2, (char)0x09c7);
				split_matra.remove(i+1);
				split_matra.add(i+1, (char)0x09be);
			}
			else{
				split_matra.add(0, (char)0x09c7);
				split_matra.remove(i+1);
				split_matra.add(i+1, (char)0x09be);
			}

		}//o-kar
		if(split_matra.get(i) == (char)0x09cc){
			if(i-2>=0){
				split_matra.add(i-2, (char)0x09c7);
				split_matra.remove(i+1);
				split_matra.add(i+1, (char)0x09d7);
			}
			else{
				split_matra.add(0, (char)0x09c7);
				split_matra.remove(i+1);
				split_matra.add(i+1, (char)0x09d7);
			}

		}

		text = new char [split_matra.size()];
		for(int j =0;j<text.length;j++){
			//			split_matra.add(text[i]);
			text[j] = split_matra.get(j);
		}
		return text;
	}
	public static char[] swap(char text[],int i){
		temp = text[i];
		text[i] = text[i-1];
		text[i-1] = temp;
		return text;
	}
}
