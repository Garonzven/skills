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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lorena Portillo
 */
@Entity
@Table(name = "log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")
    , @NamedQuery(name = "Log.findByIdlog", query = "SELECT l FROM Log l WHERE l.idlog = :idlog")
    , @NamedQuery(name = "Log.findByActionlog", query = "SELECT l FROM Log l WHERE l.actionlog = :actionlog")
    , @NamedQuery(name = "Log.findByDatetimelog", query = "SELECT l FROM Log l WHERE l.datetimelog = :datetimelog")})
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlog")
    private Integer idlog;
    @Size(max = 2147483647)
    @Column(name = "actionlog")
    private String actionlog;
    @Column(name = "datetimelog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimelog;
    @JoinColumn(name = "idpeople", referencedColumnName = "idpeople")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private People idpeople;

    public Log() {
    }

    public Log(Integer idlog) {
        this.idlog = idlog;
    }

    public Integer getIdlog() {
        return idlog;
    }

    public void setIdlog(Integer idlog) {
        this.idlog = idlog;
    }

    public String getActionlog() {
        return actionlog;
    }

    public void setActionlog(String actionlog) {
        this.actionlog = actionlog;
    }

    public Date getDatetimelog() {
        return datetimelog;
    }

    public void setDatetimelog(Date datetimelog) {
        this.datetimelog = datetimelog;
    }

    public People getIdpeople() {
        return idpeople;
    }

    public void setIdpeople(People idpeople) {
        this.idpeople = idpeople;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlog != null ? idlog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.idlog == null && other.idlog != null) || (this.idlog != null && !this.idlog.equals(other.idlog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entis.Log[ idlog=" + idlog + " ]";
    }
    
}
