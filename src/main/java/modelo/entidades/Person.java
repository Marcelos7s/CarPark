package modelo.entidades;

public abstract class Person {
	private String name, rg, cpf;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String showInformation(){
        return "Nome: "+this.name+
                "\nRG: "+this.rg+
                "\nCPF: "+this.cpf;
}

}
