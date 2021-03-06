package io.fabric8.quickstarts.camel;

import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.camel.component.ActiveMQConfiguration;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMQConfig {

    @Value("${activemq.username}")
    private String amqUsername;

    @Value("${activemq.password}")
    private String amqPassword;

    @Value("${activemq.truststore}")
    private String trustStore;

    @Value("${activemq.broker-url}")
    private String amqBrokerUrl;

    @Value("${activemq.truststore.password}")
    private String trustsStorePassword;

    @Bean(name = "activemq")
    public ActiveMQComponent createActiveMQComponent() throws Exception{
        ActiveMQSslConnectionFactory factory = new ActiveMQSslConnectionFactory(amqBrokerUrl);
        factory.setUserName(amqUsername);
        factory.setPassword(amqPassword);

        factory.setTrustStore(trustStore);
        factory.setTrustStorePassword(trustsStorePassword);

        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(factory);
        pooledConnectionFactory.setMaxConnections(10);

        ActiveMQConfiguration configuration = new ActiveMQConfiguration();
        configuration.setConnectionFactory(pooledConnectionFactory);
        configuration.setMaxConcurrentConsumers(12);

        return new ActiveMQComponent(configuration);
    }
}
