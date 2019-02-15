package anhvanmobile.service;

import java.util.List;

public interface BaseService<T, ID> {

	T create(T object);
	
	T update(T object);
	
	boolean remove(T object);
	
	T findById(ID id);
	
	List<T> findAll();
	
	
}
