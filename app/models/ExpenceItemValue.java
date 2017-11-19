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
public class ExpenceItemValue extends Model {
	
	@ManyToOne
	public CropCalenderTask cropCalenderTask;
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
	
	public CropActivityType getCropActivityType(List<models.ExpenceItem> expenceItemList)
	{
		for(models.ExpenceItem expenceItem : expenceItemList)
		{
			if(expenceItem.cropActivityItem.id == this.cropActivityItem.id)
			return expenceItem.cropActivityType;
		}
		return null;
	}
	
	public CropActivity getCropActivity(List<models.ExpenceItem> expenceItemList)
	{
		for(models.ExpenceItem expenceItem : expenceItemList)
		{
			if(expenceItem.cropActivityItem.id == this.cropActivityItem.id)
			return expenceItem.cropActivity;
		}
		return null;
	}
}

