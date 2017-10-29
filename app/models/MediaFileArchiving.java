package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistance.*;
import javax.persistence.PersistenceUnit;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
//@Table(name="MediaFileArchiving",schema="cropaccounting")
@PersistenceUnit(name = "default")
public class MediaFileArchiving extends Model{
	
	
	/*Work Register Form Fields*/
	
	@Required
	public Date dateOfUpload;
	
	@Required
	public String typeOfMedia;
	
	@Required
	public String description;
	
	@Required
	public String searchKeyward;
	
	
	public String uploadedFile;
	
	
	public String accessLink;
	
}
