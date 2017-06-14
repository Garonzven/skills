package entis;

import entis.Log;
import entis.Roltipo;
import entis.Skillpeople;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-12T21:50:18")
@StaticMetamodel(People.class)
public class People_ { 

    public static volatile SingularAttribute<People, String> pasword;
    public static volatile SingularAttribute<People, Roltipo> idrol;
    public static volatile SingularAttribute<People, String> jobtitle;
    public static volatile SingularAttribute<People, Integer> idpeople;
    public static volatile SingularAttribute<People, String> lastname;
    public static volatile SingularAttribute<People, String> fotolink;
    public static volatile SingularAttribute<People, byte[]> foto;
    public static volatile SingularAttribute<People, String> phone;
    public static volatile SingularAttribute<People, String> skillandlevel;
    public static volatile SingularAttribute<People, String> name;
    public static volatile SingularAttribute<People, String> location;
    public static volatile SingularAttribute<People, Date> indate;
    public static volatile CollectionAttribute<People, Log> logCollection;
    public static volatile SingularAttribute<People, Character> ischangepassword;
    public static volatile CollectionAttribute<People, Skillpeople> skillpeopleCollection;
    public static volatile SingularAttribute<People, String> email;

}