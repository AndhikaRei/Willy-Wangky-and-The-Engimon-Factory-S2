package main.java.engimon;

import main.java.element.Element;
import main.java.skill.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Breeding_Fountain {
    /**
     * Melakukan perkawinan antar Engimon
     * @param parentA
     * @param parentB
     * @return Engimon hasil perkawinan
     * @exception invalidEngimonLevel 
     */
    public static Engimon startBreeding(Engimon parentA, Engimon parentB) {
        if (parentA.getLevel() < 4 || parentB.getLevel() < 4) {
            // Throw Exception
            System.out.println("Exception");
        }

        // Create Parent Object
        

        // Cari skill yang mungkin bisa diwariskan
        List<Skill> calonSkill = sortingSkills(parentA, parentB);

        
        if (isElementSimilar(parentA, parentB)) {
        /* Jika elemen kedua parent sama, anak akan memiliki elemen yang sama dengan kedua parent. 
            Spesies anak dipilih dari parent A atau parent B secara bebas (boleh random atau aturan 
            spesifik tertentu). */   


        } else {
        /* Jika elemen kedua parent berbeda maka anak akan memiliki elemen dan spesies dari elemen 
            yang memiliki element advantage yang lebih tinggi. */
            double elAdvA = Element.advantage(parentA.getElement(), parentB.getElement());
            double elAdvB = Element.advantage(parentB.getElement(), parentA.getElement());
            if (elAdvA > elAdvB) {
                
            } else if (elAdvA < elAdvB) {

            } else {
            /* eleAdv_a == eleAdv_b */
            /* Jika elemen kedua parent berbeda dan kedua elemen memiliki element advantage yang sama, 
                maka anak akan memiliki spesies berbeda dari kedua parent yang memiliki kedua elemen parent 
                (boleh dipilih random atau hardcoded). */
                List<Element> listEl = sortElementAdv(parentA, parentB);


            }

        }

        parentA.setLevel(parentA.getLevel() - 3);
        parentB.setLevel(parentA.getLevel() - 3);
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

        // Urutkan berdasarkan mastery level
        // Comparator<Skill> compareByMasteryLvl = (Skill sk1, Skill sk2) -> {
        //     Integer sk1Level = sk1.getMasteryLevel();
        //     Integer sk2Level = sk2.getMasteryLevel();
        //     return sk1Level.compareTo(sk2Level);
        // };

        // Comparator<Skill> compareByMasteryLvl = (Skill sk1, Skill sk2) -> {
        //     Integer sk1Level = sk1.getMasteryLevel();
        //     Integer sk2Level = sk2.getMasteryLevel();
        //     return Comparator.comparingInt(Skill::getMasteryLevel);
        // };

        // Comparator<Skill> compareByMasteryLvl = new Comparator<Skill>() {
        //     @Override
        //     public int compare(Skill sk1, Skill sk2) {
        //         Integer sk1Level = sk1.getMasteryLevel();
        //         Integer sk2Level = sk2.getMasteryLevel();
        //         return sk1Level.compareTo(sk2Level);
        //     }
        // };

        Collections.sort(skillChild, Comparator.comparingInt(Skill::getMasteryLevel));
        return skillChild;
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
            }
            
        }

        // Urutkan berdasarkan value dan ubah ke List Entry
        List<Entry<Element, Double>> listEl = new ArrayList<>(elChild.entrySet());
        listEl.sort(Entry.comparingByValue());

        // Return list key
        return listEl.stream().map(Entry::getKey).collect(Collectors.toList());

    }





}
