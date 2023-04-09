package cm.pdl.plandelocalisation.plan.utils;

import cm.pdl.plandelocalisation.plan.dto.PlaceDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Georges DEFO
 * @date 25/07/2022
 */
@Slf4j
public class MapUtils {

    private static final String PREFIX_PDL = "PDL";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter PDL_ID_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyHHmmss");

    private static final ZoneId CAMEROUN_ZONE_ID = ZoneId.of("Africa/Douala");

    public static String pdlUniqueIdentifier(PlaceDTO place, LocalDateTime localDate) {
        log.info("Place used to calculate the unique id: {}", place);
        if (place.getAddress() == null || StringUtils.isEmpty(place.getAddress().getISO31662Lvl4())
                || StringUtils.isEmpty(place.getPlace_id())) {
            log.error("could not generate unique identifier");
            return "";
        }

        long placeId = Long.parseLong(place.getPlace_id());
        long time = Long.parseLong(pdlZonedCreationDate(localDate, PDL_ID_DATE_TIME_FORMATTER));
        long identifierBody = placeId + time;

        String suffixPDL = place.getAddress().getISO31662Lvl4().replace("-", "");

        return PREFIX_PDL +
                identifierBody +
                suffixPDL;
    }

    public static String pdlZonedCreationDate(LocalDateTime localDate, DateTimeFormatter formatter) {
        if (localDate == null) return "";
        ZonedDateTime creationDateTime = localDate.atZone(CAMEROUN_ZONE_ID);
        return formatter == null ? DATE_TIME_FORMATTER.format(creationDateTime) : formatter.format(creationDateTime);
    }

    public static String pdlZonedExpirationDate(String creationDateTime) {
        try {
            LocalDate zonedCreationDate = LocalDate.parse(creationDateTime, DATE_TIME_FORMATTER);
            ZonedDateTime expirationDateTime = LocalDateTime.of(
                            zonedCreationDate,
                            LocalTime.of(0, 0, 0, 0))
                    .atZone(CAMEROUN_ZONE_ID)
                    .plusMonths(1);
            return DATE_TIME_FORMATTER.format(expirationDateTime);
        } catch (DateTimeParseException e) {
            log.error("Could not parse the string date {}", creationDateTime, e);
        }
        return "";
    }

}
