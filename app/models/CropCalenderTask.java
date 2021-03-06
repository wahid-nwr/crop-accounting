package models;

import javax.persistence.*;
import javax.persistence.Table;
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
//@Table(name="CropCalenderTask",schema="cropaccounting")
@PersistenceUnit(name = "default")
public class CropCalenderTask  extends Model {
	public String taskName;
	public String taskDateStr;
	public Date dateOfUpload;
	
	@ManyToOne
	public models.CropActivity cropActivity;
	
	@ManyToOne
	public models.CropActivityType cropActivityType;	
	
	public String comments;
}
