package controllers;

import play.Play;
import javax.persistence.Query;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.Http.Header;
import play.server.ServletWrapper;

import controllers.deadbolt.Deadbolt;
import controllers.deadbolt.ExternalRestrictions;
import controllers.deadbolt.JSON;

import play.Logger;
import play.data.FileUpload;
import play.data.validation.Validation;
import play.mvc.With;
import controllers.deadbolt.Unrestricted;

import play.db.*;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import javax.validation.Valid;

import models.CropCalenderTask;
import models.CropTaskMap;
import models.UserModel;

import java.util.Map;
import java.util.HashMap;
import java.util.*;

@With(Deadbolt.class)
public class CropManagement extends Controller {
   
    @ExternalRestrictions("Crop price")
    public static void createcropprice() {
		List<UserModel> users = UserModel.find("id <> 1").fetch();
		render(users);
    }
    @ExternalRestrictions("Item price")
    public static void createitemprice() {
		List<UserModel> users = UserModel.find("id <> 1").fetch();
		render(users);
    }
	@ExternalRestrictions("Calendar 1")
    public static void cropcalender() {
		List<UserModel> users = UserModel.find("id <> 1").fetch();
		render(users);
    }
    @ExternalRestrictions("Calendar 2")
    public static void cropcalender2() {
		List<UserModel> users = UserModel.find("id <> 1").fetch();
		render(users);
    }
    @ExternalRestrictions("Create Crop List")
    public static void croplist() {
		List<UserModel> users = UserModel.find("id <> 1").fetch();
	//Connection conn = play.db.DB.getDBConfig("other").getConnection();
	javax.persistence.EntityManager em = play.db.jpa.JPA.em("other");
	//Query q 
	List<Object[]> types = em.createNativeQuery("SELECT distinct a.type FROM crops a").getResultList();
	List<Object[]> crops = em.createNativeQuery("SELECT  a.type,a.name,a.id FROM crops a").getResultList();	
	String query = "SELECT a.crop_id,a.name, a.local_name, a.spec,a.production_with_irrigation,a.production_without_irrigation,"
					+"a.life_time, a.special FROM varieties a where crop_id<>'' and crop_id is not null and deleted_at is null";
					System.out.println("query::::"+query);
	List<Object[]> varities = em.createNativeQuery(query).getResultList();
		render(types,crops,varities);
    }
    @ExternalRestrictions("Create Crop")
    public static void createcrop() {
		String cropId = params.get("cropId");
		String cropType = params.get("cropType");
		javax.persistence.EntityManager em = play.db.jpa.JPA.em("other");
		Object[] crop = null;	
		List<Object[]> crops = null;
		if(cropId!=null && !cropId.equals("null") && cropId.length()>0)
		{
			crops = em.createNativeQuery("SELECT  a.id,a.type,a.name FROM crops a where a.id = "+cropId).getResultList();	
			if(!crops.isEmpty())crop = crops.get(0);
		}
		//System.out.println("crop::::"+crop[0]);
		render(crops,cropType);
    }
    @ExternalRestrictions("Crop Management")
	public static void submitCrop(/*@Valid models.Crop crop*/){
		/*
		validation.valid(crop);
		if(Validation.hasErrors()) {
			flash.error("Customer "+crop.name+" could not be saved!Error="+Validation.errors().toString());
			Logger.info("Error!!  "+ Validation.errors().toString());
			render("@createcrop", crop);
		}
		crop.save();
		List<models.Crop> cropList = models.Crop.findAll();
		*/
		String cropId = params.get("cropId");
		String cropType = params.get("cropType");
		String crop_name = params.get("crop_name");
		String varityName = params.get("crop.cropCast");
		javax.persistence.EntityManager em = play.db.jpa.JPA.em("other");
		Object[] crop = null;	
		List<Object[]> crops = null;
		if(cropId!=null && !cropId.equals("null") && cropId.length()>0)
		{
			//javax.persistence.EntityTransaction et = em.getTransaction();
			//et.begin();
			String query = "insert into varieties(name,crop_id) values('"+varityName+"',"+cropId+");SELECT LAST_INSERT_ID();";
			System.out.println("query::::"+query);
			int id = em.createNativeQuery(query).executeUpdate();
			//et.commit();
		}
		else
		{
			/*
			String query = "insert into crops(name,type,image) values('"+crop_name+"','"+cropType+"','');";
			System.out.println("query::::"+query);
			int value = em.createNativeQuery(query).executeUpdate();
			
			query = "insert into varieties(name,crop_id) values('"+varityName+"',"+cropId+");";
			System.out.println("query::::"+query);
			//em.createNativeQuery(query).executeUpdate();
			*/
			models.Crops portalcrops = new models.Crops();
			portalcrops.name = crop_name;
			portalcrops.type = cropType;
			portalcrops.save();
			
			models.Varieties protalvirieties = new models.Varieties();
			protalvirieties.name = varityName;
			protalvirieties.crop_id = portalcrops.id;
			protalvirieties.save();
			System.out.println("crops::::"+protalvirieties.id);
		}
		render("@croplist");
	}
	@ExternalRestrictions("Crop Management")
	public static void list(){
	List<models.Crop> cropList = models.Crop.findAll();
		render(cropList);
	}
	
