package job;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import utils.SessionWS;


@Path("/{userId}/{userToken}/request")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JobRequestResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	Long userId;
	String userToken;
	
	public JobRequestResource(@PathParam("userId") Long userId, @PathParam("userToken") String userToken){
		this.userId = userId;
		this.userToken = userToken;
		if(!SessionWS.instance.isAuth(this.userId, this.userToken)){
			throw new RuntimeException();
		}
	}

	@GET
	@Path("oldest")
	public JobRequest getJob() {
		/** Getting job */
		JobRequest j = JobRequestDao.instance.getOldestFreeJob();
		if(j != null){
			/** Update last get */
			j.setDatetime(Calendar.getInstance().getTimeInMillis());
			j.setTaked(true);
		}
		return j;
	}
	
	@GET
	public Collection<JobRequest> getJobs() {
		return JobRequestDao.instance.getModel().values();
	}

	@GET
	@Path("count")
	public String getCount() {
		return String.valueOf(JobRequestDao.instance.getModel().size());
	}

	@POST
	public void newJob(JobRequest job){
		JobRequestDao.instance.getModel().put(job.getId(), job);
	}
	
	@POST
	@Path("collection")
	public void newJobs(List<JobRequest> jobs){
		for(JobRequest job : jobs){
			JobRequestDao.instance.getModel().put(job.getId(), job);
		}
	}

	@PUT
	public Response putJob(JobRequest job){
		Response res;
		if(JobRequestDao.instance.getModel().containsKey(job.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		JobRequestDao.instance.getModel().put(job.getId(), job);
		return res;
	}

	@DELETE
	@Path("/{id}")
	public void deleteTodo(@PathParam("id") String id) {
		JobRequestDao.instance.getModel().remove(Long.parseLong(id));
	}

} 