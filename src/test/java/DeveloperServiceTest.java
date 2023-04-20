import com.dosmakhambetbaktiyar.model.Developer;
import com.dosmakhambetbaktiyar.model.Skill;
import com.dosmakhambetbaktiyar.model.Specialty;
import com.dosmakhambetbaktiyar.repository.DeveloperRepository;
import com.dosmakhambetbaktiyar.service.impl.DeveloperServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperServiceTest {

    @InjectMocks
    private DeveloperServiceImpl service;

    @Mock
    private DeveloperRepository repository;

    private Developer getDeveloper1(){
        return new Developer(1L,"Ivan","Petrov");
    }

    private Developer getDeveloper2(){
        return new Developer(2L,"Igor","Igorov",getSpecialty2(), Sets.newSet(new Skill(1L, "Delegate")));
    }

    private Developer getDeveloper3(){
        return new Developer(10L,"Inna","Volkova",getSpecialty1(), Sets.newSet(new Skill(1L,"Coding"),new Skill(3L,"Testing")));
    }

    private Specialty getSpecialty1(){
        return new Specialty(1L,"Developer");
    }

    private Specialty getSpecialty2(){
        return new Specialty(2L,"JavaDeveloper");
    }

    @Test
    public void create(){
        when(repository.create(any(Developer.class))).thenReturn(getDeveloper2());

        Developer developer = service.create(new Developer("Bill","Gates",getSpecialty2()));

        assertEquals(2L,developer.getId().longValue());
        assertEquals(getSpecialty2().getName(),developer.getSpecialty().getName());
        assertEquals("Igor",developer.getFirstName());
        assertEquals("Igorov",developer.getLastName());
        assertEquals(1,developer.getSkills().size());
    }

    @Test
    public void get(){
        when(repository.get(anyLong())).thenReturn(getDeveloper3());

        Developer developer = service.get(2L);

        assertEquals(10,developer.getId().intValue());
        assertEquals(getSpecialty1().getName(),developer.getSpecialty().getName());
        assertEquals("Inna",developer.getFirstName());
        assertEquals("Volkova",developer.getLastName());
        assertEquals(2,developer.getSkills().size());
        assertEquals("Coding",developer.getSkills().iterator().next().getName());
    }


    @Test
    public void getAll(){


        when(repository.getAll()).thenReturn(getDevelopers());

        List<Developer> developers = service.getAll();

        assertEquals(3,developers.size());
        assertNull(developers.get(0).getSpecialty());
        assertNull(developers.get(0).getSkills());
    }

    @Test
    public void update(){
        when(repository.update(any(Developer.class))).thenReturn(getDeveloper1());

        Developer developer = service.update(new Developer(5L,"Jah","Khalib"));

        assertEquals(1L,developer.getId().longValue());
        assertEquals("Ivan",developer.getFirstName());
        assertEquals("Petrov",developer.getLastName());
    }


    @Test
    public void delete(){
        when(repository.delete(anyLong())).thenReturn(true);

        boolean ok = service.delete(3L);

        assertTrue(ok);
    }

    private List<Developer> getDevelopers(){
        List<Developer> developers = new ArrayList<>();
        developers.add(getDeveloper1());
        developers.add(getDeveloper2());
        developers.add(getDeveloper3());

        return developers;
    }
}
