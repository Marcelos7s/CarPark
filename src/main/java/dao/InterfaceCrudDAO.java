package dao;

import java.util.List;

//Toda Classe que DAO deverá implementar esta interfaceCRUD
public interface InterfaceCrudDAO<T> {
	
	public boolean insert(T obj);  // (INSERT/CREATE)
	
	public boolean update(T obj);  // (UPDATE)
	
	public boolean deleta(T obj);  	// (DELETE)
		
	public List<T> recupera(); 		// (RECUPERA CONJUNTO)
	
	public T recupera(String chave);  //  (RECUPERA UM ELEMENTO)
	

	
	
}
