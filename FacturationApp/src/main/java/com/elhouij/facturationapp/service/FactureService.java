package com.elhouij.facturationapp.service;

import com.elhouij.facturationapp.model.Facture;
import com.elhouij.facturationapp.repository.FactureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FactureService {

    @Autowired
    FactureRepo repo;

    public List<Facture> getFactures() {
        System.out.println(repo.findAll());
        return repo.findAll();
    }

    public Facture getFactureById(@PathVariable int prodId) {
        return repo.findById(prodId).orElse(null);
    }

    public Facture addFacture(Facture facture, MultipartFile FactureClient, MultipartFile FactureFournisseur) throws IOException {

        facture.setFactureClientName(FactureClient.getOriginalFilename());
        facture.setFactureClientType(FactureClient.getContentType());
        facture.setFactureClientData(FactureClient.getBytes());

        facture.setFactureFournisseurName(FactureFournisseur.getOriginalFilename());
        facture.setFactureFournisseurType(FactureFournisseur.getContentType());
        facture.setFactureFournisseurData(FactureFournisseur.getBytes());

        return repo.save(facture);
    }
}
