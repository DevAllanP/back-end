package fr.insys.commerce.models;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProduitEntity.class)
public class ProduitEntity_ {
	public static volatile SetAttribute<ProduitEntity, TagEntity> listTag;
}
