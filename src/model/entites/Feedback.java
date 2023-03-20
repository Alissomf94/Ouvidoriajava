package model.entites;



public class Feedback {
	
	private  String feedback;
	private String tipo;
	
	//construtor que inicia os atributos
	public Feedback(String feedback,String tipo) {
		this.feedback = feedback;
		this.tipo = tipo;	
	}
	//metodo que retorna o valor do atributo feedbacks
	public String getFeedback() {
		return feedback;
	}

	//atribui valor ao atributo feedback
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	//metodo que retorna o valor do atributo tipo
	public String getTipo() {
		return tipo;
	}

	//atribui valor ao atributo tipo
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
