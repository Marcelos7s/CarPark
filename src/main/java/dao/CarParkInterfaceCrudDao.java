package dao;

import java.util.List;

public interface CarParkInterfaceCrudDao<T> {
	public boolean insert(T obj); // (INSERT/CREATE)

	public boolean update(T obj); // (UPDATE)

	public boolean delete(T obj); // (DELETE)

	public List<T> recupera(); // (RECUPERA CONJUNTO)

	public T read(String chave); // (RECUPERA UM ELEMENTO)
}
