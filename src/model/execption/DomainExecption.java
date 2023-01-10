package model.execption;

public class DomainExecption extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public DomainExecption (String msg) {
		super(msg);
		
	}
}
