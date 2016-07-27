package pub.pjoc.config.impl;

import pub.pjoc.config.ConfigInfoService;

/**
 * 通用配置接口
 * Created by xiongyingqi on 16/7/25.
 */
public class ConfigInfoServiceImpl implements ConfigInfoService {
    @Override
    public final String getConfig(String groupId, String configName) {
        System.out.println(groupId);
        return groupId + configName;
    }

    @Override
    public final boolean setConfig(String groupId, String configName,
                             String configValue) {
        return groupId.equals("");
    }
}
