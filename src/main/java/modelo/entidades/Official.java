package modelo.entidades;

public class Official extends Person {
	private String salary;
	private String registry;

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}

	public String showInformation() {
		return super.showInformation();
	}
}
