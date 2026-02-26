package com.erp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Facture {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate dateFacturation;
    private BigDecimal montantTotalTTC;

    @OneToOne
    private Commande commande;

    public Facture() {}

    public Long getId() {
        return id;
    }

    public LocalDate getDateFacturation() {
        return dateFacturation;
    }

    public void setDateFacturation(LocalDate dateFacturation) {
        this.dateFacturation = dateFacturation;
    }

    public BigDecimal getMontantTotalTTC() {
        return montantTotalTTC;
    }

    public void setMontantTotalTTC(BigDecimal montantTotalTTC) {
        this.montantTotalTTC = montantTotalTTC;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
