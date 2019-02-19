package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Client;
public class ClientDao implements CarParkInterfaceCrudDao<Client> {
	private ConexaoJDBCCarPark conexaoJDBCCarPark;
	public ClientDao(ConexaoJDBCCarPark conexaoJDBCCarPark) {
		super();
		this.conexaoJDBCCarPark = conexaoJDBCCarPark;
	}

	public boolean insert(Client obj) {
		// TODO Auto-generated method stub
		String sqlInsertCommand = "";
		sqlInsertCommand = "insert into TB_Client (name,rg,cpf,address)";
		sqlInsertCommand += " values (?,?,?,?)";
		PreparedStatement statementPrep = this.conexaoJDBCCarPark.preparaDeclaracao(sqlInsertCommand);
		try {
			statementPrep.setString(1, obj.getName());
			statementPrep.setString(2, obj.getRg());
			statementPrep.setString(3, obj.getCpf());
			statementPrep.setString(4, obj.getAddress());
			statementPrep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
		return true;
	}

	public boolean update(Client obj) {
		// TODO Auto-generated method stub
		String sqlUpdateCommand = "update into TB_Client (name,rg,cpf,address)";
		sqlUpdateCommand += " values (?,?,?,?)";
		sqlUpdateCommand += " where cpf = ?";
		PreparedStatement statementPrep = this.conexaoJDBCCarPark.preparaDeclaracao(sqlUpdateCommand);
		try {
			statementPrep.setString(1, obj.getName());
			statementPrep.setString(2, obj.getRg());
			statementPrep.setString(3, obj.getCpf());
			statementPrep.setString(4, obj.getAddress());
			statementPrep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
		return true;
	}

	public boolean delete(Client obj) {
		// TODO Auto-generated method stub
		String sqlDeleteCommand = "delete from TB_Client (name,rg,cpf,address)";
		sqlDeleteCommand += " values (?,?,?,?)";
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

	public List<Client> recupera() {
		// TODO Auto-generated method stub
		ResultSet data = this.query(null);
		// se a consulta contiver erros
		if (data == null)
			return null;
		try {
			if (!data.first())
				return null;
			List<Client> list = new ArrayList<Client>();
			// inclui todos os registros provenientes do banco de dados
			// na lista
			do {
				Client client = new Client();
				client.setName(data.getString("name"));
				client.setRg(data.getString("rg"));
				client.setCpf(data.getString("cpf"));
				client.setAddress(data.getString("address"));
				list.add(client);
			} while (data.next());

			data.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public Client read(String chave) {
		// TODO Auto-generated method stub
		Client client = new Client();
		ResultSet data = this.query(" cpf = " + "'" + chave + "'");
		try {
			if (data.first()) {
				
			} else
				data.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return client;
	}

	private ResultSet query(String filter) {
		String sqlSelectCommand = "Select * from TB_Client";
		if (filter != null)
			sqlSelectCommand += " where " + filter;
		sqlSelectCommand += " order by name";
		return this.conexaoJDBCCarPark.consulta(sqlSelectCommand);
	}

}