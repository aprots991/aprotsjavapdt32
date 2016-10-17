package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testYouIp() {
    GeoIP geoIp = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.152");
    assertEquals(geoIp.getCountryCode(), "RUS");
  }
}
