package x.exemplo.jpa;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


/* se o nome da tabela fosse exatamente o nome da classe (Disciplina),
  o uso de Anottation seria opcional 
  obs: o mesmo vale para @Column*/

@Entity (name = "TB_DISCIPLINA")  
public class Disciplina {  
		      
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DISCIPLINA")   // os parametros são valido apenas para SGDBs que possuem sequence
	  @SequenceGenerator(name = "SEQ_DISCIPLINA", sequenceName = "SEQ_DISCIPLINA", allocationSize = 1) // valido apenas para Database que possuem sequence
      @Column(name="id", nullable=false)  
	  // @Column(name="id", nullable=false, unique=true) // oracle tem problemas com id = unique=true
      private int id;
      
      private String titulo;

      @Column(name="area", nullable=false, unique=false)
      private String areaAcademica;

      
      
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAreaAcademica() {
		return areaAcademica;
	}

	public void setAreaAcademica(String areaAcademica) {
		this.areaAcademica = areaAcademica;
	}
      
      
      
      
}