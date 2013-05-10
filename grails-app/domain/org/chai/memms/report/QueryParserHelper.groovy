package org.chai.memms.report

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 
 * @author donatien
 *
 *The helper used when reading the script formula from the indicator type
 */
class QueryParserHelper {
	String executableScript
	String classDomaine
	Boolean useCountFunction=false
	String followOperand="add"
	Boolean isDenominator
	Boolean isIntermidiateVariable=false
	Boolean isCriteria=false
	Boolean isDynamicFinder=false
	String indicatorType
	
	

	static belongsTo = [indicator:Indicator]
	static mapping ={
		table "memms_report_query_parser"
		version false
		content type:"text"
		cache true
	}
	static constraints = {
		executableScript blank:true, nullable:true,size:3..1000
	}
	@Override
	public String toString() {
		return executableScript;
	}
	
	
}
