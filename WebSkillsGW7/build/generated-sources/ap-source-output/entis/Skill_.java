package entis;

import entis.Skillpeople;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-08T11:17:38")
@StaticMetamodel(Skill.class)
public class Skill_ { 

    public static volatile SingularAttribute<Skill, Date> updatedate;
    public static volatile SingularAttribute<Skill, String> level;
    public static volatile SingularAttribute<Skill, String> name;
    public static volatile SingularAttribute<Skill, Date> createdate;
    public static volatile CollectionAttribute<Skill, Skillpeople> skillpeopleCollection;
    public static volatile SingularAttribute<Skill, Integer> idskill;

}