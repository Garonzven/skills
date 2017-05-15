/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario
 */
@Embeddable
public class SkillpeoplePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idpeople")
    private int idpeople;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idskill")
    private int idskill;

    public SkillpeoplePK() {
    }

    public SkillpeoplePK(int idpeople, int idskill) {
        this.idpeople = idpeople;
        this.idskill = idskill;
    }

    public int getIdpeople() {
        return idpeople;
    }

    public void setIdpeople(int idpeople) {
        this.idpeople = idpeople;
    }

    public int getIdskill() {
        return idskill;
    }

    public void setIdskill(int idskill) {
        this.idskill = idskill;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idpeople;
        hash += (int) idskill;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkillpeoplePK)) {
            return false;
        }
        SkillpeoplePK other = (SkillpeoplePK) object;
        if (this.idpeople != other.idpeople) {
            return false;
        }
        if (this.idskill != other.idskill) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entis.SkillpeoplePK[ idpeople=" + idpeople + ", idskill=" + idskill + " ]";
    }
    
}
