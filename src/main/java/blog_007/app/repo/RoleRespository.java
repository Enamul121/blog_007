package blog_007.app.repo;

import blog_007.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRespository extends JpaRepository<Role, Integer> {

	 Role findByRole(String role);

}
