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
public class FarmerTask extends Model {
	@ManyToOne
	public CropActivity cropActivity;
	@ManyToOne
	public CropActivityType cropActivityType;
	@ManyToOne
	public CropActivityItem cropActivityItem;

	public float itemExpence;
	public float labourExpence;
	public String taskName;
	public String taskDateStr;
	public Date dateOfUpload;	

	public FarmerTask(CropActivity cropActivity, 
				CropActivityType cropActivityType, 
				CropActivityItem cropActivityItem, 
				float itemExpence,
				float labourExpence,
				String taskName,
				String taskDateStr,
				Date dateOfUpload) {
		this.cropActivity = cropActivity;
		this.cropActivityType = cropActivityType;
		this.cropActivityItem = cropActivityItem;

		this.itemExpence = itemExpence;
		this.labourExpence = labourExpence;
		this.taskName = taskName;
		this.taskDateStr = taskDateStr;
		this.dateOfUpload = dateOfUpload;
	}
	

	@Override
	public String toString()
	{
		return "{id:"+this.id + ",cropActivity_id:" + this.cropActivity.id + ",cropActivity_name:" + this.cropActivity.name + "," +
			"cropActivityType_id:" + this.cropActivityType.id + ",cropActivityType_name:" + this.cropActivityType.name+"," +
			"cropActivityItem_id:" + this.cropActivityItem.id + ",cropActivityItem_name:" + this.cropActivityItem.name + "," +
			"itemExpence:" + this.itemExpence + ", labourExpence:" + this.labourExpence + "," +
			"taskName:" + this.taskName + ", taskDateStr:" + this.taskDateStr +
				"}";
	}
}
