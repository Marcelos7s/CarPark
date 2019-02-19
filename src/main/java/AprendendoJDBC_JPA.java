import java.util.List;
import java.util.Scanner;

import dao.ConexaoJDBC;
import modelo.business.AlunosBusiness;
import modelo.entidades.Aluno;
import x.exemplo.jpa.Disciplina;
import x.exemplo.jpa.DisciplinaDAO;

public class AprendendoJDBC_JPA {

	
	
	
    public static void main(String[] args) {  
    	
    	Scanner teclado = new Scanner(System.in);
    	    	
    	
    	// obj de acesso a Dados (Hibernate)
    	DisciplinaDAO dao = new DisciplinaDAO();
    	
    	
    	System.out.println("Tecle Enter para VER A LISTA DE DISCIPLINAS....");    	
    	teclado.nextLine();    	
    	// consulta disciplinas
    	List<Disciplina> lista = dao.getTodas();    	    	
    	System.out.println(relatorioDisciplinas(lista));
   	   	
		
    	
    	System.out.println("Tecle Enter para INCLUIR UMA NOVA DISCIPLINA....");    	
    	teclado.nextLine();


    	// cria novo usuario
    	Disciplina disc = new Disciplina();
    	disc.setTitulo("DISCIPLINA NOVA");
    	disc.setAreaAcademica("Humanas");
    	dao.inserir(disc);

        
        System.out.println("Tecle Enter para VER A LISTA COM A NOVA DISCIPLINA....");    	
    	teclado.nextLine();
    	// consulta disciplinas
    	List<Disciplina> lista2 = dao.getTodas();
    	System.out.println(relatorioDisciplinas(lista2));
    			
    	    	    	
    	   	    	
    	System.out.println("*****Obrigado por usar o SysEscola******");
    	System.out.println("");    	
    	System.out.println("");
    	System.out.println("       *****  **  ***   ***");
    	System.out.println("       **     **  ** * * **");
    	System.out.println("       ****   **  **  *  ** ");
    	System.out.println("       **     **  **     **");
    	System.out.println("       **     **  **     ** ");    
    	
    }
    
    
    public static String relatorioDisciplinas(List<Disciplina> list) {
    	    	   
    	   String rel = "-----------Relatório de Disciplinas-----------\n";
    	   
    	   for(Disciplina disc : list){             	
               rel += "\n" + disc.getId() + " | " + disc.getTitulo() + " | " + disc.getAreaAcademica() +"\n";
           }
    	   
    	   return rel;
           
    	
    }
    	
	
}
