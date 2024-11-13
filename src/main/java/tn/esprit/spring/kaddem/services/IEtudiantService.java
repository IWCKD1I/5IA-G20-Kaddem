package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.Etudiant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EtudiantServiceImpl implements IEtudiantService {

    private static final Logger logger = LogManager.getLogger(EtudiantServiceImpl.class);

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        logger.info("Retrieving all students.");
        try {
            // Logic for retrieving all students
            List<Etudiant> etudiants = // your logic here
            logger.info("Successfully retrieved all students.");
            return etudiants;
        } catch (Exception e) {
            logger.error("Error retrieving students", e);
            throw new RuntimeException("Error retrieving students", e);
        }
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        logger.info("Adding a new student: {}", e);
        try {
            // Logic for adding student
            Etudiant addedEtudiant = // your logic here
            logger.info("Successfully added student: {}", addedEtudiant);
            return addedEtudiant;
        } catch (Exception ex) {
            logger.error("Error adding student: {}", e, ex);
            throw new RuntimeException("Error adding student", ex);
        }
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        logger.info("Updating student: {}", e);
        try {
            // Logic for updating student
            Etudiant updatedEtudiant = // your logic here
            logger.info("Successfully updated student: {}", updatedEtudiant);
            return updatedEtudiant;
        } catch (Exception ex) {
            logger.error("Error updating student: {}", e, ex);
            throw new RuntimeException("Error updating student", ex);
        }
    }

    @Override
    public Etudiant retrieveEtudiant(Integer idEtudiant) {
        logger.debug("Retrieving student with ID: {}", idEtudiant);
        try {
            // Logic for retrieving a student by ID
            Etudiant etudiant = // your logic here
            logger.info("Successfully retrieved student: {}", etudiant);
            return etudiant;
        } catch (Exception e) {
            logger.error("Error retrieving student with ID: {}", idEtudiant, e);
            throw new RuntimeException("Error retrieving student", e);
        }
    }

    @Override
    public void removeEtudiant(Integer idEtudiant) {
        logger.info("Removing student with ID: {}", idEtudiant);
        try {
            // Logic for removing student
            // your logic here
            logger.info("Successfully removed student with ID: {}", idEtudiant);
        } catch (Exception e) {
            logger.error("Error removing student with ID: {}", idEtudiant, e);
            throw new RuntimeException("Error removing student", e);
        }
    }

    @Override
    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
        logger.debug("Assigning student with ID: {} to department with ID: {}", etudiantId, departementId);
        try {
            // Logic for assigning student to department
            // your logic here
            logger.info("Successfully assigned student with ID: {} to department with ID: {}", etudiantId, departementId);
        } catch (Exception e) {
            logger.error("Error assigning student with ID: {} to department with ID: {}", etudiantId, departementId, e);
            throw new RuntimeException("Error assigning student", e);
        }
    }

    @Override
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
        logger.info("Adding student and assigning to team and contract. Student: {}, Contract ID: {}, Team ID: {}", e, idContrat, idEquipe);
        try {
            // Logic for adding and assigning student to team and contract
            Etudiant addedEtudiant = // your logic here
            logger.info("Successfully added student and assigned to team and contract: {}", addedEtudiant);
            return addedEtudiant;
        } catch (Exception ex) {
            logger.error("Error adding and assigning student to team and contract: {}", e, ex);
            throw new RuntimeException("Error adding and assigning student", ex);
        }
    }

    @Override
    public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {
        logger.debug("Retrieving students by department ID: {}", idDepartement);
        try {
            // Logic for retrieving students by department
            List<Etudiant> etudiants = // your logic here
            logger.info("Successfully retrieved students for department ID: {}", idDepartement);
            return etudiants;
        } catch (Exception e) {
            logger.error("Error retrieving students for department ID: {}", idDepartement, e);
            throw new RuntimeException("Error retrieving students", e);
        }
    }
}
