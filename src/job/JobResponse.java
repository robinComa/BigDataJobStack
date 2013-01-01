package job;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import utils.Yresponse;

@XmlRootElement
public class JobResponse extends Job{
	
	private String computedby;
	private String x;
	private List<Yresponse> y;

	public JobResponse(){
		super();
	}

	public String getComputedby() {
		return computedby;
	}

	public void setComputedby(String computedby) {
		this.computedby = computedby;
	}
	
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public List<Yresponse> getY() {
		return y;
	}

	public void setY(List<Yresponse> y) {
		this.y = y;
	}
	
}
