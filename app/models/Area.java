package models;

import javax.persistence.*;
import javax.persistence.Table;
import play.db.jpa.Model;
import play.modules.chronostamp.NoChronostamp;

@Entity
@NoChronostamp
//@Table(name="Area",schema="cropaccounting")
@PersistenceUnit(name = "default")
public class Area extends Model
{
	public String areaType;
}
