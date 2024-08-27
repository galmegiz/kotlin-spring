package demo.common.config

import org.apache.ibatis.session.AutoMappingUnknownColumnBehavior
import org.apache.ibatis.session.Configuration

@org.springframework.context.annotation.Configuration
class MybatisConfig {
    fun configuration(): Configuration {
        val configuration = Configuration()
        configuration.autoMappingUnknownColumnBehavior = AutoMappingUnknownColumnBehavior.NONE
        return configuration
    }
}