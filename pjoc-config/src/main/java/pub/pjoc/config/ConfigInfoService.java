package pub.pjoc.config;

/**
 * 通用配置接口
 * Created by xiongyingqi on 16/7/25.
 */
public interface ConfigInfoService {

    /**
     * Getting a config by groupId and configName.
     *
     * @param groupId    Grouped config
     * @param configName The config name
     * @return String
     */
    String getConfig(String groupId, String configName);

    /**
     * Setting config.
     *
     * @param groupId     Grouped config
     * @param configName  The config name
     * @param configValue The config value
     * @return String
     */
    boolean setConfig(String groupId, String configName, String configValue);
}
