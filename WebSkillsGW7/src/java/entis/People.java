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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "people")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "People.findAll", query = "SELECT p FROM People p")
    , @NamedQuery(name = "People.findByIdpeople", query = "SELECT p FROM People p WHERE p.idpeople = :idpeople")
    , @NamedQuery(name = "People.findByName", query = "SELECT p FROM People p WHERE p.name = :name")
    , @NamedQuery(name = "People.findByLastname", query = "SELECT p FROM People p WHERE p.lastname = :lastname")
    , @NamedQuery(name = "People.findByEmail", query = "SELECT p FROM People p WHERE p.email = :email")
    , @NamedQuery(name = "People.findByPhone", query = "SELECT p FROM People p WHERE p.phone = :phone")
    , @NamedQuery(name = "People.findByLocation", query = "SELECT p FROM People p WHERE p.location = :location")
    , @NamedQuery(name = "People.findBySkillAndLevel", query = "SELECT p FROM People p WHERE p.skillandlevel = :skillandlevel")
    , @NamedQuery(name = "People.findByIndate", query = "SELECT p FROM People p WHERE p.indate = :indate")
    , @NamedQuery(name = "People.findByPasword", query = "SELECT p FROM People p WHERE p.pasword = :pasword")
    , @NamedQuery(name = "People.findByJobtitle", query = "SELECT p FROM People p WHERE p.jobtitle = :jobtitle")
    , @NamedQuery(name = "People.findByFotolink", query = "SELECT p FROM People p WHERE p.fotolink = :fotolink")
    , @NamedQuery(name = "People.findByIschangepassword", query = "SELECT p FROM People p WHERE p.ischangepassword = :ischangepassword")})
public class People implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpeople")
    private Integer idpeople;
    @Size(max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "lastname")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Size(max = 2147483647)
    @Column(name = "phone")
    private String phone;
    @Size(max = 2147483647)
    @Column(name = "location")
    private String location;
    @Size(max = 2147483647)
    @Column(name = "skillandlevel")
    private String skillandlevel;
    @Column(name = "indate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date indate;
    @Size(max = 2147483647)
    @Column(name = "pasword")
    private String pasword;
    @Size(max = 2147483647)
    @Column(name = "jobtitle")
    private String jobtitle;
    @Size(max = 2147483647)
    @Column(name = "fotolink")
    private String fotolink;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Column(name = "ischangepassword") 
    private Character ischangepassword;

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy = "people", fetch = FetchType.EAGER)
   
    private Collection<Skillpeople> skillpeopleCollection;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "idpeople", fetch = FetchType.LAZY)
   
    private Collection<Log> logCollection;
   
    @JoinColumn(name = "idrol", referencedColumnName = "idrol")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Roltipo idrol;

    public People() {
    }

    public People(Integer idpeople) {
        this.idpeople = idpeople;
    }

    public Integer getIdpeople() {
        return idpeople;
    }

    public void setIdpeople(Integer idpeople) {
        this.idpeople = idpeople;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone =phone ;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location =location ;
    } //skillandlevel
    public String getSkillandlevel() {
        return skillandlevel;
    }

    public void setSkillandlevel(String skillandlevel) {
        this.skillandlevel =skillandlevel ;
    }
    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getFotolink() {
        return fotolink;
    }

    public void setFotolink(String fotolink) {
        this.fotolink = fotolink;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Character getIschangepassword() {
        return ischangepassword;
    }

    public void setIschangepassword(Character ischangepassword) {
        this.ischangepassword = ischangepassword;
    }

    @XmlTransient
    public Collection<Skillpeople> getSkillpeopleCollection() {
        return skillpeopleCollection;
    }

    public void setSkillpeopleCollection(Collection<Skillpeople> skillpeopleCollection) {
        this.skillpeopleCollection = skillpeopleCollection;
    }

    @XmlTransient
    public Collection<Log> getLogCollection() {
        return logCollection;
    }

    public void setLogCollection(Collection<Log> logCollection) {
        this.logCollection = logCollection;
    }

    public Roltipo getIdrol() {
        return idrol;
    }

    public void setIdrol(Roltipo idrol) {
        this.idrol = idrol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpeople != null ? idpeople.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof People)) {
            return false;
        }
        People other = (People) object;
        if ((this.idpeople == null && other.idpeople != null) || (this.idpeople != null && !this.idpeople.equals(other.idpeople))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entis.People[ idpeople=" + idpeople + " ]";
    }
    
}
