import com.dosmakhambetbaktiyar.model.Specialty;
import com.dosmakhambetbaktiyar.model.Status;
import com.dosmakhambetbaktiyar.repository.SpecialtyRepository;
import com.dosmakhambetbaktiyar.service.SpecialtyService;
import com.dosmakhambetbaktiyar.service.impl.SpecialtyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private SpecialtyServiceImpl service;

    @Mock
    private SpecialtyRepository repository;

    private Specialty getSpecialty1(){
        return new Specialty(1L,"Developer");
    }

    private Specialty getSpecialty2(){
        return new Specialty(2L,"JavaDeveloper");
    }

    private Specialty getSpecialty3(){
        return new Specialty(5L,"SeniorDeveloper");
    }
    @Test
    public void create(){

        when(repository.create(any(Specialty.class))).thenReturn(getSpecialty1());

        Specialty specialty = service.create(new Specialty("Tester"));

        assertEquals(1L,specialty.getId().longValue());
        assertEquals("Developer",specialty.getName());
        assertEquals(Status.ACTIVE,specialty.getStatus());
    }

    @Test
    public void get(){
        when(repository.get(anyLong())).thenReturn(getSpecialty3());

        Specialty specialty = service.get(0L);

        assertEquals(5L,specialty.getId().longValue());
        assertEquals("SeniorDeveloper",specialty.getName());
    }


    @Test
    public void getAll() {
        when(repository.getAll()).thenReturn(getSpecialties());

        List<Specialty> specialties = service.getAll();

        assertEquals(3,specialties.size());
        assertEquals("SeniorDeveloper",specialties.get(2).getName());
        assertEquals(Status.ACTIVE, specialties.get(0).getStatus());
    }

    @Test
    public void update(){
        when(repository.update(any(Specialty.class))).thenReturn(getSpecialty2());

        Specialty specialty = service.update(new Specialty(10L,"ManualTester"));

        assertEquals("JavaDeveloper",specialty.getName());
        assertEquals(Status.ACTIVE,specialty.getStatus());
    }


    @Test
    public void delete(){
        when(repository.delete(anyLong())).thenReturn(true);

        boolean ok = service.delete(3L);
        assertTrue(ok);
    }

    private List<Specialty> getSpecialties(){
        List<Specialty> specialties = new ArrayList<>();

        specialties.add(getSpecialty1());
        specialties.add(getSpecialty2());
        specialties.add(getSpecialty3());

        return specialties;
    }
}
