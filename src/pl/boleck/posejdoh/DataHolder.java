package pl.boleck.posejdoh;

import java.util.Map;

public class DataHolder {
	static Map<String, Object> newPost;
	static String RODZAJ;
	static String PAKIET;
	public static void setNewPost(Map<String, Object> newPost) {
		DataHolder.newPost = newPost;
	}
	public static void setPakiet(String pAKIET) {
		PAKIET = pAKIET;
	}
	public static void setRodzaj(String rODZAJ) {
		RODZAJ = rODZAJ;
	}
	public static String getPAKIET() {
		return PAKIET;
	}
	public static String getRODZAJ() {
		return RODZAJ;
	}
}