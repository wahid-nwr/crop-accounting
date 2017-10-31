package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

public class ExtraUtils {

	public static Date getTodayStart(){
		 return getTodayPlus(0);
	}
	
	public static Date getTodayPlus(int plusDay){
		Calendar now =new GregorianCalendar();
        Calendar start = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        Date todayPlus = new Date(new DateTime(start.getTime()).plusDays(plusDay).getMillis());
        
        return todayPlus;
	}
	public static Date getTodayMinus(int plusDay){
		Calendar now =new GregorianCalendar();
        Calendar start = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        Date todayPlus = new Date(new DateTime(start.getTime()).minusDays(plusDay).getMillis());
        
        return todayPlus;
	}
	
	public static Date startPoint(Date date){
		Calendar now =new GregorianCalendar();
		now.setTime(date);
		Calendar start = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
	    return  new Date(new DateTime(start.getTime()).plusDays(0).getMillis());
	}

	public static Date endPoint(Date date){
		return  new Date (ExtraUtils.startPoint(
				new DateTime(date).plusDays(1).toDate()
				).getTime()-1
				);
	}
	
	/**
	 * convert a java.util.Date to a string to given format. i.e. if format = 'dd-mm-YYYY' then dateString = 08-05-2013 
	 * */
	public static String getDateString(Date date, String format) {
		String dateString = "";
		if(date != null) {
			DateTime dt = new DateTime(date);
			int month = dt.getMonthOfYear();
			int day = dt.getDayOfMonth();
			int year = dt.getYearOfEra();
			
			format = format.toLowerCase();
			format = format.replace("dd", StringUtils.leftPad(""+day, 2, "0"));
			format = format.replace("mm", StringUtils.leftPad(""+month, 2, "0"));
			format = format.replace("yyyy", ""+year);
			
			dateString = new String(format);
		}
		
		return dateString;
	}
	
	/** Method to split an inbound StringBuffer by (consumed) tokens and produce a List 
	* @param StringBuffer sbx - the StringBuffer to split
	* @param StringBuffer sbTok - a StringBuffer representation of token(s) to use to split
	* @return List of StringBuffers split out
	*/
	public static List split(StringBuffer sbx, StringBuffer sbTok)
	    {
	    int tokSz = sbTok.length();
	    List lix = new ArrayList();
	    List<Integer> lPos = getTokenPositions(sbx, sbTok );
	    if( lPos.isEmpty() || lPos == null) // no split?  send the original sb back
	    {
		lix.add(sbx);
		return lix;
	    }
	 
	    int start = 0;
	    if(lPos.get(0) == 0)
	    {
		//System.out.println("oooooooooooooooooooooooooooooooooooooooo");
	    start += tokSz;
	    }
	    StringBuffer sbnew = new StringBuffer();
	    int iSz = lPos.size();
	 
		for (int i = 0; i < iSz; i++) 
		{
		        sbnew = new StringBuffer(sbx.subSequence(start, lPos.get(i)));
			start = lPos.get(i) + tokSz;
			//System.out.println("start::"+start+":lPos.get(i):"+lPos.get(i));
		
		    

		    //System.out.println("i::"+i+"::"+sbnew.toString());
		    lix.add(sbnew);
		}
	    if(lPos.get(lPos.size()-1)<sbx.length())
	    {
		
		sbnew = new StringBuffer(sbx.subSequence(start, sbx.length()));
    	        lix.add(sbnew);
	    }	
	 
	    return lix;
    	}		
	
	public static List<Integer> getTokenPositions(StringBuffer sbx, StringBuffer tok )
	{
	    List liTok = charListFromSb(tok);
		//System.out.println("liTok::"+liTok);
	    List liOut = new ArrayList();
	    int sz = tok.length() - 1;
	    int finish = sbx.length() - sz;
	    char firstTok = tok.charAt(0);
	    char lastTok = tok.charAt(sz);
		for (int i = 0; i < finish; i++) {
		    if ( (sbx.charAt(i) == firstTok)   && (sbx.charAt(i + sz) == lastTok) )
		    {
		    List comp =  charListFromSb(sbx, i, i+ sz);

		    if (comp.equals(liTok))
		      {
		        boolean add = liOut.add(i);
		      }
		    }
		}
		//System.out.println("liOut::"+liOut);
	    return liOut;
	}
	 
	public static List charListFromSb(StringBuffer sbx)
	{
		List liOut = new ArrayList();
		int iEnd = sbx.length();
		for (int i = 0; i < iEnd; i++) {
		    boolean add = liOut.add(sbx.charAt(i));
		}
	 
	    return liOut;
	}
	
	public static List<Character> charListFromSb(StringBuffer sbx, int start, int finish)
	{
		
		List<Character> liOut = new ArrayList<Character>();
		for (int i = start; i <= finish; i++) {
		    boolean add = liOut.add(sbx.charAt(i));
		}
	    return liOut;
	}
	public static boolean ifContainsChild(String parent,List<models.Aco> acoList)
	{
		System.out.println("acoList::"+acoList.size());
		boolean contains = false;
		for(models.Aco aco:acoList)
		{
			if(parent.equals(aco.parent))
			{
				System.out.println("returning true:::"+aco.parent);
				return true;
			}
		}
		return contains;
	}
}
