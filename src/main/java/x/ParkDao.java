package x;

import javax.swing.JOptionPane;
import dao.ConexaoJDBCCarPark;
import modelo.business.OfficialBusiness;
import modelo.business.VehicleBusiness;
import modelo.entidades.Vehicle;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.HOURS;

public class ParkDao {
	static String ticket, inputData;
	static ConexaoJDBCCarPark conexaoJDBCCarPark;
	static LocalTime localTime;

	public static void main(String[] args) {
		// <<<<<<<< Conecta DB >>>>>>>
		conexaoJDBCCarPark = new ConexaoJDBCCarPark();
		if (!conexaoJDBCCarPark.conects()) {
			JOptionPane.showMessageDialog(null, "Não foi possível conectar ao Banco de Dados !");
			return;
		}

		if (loginOfficial())
			checkBoard();
	}

	public static boolean loginOfficial() {
		inputData = JOptionPane.showInputDialog("Informe seu Login");
		OfficialBusiness officialBusiness = new OfficialBusiness(conexaoJDBCCarPark);
		if (officialBusiness.readOfficial(inputData) != null) {
			JOptionPane.showMessageDialog(null, "LOGANDO");
			return true;
		}
		JOptionPane.showMessageDialog(null, "usuário não encontrado");
		return false;
	}

	public static void checkBoard() {
		LocalTime currentTime = LocalTime.now();
		LocalTime timeOfEntry;
		inputData = JOptionPane.showInputDialog("Informar placa");
		VehicleBusiness vehicleBusiness = new VehicleBusiness(conexaoJDBCCarPark);
		if ((vehicleBusiness.readVehicle(inputData)) != null) { // gera ticket
			// busca no banco de dados o horario de entrada do veiculo
			Vehicle vehicle = new Vehicle();
			vehicle = vehicleBusiness.readVehicle(inputData);
			timeOfEntry = LocalTime.parse(vehicle.getHour());
			vehicleBusiness.removeVehicle(vehicle);
			priceTable(diffHours(currentTime, timeOfEntry));
		} else {
			System.out.println("Gerar Entrada");

			Vehicle vehicle = new Vehicle();
			vehicle.setBoard(inputData);
			inputData = JOptionPane.showInputDialog(null, "marca do veículo", "Registrando",
					JOptionPane.INFORMATION_MESSAGE);
			vehicle.setMark(inputData);
			inputData = JOptionPane.showInputDialog(null, "ano do veículo", "Registrando",
					JOptionPane.INFORMATION_MESSAGE);
			vehicle.setYear(inputData);

			inputData = JOptionPane.showInputDialog(null, "cor do veículo", "Registrando",
					JOptionPane.INFORMATION_MESSAGE);
			vehicle.setColor(inputData);
			LocalTime lt = LocalTime.now();
			vehicle.setHour(lt.toString());
			vehicleBusiness.newVehicle(vehicle);
			JOptionPane.showMessageDialog(null, "Marca: "+vehicle.getMark() + "\nAno: " + vehicle.getYear() + "\nCor: " + vehicle.getColor()
					+ "\nPlaca: " + vehicle.getBoard() + "\n", "Entrada Registrada", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static long diffHours(LocalTime currentTime, LocalTime timeOfEntry) {
		LocalTime auxStay;
		System.out.println("Ticket de saida");
		ticket = "ticket de saida ";
		System.out.println("hora: entrada " + timeOfEntry + "\nhora de saida: " + LocalTime.now());
		ticket += "\nhora: entrada " + timeOfEntry.format(DateTimeFormatter.ofPattern("HH:mm")) + "\nhora de saida: "
				+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
		long stay = 0;
		if (currentTime.compareTo(timeOfEntry) > 0) {
			stay = HOURS.between(timeOfEntry, currentTime);
			return stay;
		}
		// pegando complementos de hora e minutos
		int hourInMinutes = (24 - timeOfEntry.getHour()) * 60;
		int minutes = 60 - timeOfEntry.getMinute();
		// convertendo minutos para hora em forma de decimal
		double hourWithFraction = (hourInMinutes - minutes) / 60.0;
		// extraindo parte inteira
		long intPart = (long) hourWithFraction;
		// extraindo parte fracionaria
		double FractPart = ((hourWithFraction - intPart) * 60.0);
		auxStay = currentTime.plusHours((int) intPart);
		auxStay = auxStay.plusMinutes(Math.round((int) FractPart));
		stay = auxStay.getHour();
		System.out.println("Permanência: " + stay + "Hs");
		ticket += "\nPermanência: " + stay + "Hs";
		return stay;
	}

	public static void priceTable(long stay) {
		final double pricePerHour = 2.50, extra = 0.50;
		if (stay == 0) {
			ticket += "\nEstacionamento Gratuito";
		} else if (stay < 4) {
			ticket += "\nValor a pagar: " + stay * pricePerHour;
		} else {
			ticket += "\nValor a pagar: " + (stay * pricePerHour + stay * extra);
		}
		JOptionPane.showMessageDialog(null, ticket);
	}
}
