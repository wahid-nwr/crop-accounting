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
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@Entity
//@Table(name="Crop",schema="cropaccounting")
@PersistenceUnit(name = "default")
public class FarmerCropTask extends Model {
	public String type;
	public long crop;
	public String cropName;
	public long varity;
	public String varityName;
	@ManyToOne(cascade=CascadeType.ALL)
	public Farmer farmer;
	@ManyToMany(cascade=CascadeType.ALL)
	public List<FarmerTask> farmerTaskList = new ArrayList<FarmerTask>();
}
