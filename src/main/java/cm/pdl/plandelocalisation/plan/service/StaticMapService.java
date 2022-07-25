package cm.pdl.plandelocalisation.plan.service;

/**
 * @author Georges DEFO
 * @date 24/07/2022
 */
public interface StaticMapService {

    byte[] generateMap(String longitude, String latitude);

    String generateBase64Map(String longitude, String latitude);

}
