import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    private static final Logger logger = LogManager.getLogger(DepartementServiceImplTest.class);

    @BeforeEach
    public void setUp() {
        logger.debug("Setting up the test environment.");
    }

    @Test
    public void testRetrieveAllDepartements() {
        // Given
        logger.info("Starting testRetrieveAllDepartements.");
        when(departementRepository.findAll()).thenReturn(List.of(new Departement(), new Departement()));

        // When
        List<Departement> departements = departementService.retrieveAllDepartements();

        // Then
        assertNotNull(departements);
        assertEquals(2, departements.size());
        verify(departementRepository, times(1)).findAll();
        logger.info("Tested retrieveAllDepartements successfully with {} departements.", departements.size());
    }

    @Test
    public void testAddDepartement() {
        logger.info("Starting testAddDepartement.");
        Departement departement = new Departement();
        when(departementRepository.save(departement)).thenReturn(departement);

        Departement result = departementService.addDepartement(departement);

        assertNotNull(result);
        verify(departementRepository, times(1)).save(departement);
        logger.info("Tested addDepartement successfully.");
    }

    @Test
    public void testRetrieveDepartement() {
        logger.info("Starting testRetrieveDepartement.");
        Departement departement = new Departement();
        departement.setIdDepart(1);
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        Departement result = departementService.retrieveDepartement(1);

        assertNotNull(result);
        assertEquals(1, result.getIdDepart());
        logger.info("Tested retrieveDepartement successfully for id: {}.", result.getIdDepart());
    }

    @Test
    public void testDeleteDepartement() {
        logger.info("Starting testDeleteDepartement.");
        Departement departement = new Departement();
        departement.setIdDepart(1);
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        departementService.deleteDepartement(1);

        verify(departementRepository, times(1)).delete(departement);
        logger.info("Tested deleteDepartement successfully for id: {}.", departement.getIdDepart());
    }

    @Test
    public void testDeleteDepartementWithError() {
        logger.info("Starting testDeleteDepartementWithError.");
        when(departementRepository.findById(1)).thenReturn(Optional.empty());

        try {
            departementService.deleteDepartement(1);
            fail("Expected exception was not thrown.");
        } catch (Exception e) {
            logger.error("Error during deletion of departement with id 1: {}", e.getMessage());
        }
    }

}

