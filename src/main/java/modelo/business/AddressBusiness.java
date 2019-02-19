package modelo.business;

import java.util.ArrayList;
import java.util.List;
import dao.AddressDao;
import dao.ConexaoJDBCCarPark;
import modelo.entidades.Address;

public class AddressBusiness {
	private ConexaoJDBCCarPark con;
	private List<Address> listAddress = new ArrayList<Address>();

	public AddressBusiness(ConexaoJDBCCarPark con) {
		this.con = con;
		// carregando endereco a partir do BD
		AddressDao addressDao = new AddressDao(this.con);
		this.listAddress = addressDao.recupera();
	}

	// cadastrar um novo endereco no banco
	public boolean newAddress(Address address) {
		AddressDao addressDao = new AddressDao(this.con);
		if (addressDao.insert(address)) {
			listAddress.add(address); // adiciona endereco na lista
			return true;
		}
		return false;
	}

	// retira endereco do banco
	public void removeAddress(Address address) {
		AddressDao addressDao = new AddressDao(this.con);
		if (addressDao.delete(address))
			this.listAddress.remove(address);
	}

	// acesso a lista de funcionarios
	public List<Address> getListOfficial() {
		return listAddress;
	}
}
