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
public class IncomeItem extends Model {
	@Required
	@ManyToOne
	public models.CropIncome cropIncome;
	@Required
    @ManyToOne
	public models.CropIncomeItem cropIncomeItem;
	
	@Override
	public String toString()
	{
		return "{id:"+this.id+",cropIncome_id:"+this.cropIncome.id+",cropIncome_name:"+this.cropIncome.name+","+
								"cropIncomeItem_id:"+this.cropIncomeItem.id+",cropIncomeItem_name:"+this.cropIncomeItem.name +
				"}";
	}
	
	public String toStringJson()
	{
		return "{\"id\":\""+this.id+"\",\"cropIncome_id\":\""+this.cropIncome.id+"\",\"cropIncome_name\":\""+this.cropIncome.name+"\","+
								"\"cropIncomeItem_id\":\""+this.cropIncomeItem.id+"\",\"cropIncomeItem_name\":\""+this.cropIncomeItem.name+"\""+
				"}";
	}
}
