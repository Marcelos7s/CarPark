package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidades.Disciplina;



public class DisciplinaDao implements InterfaceCrudDAO<Disciplina> {
		
	private ConexaoJDBC conexaoJDBC;
			
	
	public DisciplinaDao(ConexaoJDBC conexaoJDBC) {
		super();
		this.conexaoJDBC = conexaoJDBC;
	}
	


	public boolean insert(Disciplina obj) {
		
		String sql = "";
		sql = "insert into disciplina (titulo,area)";
		sql += " values (?,?)";						
	
		PreparedStatement declaracaoPrep = this.conexaoJDBC.preparaDeclaracao(sql);		
	
		try {
			declaracaoPrep.setString(1, obj.getTitulo());
			declaracaoPrep.setString(2, obj.getArea());
			
			declaracaoPrep.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
		
		return true;
	}


	
	public boolean update(Disciplina obj) {
		String sql = "";
		sql = "update disciplina set titulo = ?, area = ?";
		sql += " where id = ?";					
	
		PreparedStatement declaracaoPrep = this.conexaoJDBC.preparaDeclaracao(sql);		
	
		try {
			declaracaoPrep.setString(1, obj.getTitulo());
			declaracaoPrep.setString(2, obj.getArea());
			
			declaracaoPrep.setInt(3,  obj.getId());
			
			declaracaoPrep.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
		
		return true;
	}



	public boolean deleta(Disciplina obj) {
		String sql = "";
		
		sql = "delete from disciplina";
		sql += " where id = ?";		
				
		PreparedStatement declaracaoPrep = this.conexaoJDBC.preparaDeclaracao(sql);			

		try {
			
			declaracaoPrep.setInt(1,  obj.getId());
			
			declaracaoPrep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}


	public List<Disciplina> recupera() {

		ResultSet dados = this.query(null);

		// se a consulta contiver erros 
		if (dados == null) 
			return null;


		try {

			// se a consulta n�o retornar dados
			if (!dados.first()) 
				return null;
			
			
			List<Disciplina> list = new ArrayList<Disciplina>();

			// inclui todos os registros provenientes do banco de dados
			//   na lista
			do  
			{
				Disciplina a = new Disciplina();
				a.setTitulo(dados.getString("titulo"));
				a.setArea(dados.getString("area"));
				list.add(a);

			} while (dados.next());
			
			dados.close();
			
			return list;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;			
		}

		
	}



	public Disciplina recupera(String chave) {

		ResultSet dados = this.query("id = " + chave);

		// se a consulta contiver erros 
		if (dados == null) 
			return null;

		Disciplina d = null;
		
		try {

			if (dados.first()) {
				d = new Disciplina();
				d.setTitulo(dados.getString("titulo"));
				d.setArea(dados.getString("area"));
			}
			
			dados.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;			
		}
		
		return d;

		
	}
	
	
	private ResultSet query(String filtro) {
		
		String sql = "Select * from disciplina";
				
		if (filtro != null)
			sql += " where " + filtro;	
			
		sql += " order by titulo";

		return this.conexaoJDBC.consulta(sql);		
	}



		
	

}
