package org.chai.memms.report.utils

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.chai.memms.report.IndicatorType

/**
 * 
 * @author donatien
 *
 *The helper used when reading the script formula from the indicator type
 */
class QueryParserHelper {
	String excutableScript
	String classDomaine
	Boolean useCountFunction=false
	String followOperand="add"
	Boolean isDenominator
	Boolean isIntermidiateVariable=false
	Boolean isDynamicFinder=false
	IndicatorType indicatorType
	String indType

	static belongsTo = [indicatorType:IndicatorType]
	static mapping ={
		table "memms_report_query_parser"
		version false
		content type:"text"
		cache true
	}
	static constraints = {
		excutableScript blank:true, nullable:true,size:3..1000
	}
	@Override
	public String toString() {
		return excutableScript;
	}
	
	
}
