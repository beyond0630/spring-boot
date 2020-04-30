package com.sweet.mapper;

import java.util.List;

import com.sweet.entities.DataSourceConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
@Mapper
public interface DataSourceConfigMapper {

    DataSourceConfig getDataSourceConfig(int id);

    List<DataSourceConfig> listDataSourceConfig();

}
