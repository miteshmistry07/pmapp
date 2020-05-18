package org.mistry.pms.repository;
import org.mistry.pms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	public User findByUsername(String username);
}
