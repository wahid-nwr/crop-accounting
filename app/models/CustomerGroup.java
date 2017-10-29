package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import net.sf.oval.constraint.Length;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import javax.persistence.ManyToOne;
import play.data.validation.Required;

import javax.persistence.*;

@Entity
//@Table(name="CustomerGroup",schema="cropaccounting")
@PersistenceUnit(name = "default")
public class CustomerGroup extends Model {
	@Required
	@Unique(message="Group Id must be unique")
	public long groupId;
	@Required
	@Unique(message="Group Name must be unique")
	public String groupName;
}
