import com.dosmakhambetbaktiyar.model.Specialty;
import com.dosmakhambetbaktiyar.model.Status;
import com.dosmakhambetbaktiyar.repository.SpecialtyRepository;
import com.dosmakhambetbaktiyar.service.SpecialtyService;
import com.dosmakhambetbaktiyar.service.impl.SpecialtyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpecialtyServiceTest {

    private SpecialtyService service;

    @Mock
    private SpecialtyRepository repository;

    private Specialty specialty1 = new Specialty(1L,"Developer");
    private Specialty specialty2 = new Specialty(2L,"JavaDeveloper");
    private Specialty specialty3 = new Specialty(5L,"SeniorDeveloper");


    @Before
    public void setUp(){
        service = new SpecialtyServiceImpl(repository);
    }

    @Test
    public void create(){

        when(service.create(any(Specialty.class))).thenReturn(specialty1);

        Specialty specialty = service.create(new Specialty("Tester"));

        assertEquals(1L,specialty.getId().longValue());
        assertEquals("Developer",specialty.getName());
        assertEquals(Status.ACTIVE,specialty.getStatus());
    }

    @Test
    public void get(){
        when(service.get(anyLong())).thenReturn(specialty3);

        Specialty specialty = service.get(0L);

        assertEquals(5L,specialty.getId().longValue());
        assertEquals("SeniorDeveloper",specialty.getName());
    }


    @Test
    public void getAll() {
        when(service.getAll()).thenReturn(getSpecialties());

        List<Specialty> specialties = service.getAll();

        assertEquals(3,specialties.size());
        assertEquals("SeniorDeveloper",specialties.get(2).getName());
        assertEquals(Status.ACTIVE, specialties.get(0).getStatus());
    }

    @Test
    public void update(){
        when(service.update(any(Specialty.class))).thenReturn(specialty2);

        Specialty specialty = service.update(new Specialty("ManualTester"));

        assertEquals("JavaDeveloper",specialty.getName());
        assertEquals(Status.ACTIVE,specialty.getStatus());
    }


    @Test
    public void delete(){
        when(service.delete(anyLong())).thenReturn(true);

        boolean ok = service.delete(3L);
        assertTrue(ok);
    }

    private List<Specialty> getSpecialties(){
        List<Specialty> specialties = new ArrayList<>();

        specialties.add(specialty1);
        specialties.add(specialty2);
        specialties.add(specialty3);

        return specialties;
    }
}
