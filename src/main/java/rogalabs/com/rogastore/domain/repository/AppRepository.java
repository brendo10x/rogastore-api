package rogalabs.com.rogastore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rogalabs.com.rogastore.domain.model.App;

@Repository
public interface AppRepository extends JpaRepository<App, Long> {

}
