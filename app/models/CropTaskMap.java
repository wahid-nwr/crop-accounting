package models;

import java.util.ArrayList;
import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import net.sf.oval.constraint.Length;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import play.data.validation.Required;
import java.util.Date;

import org.hibernate.annotations.Cascade;
import javax.persistence.CascadeType;
import java.util.List;

@Entity
//@Table(name="CropTaskMap",schema="cropaccounting")
@PersistenceUnit(name = "default")
public class CropTaskMap extends Model{

	public String taskDesk = null;
	@ManyToMany(cascade=CascadeType.ALL)
	public List<CropCalenderTask> taskList = new ArrayList<CropCalenderTask>();
}
