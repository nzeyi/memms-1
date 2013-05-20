package org.chai.memms.report.utils

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Iterator;

class ExecutorProvider {

	

	//REPORTS CATEGORIES FINDERS
	//====================
	static final String CORRECTIVE_MAINTENANCE="CORRECTIVE MAINTENANCE"
	static final String PRIVENTIVE_MAINTENANCE="PRIVENTIVE MAINTENANCE"
	static final String MANAGEMENT_MEDICAL_EQUIPEMENT="MANAGEMENT MEDICAL EQUIPEMENT"
	static final String MANAGEMENT_OF_SPARE_PARTS="MANAGEMENT OF SPARE PARTS"
	static final String MANAGEMENT_MEMMS_USERS="MANAGEMENT MEMMS USERS"

	// REPORT CATEGORY COLOR EVALUATORS
	//======================================
	// REPORT CATEGORY COLOR EVALUATORS COLLECTIVE MAINTENANCE
	// NAMES
	static final String REPORT_CATEGORY_EVALUATOR_GREEN="green"
	static final String REPORT_CATEGORY_EVALUATOR_YELLOW="yellow"
	static final String REPORT_CATEGORY_EVALUATOR_RED="red"
	//values
	//GREEN
	static final String REPORT_CATEGORY_EVALUATOR_GREEN_VALUE_MIN="81"
	static final String REPORT_CATEGORY_EVALUATOR_GREEN_VALUE_MAX="100"
	//YELLOW
	static final String REPORT_CATEGORY_EVALUATOR_YELLOW_VALUE__MIN="60"
	static final String REPORT_CATEGORY_EVALUATOR_YELLOW_VALUE_MAX="80"
	//RED
	static final String REPORT_CATEGORY_EVALUATOR_RED_VALUE_MIN="0"
	static final String REPORT_CATEGORY_EVALUATOR_RED_VALUE_MAX="59"



	//INDICATORS FINDERS
	//====================
	//Management medical EQUIPMENT_:

	static final String MANA_EQUIPMENT_SHARE_OF_OPERATIONAL_EQUIPMENT="MANA_EQUIPMENT_SHARE_OF_OPERATIONAL_EQUIPMENT"
	static final String MANA_EQUIPMENT_QUANTITY_OF_EQUIPMENT__FOR_DISPOSAL="MANA_EQUIPMENT_QUANTITY_OF_EQUIPMENT__FOR_DISPOSAL"
	static final String MANA_EQUIPMENT_QUANTITY_OF_EQUIPMENT__IN_STOCK="MANA_EQUIPMENT_QUANTITY_OF_EQUIPMENT__IN_STOCK"
	static final String MANA_EQUIPMENT_DEGREE_OF_EQUIPMENT__STANDARDIZATION="MANA_EQUIPMENT_DEGREE_OF_EQUIPMENT__STANDARDIZATION"
	static final String MANA_EQUIPMENT_SHARE_OF_EQUIPMENT__DONATED="MANA_EQUIPMENT_SHARE_OF_EQUIPMENT__DONATED"
	static final String MANA_EQUIPMENT_SHARE_OF_EQUIPMENT_UNDER_ACTIVE_WARRANTY_OR_UNDER_ACTIVE_SERVICE_PROVIDER_CONTRACT="MANA_EQUIPMENT_SHARE_OF_EQUIPMENT_UNDER_ACTIVE_WARRANTY_OR_UNDER_ACTIVE_SERVICE_PROVIDER_CONTRACT"
	static final String MANA_EQUIPMENT_SHARE_OF_EQUIPMENT_THAT_IS_OBSOLETE="MANA_EQUIPMENT_SHARE_OF_EQUIPMENT_THAT_IS_OBSOLETE"
	static final String MANA_EQUIPMENT_SHARE_OF_EQUIPMENT_WITH_LIFETIME_EXPIRING_IN_LESS_THAN_2YEARS="MANA_EQUIPMENT_SHARE_OF_EQUIPMENT_WITH_LIFETIME_EXPIRING_IN_LESS_THAN_2YEARS"

	//Preventive maintenance :

	static final String PRIV_MAINT_AVERAGE_IN_EWAITING_FOR_SPAREPARTS="AVERAGE IN WAITING FOR SPAREPARTS"
	static final String PRIV_MAINT_COST_EFFECTIVENESS_OF_PREVENTIVE_MAINTENANCE="COST EFFECTIVENESS OF PREVENTIVE MAINTENANCE"
	static final String PRIV_MAINT_DEGREE_EXECUTION="DEGREE EXECUTION"
	static final String PRIV_MAINT_SHARE_OF_MAINTENANCE_EXECUTED_AT_THE_FACILITY="SHARE O FMAINTENANCE EXECUTED AT THE FACILITY"
	static final String PRIV_MAINT_DEVIATION_AGAINST_SCHEDULED_DATE="DEVIATION AGAINST SCHEDULED DATE"

