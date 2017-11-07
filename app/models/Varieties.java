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
@Table(name="varieties")
public class Varieties extends Model {
	@SequenceGenerator(name = "varitySqGn", sequenceName = "varietySeq", initialValue = 28, allocationSize = 100)
	@GeneratedValue(generator = "varitySqGn")
	@Id
	public long id;
	@Required
	@Column(name = "name")
	public String name = null ;	
	@Column(name = "crop_id")
	public long crop_id;	
}



