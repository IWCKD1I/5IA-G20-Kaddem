package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant()); // Ajoutez des objets Etudiant si nécessaire
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();
        assertEquals(1, result.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.addEtudiant(etudiant);
        assertNotNull(result);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testUpdateEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.updateEtudiant(etudiant);
        assertNotNull(result);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testRetrieveEtudiant() {
        int id = 1;
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.retrieveEtudiant(id);
        assertNotNull(result);
        verify(etudiantRepository, times(1)).findById(id);
    }

    @Test
    void testRemoveEtudiant() {
        int id = 1;
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant));

        etudiantService.removeEtudiant(id);
        verify(etudiantRepository, times(1)).delete(etudiant);
    }

    @Test
    void testAssignEtudiantToDepartement() {
        int etudiantId = 1;
        int departementId = 1;
        Etudiant etudiant = new Etudiant();
        Departement departement = new Departement();

        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));

        etudiantService.assignEtudiantToDepartement(etudiantId, departementId);

        verify(etudiantRepository, times(1)).save(etudiant);
        assertEquals(departement, etudiant.getDepartement());
    }

    @Test
    void testAddAndAssignEtudiantToEquipeAndContract() {
        int contratId = 1;
        int equipeId = 1;
        Etudiant etudiant = new Etudiant();
        Contrat contrat = new Contrat();
        Equipe equipe = new Equipe();

        when(contratRepository.findById(contratId)).thenReturn(Optional.of(contrat));
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, contratId, equipeId);

        verify(etudiantRepository, times(1)).save(etudiant);
        assertEquals(etudiant, contrat.getEtudiant());
        assertTrue(equipe.getEtudiants().contains(etudiant));
    }

    @Test
    void testGetEtudiantsByDepartement() {
        int departementId = 1;
        List<Etudiant> etudiants = new ArrayList<>();
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(departementId)).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(departementId);
        assertNotNull(result);
        verify(etudiantRepository, times(1)).findEtudiantsByDepartement_IdDepart(departementId);
    }
}
