package job;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import utils.Xdata;

@XmlRootElement
public class JobRequest extends Job{
	
	private boolean taked;
	private String service;
	private String script;
	private Xdata x;
	private List<String> y;
		
	public JobRequest(){
		super();
		this.taked = false;
	}
	
	public boolean isTaked() {
		return taked;
	}

	public void setTaked(boolean taked) {
		this.taked = taked;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Xdata getX() {
		return x;
	}

	public void setX(Xdata x) {
		this.x = x;
	}

	public List<String> getY() {
		return y;
	}

	public void setY(List<String> y) {
		this.y = y;
	}

}
