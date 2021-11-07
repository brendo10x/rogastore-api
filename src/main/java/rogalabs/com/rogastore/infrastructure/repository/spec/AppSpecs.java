package rogalabs.com.rogastore.infrastructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import rogalabs.com.rogastore.domain.filter.AppFilter;
import rogalabs.com.rogastore.domain.model.App;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

public class AppSpecs {

	public static Specification<App> usingFilter(AppFilter filtro) {
		return (root, query, builder) -> {

			if (App.class.equals(query.getResultType())) {
				root.fetch("category");
			}

			var predicates = new ArrayList<Predicate>();

			if (filtro.getName() != null) {
				predicates.add(builder.like(root.get("name"), "%" + filtro.getName() + "%"));
			}

			if (filtro.getCategoryId() != null) {
				predicates.add(builder.equal(root.get("category"), filtro.getCategoryId()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
