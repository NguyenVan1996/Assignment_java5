package anhvanmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import anhvanmobile.model.Role;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByName(String name);
	
}
