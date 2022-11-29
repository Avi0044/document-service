package in.nbt.documentservice.restapi.helper;

public class ConfigDetails extends in.nbt.core.restapi.helper.ConfigDetails {

   public static final String HOST_V5 = Configvariable.envPropertyMap.get("api.host.url.v5uat");
   public static final String HOST_AWS = Configvariable.envPropertyMap.get("api.host.url.v1awsuat");
   public static final String HOST_VSID = Configvariable.envPropertyMap.get("api.host.url.VSIDProd");
   public static final String HOST_RP2 = Configvariable.envPropertyMap.get("api.host.url.prod.RP2");
   public static final String HOST_V1 = Configvariable.envPropertyMap.get("api.host.url.v1uat");
   public static final String APP_KEY = Configvariable.envPropertyMap.get("api.app.key");
   public static final String Host_Prod = Configvariable.envPropertyMap.get("api.host.url.Prod");
   public static final String XAPPKey = Configvariable.envPropertyMap.get("api.app.Xkey");

}
