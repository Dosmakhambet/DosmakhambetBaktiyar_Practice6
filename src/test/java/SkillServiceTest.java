import com.dosmakhambetbaktiyar.model.Skill;
import com.dosmakhambetbaktiyar.model.Status;
import com.dosmakhambetbaktiyar.repository.SkillRepository;
import com.dosmakhambetbaktiyar.service.impl.SkillServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {

    @InjectMocks
    private SkillServiceImpl service;

    @Mock
    private SkillRepository skillRepository;

    private Skill getSkill1(){
        return new Skill(1L,"Test");
    }

    private Skill getSkill2(){
        return new Skill(2L,"Test2");
    }

    private Skill getSkill3(){
        return new Skill(10L,"Cook");
    }


    @Test
    public void create() {

        when(skillRepository.create(any(Skill.class))).thenReturn(getSkill1());

        Skill skill = service.create(new Skill("Clean"));
        assertEquals(1L,skill.getId().longValue());
        assertEquals("Test",skill.getName());
        assertEquals(Status.ACTIVE,skill.getStatus());
    }

    @Test
    public void get(){

        when(skillRepository.get(anyLong())).thenReturn(getSkill2());

        Skill skill = service.get(10L);

        assertEquals(2L,skill.getId().longValue());
        assertEquals("Test2",skill.getName());
        assertEquals(Status.ACTIVE, skill.getStatus());
    }


    @Test
    public void getAll() {
        List<Skill> skills = getSkills();

        when(skillRepository.getAll()).thenReturn(skills);

        List<Skill> skillList = service.getAll();

        assertEquals(3,skillList.size());
        assertEquals(Status.ACTIVE, skillList.get(2).getStatus());
    }

    @Test
    public void update() {
        when(skillRepository.update(any(Skill.class))).thenReturn(getSkill3());

        Skill skill = service.update(getSkill1());

        assertEquals(10L,skill.getId().longValue());
        assertEquals("Cook",skill.getName());
        assertEquals(Status.ACTIVE, skill.getStatus());
    }


    @Test
    public void delete() {
       when(skillRepository.delete(anyLong())).thenReturn(true);

       boolean ok = service.delete(getSkill1().getId());

       assertTrue(ok);
    }

    private List<Skill> getSkills(){
        List<Skill> list = new ArrayList<>();

        list.add(getSkill1());
        list.add(getSkill2());
        list.add(getSkill3());

        return list;
    }
}
