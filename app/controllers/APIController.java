package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.asn1.x509.UserNotice;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

import controllers.deadbolt.Unrestricted;

import play.Logger;
import play.Play;
import play.classloading.enhancers.ControllersEnhancer.ByPass;
import play.data.FileUpload;
import play.libs.F.Promise;
import play.libs.XML;
import play.libs.XPath;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Util;
import sun.java2d.pipe.SpanShapeRenderer.Simple;

import models.UserModel;
/**
 * Mobile Controller - Mobile API end points handlers.
 */
public class APIController extends Controller {
	/**
	 * Mobile response -
	 * list of crops
	 *
	 */
	@annotations.Mobile
   	public static void getCrops() {
		List<models.CropExpenceList> cropExpenceList = models.CropExpenceList.findAll();
		List<models.UserModel> userList = models.UserModel.findAll();
		renderJSON(cropExpenceList);
	}
	
	@annotations.Mobile
   	public static void submitCrops() {
		renderJSON("");
	}

	@annotations.Mobile
   	public static void getExpense(long id) {
		renderJSON("");
	}
}
