package com.elhouij.facturationapp.controller;

import com.elhouij.facturationapp.model.Facture;
import com.elhouij.facturationapp.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FacutreController {

    @Autowired
    FactureService service;

    @GetMapping("/factures")
    public ResponseEntity<List<Facture>> getFactures() {

        List<Facture> factures = service.getFactures();
        return new ResponseEntity<>(factures, HttpStatus.OK);
    }

    @GetMapping("/factures/{id}")
    public ResponseEntity<Facture>  getFactureById(@PathVariable int id) {

        Facture facture = service.getFactureById(id);
        return new ResponseEntity<>(facture, HttpStatus.OK);
    }

    @GetMapping("/facture-client/{factureId}/pdf")
    public ResponseEntity<byte[]> getFactureClientById(@PathVariable int factureId){
        Facture facture = service.getFactureById(factureId);
        byte[] billFile = facture.getFactureClientData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(facture.getFactureClientType()))
                .body(billFile);
    }

    @GetMapping("/facture-fournisseur/{factureId}/pdf")
    public ResponseEntity<byte[]> getFactureSupplierById(@PathVariable int factureId){
        Facture facture = service.getFactureById(factureId);
        byte[] billFile = facture.getFactureFournisseurData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(facture.getFactureFournisseurType()))
                .body(billFile);
    }

    @PostMapping("/facture")
    public ResponseEntity<?> addFacture(@RequestPart Facture facture,
                                        @RequestPart MultipartFile FactureClient,
                                        @RequestPart MultipartFile FactureFournisseur) {
        try {
            Facture facture1 = service.addFacture(facture, FactureClient, FactureFournisseur);
            //System.out.println(facture1);
            return new ResponseEntity<>(facture1, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
