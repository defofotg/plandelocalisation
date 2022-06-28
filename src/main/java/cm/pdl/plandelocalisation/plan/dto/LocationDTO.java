package cm.pdl.plandelocalisation.plan.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Georges DEFO
 * @date 15/06/2022
 */
@Getter
@Setter
@RequiredArgsConstructor
public class LocationDTO {
    PositionDTO center;
    ImageSizeDTO size;
    String zoom;
    String mapType;
    MarkerDTO marker;

    public String getMapUrl() {
        return "&center=" + center.getLatitude() + "," + center.getLongitude() +
                "&size=" + size.getHeight() + "x" + size.getWidth() +
                "&zoom=" + zoom +
                "&maptype=" + mapType +
                "&markers=icon:" + marker.getIconUrl() + "|"+ center.getLatitude()+","+ center.getLongitude();
    }
}
