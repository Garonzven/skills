/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "skillpeople")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skillpeople.findAll", query = "SELECT s FROM Skillpeople s")
    , @NamedQuery(name = "Skillpeople.findByIdpeople", query = "SELECT s FROM Skillpeople s WHERE s.skillpeoplePK.idpeople = :idpeople")
    , @NamedQuery(name = "Skillpeople.findByIdskill", query = "SELECT s FROM Skillpeople s WHERE s.skillpeoplePK.idskill = :idskill")
    , @NamedQuery(name = "Skillpeople.findByLevel", query = "SELECT s FROM Skillpeople s WHERE s.level = :level")
    , @NamedQuery(name = "Skillpeople.findByUpdatedate", query = "SELECT s FROM Skillpeople s WHERE s.updatedate = :updatedate")})
public class Skillpeople implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SkillpeoplePK skillpeoplePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "level")
    private int level;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @JoinColumn(name = "idpeople", referencedColumnName = "idpeople", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private People people;
    @JoinColumn(name = "idskill", referencedColumnName = "idskill", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Skill skill;

    public Skillpeople() {
    }

    public Skillpeople(SkillpeoplePK skillpeoplePK) {
        this.skillpeoplePK = skillpeoplePK;
    }

    public Skillpeople(SkillpeoplePK skillpeoplePK, int level) {
        this.skillpeoplePK = skillpeoplePK;
        this.level = level;
    }

    public Skillpeople(int idpeople, int idskill) {
        this.skillpeoplePK = new SkillpeoplePK(idpeople, idskill);
    }

    public SkillpeoplePK getSkillpeoplePK() {
        return skillpeoplePK;
    }

    public void setSkillpeoplePK(SkillpeoplePK skillpeoplePK) {
        this.skillpeoplePK = skillpeoplePK;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillpeoplePK != null ? skillpeoplePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skillpeople)) {
            return false;
        }
        Skillpeople other = (Skillpeople) object;
        if ((this.skillpeoplePK == null && other.skillpeoplePK != null) || (this.skillpeoplePK != null && !this.skillpeoplePK.equals(other.skillpeoplePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entis.Skillpeople[ skillpeoplePK=" + skillpeoplePK + " ]";
    }
    
}
