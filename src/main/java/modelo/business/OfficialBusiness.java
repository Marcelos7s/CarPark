package modelo.business;

import java.util.ArrayList;
import java.util.List;
import dao.ConexaoJDBCCarPark;
import dao.OfficialDao;
import modelo.entidades.Official;

public class OfficialBusiness {
	private ConexaoJDBCCarPark con;
	private List<Official> listOfficial = new ArrayList<Official>();

	public OfficialBusiness(ConexaoJDBCCarPark con) {
		this.con = con;
		// carregando funcionarios a partir do BD
		OfficialDao officialDao = new OfficialDao(this.con);
		this.listOfficial = officialDao.recupera();
	}

	// cadastrar um novo funcionario no banco
	public boolean newOfficial(Official official) {

		OfficialDao officialDao = new OfficialDao(this.con);

		if (officialDao.insert(official)) {

			listOfficial.add(official); // adiciona funcionario na lista
			return true;
		}
		return false;
	}

	// acesso a lista de funcionarios
	public List<Official> getListOfficial() {
		return listOfficial;
	}

	public String employeeReports() {

		String emp = "-----------Relatório de funcionarios-----------\n";

		for (Official officialItem : listOfficial) {

			emp += "\n" + officialItem.showInformation() + "\n";
		}

		return emp;
	}

	public void updateOfficial(Official official) {
		OfficialDao officialDao = new OfficialDao(this.con);
		if (officialDao.update(official))
			System.out.println("implementar alteracao de dados na lista");// this.listOfficial.remove(official);
	}

	public Official readOfficial(String cpf) {
		OfficialDao officialDao = new OfficialDao(this.con);
		return officialDao.read(cpf);
	}

	// retira funcionario do banco
	public void removeOfficial(Official official) {
		OfficialDao officialDao = new OfficialDao(this.con);
		if (officialDao.delete(official))
			this.listOfficial.remove(official);
	}
}