    @ExternalRestrictions("Create Activity")
    public static void createActivity() {
		List<UserModel> users = UserModel.find("id <> 1").fetch();
		render(users);
    }
    
    @ExternalRestrictions("Create Activity Type")
    public static void createActivityType() {
		List<UserModel> users = UserModel.find("id <> 1").fetch();
		render(users);
    }
    
    @ExternalRestrictions("Create Activity Item")
    public static void createActivityItem() {
		List<UserModel> users = UserModel.find("id <> 1").fetch();
		render(users);
    }
    
    @ExternalRestrictions("Crop Management")
	public static void submitActivity(@Valid models.CropActivity cropActivity){
		validation.valid(cropActivity);
		if(Validation.hasErrors()) {
			flash.error("Customer "+cropActivity.name+" could not be saved!Error="+Validation.errors().toString());
			Logger.info("Error!!  "+ Validation.errors().toString());
			render("@createactivity", cropActivity);
		}
		cropActivity.save();
		List<models.CropActivity> activityList = models.CropActivity.findAll();
		render("@activitylist",activityList);
	}
	
	@ExternalRestrictions("Crop Management")
	public static void submitActivityType(@Valid models.CropActivityType cropActivityType){
		validation.valid(cropActivityType);
		if(Validation.hasErrors()) {
			flash.error("Customer "+cropActivityType.name+" could not be saved!Error="+Validation.errors().toString());
			Logger.info("Error!!  "+ Validation.errors().toString());
			render("@createactivitytype", cropActivityType);
		}
		cropActivityType.save();
		List<models.CropActivityType> activityList = models.CropActivityType.findAll();
		render("@activitylist",activityList);
	}
	
	
	@ExternalRestrictions("Crop Management")
	public static void submitActivityItem(@Valid models.CropActivityItem cropActivityItem){
		validation.valid(cropActivityItem);
		if(Validation.hasErrors()) {
			flash.error("Customer "+cropActivityItem.name+" could not be saved!Error="+Validation.errors().toString());
			Logger.info("Error!!  "+ Validation.errors().toString());
			render("@createactivityitem", cropActivityItem);
		}
		cropActivityItem.save();
		List<models.CropActivityItem> cropActivityItemList = models.CropActivityItem.findAll();
		render("@activitylist",cropActivityItemList);
	}
    
    @ExternalRestrictions("Activity List")
	public static void activitylist(){
	List<models.CropActivity> activityList = models.CropActivity.findAll();
		render(activityList);
	}
    
    @ExternalRestrictions("Crop Management")
	public static void editActivity(Long id){
	models.CropActivity cropActivity = models.CropActivity.findById(id);
		render("@createActivity",cropActivity);
	}
	
    @ExternalRestrictions("Create Main Crop")
    public static void createmaincrop() {
		List<UserModel> users = UserModel.find("id <> 1").fetch();
		render(users);
    }
    
