# minibatis 是一个根据mybatis原理简化而成的极简版，目标是掌握mybatis的核心

## 主要核心功能是mapper映射成可执行的SQL

### 暂时忽略配置自动化、mapper加载自动化、SQL解析自动化、查询结果映射处理等

### 仅保留连接数据库、映射mapper的SQL的极简解析、代理生成、执行SQL、返回结果；


 仅需要依赖mysql官方提供的jconnector即可
 
     <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.49</version>
        </dependency>
    </dependencies>
