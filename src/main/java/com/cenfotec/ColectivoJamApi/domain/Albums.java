package com.cenfotec.ColectivoJamApi.domain;

import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.google.cloud.Timestamp;

@Data
@EqualsAndHashCode
public class Albums {
	private String id;
	private ArrayList<String> tracks;
	private ArrayList<String> genres;
	private String cover;
	private String name;
	private Timestamp dateCreated;
	
	public Albums() {}
}