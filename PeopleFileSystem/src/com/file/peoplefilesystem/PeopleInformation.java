package com.file.peoplefilesystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeopleInformation {
	//List<String[]> info = new ArrayList<String[]>();
	String name;
	String desc;
	public PeopleInformation(String name, String desc){
	    this.name = name;
	    this.desc = desc;
	}
	HashMap<Integer, String[]> item = new HashMap<Integer, String[]>();
	public void addInfo(){
		String[] info = new String[2];
		info[0] = name;
		info[1] = desc;
		item.put(1, info);
	}
}