    @ExternalRestrictions("Crop Matrix")
    public static void cropmatrix() {
		List<UserModel> users = UserModel.find("id <> 1").fetch();
		render(users);
    }
    
    @ExternalRestrictions("Update crop price")
    public static void updatecropprice() {
		List<UserModel> users = UserModel.find("id <> 1").fetch();
		render(users);
    }
    @ExternalRestrictions("Create Crop Calendar")
    public static void createcropcalendar() {
		javax.persistence.EntityManager em = play.db.jpa.JPA.em("other");
    	List<models.Crop> cropList = models.Crop.findAll();
    	List<Object[]> types = em.createNativeQuery("SELECT distinct a.type FROM crops a").getResultList();
		List<Object[]> crops = em.createNativeQuery("SELECT  a.type,a.name,a.id FROM crops a").getResultList();	
		String query = "SELECT a.crop_id,a.name, a.local_name, a.spec,a.production_with_irrigation,a.production_without_irrigation,"
					+"a.life_time, a.special FROM varieties a where crop_id<>'' and crop_id is not null and deleted_at is null";
					System.out.println("query::::"+query);
		List<Object[]> varities = em.createNativeQuery(query).getResultList();
    	List<models.CropActivity> cropActivityList = models.CropActivity.findAll();
    	List<models.ExpenceItem> expenceItemList = models.ExpenceItem.findAll();
		render(cropActivityList,expenceItemList,types,crops,varities);
    }
    @ExternalRestrictions("Crop Management")
	public static void submitCropCalTask(@Valid models.CropTaskMap cropTaskMap){
    	/*
		validation.valid(cropTask);
		if(Validation.hasErrors()) {
			flash.error("Customer "+cropTask.taskName+" could not be saved!Error="+Validation.errors().toString());
			Logger.info("Error!!  "+ Validation.errors().toString());
			render("@createcropcalendar", cropTask);
		}
		cropTask.save();
		List<models.CropCalenderTask> cropTaskList = models.CropCalenderTask.findAll();*/
    	
    	String type = request.params.get("cropTaskMap.type");
    	type = type.replace("string:","");
    	String crop = request.params.get("cropTaskMap.crop");
    	crop = crop.replace("string:","");
    	String varity = request.params.get("cropTaskMap.varity");
    	varity = varity.replace("string:","");
    	System.out.println("type:::"+type+" crop::"+crop+" varity::"+varity);
	
		if(!type.isEmpty())cropTaskMap.type = type;
		if(!crop.isEmpty())cropTaskMap.crop = Long.parseLong(crop);
		if(!varity.isEmpty())cropTaskMap.varity = Long.parseLong(varity);
		
		String[] replaceNames = params.getAll("taskname");
		String[] replaceDates = params.getAll("taskdate");
		
		String[] replaceAct = params.getAll("activity");
		System.out.println("replaceAct::"+replaceAct.length);
		String[] replaceTask = params.getAll("task");
		System.out.println("replaceTask::"+replaceTask.length);
		String[] replaceComm = params.getAll("comments");
		
		List<CropCalenderTask> taskList = new ArrayList<CropCalenderTask>();
		
		CropCalenderTask cropCalenderTask = null;
		models.CropActivity cropActivity = null;
		models.CropActivityType cropActivityType = null;
		for(int i = 1; replaceDates!=null && i<replaceDates.length;i++)
		{
			cropCalenderTask = new CropCalenderTask();
			if(replaceDates[i]!=null && replaceDates[i].length()>0) cropCalenderTask.taskDateStr = replaceDates[i];
			if(replaceNames!=null && replaceNames[i]!=null && replaceNames[i].length()>0) cropCalenderTask.taskName = replaceNames[i];
			System.out.println("replaceAct[i]::"+replaceAct[i]);
			if(replaceAct[i]!=null && replaceAct[i].length()>0){
				cropActivity = models.CropActivity.findById(Long.parseLong(replaceAct[i]));
				cropCalenderTask.cropActivity = cropActivity;
			}
			System.out.println("replaceTask[i]::"+replaceTask[i]);
			if(replaceTask[i]!=null && replaceTask[i].length()>0){
				cropActivityType = models.CropActivityType.findById(Long.parseLong(replaceTask[i]));
				cropCalenderTask.cropActivityType = cropActivityType;
			}
			if(replaceComm[i]!=null && replaceComm[i].length()>0) cropCalenderTask.comments = replaceComm[i];
			
			taskList.add(cropCalenderTask);
		}
		cropTaskMap.taskList = taskList;
		cropTaskMap.save();
		List<CropTaskMap> cropTaskMapList = models.CropTaskMap.findAll();
		//render("@cropTaskMaps",cropTaskMaps);
		render("@cropTasklist",cropTaskMapList);
	}
    
