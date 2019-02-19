package modelo.business;

import java.util.ArrayList;
import java.util.List;

import dao.AlunoDao;
import dao.ConexaoJDBC;
import modelo.entidades.Aluno;


public class AlunosBusiness {

	private ConexaoJDBC con;
	
	private List<Aluno> listAlunos = new ArrayList();
	
	
	public AlunosBusiness(ConexaoJDBC con) {
		super();
		
		this.con = con;
		
        System.out.println("Aguarde, carregando alunos matriculados a partir do BD....");
		
    	AlunoDao alunoDao = new AlunoDao(this.con);
		this.listAlunos = alunoDao.recupera();
		
	}


	// matricula de um novo aluno 
    public boolean matricular(Aluno a) {
    	
    	AlunoDao alunoDao = new AlunoDao(this.con);
    	
    	if (alunoDao.insert(a)) {    		
            listAlunos.add(a);    // adiciona aluno na lista
            return true;
    	}
    	
    	return false;
    }
	
		
	
	// saida de um aluno da escola 
	public void removeAluno(Aluno a) {
			
		AlunoDao alunoDao = new AlunoDao(this.con);
    	
	  	if (alunoDao.deleta(a))   
		   this.listAlunos.remove(a);
	}


	/* retorna a lista completa de Alunos
	 matriculados */
	public List<Aluno> getListAlunos() {
		return listAlunos;
	}
	
	
	  
    //Retorna para o main uma String com os dados dos alunos cadastrados na list alunos
    public String relatorioAlunos() {
    	
        String rel = "-----------Relatório de Alunos-----------\n";
        
        for(Aluno a : listAlunos){ 
        	
            rel += "\n" + a.montaCadastro()+"\n";
        }
        
        
        /*
        for(int i=0; i<listaAlunos.size(); i++){       
            rel += "\n"+listaAlunos.get(i).montaCadastro()+"\n";
        }
        */
        
        return rel; 
    }
	


		


	
	
			
	
	

}
