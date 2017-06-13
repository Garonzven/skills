/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "skill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s")
    , @NamedQuery(name = "Skill.findByIdskill", query = "SELECT s FROM Skill s WHERE s.idskill = :idskill")
    , @NamedQuery(name = "Skill.findByName", query = "SELECT s FROM Skill s WHERE s.name = :name")
    , @NamedQuery(name = "Skill.findByLevel", query = "SELECT s FROM Skill s WHERE s.level = :level")
    , @NamedQuery(name = "Skill.findByCreatedate", query = "SELECT s FROM Skill s WHERE s.createdate = :createdate")
    , @NamedQuery(name = "Skill.findByUpdatedate", query = "SELECT s FROM Skill s WHERE s.updatedate = :updatedate")
        
})
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idskill")
    private Integer idskill;
    @Size(max = 2147483647)
    @Column(name = "name")
    private String name;
    @Column(name = "level")
    private String level;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "updatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "skill", fetch = FetchType.LAZY)
    private Collection<Skillpeople> skillpeopleCollection;

    public Skill() {
    }

    public Skill(Integer idskill) {
        this.idskill = idskill;
    }

    public Integer getIdskill() {
        return idskill;
    }

    public void setIdskill(Integer idskill) {
        this.idskill = idskill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @XmlTransient
    public Collection<Skillpeople> getSkillpeopleCollection() {
        return skillpeopleCollection;
    }

    public void setSkillpeopleCollection(Collection<Skillpeople> skillpeopleCollection) {
        this.skillpeopleCollection = skillpeopleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idskill != null ? idskill.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skill)) {
            return false;
        }
        Skill other = (Skill) object;
        if ((this.idskill == null && other.idskill != null) || (this.idskill != null && !this.idskill.equals(other.idskill))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entis.Skill[ idskill=" + idskill + " ]";
    }
    
}
