package com.elhouij.facturationapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double Tva;
    private double TauxTva;

    private String FactureClientName;
    private String FactureClientType;
    @Lob
    private byte[] FactureClientData;

    private String FactureFournisseurName;
    private String FactureFournisseurType;
    @Lob
    private byte[] FactureFournisseurData;

}
