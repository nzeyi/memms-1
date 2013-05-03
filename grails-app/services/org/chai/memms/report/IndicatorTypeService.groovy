package org.chai.memms.report
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import  groovy.sql.Sql
@Transactional
class IndicatorTypeService {
	static transactional = true
	def sessionFactory;


	def dataSource  //Auto Injected

	public  List<Object> getIndicatorTypes(String sqlQuery){

		def sql = Sql.newInstance(dataSource)

		def rows = sql.rows(""+sqlQuery+"")

		//rows.collect{ new IndicatorType(it) }
		return rows
	}
	public  int getIndicatorTypeValue(String sqlQuery){
		
				def sql = Sql.newInstance(dataSource)
		
			def rows = sql.rows(""+sqlQuery+"")
		
				" -----------------------------returned an list of meny records"
					
					return rows.size()
				
			
				return 0
			}
	public  List<IndicatorType> getIndicatorTypes(){
		def indicators=IndicatorType.findAll()
		println "All indicators :"+indicators
		return indicators
	}

	public List<ParsedQuery> parseIndicatorScript(List<IndicatorType> indicators){
		if(indicators!=null){
		}else
			println"Indicators not found"

		return null
	}
}
