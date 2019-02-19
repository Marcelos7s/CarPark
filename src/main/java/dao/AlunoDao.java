package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidades.Aluno;

public class AlunoDao implements InterfaceCrudDAO<Aluno> {
		
	private ConexaoJDBC conexaoJDBC;
			
	
	public AlunoDao(ConexaoJDBC conexaoJDBC) {
		super();
		this.conexaoJDBC = conexaoJDBC;
	}

	public boolean insert(Aluno obj) {
		
		String sql = "";
		sql = "insert into tb_aluno (nome,cpf,matricula,idade)";
		sql += " values (?,?,?,?)";						
	
		PreparedStatement declaracaoPrep = this.conexaoJDBC.preparaDeclaracao(sql);		
	
		try {
			declaracaoPrep.setString(1, obj.getNome());
			declaracaoPrep.setString(2, obj.getCpf());
			declaracaoPrep.setString(3, obj.getMatricula());
			declaracaoPrep.setInt(4, obj.getIdade());
			
			declaracaoPrep.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
		
		return true;
	}



	public boolean update(Aluno obj) {
		String sql = "";
		sql = "update tb_aluno set nome = ?, cpf = ?, maticula = ?, idade = ?";
		sql += " where matricula = ?";					
	
		PreparedStatement declaracaoPrep = this.conexaoJDBC.preparaDeclaracao(sql);		
	
		try {
			declaracaoPrep.setString(1, obj.getNome());
			declaracaoPrep.setString(2, obj.getCpf());
			declaracaoPrep.setString(3, obj.getMatricula());
			declaracaoPrep.setInt(4, obj.getIdade());
			
			declaracaoPrep.setString(5,  obj.getMatricula());
			
			declaracaoPrep.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
		
		return true;
	}


	public boolean deleta(Aluno obj) {
		String sql = "";
		
		sql = "delete from tb_aluno";
		sql += " where matricula = ?";		
				
		
		PreparedStatement declaracaoPrep = this.conexaoJDBC.preparaDeclaracao(sql);

		try {
			
			declaracaoPrep.setString(1,  obj.getMatricula());
			
			declaracaoPrep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}


	
	public List<Aluno> recupera() {


		ResultSet dados = this.query(null);

		// se a consulta contiver erros 
		if (dados == null) 
			return null;


		try {

			if (!dados.first()) 
				return null;
			
			
			List<Aluno> list = new ArrayList<Aluno>();

			// inclui todos os registros provenientes do banco de dados
			//   na lista
			do  
			{
				Aluno a = new Aluno();
				a.setCpf(dados.getString("cpf"));
				a.setMatricula(dados.getString("matricula"));
				a.setNome(dados.getString("nome"));
				a.setIdade(dados.getInt("idade"));
				list.add(a);

			} while (dados.next());
			
			dados.close();
			
			return list;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;			
		}

		
	}


	public Aluno recupera(String chave) {
		// TODO Auto-generated method stub
		
		ResultSet dados = this.query(" cpf = " + "'" + chave + "'");

		Aluno a = null;
		
		try {
			if (dados.first()) { 
				a = new Aluno();
				a.setCpf(dados.getString("cpf"));
				a.setMatricula(dados.getString("matricula"));
				a.setNome(dados.getString("nome"));
				a.setIdade(dados.getInt("idade"));				
			}			
			dados.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;			
		}
		
		return a;
	}

	
	
	private ResultSet query(String filtro) {
	
		String sql = "Select * from tb_aluno";
		
		if (filtro != null)
			sql += " where " + filtro;	
			
		sql += " order by nome";

		return this.conexaoJDBC.consulta(sql);		
	}
	
			
	

}