	// Corrective maintenance :

	static final String CORRE_MAINT_TOTAL_NO_OF_WORK_ORDERS_PER_QUARTER="TOTAL NO OF WORK ORDERS PER QUARTER"
	static final String CORRE_MAINT_DEGREE_OF_CORRECTIVE_MAINTENANCE_EXECUTION_ACCORDING_TO_BENCHMARK="DEGREEOFCORRECTIVEMAINTENANCEEXECUTIONACCORDINGTOBENCHMARK"
	static final String CORRE_MAINT_DEGREE_OF_POSITIVE_FEEDBACK_FROM_EQUIPMENT_USER_POST_CORRECTIVE_MAINTENANCE="DEGREEOFPOSITIVEFEEDBACKFROMEQUIPMENT_USERPOSTCORRECTIVEMAINTENANCE"
	static final String CORRE_MAINT_SHARE_OF_WORK_ORDERS_WITNESSING_REINCIDENCE_IN_A_PERIOD_OF_TIME_USERDEFINED="SHARE_OF_WORKORDERSWITNESSINGREINCIDENCEINAPERIODOFTIMEUSERDEFINED"
	static final String CORRE_MAINT_COST_EFFECTIVENESS_OF_CORRECTIVE_MAINTENANCE="COST EFFECTIVENESS OF CORRECTIVE MAINTENANCE"
	static final String CORRE_MAINT_SHARE_OF_WORK_ORDERS_ESCALETED_T_OMMC="SHARE_OF_WORKORDERSESCALETEDTOMMC"
	static final String CORRE_MAINT_SHARE_OF_WORK_ORDERS_OUTSOURCED_TO_3RDPARTIES="SHARE_OF_WORKORDERSOUTSOURCEDTO3RDPARTIES (EXCLUDING WARRANTY/SERVICE PROVIDER CONTRACTS)"
	static final String CORRE_MAINT_SHARE_OF_WORK_ORDERS_UNDER_WARRANTY_SERVICE_PROVIDER_CONTRACTS="SHARE  OF WORK ORDERS UNDER WARRANTY/SERVICE PROVIDER CONTRACTS"
	static final String CORRE_MAINT_SHARE_OF_WORK_ORDERS_EXECUTED_UNDERWARRANTY_SERVICE_PROVIDER_CONTRACTS=" SHARE  OF WORK ORDERS EXECUTED UNDER WARRANTY/SERVICE PROVIDER CONTRACTS"
	static final String CORRE_MAINT_AVERAGE_TIME_TO_FIX_EQUIPMENT="AVERAGE TIME TO FIX EQUIPMENT"
	static final String CORRE_MAINT_AVERAGE_TIME_TO_FIX_EQUIPMENT_BYDHTECHNICIAN="AVERAGE_ TIME TO FIX EQUIPMENT_ BY DH TECHNICIAN"
	static final String CORRE_MAINT_AVERAGE_TIME_TO_FIX_EQUIPMENT_BYMMC="AVERAGE_ TIME TO FIX EQUIPMENT_ BY MMC"
	static final String CORRE_MAINT_AVERAGE_TIME_TO_FIX_EQUIPMENT_BY3RDPARTIES="AVERAGE_ TIME TO FIX EQUIPMENT_ BY 3RD PARTIES (EXCLUDING WARRANTY/SERVICE PROVIDER CONTRACTS)"
	static final String CORRE_MAINT_AVERAGE_TIME_TO_FIX_EQUIPMENT_BYWARRANTY_SERVICE_PROVIDER="AVERAGE_ TIME TO FIX EQUIPMENT_ BY WARRANTY/SERVICE PROVIDER"
	static final String CORRE_MAINT_SHARE_OF_WORK_ORDERS_REQUIRING_NEW_SPAREPARTS="SHARE OF WORK ORDERS REQUIRING NEW SPARE PARTS"
	static final String CORRE_MAINT_AVERAGE_TIME_WAITING_FOR_SPAREPARTS="AVERAGE_ TIME WAITING FOR SPARE PARTS"
	static final String CORRE_MAINT_SHARE_OF_PROBLEMS_CAUSED_BY_MISUSE="SHARE OF PROBLEMS CAUSED BY MISUSE"

	//  Management of spare parts:

