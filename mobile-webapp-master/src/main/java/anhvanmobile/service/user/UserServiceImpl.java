package anhvanmobile.service.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import anhvanmobile.dto.UserDTO;
import anhvanmobile.exception.DuplicateEmailException;
import anhvanmobile.exception.DuplicatePhoneNumberException;
import anhvanmobile.exception.DuplicateUsernameException;
import anhvanmobile.model.Role;
import anhvanmobile.model.User;
import anhvanmobile.repository.RoleRepository;
import anhvanmobile.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found!");
		return user;
	}

	@Override
	public User findUserById(Integer id) {
		return userRepository.getOne(id);
	}

	@Override
	public User create(User u) {
		if (u == null)
			throw new NullPointerException();
		if (findByUsername(u.getUsername()) != null)
			throw new DuplicateUsernameException();
		if (findByEmail(u.getEmail()) != null)
			throw new DuplicateEmailException();
		if (findByPhoneNumber(u.getPhoneNumber()) != null)
			throw new DuplicatePhoneNumberException();

		u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName("USER"));
		u.setRoles(roles);
		u.setEnabled(true);
		return userRepository.save(u);
	}

	@Deprecated
	@Override
	public User update(User u) {
		return null;
	}

	@Override
	public boolean remove(User u) {
		try {
			userRepository.delete(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User findById(Integer id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByPhoneNumber(String phoneNumber) {
		return userRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public boolean disableUser(String username, boolean disable) {
		userRepository.disableUser(username, disable);
		return true;
	}

	@Override
	public boolean changePassword(String username, String password) {
		userRepository.updatePassword(username, password);
		return true;
	}

	@Override
	public boolean changeFullName(String username, String fullname) {
		userRepository.updateFullname(username, fullname);
		return true;
	}

	@Override
	public boolean changeAddress(String username, String address) {
		 userRepository.updateAddress(username, address);
		 return true;
	}

	@Override
	public boolean changeGender(String username, boolean gender) {
		userRepository.updateGender(username, gender);
		return true;
	}

	@Override
	public Page<UserDTO> getPages(int page, int size) {
		if (page < 1)
			page = 1;
		if (size < 5)
			size = 5;
		return userRepository.getPages(PageRequest.of(page - 1, size));
	}

	@Override
	public long getTotalUser() {
		return userRepository.count();
	}

	@Override
	public Page<UserDTO> getPages(String search, int page, int size) {
		if (page < 1)
			page = 1;
		if (size < 5)
			size = 5;
		return userRepository.getPages("%" + search + "%", PageRequest.of(page - 1, size));
	}


}
