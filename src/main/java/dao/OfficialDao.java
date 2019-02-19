package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Official;

public class OfficialDao implements CarParkInterfaceCrudDao<Official> {
	private ConexaoJDBCCarPark conexaoJDBCCarPark;

	public OfficialDao(ConexaoJDBCCarPark conexaoJDBCCarPark) {
		super();
		this.conexaoJDBCCarPark = conexaoJDBCCarPark;
	}

	public boolean insert(Official obj) {
		// TODO Auto-generated method stub
		String sqlInsertCommand = "";
		sqlInsertCommand = "insert into TB_OFFICIAL (name,rg,cpf,salary,registry)";
		sqlInsertCommand += " values (?,?,?,?,?)";
		PreparedStatement statementPrep = this.conexaoJDBCCarPark.preparaDeclaracao(sqlInsertCommand);
		try {
			statementPrep.setString(1, obj.getName());
			statementPrep.setString(2, obj.getRg());
			statementPrep.setString(3, obj.getCpf());
			statementPrep.setString(4, obj.getSalary());
			statementPrep.setString(5, obj.getRegistry());
			statementPrep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
		return true;
	}

	public boolean update(Official obj) {
		// TODO Auto-generated method stub
		String sqlUpdateCommand = "update into TB_OFFICIAL (name,rg,cpf,salary,registry)";
		sqlUpdateCommand += " values (?,?,?,?,?)";
		sqlUpdateCommand += " where cpf = ?";
		PreparedStatement statementPrep = this.conexaoJDBCCarPark.preparaDeclaracao(sqlUpdateCommand);
		try {
			statementPrep.setString(1, obj.getName());
			statementPrep.setString(2, obj.getRg());
			statementPrep.setString(3, obj.getCpf());
			statementPrep.setString(4, obj.getSalary());
			statementPrep.setString(5, obj.getRegistry());
			statementPrep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
		return true;
	}

	public boolean delete(Official obj) {
		// TODO Auto-generated method stub
		String sqlDeleteCommand = "delete from TB_OFFICIAL (cpf)";
		sqlDeleteCommand += " values (?)";
		sqlDeleteCommand = "where cpf = ?";
		PreparedStatement statementPrep = this.conexaoJDBCCarPark.preparaDeclaracao(sqlDeleteCommand);
		try {
			statementPrep.setString(1, obj.getCpf());
			statementPrep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Official> recupera() {
		// TODO Auto-generated method stub
		ResultSet data = this.query(null);
		// se a consulta contiver erros
		if (data == null)
			return null;
		try {
			if (!data.first())
				return null;
			List<Official> list = new ArrayList<Official>();
			// inclui todos os registros provenientes do banco de dados
			// na lista
			do {
				Official official = new Official();
				official.setName(data.getString("name"));
				official.setRg(data.getString("rg"));
				official.setCpf(data.getString("cpf"));
				official.setSalary(data.getString("salary"));
				list.add(official);
			} while (data.next());

			data.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public Official read(String chave) {
		// TODO Auto-generated method stub
		Official official = null;
		ResultSet data = this.query(" cpf = " + "'" + chave + "'");
		try {
			if (data.first()) {
				official = new Official();
				official.setName(data.getString("name"));
				official.setRg(data.getString("rg"));
				official.setCpf(data.getString("cpf"));
				official.setSalary(data.getString("salary"));
				return official;
			} else
				data.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		return official;
	}

	private ResultSet query(String filter) {

		String sqlSelectCommand = "Select * from TB_OFFICIAL";

		if (filter != null)
			sqlSelectCommand += " where " + filter;

		sqlSelectCommand += " order by name";

		return this.conexaoJDBCCarPark.consulta(sqlSelectCommand);
	}

}
