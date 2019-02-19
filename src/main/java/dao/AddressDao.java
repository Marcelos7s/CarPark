package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Address;

public class AddressDao implements CarParkInterfaceCrudDao<Address> {
	private ConexaoJDBCCarPark conexaoJDBCCarPark;

	public AddressDao(ConexaoJDBCCarPark conexaoJDBCCarPark) {
		this.conexaoJDBCCarPark = conexaoJDBCCarPark;
	}

	public boolean insert(Address obj) {
		// TODO Auto-generated method stub

		String sqlInsertCommand = "insert into TB_ADDRESS (cep,street,home_number,city,state)";
		sqlInsertCommand += " values (?,?,?,?,?,?)";
		PreparedStatement statementPrep = this.conexaoJDBCCarPark.preparaDeclaracao(sqlInsertCommand);
		try {
			statementPrep.setString(1, obj.getCep());
			statementPrep.setString(2, obj.getStreet());
			statementPrep.setInt(3, obj.getNumber());
			statementPrep.setString(4, obj.getCity());
			statementPrep.setString(5, obj.getState());
			statementPrep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Address obj) {
		// TODO Auto-generated method stub
		String sqlUpdateCommand = "update into TB_ADDRESS (cep,street,home_number,city,state)";
		sqlUpdateCommand += " values (?,?,?,?,?,?)";
		sqlUpdateCommand += " where cep = ?";
		PreparedStatement statementPrep = this.conexaoJDBCCarPark.preparaDeclaracao(sqlUpdateCommand);
		try {
			statementPrep.setString(1, obj.getCep());
			statementPrep.setString(2, obj.getStreet());
			statementPrep.setInt(3, obj.getNumber());
			statementPrep.setString(4, obj.getCity());
			statementPrep.setString(5, obj.getState());
			statementPrep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(Address obj) {
		// TODO Auto-generated method stub
		String sqldeleteCommand = "delete into TB_ADDRESS (cep,street,home_number,city,state)";
		sqldeleteCommand += " values (?,?,?,?,?,?)";
		sqldeleteCommand += " where cep = ?";
		PreparedStatement statementPrep = this.conexaoJDBCCarPark.preparaDeclaracao(sqldeleteCommand);
		try {
			statementPrep.setString(1, obj.getCep());
			statementPrep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Address> recupera() {
		// TODO Auto-generated method stub
		ResultSet data = this.query(null);
		// se a consulta contiver erros
		if (data == null)
			return null;
		try {
			if (!data.first())
				return null;
			List<Address> list = new ArrayList<Address>();
			// inclui todos os registros provenientes do banco de dados
			// na lista
			do {
				Address address = new Address();
				address.setCep(data.getString("cep"));
				address.setStreet(data.getString("street"));
				address.setNumber(data.getInt("home_number"));
				address.setCity(data.getString("city"));
				address.setState(data.getString("state"));
				list.add(address);
			} while (data.next());

			data.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public Address read(String chave) {
		// TODO Auto-generated method stub
		Address address = new Address();
		return address;
	}

	private ResultSet query(String filter) {

		String sqlSelectCommand = "Select * from TB_OFFICIAL";

		if (filter != null)
			sqlSelectCommand += " where " + filter;

		sqlSelectCommand += " order by name";

		return this.conexaoJDBCCarPark.consulta(sqlSelectCommand);
	}
}