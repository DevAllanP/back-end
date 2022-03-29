package fr.insys.commerce.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import fr.insys.commerce.models.ProduitEntity;
import fr.insys.commerce.models.ProduitEntity_;

public class ProduitSpecification implements Specification<ProduitEntity> {
	private static final long serialVersionUID = -5538716487168377787L;
	private Object val;
	private String col;
	private ERequeteOperateur op;
	
	public ProduitSpecification(String col, ERequeteOperateur op, Object val) {
		this.col = col;
		this.val = val;
		this.op = op;
	}

	@Override
	public Predicate toPredicate(Root<ProduitEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		switch(op) {
		case INF_EGALE:
			return criteriaBuilder.lessThanOrEqualTo(root.get(col), (Float) val);
		case INF:
			return criteriaBuilder.lessThan(root.get(col), (Float) val);
		case SUP_EGALE:
			return criteriaBuilder.greaterThanOrEqualTo(root.get(col), (Float) val);
		case SUP:
			return criteriaBuilder.greaterThan(root.get(col), (Float) val);
		case COMME:
			return criteriaBuilder.like(root.get(col), "%" + val + "%");
		case IS_TAG:
			return criteriaBuilder.equal(root.join(ProduitEntity_.listTag), val);
		default:
			return criteriaBuilder.equal(root.get(col), val);
		}
	}
}
