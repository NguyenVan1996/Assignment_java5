package anhvanmobile.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import anhvanmobile.dto.UserDTO;
import anhvanmobile.model.User;
import anhvanmobile.service.BaseService;
import anhvanmobile.service.DTOService;

public interface UserService extends UserDetailsService, BaseService<User, Integer>, DTOService<UserDTO> {

	User findUserById(Integer id);

	User findByUsername(String username);

	User findByEmail(String email);

	User findByPhoneNumber(String phoneNumber);

	boolean disableUser(String username, boolean enabled);

	boolean changePassword(String username, String password);

	boolean changeFullName(String username, String fullname);

	boolean changeAddress(String username, String address);

	boolean changeGender(String username, boolean gender);
	
	long getTotalUser();

}
