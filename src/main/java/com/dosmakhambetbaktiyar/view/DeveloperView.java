package com.dosmakhambetbaktiyar.view;

import com.dosmakhambetbaktiyar.controller.DeveloperController;
import com.dosmakhambetbaktiyar.model.Developer;
import com.dosmakhambetbaktiyar.model.Skill;
import com.dosmakhambetbaktiyar.model.Specialty;
import com.dosmakhambetbaktiyar.repository.impl.SkillRepositoryImpl;
import com.dosmakhambetbaktiyar.repository.impl.SpecialtyRepositoryImpl;

import java.util.HashSet;
import java.util.Scanner;

public class DeveloperView {

    private SkillRepositoryImpl skillRepository;
    private SpecialtyRepositoryImpl specialtyRepository;

    public DeveloperView(SkillRepositoryImpl skillRepository,
                         SpecialtyRepositoryImpl specialtyRepository){
        this.skillRepository = skillRepository;
        this.specialtyRepository = specialtyRepository;
    }

    public void menu(Scanner in, DeveloperController controller){

        byte b;

        do{
            System.out.println("Мы в меню Developer");
            System.out.println("1. Создать запись Developer");
            System.out.println("2. Удалить запись");
            System.out.println("3. Все запись");
            System.out.println("4. Запись по id");
            System.out.println("5. Обновить запись");
            System.out.println("6. Выход ");
            b = in.nextByte();

            switch ((int)b){
                case 1:
                    System.out.print("Напиши имя : ");
                    String firstName = in.next();
                    System.out.print("Напиши фамилию : ");
                    String lastName = in.next();
                    HashSet<Skill> skills = new HashSet<>();
                    skillRepository.getAll().forEach((a) -> System.out.println(a.toString()));
                    System.out.print("Сколько Skills: ");
                    Long n = in.nextLong();
                    for(int i =0 ; i < n; i ++){
                        System.out.print("Введите id skill: ");
                        skills.add(skillRepository.get(in.nextLong()));
                    }
                    specialtyRepository.getAll().forEach((a) -> System.out.println(a.toString()));
                    System.out.print("Введите id specialty: ");
                    Long m = in.nextLong();
                    Specialty specialty = specialtyRepository.get(m);
                    Developer newDeveloper = controller.create(new Developer(firstName,lastName,specialty,skills));
                    System.out.println(newDeveloper);
                    break;
                case 2:
                    System.out.print("Напиши id : ");
                    Long id = in.nextLong();
                    if(controller.delete(id)){
                        System.out.println("Удаление записи прошла успешно");
                    }else{
                        System.out.println("Удаление записи провалилось, попробуйте снова");
                    }
                    break;
                case 3:
                    System.out.println("Все записи  : ");
                    controller.getAll().forEach((a) -> System.out.println(a.toString()));
                    break;
                case 4:
                    System.out.print("Напиши id : ");
                    Long getId = in.nextLong();
                    Developer developer = controller.get(getId);

                    if(developer == null)
                        System.out.println("Нету записи по данному id " + getId);
                    else
                        System.out.println(developer);
                    break;
                case 5:
                    System.out.print("Напиши id : ");
                    Long iD = in.nextLong();
                    Developer dev = controller.get(iD);

                    if(dev == null)
                        System.out.println("Нету записи по данному id " + iD);
                    else {
                        System.out.println(dev);
                        System.out.print("Введите имя : ");
                        String str = in.next();
                        dev.setFirstName(str);
                        System.out.print("Введите фамилия : ");
                        String str2 = in.next();
                        dev.setLastName(str2);

                        HashSet<Skill> skills1 = new HashSet<>();
                        skillRepository.getAll().forEach((a) -> System.out.println(a.toString()));
                        System.out.print("Сколько Skills: ");
                        Long n1 = in.nextLong();
                        for(int i =0 ; i < n1; i ++){
                            System.out.print("Введите id skill: ");
                            skills1.add(skillRepository.get(in.nextLong()));
                        }
                        dev.setSkills(skills1);
                        specialtyRepository.getAll().forEach((a) -> System.out.println(a.toString()));
                        System.out.print("Введите id specialty: ");
                        Long m1 = in.nextLong();
                        Specialty specialty1 = specialtyRepository.get(m1);
                        dev.setSpecialty(specialty1);
                        Developer updatedDeveloper = controller.update(dev);
                        System.out.println(updatedDeveloper);
                    }
                    break;
                default:
                    System.out.println("Выход 6");
                    break;
            }

        } while (((int) b != 6));
    }
}
