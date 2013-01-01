package job;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public enum  JobRequestDao {
	instance;

	private Map<Long, JobRequest> contentProvider;
	private static final long RELOAD_DELAY = 1000 * 60;

	private JobRequestDao(){
		this.contentProvider = new HashMap<Long, JobRequest>();
	}

	public Map<Long, JobRequest> getModel(){
		return contentProvider;
	}

	public JobRequest getOldestFreeJob(){
		Calendar c = Calendar.getInstance();
		for (JobRequest j : this.contentProvider.values()){
			if(!j.isTaked()){
				return j;
			}else if(c.getTimeInMillis() - j.getDatetime() > RELOAD_DELAY){
				System.out.println("job " + j.getId() +" reloading");
				return j;
			}
		}
		return null;
	}
}
