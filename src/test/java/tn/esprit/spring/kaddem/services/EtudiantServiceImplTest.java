package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);

        // Mock the behavior of the repository to return a List
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant));  // Use Arrays.asList for Java 8 compatibility

        // Act
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();  // Ensure the result is a List

        // Assert
        assertNotNull(result);  // Ensure the result is not null
        assertEquals(1, result.size());  // Ensure the list contains exactly one student
        verify(etudiantRepository, times(1)).findAll();  // Verify that findAll was called once
    }

    @Test
    void testAddEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);

        // Mock the behavior of the repository to return the student
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Assert
        assertNotNull(result);  // Ensure the result is not null
        assertEquals(1, result.getIdEtudiant());  // Ensure the student ID matches
        verify(etudiantRepository, times(1)).save(etudiant);  // Verify that save was called once
    }

    // Additional tests for other methods can be added similarly...
}
