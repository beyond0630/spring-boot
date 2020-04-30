package com.sweet.datasource;

import java.time.LocalDateTime;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/29
 */
public class DynamicDataSourceManager {

    /**
     * 默认释放时间
     */
    private static final Long DEFAULT_RELEASE = 10L;

    private final DruidDataSource dataSource;

    /**
     * 上一次使用时间
     */
    private LocalDateTime lastUseTime;

    public DruidDataSource getDataSource() {
        return dataSource;
    }

    public DynamicDataSourceManager(DruidDataSource dataSource) {
        this.dataSource = dataSource;
        this.lastUseTime = LocalDateTime.now();
    }

    /**
     * 是否已过期，如果过期则关闭数据源
     *
     * @return 是否过期，{@code true} 过期，{@code false} 未过期
     */
    public boolean isExpired() {
        if (LocalDateTime.now().isBefore(this.lastUseTime.plusMinutes(DEFAULT_RELEASE))) {
            return false;
        }
        this.dataSource.close();
        return true;
    }

    /**
     * 刷新上次使用时间
     */
    public void refreshTime() {
        this.lastUseTime = LocalDateTime.now();
    }
}