    @ExternalRestrictions("Crop Management")
	public static void cropTasklist(){
	List<CropTaskMap> cropTaskMapList = models.CropTaskMap.findAll();
		render(cropTaskMapList);
	}
    
    @ExternalRestrictions("Create Task Expenditure")
	public static void createTaskExpenditure(Long id){
    	System.out.println("id:::"+id);
    	CropTaskMap cropTaskMap = models.CropTaskMap.findById(id);
    	//List<models.CropActivity> cropActivityList = models.CropActivity.findAll();
    	//List<models.CropActivityType> cropActivityTypeList = models.CropActivityType.findAll();
    	List<models.ExpenceItem> expenceItemList = models.ExpenceItem.findAll();
    	
		render(cropTaskMap,expenceItemList);
	}
	
	@ExternalRestrictions("Create Expence Item")
	public static void createExpenceItem(){
    	List<models.CropActivity> cropActivityList = models.CropActivity.findAll();
    	List<models.CropActivityType> cropActivityTypeList = models.CropActivityType.findAll();
		render(cropActivityList,cropActivityTypeList);
	}
	
	@ExternalRestrictions("Crop Management")
	public static void submitExpenceItem(@Valid models.ExpenceItem expenceItem){
		expenceItem = new models.ExpenceItem();
		String cropActivityId = request.params.get("cropActivity");//request.params.get("crop.detail");
		System.out.println("cropActivityId:::"+cropActivityId);
		String cropActivityTypeId = request.params.get("cropActivityType");
		String cropActivityItemId = request.params.get("cropActivityItem");
		models.CropActivity cropActivity = null;
		models.CropActivityType cropActivityType = null;
		models.CropActivityItem cropActivityItem = null;
		
		if(!cropActivityId.isEmpty()) cropActivity = models.CropActivity.findById(Long.parseLong(cropActivityId));
		if(!cropActivityTypeId.isEmpty()) cropActivityType = models.CropActivityType.findById(Long.parseLong(cropActivityTypeId));
		if(!cropActivityItemId.isEmpty()) cropActivityItem = models.CropActivityItem.findById(Long.parseLong(cropActivityItemId));
		
		if(cropActivity!=null) expenceItem.cropActivity = cropActivity;
		if(cropActivityType!=null) expenceItem.cropActivityType = cropActivityType;
		if(cropActivityItem!=null) expenceItem.cropActivityItem = cropActivityItem;
		/*
		validation.valid(expenceItem);
		if(Validation.hasErrors()) {
			flash.error("Customer "+expenceItem+" could not be saved!Error="+Validation.errors().toString());
			Logger.info("Error!!  "+ Validation.errors().toString());
			//render("@createExpenceItem", expenceItem);
		}
		*/
		//System.out.println("cropActivity:::"+cropActivity);
		//System.out.println("expenceItem:::"+expenceItem);
		if(expenceItem!=null) expenceItem.save();
		List<models.ExpenceItem> expenceItemList = models.ExpenceItem.findAll();
		//System.out.println("\n\n"+expenceItemList.toString());
		String json = expenceItemList.toString();
		json = json.substring(1,json.length()-1);
		System.out.println("\n\n"+expenceItem.toStringJson());
		renderText(expenceItem.toStringJson());
		//renderJson(Json.toJson(expenceItemList));
		//renderText(expenceItemList.toString());
		//flash.error("item saved");
		//render("@expenceItemList",expenceItemList);
	}
 
