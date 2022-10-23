package config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:config/${deviceHost}.properties"
})
public interface DeviceConfig extends Config {

    String deviceName();

    String platformVersion();

    String remoteDriver();

}