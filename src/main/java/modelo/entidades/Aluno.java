package modelo.entidades;


public final class Aluno extends Pessoa {
	
    private String matricula = "";
    private boolean pne = false;
        
    
    public String montaCadastro(){
        String pn = "";
        
        if(this.pne)
          pn = "\nPortador de necessidades especiais";
        
        return super.montaCadastro() + "\nMatricula: "+this.matricula+pn;
                                     
    }
    
    
    
    public String getMatricula() {
        return matricula;
    }
        
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public boolean isPne() {
        return pne;
    }
        
    public void setPne(boolean pne) {
        this.pne = pne;
    }
    
    
    
}
