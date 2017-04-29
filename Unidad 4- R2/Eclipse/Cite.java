package mx.edu.utng.ws;

public class Cite {
	private  int id;
	private String usuario;
	private String room;
	private String recurrenceParent;
	private String subject;
	private String inicio;
	private String finall;
	private String recurrenceRule;
	private String annotations;
	private String descripcion;
	private String reminder;
	
	
	
	
	public Cite(int id, String usuario, String room, String recurrenceParent, String subject, String inicio,
			String finall, String recurrenceRule, String annotations, String descripcion, String reminder) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.room = room;
		this.recurrenceParent = recurrenceParent;
		this.subject = subject;
		this.inicio = inicio;
		this.finall = finall;
		this.recurrenceRule = recurrenceRule;
		this.annotations = annotations;
		this.descripcion = descripcion;
		this.reminder = reminder;
	}


	public Cite() {
		this(0,"","","","","","","","","","");
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public String getRecurrenceParent() {
		return recurrenceParent;
	}


	public void setRecurrenceParent(String recurrenceParent) {
		this.recurrenceParent = recurrenceParent;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getInicio() {
		return inicio;
	}


	public void setInicio(String inicio) {
		this.inicio = inicio;
	}


	public String getFinall() {
		return finall;
	}


	public void setFinall(String finall) {
		this.finall = finall;
	}


	public String getRecurrenceRule() {
		return recurrenceRule;
	}


	public void setRecurrenceRule(String recurrenceRule) {
		this.recurrenceRule = recurrenceRule;
	}


	public String getAnnotations() {
		return annotations;
	}


	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getReminder() {
		return reminder;
	}


	public void setReminder(String reminder) {
		this.reminder = reminder;
	}


	@Override
	public String toString() {
		return "Cite [id=" + id + ", usuario=" + usuario + ", room=" + room + ", recurrenceParent=" + recurrenceParent
				+ ", subject=" + subject + ", inicio=" + inicio + ", finall=" + finall + ", recurrenceRule="
				+ recurrenceRule + ", annotations=" + annotations + ", descripcion=" + descripcion + ", reminder="
				+ reminder + "]";
	}



}
