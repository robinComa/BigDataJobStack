package job;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import utils.SessionWS;


@Path("/{userId}/{userToken}/response")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JobResponseResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	Long userId;
	String userToken;
	
	public JobResponseResource(@PathParam("userId") Long userId, @PathParam("userToken") String userToken){
		this.userId = userId;
		this.userToken = userToken;
		if(!SessionWS.instance.isAuth(this.userId, this.userToken)){
			throw new RuntimeException();
		}
	}

	@GET
	public Deque<JobResponse> getJob() {
		//TODO remove
//		List<JobResponse> jobs = new ArrayList<JobResponse>();
//		for(int i=0;JobResponseDao.instance.getModel().get(this.userId) != null && !JobResponseDao.instance.getModel().get(this.userId).isEmpty() && i<30;i++){
//			jobs.add(JobResponseDao.instance.getModel().get(this.userId).pop());
//		}
//		return jobs;
		
		ArrayDeque<JobResponse> jobs = JobResponseDao.instance.getModel().get(this.userId);
		JobResponseDao.instance.getModel().put(this.userId, new ArrayDeque<JobResponse>());
		//Remove all get jobs
		Iterator<JobResponse> it = jobs.iterator();
		while(it.hasNext()){
			JobRequestDao.instance.getModel().remove(it.next().getId());
		}
		return jobs;
	}

	@PUT
	@Path("/{id}")
	public JobResponse newJob(JobResponse job){
		if(JobResponseDao.instance.getModel().containsKey(this.userId)){
			JobResponseDao.instance.getModel().get(this.userId).add(job);
		}else{
			JobResponseDao.instance.getModel().put(this.userId, new ArrayDeque<JobResponse>());
			JobResponseDao.instance.getModel().get(this.userId).add(job);
		}
		return job;
	}

	@DELETE
	public void deleteJob(@PathParam("job") String id) {
		JobResponseDao.instance.getModel().get(1234567890L).remove(Long.parseLong(id));
	}

} 