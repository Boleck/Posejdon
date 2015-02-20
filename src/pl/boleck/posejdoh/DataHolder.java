package pl.boleck.posejdoh;

import java.util.Map;

public class DataHolder {
	static Map<String, Object> newPost;
	public static void setNewPost(Map<String, Object> newPost) {
		DataHolder.newPost = newPost;
	}
}