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
@PersistenceUnit(name = "other")
@Table(name="crops")
public class Crops extends Model {
	@SequenceGenerator(name = "cropsSqGn", sequenceName = "cropsSeq", initialValue = 155, allocationSize = 100)
	@GeneratedValue(generator = "cropsSqGn")
	@Id
	public long id;
	@Required
	@Column(name = "name")
	public String name = null ;	
	@Column(name = "type")
	public String type = null;	
	@Column(name = "image")
	public String image = "";	

}



