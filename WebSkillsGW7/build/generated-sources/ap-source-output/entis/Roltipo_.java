package entis;

import entis.People;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-08T11:17:38")
@StaticMetamodel(Roltipo.class)
public class Roltipo_ { 

    public static volatile SingularAttribute<Roltipo, Integer> idrol;
    public static volatile SingularAttribute<Roltipo, String> name;
    public static volatile CollectionAttribute<Roltipo, People> peopleCollection;

}