package cm.pdl.plandelocalisation.plan.utils;

import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;

import java.util.Locale;

/**
 * @author Georges DEFO
 * @date 25/07/2022
 */
public class MapUtils {

    private static final String PREFIX_PDL = "PDL";

    public static String pdlUniqueIdentifier(PlaceDTO place) {
        if (place.getAddress() == null || place.getAddress().getState() == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(PREFIX_PDL);
        builder.append(place.getPlace_id());
        builder.append(place.getAddress().getState().toUpperCase().substring(0, 3));

        return builder.toString();
    }
}
