package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.Etudiant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EtudiantServiceTest {

    @Mock
    private IEtudiantRepository etudiantRepository; // Remplacer par votre classe de repository

    @InjectMocks
    private EtudiantService etudiantService; // La classe à tester

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        // Initialisation des objets avant chaque test
        etudiant = new Etudiant(1, "Mohamed Malek", "Gritli", "mohamed@example.com");
    }

    @Test
    void testAddEtudiant() {
        // Mock de la méthode save
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        Etudiant result = etudiantService.addEtudiant(etudiant);
        
        assertNotNull(result);
        assertEquals("Mohamed Malek", result.getPrenom());
        verify(etudiantRepository, times(1)).save(etudiant); // Vérifier si save a été appelé
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Mock de la méthode findAll
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant));

        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        
        assertNotNull(etudiants);
        assertEquals(1, etudiants.size());
        assertEquals("Mohamed Malek", etudiants.get(0).getPrenom());
        verify(etudiantRepository, times(1)).findAll(); // Vérifier si findAll a été appelé
    }

    @Test
    void testRetrieveEtudiant() {
        // Mock de la méthode findById
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.retrieveEtudiant(1);

        assertNotNull(result);
        assertEquals("Mohamed Malek", result.getPrenom());
        verify(etudiantRepository, times(1)).findById(1); // Vérifier si findById a été appelé
    }

    @Test
    void testUpdateEtudiant() {
        // Mock de la méthode save pour l'update
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        etudiant.setPrenom("Malek");
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        assertNotNull(updatedEtudiant);
        assertEquals("Malek", updatedEtudiant.getPrenom());
        verify(etudiantRepository, times(1)).save(etudiant); // Vérifier si save a été appelé pour l'update
    }

    @Test
    void testRemoveEtudiant() {
        // Test de la suppression
        doNothing().when(etudiantRepository).deleteById(1);

        etudiantService.removeEtudiant(1);

        verify(etudiantRepository, times(1)).deleteById(1); // Vérifier si deleteById a été appelé
    }

    @Test
    void testAssignEtudiantToDepartement() {
        // Assumer que la méthode assignation fonctionne sans erreur
        doNothing().when(etudiantRepository).assignEtudiantToDepartement(anyInt(), anyInt());

        etudiantService.assignEtudiantToDepartement(1, 1);

        verify(etudiantRepository, times(1)).assignEtudiantToDepartement(1, 1); // Vérifier l'appel de la méthode
    }

    @Test
    void testGetEtudiantsByDepartement() {
        // Mock de la méthode findByDepartementId
        when(etudiantRepository.getEtudiantsByDepartement(1)).thenReturn(Arrays.asList(etudiant));

        List<Etudiant> etudiants = etudiantService.getEtudiantsByDepartement(1);

        assertNotNull(etudiants);
        assertEquals(1, etudiants.size());
        assertEquals("Mohamed Malek", etudiants.get(0).getPrenom());
        verify(etudiantRepository, times(1)).getEtudiantsByDepartement(1); // Vérifier l'appel de la méthode
    }
}
