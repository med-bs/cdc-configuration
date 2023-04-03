package org.pfe.cdcconfiguration.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor @AllArgsConstructor
public class WatcherConfig {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String connectorClass;
    private String tasksMax;
    private String databaseHostname;
    private String databasePort;
    private String databaseUser;
    private String databasePassword;
    private String databaseServerId;
    private String topicPrefix;
    private String databaseIncludeList;
    private String schemaHistoryInternalKafkaBootstrapServers;
    private String schemaHistoryInternalKafkaTopic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("connector.class")
    public String getConnectorClass() {
        return connectorClass;
    }

    public void setConnectorClass(String connectorClass) {
        this.connectorClass = connectorClass;
    }

    @JsonProperty("tasks.max")
    public String getTasksMax() {
        return tasksMax;
    }

    public void setTasksMax(String tasksMax) {
        this.tasksMax = tasksMax;
    }

    @JsonProperty("database.hostname")
    public String getDatabaseHostname() {
        return databaseHostname;
    }

    public void setDatabaseHostname(String databaseHostname) {
        this.databaseHostname = databaseHostname;
    }

    @JsonProperty("database.port")
    public String getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }

    @JsonProperty("database.user")
    public String getDatabaseUser() {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    @JsonProperty("database.password")
    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    @JsonProperty("database.server.id")
    public String getDatabaseServerId() {
        return databaseServerId;
    }

    public void setDatabaseServerId(String databaseServerId) {
        this.databaseServerId = databaseServerId;
    }

    @JsonProperty("topic.prefix")
    public String getTopicPrefix() {
        return topicPrefix;
    }

    public void setTopicPrefix(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }

    @JsonProperty("database.include.list")
    public String getDatabaseIncludeList() {
        return databaseIncludeList;
    }

    public void setDatabaseIncludeList(String databaseIncludeList) {
        this.databaseIncludeList = databaseIncludeList;
    }

    @JsonProperty("schema.history.internal.kafka.bootstrap.servers")
    public String getSchemaHistoryInternalKafkaBootstrapServers() {
        return schemaHistoryInternalKafkaBootstrapServers;
    }

    public void setSchemaHistoryInternalKafkaBootstrapServers(String schemaHistoryInternalKafkaBootstrapServers) {
        this.schemaHistoryInternalKafkaBootstrapServers = schemaHistoryInternalKafkaBootstrapServers;
    }

    @JsonProperty("schema.history.internal.kafka.topic")
    public String getSchemaHistoryInternalKafkaTopic() {
        return schemaHistoryInternalKafkaTopic;
    }

    public void setSchemaHistoryInternalKafkaTopic(String schemaHistoryInternalKafkaTopic) {
        this.schemaHistoryInternalKafkaTopic = schemaHistoryInternalKafkaTopic;
    }
}
