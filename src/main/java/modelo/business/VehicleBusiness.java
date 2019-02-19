package modelo.business;

import java.util.ArrayList;
import java.util.List;
import dao.ConexaoJDBCCarPark;
import dao.VehicleDao;
import modelo.entidades.Vehicle;

public class VehicleBusiness {
	private ConexaoJDBCCarPark con;
	private List<Vehicle> listVehicle = new ArrayList<Vehicle>();

	public VehicleBusiness(ConexaoJDBCCarPark con) {
		this.con = con;
		// carregando carros a partir do BD
		VehicleDao vehicleDao = new VehicleDao(this.con);
		this.listVehicle = vehicleDao.recupera();
	}

	// cadastrar um novo carro no banco
	public boolean newVehicle(Vehicle vehicle) {

		VehicleDao vehicleDao = new VehicleDao(this.con);

		if (vehicleDao.insert(vehicle)) {

			// listVehicle.add(vehicle); // adiciona funcionario na lista
			return true;
		}
		return false;
	}

	// acesso a lista de veiculos
	public List<Vehicle> getListVehicle() {
		return listVehicle;
	}

	public void updateVehicle(Vehicle vehicle) {
		VehicleDao vehicleDao = new VehicleDao(this.con);
		if (vehicleDao.update(vehicle))
			System.out.println("implementar alteracao de dados na lista");// this.listOfficial.remove(official);
	}

	public Vehicle readVehicle(String board) {
		VehicleDao vehicleDao = new VehicleDao(this.con);
		return vehicleDao.read(board);
	}

	// retira funcionario do banco
	public void removeVehicle(Vehicle vehicle) {
		VehicleDao vehicleDao = new VehicleDao(this.con);
		if (vehicleDao.delete(vehicle))
			this.listVehicle.remove(vehicle);
	}
}
