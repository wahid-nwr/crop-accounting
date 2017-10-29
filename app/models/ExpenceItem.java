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
public class ExpenceItem extends Model {
	@Required
	@ManyToOne
	public CropActivity cropActivity;
	@Required
    @ManyToOne
	public CropActivityType cropActivityType;
    @ManyToOne
	public CropActivityItem cropActivityItem;
	
	@Override
	public String toString()
	{
		return "{id:"+this.id+",cropActivity_id:"+this.cropActivity.id+",cropActivity_name:"+this.cropActivity.name+","+
								"cropActivityType_id:"+this.cropActivityType.id+",cropActivityType_name:"+this.cropActivityType.name+","+
								"cropActivityItem_id:"+this.cropActivityItem.id+",cropActivityItem_name:"+this.cropActivityItem.name+
				"}";
	}
}
