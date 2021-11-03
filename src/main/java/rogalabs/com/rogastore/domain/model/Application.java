package rogalabs.com.rogastoreapi.domain.model;
  
import javax.persistence.Entity;
import javax.persistence.Table;
 
@Entity
@Table(name = "tb_application")
public class Application extends Product{
 
	private static final long serialVersionUID = 1L;
}
