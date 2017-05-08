package entis;

import entis.People;
import entis.Skill;
import entis.SkillpeoplePK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-08T11:17:38")
@StaticMetamodel(Skillpeople.class)
public class Skillpeople_ { 

    public static volatile SingularAttribute<Skillpeople, Date> updatedate;
    public static volatile SingularAttribute<Skillpeople, Integer> level;
    public static volatile SingularAttribute<Skillpeople, Skill> skill;
    public static volatile SingularAttribute<Skillpeople, SkillpeoplePK> skillpeoplePK;
    public static volatile SingularAttribute<Skillpeople, People> people;

}