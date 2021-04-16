package main.java.engimon;

import main.java.element.Element;
import main.java.exception.*;
import main.java.skill.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Breeding_Fountain {

    /**
     * Melakukan perkawinan antar Engimon
     * @param parentA
     * @param parentB
     * @return Engimon hasil perkawinan
     * @throws Exception Unexpected Error, gagal breeding
     * @throws InvalidEngimonLevelException Level Engimon tidak cukup
     */
    public static Engimon startBreeding(Engimon parentA, Engimon parentB) throws InvalidEngimonLevelException, Exception {
        if (parentA.getLevel() < 4 || parentB.getLevel() < 4) {
            // Throw Exception
            throw new InvalidEngimonLevelException();
        }
        // Kurangi level Parent
        parentA.setLevel(parentA.getLevel() - 3);
        parentB.setLevel(parentB.getLevel() - 3);

        // Create Parent Object
        Parent ortu = new Parent(parentA.getName(), parentA.getSpecies(), parentB.getName(), parentB.getSpecies());
        
        // Cari skill yang mungkin bisa diwariskan
        List<Skill> calonSkill = sortingSkills(parentA, parentB);

        // Random module
        Random rand = new Random();

        try {
            if (isElementSimilar(parentA, parentB)) {
            /* Jika elemen kedua parent sama, anak akan memiliki elemen yang sama dengan kedua parent. 
                Spesies anak dipilih dari parent A atau parent B secara bebas (boleh random atau aturan 
                spesifik tertentu). */
                int choice = rand.nextInt(2);
                if (choice == 0) {
                    Engimon anak = Engidex.getEngimonBySpecies(parentA.getSpecies()).cloneEngimon();
                    initAnak(anak, ortu, calonSkill);
                    return anak;
                } else {
                    Engimon anak = Engidex.getEngimonBySpecies(parentB.getSpecies()).cloneEngimon();
                    initAnak(anak, ortu, calonSkill);
                    return anak;
                }
            } else {
            /* Jika elemen kedua parent berbeda maka anak akan memiliki elemen dan spesies dari elemen 
                yang memiliki element advantage yang lebih tinggi. */
                double elAdvA = Element.advantage(parentA.getElement(), parentB.getElement());
                double elAdvB = Element.advantage(parentB.getElement(), parentA.getElement());
                if (elAdvA > elAdvB) {
                    Engimon anak = Engidex.getEngimonBySpecies(parentA.getSpecies()).cloneEngimon();
                    initAnak(anak, ortu, calonSkill);
                    return anak;
                } else if (elAdvA < elAdvB) {
                    Engimon anak = Engidex.getEngimonBySpecies(parentB.getSpecies()).cloneEngimon();
                    initAnak(anak, ortu, calonSkill);
                    return anak;
                } else {
                /* eleAdv_a == eleAdv_b */
                /* Jika elemen kedua parent berbeda dan kedua elemen memiliki element advantage yang sama, 
                    maka anak akan memiliki spesies berbeda dari kedua parent yang memiliki kedua elemen parent 
                    (boleh dipilih random atau hardcoded). */
                    List<Element> listEl = sortElementAdv(parentA, parentB);
    
                    Engimon anak = Engidex.getEngimonByElement(listEl.get(0), listEl.get(1)).cloneEngimon();
                    initAnak(anak, ortu, calonSkill);
                    return anak;
    
                }
    
            } 
        } catch (Exception e) {
            // Unexpected Error
            parentA.setLevel(parentA.getLevel() + 3);
            parentB.setLevel(parentB.getLevel() + 3);

            System.out.println(e.getMessage());
            throw e;
        }

    }

    /**
     * Inisialisasi parameter anak
     * @param anak
     * @param ortu
     * @param calonSkill
     */
    private static void initAnak(Engimon anak, Parent ortu, List<Skill> calonSkill) {
        anak.setParent(ortu);
        anak.setLivesIsWild(false);
        addSkillAnak(anak, calonSkill);
    }

    /**
     * Menyusun skill yang dapat diwariskan kepada pokemon hasil breeding
     * @param parentA
     * @param parentB
     * @return List skill yang sudah disusuh berdasarkan ketentuan
     */
    private static List<Skill> sortingSkills(Engimon parentA, Engimon parentB) {
        List<Skill> skillParentA = parentA.getSkill();
        List<Skill> skillParentB = parentB.getSkill();
        List<Skill> skillChild = new ArrayList<>();

        // Tambah skill dari Parent A
        for (Skill skill : skillParentA) {
            skillChild.add(new Skill(skill));
        }

        // Tambah skill dari Parent B
        for (Skill skill : skillParentB) {
            int idx = skillChild.indexOf(skill); 
            if (idx == -1) {    // Skill belum ditemukan
                skillChild.add(new Skill(skill));
            } else {            // Skill sudah ada ditemukan
                if (skill.getMasteryLevel() == skillChild.get(idx).getMasteryLevel()) { // Jika mastery level sama, coba naikkan
                    skillChild.get(idx).levelUp();   
                } else if (skill.getMasteryLevel() > skillChild.get(idx).getMasteryLevel()) { // Jika tidak, maka ambil yang terbesar
                    skillChild.set(idx, new Skill(skill));
                }
            }

        }

        return skillChild.stream()
            .sorted((s1,s2) -> s2.getMasteryLevel().compareTo(s1.getMasteryLevel()))
            .collect(Collectors.toList());
    }


    /**
     * Menambah calonSkill ke child
     * @param child
     * @param calonSkill
     */
    private static void addSkillAnak(Engimon child, List<Skill> calonSkill) {
        int i = 0;
        List<Skill> childSkill = child.getSkill();

        // Selama skill anak belum 4 dan masih ada skill yang tersedia
        while (child.getSkill().size() <= 4 && i < calonSkill.size()) {
            Skill sk = calonSkill.get(i);
            int idx = childSkill.indexOf(sk);
            if (idx == -1) {
                // Belum ada skill
                child.addSkill(new Skill(sk));
            } else {
                // Tambah Level
                while (child.getSkill().get(idx).getMasteryLevel() < sk.getMasteryLevel()) {
                    child.getSkill().get(idx).levelUp();
                }  
            }
            i++;
        }
    }

    /**
     * Mengecek apakah ada element antar parent yang sama
     * @param parentA
     * @param parentB
     * @return True jika ada element yang sama
     */
    private static boolean isElementSimilar(Engimon parentA, Engimon parentB) {
        List<Element> elParentA = parentA.getElement();
        List<Element> elParentB = parentB.getElement();

        for (Element el : elParentA) {
            if (elParentB.contains(el)) {
                return true;
            }            
        }
        return false;
    }

    
    /**
     * Mengurutkan element yang dimiliki parent berdasarkan Advantagenya
     * @param parentA
     * @param parentB
     * @return list of Element yang sudah diurut
     */
    private static List<Element> sortElementAdv(Engimon parentA, Engimon parentB) {
        List<Element> elParentA = parentA.getElement();
        List<Element> elParentB = parentB.getElement();
        Map<Element,Double> elChild = new HashMap<>();

        double elAdv;
        // Hitung Element Advantage parent A
        for (Element elA : elParentA) {
            elAdv = Element.advantage(elA, elParentB);
            elChild.put(elA, elAdv);
        }

        // Hitung Element Advantage parent B
        for (Element elB : elParentB) {
            elAdv = Element.advantage(elB, elParentA);
            if (elChild.containsKey(elB) && elAdv > elChild.get(elB)) {
                elChild.replace(elB, elAdv);
            } else {
                elChild.put(elB, elAdv);
            }
        }

        // Ubah ke List Entry
        List<Entry<Element, Double>> listEl = new ArrayList<>(elChild.entrySet());

        // Return list key
        return listEl.stream()
            .sorted((s1,s2) -> s2.getValue().compareTo(s1.getValue()))
            .map(Entry::getKey)
            .collect(Collectors.toList());

    }





}
