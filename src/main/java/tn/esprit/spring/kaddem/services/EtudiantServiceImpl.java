package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EtudiantServiceImpl implements IEtudiantService {

	@Autowired
	EtudiantRepository etudiantRepository;

	@Autowired
	ContratRepository contratRepository;

	@Autowired
	EquipeRepository equipeRepository;

	@Autowired
	DepartementRepository departementRepository;

	public List<Etudiant> retrieveAllEtudiants() {
		log.info("Retrieving all students.");
		List<Etudiant> etudiants = new ArrayList<>();
		etudiantRepository.findAll().forEach(etudiants::add);  // Converting Iterable to List
		log.debug("Number of students retrieved: {}", etudiants.size());
		return etudiants;
	}

	public Etudiant addEtudiant(Etudiant e) {
		log.info("Adding a new student: {}", e);
		Etudiant savedEtudiant = etudiantRepository.save(e);
		log.debug("Student added with ID: {}", savedEtudiant.getIdEtudiant());
		return savedEtudiant;
	}

	public Etudiant updateEtudiant(Etudiant e) {
		log.info("Updating student with ID: {}", e.getIdEtudiant());
		Etudiant updatedEtudiant = etudiantRepository.save(e);
		log.debug("Student updated: {}", updatedEtudiant);
		return updatedEtudiant;
	}

	public Etudiant retrieveEtudiant(Integer idEtudiant) {
		log.info("Retrieving student with ID: {}", idEtudiant);
		Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);
		if (etudiant == null) {
			log.error("Student with ID {} not found", idEtudiant);
		} else {
			log.debug("Student retrieved: {}", etudiant);
		}
		return etudiant;
	}

	public void removeEtudiant(Integer idEtudiant) {
		log.info("Removing student with ID: {}", idEtudiant);
		Etudiant e = retrieveEtudiant(idEtudiant);
		if (e != null) {
			etudiantRepository.delete(e);
			log.info("Student with ID {} removed", idEtudiant);
		} else {
			log.error("Failed to remove student: student with ID {} does not exist", idEtudiant);
		}
	}

	public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
		log.info("Assigning student with ID {} to department ID {}", etudiantId, departementId);
		Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
		Departement departement = departementRepository.findById(departementId).orElse(null);

		if (etudiant != null && departement != null) {
			etudiant.setDepartement(departement);
			etudiantRepository.save(etudiant);
			log.debug("Student with ID {} assigned to department ID {}", etudiantId, departementId);
		} else {
			log.error("Failed to assign: student or department not found (Student ID: {}, Department ID: {})", etudiantId, departementId);
		}
	}

	@Transactional
	public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
		log.info("Adding and assigning student to contract ID {} and team ID {}", idContrat, idEquipe);
		Contrat c = contratRepository.findById(idContrat).orElse(null);
		Equipe eq = equipeRepository.findById(idEquipe).orElse(null);

		if (c != null && eq != null) {
			c.setEtudiant(e);
			eq.getEtudiants().add(e);
			etudiantRepository.save(e);
			log.debug("Student assigned to contract ID {} and team ID {}", idContrat, idEquipe);
		} else {
			log.error("Failed to add and assign: contract or team not found (Contract ID: {}, Team ID: {})", idContrat, idEquipe);
		}
		return e;
	}

	public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {
		log.info("Retrieving students for department ID {}", idDepartement);
		List<Etudiant> etudiants = etudiantRepository.findEtudiantsByDepartement_IdDepart(idDepartement);
		log.debug("Number of students in department {}: {}", idDepartement, etudiants.size());
		return etudiants;
	}
}
