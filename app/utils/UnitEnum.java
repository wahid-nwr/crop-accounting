package utils;
public enum UnitEnum {
    katha (1,"কাঠা", 1.65),
    bigha (2,"বিঘা", 1.65*20),
    acre (3,"একর", 1.65*20*3);


    UnitEnum(int id, String unitsName, double convertionRate)
    {
	this.id = id;
	this.unitsName = unitsName;
	this.convertionRate = convertionRate;
    }	

    private final int id;
    private final String unitsName;   // in kilograms
    private final double convertionRate; // in meters

    private int id() { 
	return id; 
    }
    private String unitsName() { 
	return unitsName; 
    }
    private double convertionRate() { 
	return convertionRate; 
    }
}
