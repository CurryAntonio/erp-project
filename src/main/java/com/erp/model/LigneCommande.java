package com.erp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class LigneCommande {

    @Id
    @GeneratedValue
    private Long id;

    private int quantite;
    private BigDecimal prixVenteTotal;

    @ManyToOne
    private Produit produit;

    @ManyToOne
    private Commande commande;

    public LigneCommande() {}

    public Long getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixVenteTotal() {
        return prixVenteTotal;
    }

    public void setPrixVenteTotal(BigDecimal prixVenteTotal) {
        this.prixVenteTotal = prixVenteTotal;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
