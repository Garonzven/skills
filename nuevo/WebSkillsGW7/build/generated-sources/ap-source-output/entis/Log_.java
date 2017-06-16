package entis;

import entis.People;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-12T21:50:18")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, People> idpeople;
    public static volatile SingularAttribute<Log, Date> datetimelog;
    public static volatile SingularAttribute<Log, Integer> idlog;
    public static volatile SingularAttribute<Log, String> actionlog;

}