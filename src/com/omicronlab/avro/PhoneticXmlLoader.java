/*
    =============================================================================
 *****************************************************************************
    The contents of this file are subject to the Mozilla Public License
    Version 1.1 (the "License"); you may not use this file except in
    compliance with the License. You may obtain a copy of the License at
    http://www.mozilla.org/MPL/

    Software distributed under the License is distributed on an "AS IS"
    basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
    License for the specific language governing rights and limitations
    under the License.

    The Original Code is JAvroPhonetic

    The Initial Developer of the Original Code is
    Rifat Nabi <to.rifat@gmail.com>

    Copyright (C) OmicronLab (http://www.omicronlab.com). All Rights Reserved.


    Contributor(s): ______________________________________.

 *****************************************************************************
    =============================================================================
 */

package com.omicronlab.avro;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


import android.content.Context;

import com.omicronlab.avro.phonetic.Data;

public class PhoneticXmlLoader implements PhoneticLoader {

//	private URL url = null;
	Context context;

	public PhoneticXmlLoader(Context context) {
		this.context = context;
		//    	String PACKAGE_NAME = context.getResources().getResourcePackageName(R.raw.phonetic);
		//    	String uriStr = "android.resource://" + PACKAGE_NAME + "/" + 
		//    			context.getResources().getResourceEntryName(R.raw.phonetic);
		//    	String res = Resources.getSystem().getString(R.raw.phonetic);    	
		//   
		//     	Log.d("TEXT",uriStr); 	
		//    	this.url = Data.class.getResource(res);
		try{
			//    		this.url = new URL("android.resource://amadeyr.bengali.wordprocessor/raw/phonetic");

			//    		this.url = new URL("file:///assets/phonetic.xml");
			//    		Log.d("TEXT",this.url.toString());
//			this.url = Main.class.getResource("/assets/phonetic.xml");
		}catch(Exception e){

		}


	}

	public PhoneticXmlLoader(String path) throws MalformedURLException {
//		this.url = new URL(path);
	}
	XMLReader xr;
	SAXParserFactory spf;
	SAXParser sp;
	XMLParser parser;
	public Data getData() throws IOException, SAXException {
		
		try{
		
			spf = SAXParserFactory.newInstance();
			sp = spf.newSAXParser();
			xr = sp.getXMLReader();
			parser= new XMLParser();
			xr.setContentHandler(parser);
//			xr.parse(new InputSource(context.getResources().openRawResource(R.raw.phonetic)));
			xr.parse(new InputSource(context.getAssets().open("phonetic.xml")));
		
		}catch(Exception e){

		}

		//		xr.parse(new InputSource(website.openStream()));
		return parser.getNewData();
//		return null;
	}
}
