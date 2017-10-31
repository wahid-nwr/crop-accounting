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
public class ExpenceItemValue extends Model {
	
    @ManyToOne
	public CropActivityItem cropActivityItem;
	
	public float itemExpence;
	public float labourExpence;
	@Override
	public String toString()
	{
		return "{id:"+this.id+",cropActivityItem:"+this.cropActivityItem.id+",cropActivityItemname:"+this.cropActivityItem.name+","+
								"itemExpence:"+this.itemExpence+",labourExpence:"+this.labourExpence+
				"}";
	}
}
