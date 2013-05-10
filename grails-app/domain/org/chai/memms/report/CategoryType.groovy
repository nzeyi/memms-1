package org.chai.memms.report

class CategoryType {
	String code
	String name
	//color not yet decided
	Double minYellowValue
	Double maxYellowValue 
	
	//*indicatorTypes
	
	static hasMany = [indicators:Indicator]
	static mapping ={
		table "memms_report_category_type"
		version false
		content type:"text"
		
	}
	
	static constraints = {
		code (blank:false, nullable:false)
		name (blank:false, nullable:false,unique: true, size:3..100)
		
		
	}
	@Override
	public String toString() {
		return name;
	}
}
