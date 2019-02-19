package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Vehicle;

public class VehicleDao implements CarParkInterfaceCrudDao<Vehicle> {
	private ConexaoJDBCCarPark conexaoJDBCCarPark;

	public VehicleDao(ConexaoJDBCCarPark conexaoJDBCCarPark) {
		super();
		this.conexaoJDBCCarPark = conexaoJDBCCarPark;
	}

	public boolean insert(Vehicle obj) {
		// TODO Auto-generated method stub
		String sqlInsertCommand = "";
		sqlInsertCommand = "insert into TB_VEHICLE (year,mark,board,color,hour)";
		sqlInsertCommand += " values (?,?,?,?,?)";
		PreparedStatement statementPrep = this.conexaoJDBCCarPark.preparaDeclaracao(sqlInsertCommand);
		try {
			statementPrep.setString(1, obj.getYear());
			statementPrep.setString(2, obj.getMark());
			statementPrep.setString(3, obj.getBoard());
			statementPrep.setString(4, obj.getColor());
			statementPrep.setString(5, obj.getHour());
			statementPrep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			 
			return false;
		}
		return true;
	}

	public boolean update(Vehicle obj) {
		// TODO Auto-generated method stub
		String sqlUpdateCommand = "update into TB_VEHICLE (year,mark,board,color,hour)";
		sqlUpdateCommand += " values (?,?,?,?,?)";
		sqlUpdateCommand += " where board = ?";
		PreparedStatement statementPrep = this.conexaoJDBCCarPark.preparaDeclaracao(sqlUpdateCommand);
		try {
			statementPrep.setString(1, obj.getYear());
			statementPrep.setString(2, obj.getMark());
			statementPrep.setString(3, obj.getBoard());
			statementPrep.setString(4, obj.getColor());
			statementPrep.setString(5, obj.getHour());
			statementPrep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
	}

	public boolean delete(Vehicle obj) {
		// TODO Auto-generated method stub
		String sqlDeleteCommand = "delete from TB_VEHICLE where board = ?";
		PreparedStatement statementPrep = this.conexaoJDBCCarPark.preparaDeclaracao(sqlDeleteCommand);
		try {
			statementPrep.setString(1, obj.getBoard());
			statementPrep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Vehicle> recupera() {
		// TODO Auto-generated method stub
		ResultSet data = this.query(null);
		// se a consulta contiver erros
		if (data == null)
			return null;
		try {
			if (!data.first())
				return null;
			List<Vehicle> list = new ArrayList<Vehicle>();
			// inclui todos os registros provenientes do banco de dados
			// na lista
			do {
				Vehicle vehicle = new Vehicle();
				vehicle.setYear(data.getString("year"));
				vehicle.setMark(data.getString("mark"));
				vehicle.setBoard(data.getString("board"));
				vehicle.setColor(data.getString("color"));
				vehicle.setHour(data.getString("hour"));
				list.add(vehicle);
			} while (data.next());

			data.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public Vehicle read(String chave) {
		// TODO Auto-generated method stub
		Vehicle vehicle = null;
		ResultSet data = this.query(" board = " + "'" + chave + "'");
		try {
			if (data.first()) {
				vehicle = new Vehicle();
				vehicle.setYear(data.getString("year"));
				vehicle.setMark(data.getString("mark"));
				vehicle.setBoard(data.getString("board"));
				vehicle.setColor(data.getString("color"));
				vehicle.setHour(data.getString("hour"));
				data.close();
			return vehicle;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}

		return vehicle;
	}

	private ResultSet query(String filter) {
		
		String sqlSelectCommand = "Select * from TB_VEHICLE";

		if (filter != null) 
			sqlSelectCommand += " where " + filter;
		
		sqlSelectCommand += " order by year";
		
		return this.conexaoJDBCCarPark.consulta(sqlSelectCommand);
	}
	
}
