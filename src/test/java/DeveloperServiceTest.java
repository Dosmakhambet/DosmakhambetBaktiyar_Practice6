import com.dosmakhambetbaktiyar.model.Developer;
import com.dosmakhambetbaktiyar.model.Skill;
import com.dosmakhambetbaktiyar.model.Specialty;
import com.dosmakhambetbaktiyar.repository.DeveloperRepository;
import com.dosmakhambetbaktiyar.service.DeveloperService;
import com.dosmakhambetbaktiyar.service.impl.DeveloperServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    private DeveloperService service;

    @Mock
    private DeveloperRepository repository;

    private Specialty specialty1 = new Specialty(1L,"Developer");
    private Specialty specialty2 = new Specialty(2L,"JavaDeveloper");

    private Developer developer1 = new Developer(1L,"Ivan","Petrov");
    private Developer developer2 = new Developer(2L,"Igor","Igorov",specialty2, Sets.newSet(new Skill(1L, "Delegate")));
    private Developer developer3 = new Developer(10L,"Inna","Volkova",specialty1, Sets.newSet(new Skill(1L,"Coding"),new Skill(3L,"Testing")));


    @Before
    public void setUp(){
        service = new DeveloperServiceImpl(repository);
    }

    @Test
    public void create(){
        when(service.create(any(Developer.class))).thenReturn(developer2);

        Developer developer = service.create(new Developer(2L,"Bill","Gates"));

        assertEquals(2L,developer.getId().longValue());
        assertEquals(specialty2,developer.getSpecialty());
        assertEquals("Igor",developer.getFirstName());
        assertEquals("Igorov",developer.getLastName());
        assertEquals(1,developer.getSkills().size());
    }

    @Test
    public void get(){
        when(service.get(anyLong())).thenReturn(developer3);

        Developer developer = service.get(2L);

        assertEquals(10,developer.getId().intValue());
        assertEquals(specialty1,developer.getSpecialty());
        assertEquals("Inna",developer.getFirstName());
        assertEquals("Volkova",developer.getLastName());
        assertEquals(2,developer.getSkills().size());
        assertEquals("Coding",developer.getSkills().iterator().next().getName());
    }


    @Test
    public void getAll(){


        when(service.getAll()).thenReturn(getDevelopers());

        List<Developer> developers = service.getAll();

        assertEquals(3,developers.size());
        assertNull(developers.get(0).getSpecialty());
        assertNull(developers.get(0).getSkills());
    }

    @Test
    public void update(){
        when(service.update(any(Developer.class))).thenReturn(developer1);

        Developer developer = service.update(new Developer(5L,"Jah","Khalib"));

        assertEquals(1L,developer.getId().longValue());
        assertEquals("Ivan",developer.getFirstName());
        assertEquals("Petrov",developer.getLastName());
    }


    @Test
    public void delete(){
        when(service.delete(anyLong())).thenReturn(true);

        boolean ok = service.delete(3L);

        assertTrue(ok);
    }

    private List<Developer> getDevelopers(){
        List<Developer> developers = new ArrayList<>();
        developers.add(developer1);
        developers.add(developer2);
        developers.add(developer3);

        return developers;
    }
}
