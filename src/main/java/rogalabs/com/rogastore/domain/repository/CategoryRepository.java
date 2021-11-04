package rogalabs.com.rogastore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rogalabs.com.rogastore.domain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
