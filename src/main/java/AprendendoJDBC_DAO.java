import java.util.Scanner;

import dao.ConexaoJDBC;
import modelo.business.AlunosBusiness;
import modelo.entidades.Aluno;

public class AprendendoJDBC_DAO {

	
	
	
    public static void main(String[] args) {  
    	
    	
    	
    	ConexaoJDBC conexao;
        	    	
    	
    	// <<<<<<<<  Conecta DB >>>>>>>    	
    	conexao = new ConexaoJDBC();
		if (!conexao.conecta()) {
			System.out.println("Não foi possível conectar ao Banco de Dados !");			
			return;
		}		
		System.out.println("Conectado ao Banco de Dados !");
		
		// <<<<<<<<  Exibe dados no DB >>>>>>>		
		AlunosBusiness alunosBusiness = new AlunosBusiness(conexao); 
    	
    	System.out.println("");
    	System.out.println("");
    	System.out.println("*********Relatório de Alunos**********");
    	System.out.println(alunosBusiness.relatorioAlunos());
    	
    	
    	System.out.println("");
    	System.out.println("");
    	System.out.println("*********Disciplinas Ofertadas**********");
    	//System.out.println(minhaEscola.relatorioDisciplinas());
    	
    	    	
    	
    	// <<<<<<<<  Inclui dados no DB >>>>>>>
    	
    	Scanner teclado = new Scanner(System.in);
    	System.out.println("Tecle Enter para INCLUIR UM NOVO ALUNO....");    	
    	teclado.nextLine();
    	
    	Aluno aluno1 = new Aluno();
        aluno1.setNome("Aluno NOVISSIMO");
        aluno1.setIdade(19);
        aluno1.setCpf("000.001.002.41");
        aluno1.setMatricula("70256");        
        alunosBusiness.matricular(aluno1);
        
        
        System.out.println("Tecle Enter para VER A LISTA COM O NOVO ALUNO....");    	
    	teclado.nextLine();
    	
    	System.out.println(alunosBusiness.relatorioAlunos());
    	    	    	
    	   	    	
    	System.out.println("*****Obrigado por usar o SysEscola******");
    	System.out.println("");    	
    	System.out.println("");
    	System.out.println("       *****  **  ***   ***");
    	System.out.println("       **     **  ** * * **");
    	System.out.println("       ****   **  **  *  ** ");
    	System.out.println("       **     **  **     **");
    	System.out.println("       **     **  **     ** ");    
    	
    }
    	
	
}
