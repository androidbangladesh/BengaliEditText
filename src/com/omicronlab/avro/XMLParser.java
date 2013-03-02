package com.omicronlab.avro;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.omicronlab.avro.phonetic.Data;
import com.omicronlab.avro.phonetic.Match;
import com.omicronlab.avro.phonetic.Pattern;
import com.omicronlab.avro.phonetic.Rule;

public class XMLParser extends DefaultHandler{

	Data data;
	Match match;
	Rule rule;
	Pattern pattern;

	boolean isVowel;
	boolean isConsonant;
	boolean isCasesensitive;
	boolean isPattern;
	boolean isFind;
	boolean isReplace;
	boolean isRuleReplace;
	boolean isRule;
	boolean isMatch;

	boolean testcase;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		//		Log.d("TEXT",new String(ch));
		//		if(ch[0] == '\n' || ch[0] == '\t'){
		//			falsify();
		//			return;
		//		}
		if(new String(ch,start,length).equals("OI")){
//			Log.d("TEXT",new String(ch));
			testcase = true;
		}
		if(isVowel){
			isVowel = false;
			data.setVowel(new String(ch,start,length));
			//			Log.d("TEXT",new String(ch,start,length));
		}
		if(isConsonant){
			isConsonant = false;
			data.setConsonant(new String(ch,start,length));
			//			Log.d("TEXT",new String(ch,start,length));
		}
		if(isCasesensitive){
			isCasesensitive = false;
			data.setCasesensitive(new String(ch,start,length));
			//			Log.d("TEXT",new String(ch,start,length));
		}
		if(isPattern){
			isPattern = false;
			data.addPattern(pattern = new Pattern());
			//	pattern = data.getPatterns().get(data.getPatterns().size()-1);
		}
		if(isFind){
//			if(!isRuleReplace){
				String s = new String(ch,start,length);
				if(!s.equals('\n'+""))
					pattern.setFind(s);
//					Log.d("","");
				isFind = false;
				
				//			Log.d("TEXT",new String(ch,start,length));
//			}
			//
		}
		if(isReplace){
			if(isRuleReplace){
				String s = new String(ch,start,length);
				if(!s.equals('\n'+""))
				rule.setReplace(new String(ch,start,length));
				else
					rule.setReplace((char)0x200b+"");
				//				Log.d("TEXT",new String(ch,start,length));
				isReplace = false;
				isRuleReplace = false;
			}else{
				String s = new String(ch,start,length);
				if(!s.equals('\n'+""))
					pattern.setReplace(new String(ch,start,length));
				else
					pattern.setReplace((char)0x200b+"");
				isReplace = false;
				//				Log.d("TEXT",new String(ch,start,length));
			}
			
		}
		if(isMatch){
//			if(testcase)
//				Log.d("","");
			isMatch = false;
			String ss = "";
			if(ch[0]!='\n' && ch[0]!='\t')
			match.setValue(ss = new String(ch,start,length));
//						Log.d("TEXT",ss.toString());
//						Log.d("TEXT",getString(ch));
		}
	}

	public XMLParser(){
		this.data = new Data();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(localName.equals("vowel")){
			isVowel = true;
		}
		if(localName.equals("consonant")){
			isConsonant = true;
		}
		if(localName.equals("casesensitive")){
			isCasesensitive = true;
		}
		if(localName.equals("pattern")){
			isPattern = true;
		}
		if(localName.equals("find")){
			isFind = true;
		}
		if(localName.equals("replace")){
			isReplace = true;
		}
		if(localName.equals("rule")){
			pattern.addRule(rule = new Rule());

			//rule = pattern.getRules().get(pattern.getRules().size()-1);

			isRuleReplace = true;
		}
		if(localName.equals("match")){
			isMatch = true;



			rule.addMatch(match = new Match());
			//			match = rule.getMatches().get(rule.getMatches().size()-1);

			//			Log.d("TEXT", attributes.getQName(0));
			String s;
			if(attributes.getQName(0).equals("type")){
				
				match.setType(s = attributes.getValue("type"));
				Log.d("","");
			}
			if(attributes.getQName(1).equals("scope")){
				match.setScope(s = attributes.getValue("scope"));
			}
		}


	}
	public void falsify(){
		isVowel = false;
		isConsonant = false;
		isCasesensitive = false;
		isPattern = false;
		isFind = false;
		isReplace = false;
		isRuleReplace = false;
		isRule = false;
		isMatch = false;
	}
	public Data getNewData(){
		return data;
	}
}