	static final String MANA_SPARE_NUMBER_OF_TYPES_OF_SPAREPARTS_ABOUT_TO_BE_DISCONTINUED_IN_AYEAR="NUMBER OF TYPES OF SPARE PARTS ABOUT TO BE DISCONTINUED IN A YEAR"
	static final String MANA_SPARE_NUMBER_OF_TYPES_OF_SPAREPARTS_ABOUT_TO_STOCKOUT_IN_AGIVEN_TIME_PERIOD="NUMBER OF TYPES OF  SPARE PARTS ABOUT TO STOCK OUT IN A GIVEN TIME PERIOD"
	static final String MANA_SPARE_NUMBER_OF_TYPES_OF_SPAREPARTS_WITH_STOCK_MORE_THAN_A_GIVEN_TIME_PERIOD="NUMBER OF TYPES OF  SPARE PARTS WITH STOCK MORE THAN A GIVEN TIME PERIOD"
	static final String MANA_SPARE_AVERAGE__STOCK_TIME="AVERAGE_ STOCK TIME"
	static final String MANA_SPARE_AVERAGE__TIME_BETWEEN_REQUEST_AND_ARRIVAL_OF_SPAREPARTS="AVERAGE_ TIME BETWEEN REQUEST AND ARRIVAL OF SPARE PARTS"

	//Monitoring of MEMMS use

	static final String MON_MEMMS_AVERAGE_NUMBER_OF_EQUIPMENT_PER_FACILITY="AVERAGE_ NUMBER OF EQUIPMENT_ PER FACILITY"
	static final String MON_MEMMS_SHARE_OF_FACILITIES_WITH_NUMBER_OF_INVENTORYSTATUSCHANGE_SLESS_THAN_10INAYEAR="SHARE OF FACILITIES WITH NUMBER OF INVENTORY STATUS CHANGES LESS THAN 10 IN A YEAR"
	static final String MON_MEMMS_SHARE_OF_FACILITIES_WITH_NUMBER_OF_WORKORDERS_LESSTHAN_10INAYEAR="SHARE OF FACILITIES WITH NUMBER OF WORK ORDERS LESS THAN 10 IN A YEAR"
	static final String MON_MEMMS_AVERAGE_PERCENTAGE_OF_EQUIPMENTS_PER_FACILITY_HAVING_A_PREVENTIVE_MAINTENANCE_PLAN="AVERAGE PERCENTAGE OF EQUIPMENT_S PER FACILITY HAVING A PREVENTIVE MAINTENANCE PLAN"
	static final String MON_MEMMS_SHARE_OF_FACILITIES_WITH_NO_PREVENTIVE_MAINTENANCE_PLAN_FOR_MORE_THAN10_OF_THE_EQUIPMENT_="SHARE OF FACILITIES WITH NO PREVENTIVE MAINTENANCE PLAN FOR MORE THAN 10% OF THE EQUIPMENT"
	static final String MON_MEMMS_SHARE_OF_WAREHOUSES_WITH_NUMBER_OF_SPAREPART_INVENTORYSTATUS_CHANGE_SLESSTHAN_20INAMONTH="SHARE OF WAREHOUSES WITH NUMBER OF SPARE PART INVENTORY STATUS CHANGES LESS THAN 20 IN A MONTH"


	public static boolean isInt(String anyValue) {

		try {
			Integer.parseInt(anyValue);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Test if something is a long number
	 *
	 * @param anyValue
	 *            value to test
	 * @return boolean test result
	 */
	public static boolean isLong(String longNumber) {
		try {
			Long.parseLong(longNumber);
			return true;

		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	/**
	 * Test if something is a double number
	 *
	 * @param anyValue
	 *            value to test
	 * @return boolean test result
	 */
	public static boolean isDouble(String doubleNumber) {
		try {
			Double.parseDouble(doubleNumber);
			return true;

		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	/**
	 * Subtracts any number from the gate input
	 *
	 * @param dateInAdvence
	 *            the date input
	 * @param positiveAdvencedDays
	 *            the number input to subtract from the date
	 * @return dateDiff the date reduced of positiveAdvencedDays
	 */
	public static String datesAndDaysDiff(Date dateInAdvence,
			int positiveAdvencedDays) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateDiff = null;

		try {
			if (positiveAdvencedDays > 0) {

				Calendar cal = Calendar.getInstance();
				cal.setTime(dateInAdvence);
				cal.add(Calendar.DATE, -positiveAdvencedDays);

				dateDiff = formater.format(cal.getTime());
			} else
				dateDiff = formater.format(new Date());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dateDiff;
	}

	/**
	 * Generates the hashed value of any string
	 *
	 * @param input
	 *            any string to be hashed
	 * @return the hashed value of the input
	 */
	public static int hashCodeGenerator(String input) {
		StringBuilder builder = new StringBuilder();
		builder.append(input);
		builder.append(5);
		return builder.toString().hashCode();
	}

	/**
	 * Generates the id for any bean
	 *
	 * @param input
	 *            form the bean description
	 *
	 * @return anyid the generated id for the bean in question
	 */
	public static String idGenerator(String input) {

		String anyid = null;

		StringBuffer buffer = new StringBuffer();
		for (int x = 0; x < 8; x++) {
			buffer.append((char) ((int) (Math.random() * 26) + 97));
		}

		anyid = Math.abs(hashCodeGenerator(buffer + input)) + "";

		return anyid;
	}


}
