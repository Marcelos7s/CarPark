
// Importação do Pacote java.sql para acesso a bancos de dados relacionais
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AprendendoJDBC {
	
	public static void main(String[] args) {
					
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< CONEXÃO COM O BANCO DE DADOS  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
				
		/* As classes Connection, Statement, PreparedStatement e ResultSet
		 * fazem parte do pacote java.sql e servem para acessar
		 * a persistencia de dados (banco de dados)
		 */						
						
	    // Dados necessários para CONECTAR AO BANCO DE DADOS
	    String driver = "jdbc:oracle:thin:";
		int port = 1521;
		String servidor = "localhost";
	    String banco = "xe";
	    String usuario = "system";
        String senha = "123";

	    String urlConexao = driver + "@//" + servidor +  ":" + port + "/" + banco;
	    
    		
        // <<<<<< CONEXÃO COM O BD >>>>>>
     	Connection conexao = null;             
		try {        
	        conexao = DriverManager.getConnection(urlConexao,usuario, senha);
	        conexao.setAutoCommit(true);
	        
	        System.out.println(urlConexao);
            System.out.println("Conexão Realizada com sucesso !"); 
	        
	    }		
        catch(SQLException e ) {
        	//e.printStackTrace();
        	System.out.println("Erro de Conexão:" + e.getErrorCode() + " " + e.getMessage().toString());
        	return;
        }	  
		// <<<<<< FIM DA CONEXÃO  COM O BD >>>>>>> 

		
		
		/* Banco mysql
	    String driver = "jdbc:mysql:";
	    String urlConexao = driver + "//" + servidor +  "/" + banco;
	    String usuario = "root";
	    */
		
		/*                
           jdbc:oracle:thin:@(description=(address=(host=HOSTNAME)(protocol=tcp)(port=PORT))(connect_data=(service_name=SERVICENAME)(server=SHARED)));              
           jdbc:oracle:thin:@(description=(address=(host=localhost)(protocol=tcp)(port=1521))(connect_data=(service_name=XE)(server=SHARED)));        
        */
		
		
		
		
		// <<<<<< INSTRUÇÕES DML  >>>>>>> 

		
		

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< execução de código SQL >>>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
        
		
		// executa um comando DML (update ou insert)
		try {	
			// instrução SQL a ser enviada ao BD
			Statement statement = conexao.createStatement();
			
			statement.execute("insert into tb_aluno (nome,cpf,matricula,idade) values ('João da Silva','00100003004','abdefg',18)");
						
		} catch (SQLException e) {
			System.out.println("Erro de insert:" + e.getMessage().toString());
		}
				
		
		
		// executa um comando DML (update ou insert)
		/* Mais otimizado (rapida) para execuções repetidas 
		 * e prática com métodos SET 
		 */
		try {
			String sql = "";		
			sql = "insert into tb_aluno (nome,cpf,matricula,idade,sexo)";
			sql += " values (?,?,?,?,?)";					
			PreparedStatement prepStatement = conexao.prepareStatement(sql);	
			
			prepStatement.setString(1, "Zezinho");
			prepStatement.setString(2, "1267");
			prepStatement.setString(3, "aau");
			prepStatement.setInt(4, 15);
			prepStatement.setString(5, "M");
			
			prepStatement.execute(); 
			
		} catch (SQLException e) {
			System.out.println("Erro de insert Prepared:" + e.getMessage().toString());
		}
			
		
		
		
		// <<<<<< INSTRUÇÕES SELECT  >>>>>>> 
				
		
		
		System.out.println("");			
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< execução de CONSULTA SQL (com retorno de dados)  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>") ;

		
		System.out.println("Consultando disciplinas...");		
		
		try {						
			Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			/* a classe ResultSet respresenta uma matriz 
               contendo as colunas e linhas resultantes 
               da consulta select */
			ResultSet dbResultado = statement.executeQuery("Select id, titulo from tb_disciplina");
			
			// metodo que posiciona no primeiro registro da consulta
			dbResultado.first();
			System.out.println("Disciplina: " + dbResultado.getString("id") + " " + dbResultado.getString("titulo"));
			
			// metodo que posiciona no ultimo registro da consulta
			dbResultado.last();
			System.out.println("Disciplina: " + dbResultado.getString("id") + " " + dbResultado.getString("titulo"));
			
			
		} catch (SQLException e) {

			System.out.println("Erro :" + e.getMessage().toString());
		}
		
		
		
		System.out.println("");
		
		
		
		try {
			
			Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet dbResultado = statement.executeQuery("Select nome, cpf from tb_aluno");
			
			
			System.out.println("Lista de Alunos");			
			
		    /*  o metodo first() posiciona no primeiro registro da consulta
			 *  e retorna false, se o resultado for vazio 
			 */			
			if (dbResultado.first()) { 
				
				do {					
					System.out.println("Nome: " + dbResultado.getString("nome") + " CPF: " + dbResultado.getString("cpf"));					
				}
				while (dbResultado.next()); 
				
			    /*  o metodo next() posiciona no próximo registro
			     *  da consulta.  Retorna false quando não houver
			     *  mais registros.
			     */				
			}
				
			
		} catch (SQLException e) {

			System.out.println("Erro :" + e.getMessage().toString());
		}
    		
		
	}
	  
			
		
}
