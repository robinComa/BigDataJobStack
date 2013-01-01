package job;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public enum JobResponseDao {
	instance;
	
	private Map<Long, ArrayDeque<JobResponse>> contentProvider;
	
	private JobResponseDao(){
		this.contentProvider = new HashMap<Long, ArrayDeque<JobResponse>>();
	}
	
	public Map<Long, ArrayDeque<JobResponse>> getModel(){
		return contentProvider;
	}

}
