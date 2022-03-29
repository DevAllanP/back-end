package fr.insys.commerce.dto;

public class LigneCommandeStripeDto {
private ProduitStripeDto produit;
private int quantite;

public ProduitStripeDto getProduit() {
	return produit;
}
public void setProduit(ProduitStripeDto produit) {
	this.produit = produit;
}
public int getQuantite() {
	return quantite;
}
public void setQuantite(int quantite) {
	this.quantite = quantite;
}


}
