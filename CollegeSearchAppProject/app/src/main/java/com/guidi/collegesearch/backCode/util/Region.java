package com.guidi.collegesearch.backCode.util;

public enum Region {
    SERVICE_SCHOOL(0, "U.S. Service Schools"),NEW_ENGLAND(1, "New England"),
    MID_EAST(2,"Mid East"),GREAT_LAKES(3,"Great Lakes"),PLAINS(4,"Plains"),
    SOUTHEAST(5,"Southeast"), SOUTHWEST(6,"Southwest"), ROCKY_MOUNTAINS(7,"Rocky Mountains"),
    FAR_WEST(8,"Far West"), OUTLYING_AREAS(9,"Outlying Areas");

    private final int regID;
    private final String regionS;
    Region(int regID, String regionS){
        this.regID = regID;
        this.regionS = regionS;
    }
    public static String getRegionS(int regID){
        switch(regID){
        case 0:
            return SERVICE_SCHOOL.regionS;
        case 1:
            return NEW_ENGLAND.regionS;
        case 2:
            return MID_EAST.regionS;
        case 3:
            return GREAT_LAKES.regionS;
        case 4:
            return PLAINS.regionS;
        case 5:
            return SOUTHEAST.regionS;
        case 6:
            return SOUTHWEST.regionS;
        case 7:
            return ROCKY_MOUNTAINS.regionS;
        case 8:
            return FAR_WEST.regionS;
        case 9:
            return OUTLYING_AREAS.regionS;
        }
        return null;
    }
}
