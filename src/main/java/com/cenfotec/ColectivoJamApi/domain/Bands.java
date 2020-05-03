package com.cenfotec.ColectivoJamApi.domain;

import java.util.ArrayList;
import java.util.HashMap;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.google.cloud.Timestamp;

@Data
@EqualsAndHashCode
public class Bands {
	private String id;
	private ArrayList<Albums> albums;
	private String email;
	private ArrayList<String> genres; 
	private String image;
	private String intro;
	private HashMap<String, String> links;
	private String name;
	private Timestamp dateCreated;
	
	public Bands() {}

}