	@ExternalRestrictions("Crop Management")
	public static void editExpenceItem(Long id){
		models.ExpenceItem expenceItem = models.ExpenceItem.findById(id);
		List<models.CropActivity> cropActivityList = models.CropActivity.findAll();
    	List<models.CropActivityType> cropActivityTypeList = models.CropActivityType.findAll();
    	
		render("@createExpenceItem",expenceItem,cropActivityList,cropActivityTypeList);
	}
	
	@ExternalRestrictions("Expence Item List")
	public static void expenceItemList(){
		List<models.ExpenceItem> expenceItemList = models.ExpenceItem.find("order by id desc").fetch();	
		List<models.CropActivity> cropActivityList = models.CropActivity.findAll();
    	List<models.CropActivityType> cropActivityTypeList = models.CropActivityType.findAll();
    	List<models.CropActivityItem> cropActivityItemList = models.CropActivityItem.findAll();
		render(expenceItemList,cropActivityList,cropActivityTypeList,cropActivityItemList);
	}
	
    @ExternalRestrictions("View User")
    public static void submit(@Valid models.Crop crop){
		crop.name = "asdf";
		String type = params.get("type");
		String cropIdStr = params.get("crop");
		
		String varity = params.get("varity");
		long cropId = 0L;
		long varityId = 0L;
		if(!type.isEmpty()) type = type.replace("string:","");
		if(!cropIdStr.isEmpty()){
			cropIdStr = cropIdStr.replace("string:","");
			cropId = Long.parseLong(cropIdStr);
		}
		if(!varity.isEmpty())
		{
			varity = varity.replace("string:","");
			varityId = Long.parseLong(varity);
		}
		
		System.out.println("crop:::"+crop);
		validation.valid(crop);
		if(Validation.hasErrors()) {
			flash.error("Customer "+crop.farmer.name+" could not be saved!Error="+Validation.errors().toString());
			Logger.info("Error!!  "+ Validation.errors().toString());
			List<models.ExpenceItem> expenceItemList = models.ExpenceItem.find("order by id desc").fetch();	
			List<models.CropActivity> cropActivityList = models.CropActivity.findAll();
			List<models.CropActivityType> cropActivityTypeList = models.CropActivityType.findAll();
			List<models.CropActivityItem> cropActivityItemList = models.CropActivityItem.findAll();
			render("@croplist", crop,expenceItemList,cropActivityList,cropActivityTypeList,cropActivityItemList);
		}
		
		crop.save();
		models.CropExpenceList cropExpenceList = models.CropExpenceList.find("type='"+type+"' and crop="+cropId+" and varity="+varityId).first();
    	List<models.ExpenceItem> expenceItemList = models.ExpenceItem.findAll();
    	
		render("@farmerTaskExpenditure",crop,cropExpenceList,expenceItemList);
    }
    
