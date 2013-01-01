package utils;

import java.util.HashMap;
import java.util.Map;

public enum SessionWS {
	instance;

	private Map<Long, String> contentProvider;
	
	private SessionWS(){
		this.contentProvider = new HashMap<Long, String>();
		this.contentProvider.put(1234567890L, "robinToken1234567890");
	}
	
	public boolean isAuth(Long uid, String userToken){
		if(!this.contentProvider.containsKey(uid)){
			return false;
		}
		return this.contentProvider.get(uid).equals(userToken);
	}
	public boolean isAuth(String userId, String userToken){
		Long uid = Long.parseLong(userId);
		return this.isAuth(uid, userToken);
	}

	public Map<Long, String> getModel(){
		return contentProvider;
	}
	
}
