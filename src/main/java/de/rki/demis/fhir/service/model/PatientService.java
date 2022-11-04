package de.rki.demis.fhir.service.model;

import de.rki.demis.fhir.model.PatientMod;
//import de.rki.demis.fhir.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class PatientService {
//    private final PatientRepository repository;
//
//
//    public List<PatientMod> listAll() {
//        return repository.findAll();
//    }


}
