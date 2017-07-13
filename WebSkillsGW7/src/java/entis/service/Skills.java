/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entis.service;

import entis.Skill;

/**
 *
 * @author Ing. Jeanmarie Gonzalez
 */
public class Skills {
    int idpeople;
    Skill[] skills;

    
    public Skills(){
        
    }
    
    public Skills(int idpeople, Skill[] skills) {
        this.idpeople = idpeople;
        this.skills = skills;
    }

    public int getIdpeople() {
        return idpeople;
    }

    public void setIdpeople(int idpeople) {
        this.idpeople = idpeople;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }
   
    
}

