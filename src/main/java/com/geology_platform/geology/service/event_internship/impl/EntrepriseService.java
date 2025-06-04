package com.geology_platform.geology.service.event_internship.impl;

import com.geology_platform.geology.entity.event_internship.Entreprise;
import com.geology_platform.geology.exception.event_internship.EntrepriseExistsAlready;
import com.geology_platform.geology.exception.event_internship.EntrepriseNotFoundException;
import com.geology_platform.geology.repository.event_internship.EntrepriseRepository;
import com.geology_platform.geology.service.event_internship.interfaces.IEntrepriseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EntrepriseService implements IEntrepriseService {

    private EntrepriseRepository entrepriseRepo;

    @Override
    public List<Entreprise> getAllEntreprises(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Entreprise> events = entrepriseRepo.findAll(pageable);
        return  events.stream().collect(Collectors.toList());

    }

    @Override
    public Entreprise getEntreprise(long id) {
        return entrepriseRepo.findById(id)
                .orElseThrow(()->new EntrepriseNotFoundException("entreprise couldn't be found"));
    }

    @Override
    public Entreprise createEntreprise(Entreprise entreprise) {
        if(entrepriseRepo.existsByName(entreprise.getName())){
            throw new EntrepriseExistsAlready("this entreprise exists already");
        }
        Entreprise ent = new Entreprise();
        ent.setUrl(entreprise.getUrl());
        ent.setName(entreprise.getName());
        return entrepriseRepo.save(ent);
    }

    @Override
    public Entreprise updateEntreprise(long id, Entreprise entreprise) {
        Entreprise ent=entrepriseRepo.findById(id)
                .orElseThrow(()->new EntrepriseNotFoundException("entreprise couldn't be found"));
        ent.setName(entreprise.getName());
        ent.setUrl(entreprise.getUrl());
        return ent;
    }

    @Override
    public void deleteEntreprise(long id) {
        Entreprise ent = entrepriseRepo.findById(id)
                .orElseThrow(()->new EntrepriseNotFoundException("entreprise couldn't be found"));
        entrepriseRepo.delete(ent);
    }
}
