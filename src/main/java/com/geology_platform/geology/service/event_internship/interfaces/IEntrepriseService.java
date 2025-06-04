package com.geology_platform.geology.service.event_internship.interfaces;

import com.geology_platform.geology.entity.event_internship.Entreprise;

import java.util.List;

public interface IEntrepriseService {
    List<Entreprise> getAllEntreprises(int page,int size);
    Entreprise getEntreprise(long id);
    Entreprise createEntreprise(Entreprise entreprise);
    Entreprise updateEntreprise(long id,Entreprise entreprise);
    void deleteEntreprise(long id);
}
