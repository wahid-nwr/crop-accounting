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
//@Table(name="Crop",schema="cropaccounting")
@PersistenceUnit(name = "default")
public class Crop extends Model {
	@Required
	public String name = null ;
	
	public String customerId = null;
	
	public long portalid;
	
	
	public long cropLifeCyle;
	public long cropUnit;
	public int cropLandQuantity;
	public String cropCast = null;
	public String groupId = null;
	public String agreementId = null;
	public String description = null;
	
	public String locations = null;
	@ManyToOne(cascade = {CascadeType.ALL})
	public Farmer farmer;
	/*
	@ManyToMany(cascade = {CascadeType.ALL})
	public List<DocDescType> listOfDocDescType;
	*/
	
}
