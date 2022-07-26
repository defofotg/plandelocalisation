package cm.pdl.plandelocalisation.plan.utils;

import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author Georges DEFO
 * @date 25/07/2022
 */
public class MapUtils {

    private static final String PREFIX_PDL = "PDL";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final ZoneId CAMEROUN_ZONE_ID = ZoneId.of("Africa/Douala");

    public static String pdlUniqueIdentifier(PlaceDTO place) {
        if (place.getAddress() == null || place.getAddress().getState() == null) {
            return null;
        }

        return PREFIX_PDL +
                place.getPlace_id() +
                place.getAddress().getState().toUpperCase().substring(0, 3);
    }

    public static String pdlZonedCreationDate() {
        LocalDateTime localDate = LocalDateTime.now();
        ZonedDateTime creationDateTime = localDate.atZone(CAMEROUN_ZONE_ID);
        return DATE_TIME_FORMATTER.format(creationDateTime);
    }

    public static String pdlZonedExpirationDate(String creationDateTime) {
        LocalDate zonedCreationDate = LocalDate.parse(creationDateTime, DATE_TIME_FORMATTER);
        ZonedDateTime expirationDateTime = LocalDateTime.of(
                        zonedCreationDate,
                        LocalTime.of(0, 0, 0, 0))
                .atZone(CAMEROUN_ZONE_ID)
                .plusMonths(1);
        return DATE_TIME_FORMATTER.format(expirationDateTime);
    }

}
