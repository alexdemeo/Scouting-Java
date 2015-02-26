package org.fullmetalfalcons.data;

public enum DataType {

	STRING,INT,BOOLEAN,TRILEAN,INT_REPLACE,INT_FAIL,MATH;
	
	public static DataType getDataType(String type){
		type = type.toLowerCase();
		switch (type){
		case "string":
			return STRING;
		case "int":
			return INT;
		case "boolean":
			return BOOLEAN;
		case "trilean":
			return TRILEAN;
		case "int-replace":
			return INT_REPLACE;
		case "int-fail":
			return INT_FAIL;
		case "math":
			return MATH;
		default:
			return null;
		}
	}
}