    @ExternalRestrictions("View User")
    public static void submitExpenditure(@Valid models.CropExpenceList cropExpenceList){
		
		CropTaskMap cropTaskMap = models.CropTaskMap.findById(cropExpenceList.cropTaskMap.id);
		models.CropExpenceList repoCropExpenceList = models.CropExpenceList.find("type='"+cropTaskMap.type+"' and crop="+cropTaskMap.crop
			+" and varity="+cropTaskMap.varity).first();
		validation.valid(cropExpenceList);
		String[] cropActivityItems = params.getAll("cropExpenceList.expenceItemValue.cropActivityItem");
		String[] itemExpences = params.getAll("cropExpenceList.expenceItemValue.itemExpence");
		String[] labourExpences = params.getAll("cropExpenceList.expenceItemValue.labourExpence");
		String[] taskIds = params.getAll("taskId");
		models.ExpenceItemValue expenceItemValue = null;
		models.CropActivityItem cropActivityItem = null;
		models.CropActivityType cropActivityType = null;
		models.CropCalenderTask cropCalenderTask = null;
		List<models.ExpenceItemValue> expenceItemValueList = new ArrayList<>();
		float itemExp = 0;
		float labourExp = 0;
		for(int i = 0; cropActivityItems!=null && i<cropActivityItems.length;i++)
		{
			expenceItemValue = new models.ExpenceItemValue();
			if(cropActivityItems[i]!=null && cropActivityItems[i].length()>0)
			cropActivityItem = models.CropActivityItem.findById(Long.parseLong(cropActivityItems[i]));
			expenceItemValue.cropActivityItem = cropActivityItem;
			
			if(taskIds[i]!=null && taskIds[i].length()>0)
			cropCalenderTask = models.CropCalenderTask.findById(Long.parseLong(taskIds[i]));
			expenceItemValue.cropCalenderTask = cropCalenderTask;
						
			if(itemExpences[i]!=null && itemExpences[i].length()>0)
			itemExp = Float.parseFloat(itemExpences[i]);
			expenceItemValue.itemExpence = itemExp;
			if(labourExpences[i]!=null && labourExpences[i].length()>0)
			labourExp = Float.parseFloat(labourExpences[i]);
			expenceItemValue.labourExpence = labourExp;
			
			expenceItemValueList.add(expenceItemValue);
		}
		if(repoCropExpenceList != null) cropExpenceList = repoCropExpenceList;
		cropExpenceList.expenceItemValueList = expenceItemValueList;
		if(Validation.hasErrors()) {
			flash.error("Customer "+cropExpenceList.cropTaskMap.id+" could not be saved!Error="+Validation.errors().toString());
			Logger.info("Error!!  "+ Validation.errors().toString());

			//List<models.CropActivity> cropActivityList = models.CropActivity.findAll();
			//List<models.CropActivityType> cropActivityTypeList = models.CropActivityType.findAll();
			List<models.ExpenceItem> expenceItemList = models.ExpenceItem.findAll();
			render("@createTaskExpenditure", cropTaskMap, expenceItemList, cropExpenceList);
		}
		cropExpenceList.type = cropTaskMap.type;
		cropExpenceList.crop = cropTaskMap.crop;
		cropExpenceList.varity = cropTaskMap.varity;
		
		cropExpenceList.save();
		List<models.CropExpenceList> cropExpences = models.CropExpenceList.findAll();
		render("@activitylist",cropExpences);
    }
    
    @ExternalRestrictions("Farmer Task Expenditure")
	public static void farmerTaskExpenditure(Long id){
    	System.out.println("id:::"+id);
    	models.CropExpenceList cropExpenceList = models.CropExpenceList.findById(id);
    	CropTaskMap cropTaskMap = models.CropTaskMap.findById(id);
    	//List<models.CropActivity> cropActivityList = models.CropActivity.findAll();
    	//List<models.CropActivityType> cropActivityTypeList = models.CropActivityType.findAll();
    	List<models.ExpenceItem> expenceItemList = models.ExpenceItem.findAll();
    	
		render(cropExpenceList,expenceItemList);
	}

   @ExternalRestrictions("View User")
    public static void otherdb() {
	//Connection conn = play.db.DB.getDBConfig("other").getConnection();
	javax.persistence.EntityManager em = play.db.jpa.JPA.em("other");
	//Query q 
	List<Object[]> crops = em.createNativeQuery("SELECT a.name, a.type FROM crops a").getResultList();
	List<Object[]> types = em.createNativeQuery("SELECT distinct a.type FROM crops a").getResultList();
	String query = "SELECT a.crop_id,a.name, a.local_name, a.spec,a.production_with_irrigation,a.production_without_irrigation,"
					+"a.life_time, a.special FROM varieties a where crop_id<>'' and crop_id is not null and deleted_at is null";
					System.out.println("query::::"+query);
	List<Object[]> varities = em.createNativeQuery(query).getResultList();
	for (Object[] a : crops) {
	    /*System.out.println("Author "
		    + a[0]
		    + " "
		    + a[1]);
		*/
	}
	
	for (Object a : types) {
	    System.out.println("variety "
		    + a);
	}
	render(crops,varities);
    }
}
