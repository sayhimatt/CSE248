package util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This holds the enum State since there is a limited amount of state's to register in
 * @author Matt Guidi
 *
 */
public enum State {

    ALABAMA("Alabama", "AL"), ALASKA("Alaska", "AK"), ARIZONA("Arizona", "AZ"), ARKANSAS(
            "Arkansas", "AR"), CALIFORNIA("California", "CA"), COLORADO("Colorado", "CO"), CONNECTICUT("Connecticut", "CT"), DELAWARE(
            "Delaware", "DE"), DISTRICT_OF_COLUMBIA("District of Columbia", "DC"), FLORIDA("Florida", "FL"), GEORGIA("Georgia", "GA"), HAWAII(
            "Hawaii", "HI"), IDAHO("Idaho", "ID"), ILLINOIS("Illinois", "IL"), INDIANA("Indiana", "IN"), IOWA("Iowa", "IA"), KANSAS(
            "Kansas", "KS"), KENTUCKY("Kentucky", "KY"), LOUISIANA("Louisiana", "LA"), MAINE("Maine", "ME"), MARYLAND("Maryland", "MD"),
    MASSACHUSETTS("Massachusetts", "MA"), MICHIGAN("Michigan", "MI"), MINNESOTA("Minnesota", "MN"), MISSISSIPPI(
            "Mississippi", "MS"), MISSOURI("Missouri", "MO"), MONTANA("Montana", "MT"), NEBRASKA("Nebraska", "NE"), NEVADA("Nevada",
            "NV"), NEW_HAMPSHIRE("New Hampshire", "NH"), NEW_JERSEY("New Jersey", "NJ"), NEW_MEXICO("New Mexico", "NM"), NEW_YORK(
            "New York", "NY"), NORTH_CAROLINA("North Carolina", "NC"), NORTH_DAKOTA("North Dakota", "ND"), OHIO("Ohio", "OH"), OKLAHOMA("Oklahoma", "OK"), OREGON("Oregon", "OR"),
    PENNSYLVANIA("Pennsylvania", "PA"), RHODE_ISLAND("Rhode Island", "RI"), SOUTH_CAROLINA(
            "South Carolina", "SC"), SOUTH_DAKOTA("South Dakota", "SD"), TENNESSEE("Tennessee", "TN"), TEXAS("Texas", "TX"), UTAH(
            "Utah", "UT"), VERMONT("Vermont", "VT"), VIRGINIA("Virginia", "VA"), WASHINGTON(
            "Washington", "WA"), WEST_VIRGINIA("West Virginia", "WV"), WISCONSIN("Wisconsin", "WI"), WYOMING("Wyoming", "WY"), NO_STATE(
            "No State", "N/A");

    /**
     * The state's name.
     */
    private String name;

    /**
     * The state's abbreviation.
     */
    private String abbreviation;

    /**
     * The set of states addressed by abbreviations.
     */
    private static final Map<String, State> STATES_BY_ABBR = new HashMap<String, State>();

    /* static initializer */
    static {
        // for : each
        // Go through each state of the possible values
        for (State state : values()) {
            // then throw it into the Map so we can get the State enum from each String of the acronym
            STATES_BY_ABBR.put(state.getAbbreviation(), state);
        }
    }

    State(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the state's abbreviation.
     *
     * @return the state's abbreviation.
     */
    public String getAbbreviation() {
        return abbreviation;
    }
    /**
     *
     * @return the list of all abbreviation's
     */
    public static ArrayList<String> getAbbreviationList() {
        ArrayList<String> abbrList = new ArrayList<String>();

        for(State state : values()) {
            abbrList.add(state.abbreviation);
        }
        return abbrList;
    }
    public static ArrayList<String> getStateNameList(){
        ArrayList<String> nameList = new ArrayList<String>();
        for(State state : values()) {
            nameList.add(state.name);
        }
        return nameList;
    }

    /**
     * Gets the enum constant with the specified abbreviation.
     *
     * @param abbr the state's abbreviation.
     * @return the enum constant with the specified abbreviation.
     */
    public static State valueOfAbbreviation(final String abbr) {
        final State state = STATES_BY_ABBR.get(abbr);
        if (state != null) {
            return state;
        } else {
            return NO_STATE;
        }
    }

    public static State valueOfName(final String name) {
        final String enumName = name.toUpperCase().replaceAll(" ", "_");
        try {
            return valueOf(enumName);
        } catch (final IllegalArgumentException e) {
            return State.NO_STATE;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}