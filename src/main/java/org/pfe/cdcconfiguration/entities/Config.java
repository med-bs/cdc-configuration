package org.pfe.cdcconfiguration.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Config {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Config() {
    }

    public Config(Long id, String connectorClass, String tasksMax, String databaseHostname, String databasePort, String databaseUser, String databasePassword, String databaseServerId, String topicPrefix, String databaseIncludeList, String schemaHistoryInternalKafkaBootstrapServers, String schemaHistoryInternalKafkaTopic) {
        this.id = id;
        this.connectorClass = connectorClass;
        this.tasksMax = tasksMax;
        this.databaseHostname = databaseHostname;
        this.databasePort = databasePort;
        this.databaseUser = databaseUser;
        this.databasePassword = databasePassword;
        this.databaseServerId = databaseServerId;
        this.topicPrefix = topicPrefix;
        this.databaseIncludeList = databaseIncludeList;
        this.schemaHistoryInternalKafkaBootstrapServers = schemaHistoryInternalKafkaBootstrapServers;
        this.schemaHistoryInternalKafkaTopic = schemaHistoryInternalKafkaTopic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConnectorClass() {
        return connectorClass;
    }

    public void setConnectorClass(String connectorClass) {
        this.connectorClass = connectorClass;
    }

    public String getTasksMax() {
        return tasksMax;
    }

    public void setTasksMax(String tasksMax) {
        this.tasksMax = tasksMax;
    }

    public String getDatabaseHostname() {
        return databaseHostname;
    }

    public void setDatabaseHostname(String databaseHostname) {
        this.databaseHostname = databaseHostname;
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getDatabaseServerId() {
        return databaseServerId;
    }

    public void setDatabaseServerId(String databaseServerId) {
        this.databaseServerId = databaseServerId;
    }

    public String getTopicPrefix() {
        return topicPrefix;
    }

    public void setTopicPrefix(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }

    public String getDatabaseIncludeList() {
        return databaseIncludeList;
    }

    public void setDatabaseIncludeList(String databaseIncludeList) {
        this.databaseIncludeList = databaseIncludeList;
    }

    public String getSchemaHistoryInternalKafkaBootstrapServers() {
        return schemaHistoryInternalKafkaBootstrapServers;
    }

    public void setSchemaHistoryInternalKafkaBootstrapServers(String schemaHistoryInternalKafkaBootstrapServers) {
        this.schemaHistoryInternalKafkaBootstrapServers = schemaHistoryInternalKafkaBootstrapServers;
    }

    public String getSchemaHistoryInternalKafkaTopic() {
        return schemaHistoryInternalKafkaTopic;
    }

    public void setSchemaHistoryInternalKafkaTopic(String schemaHistoryInternalKafkaTopic) {
        this.schemaHistoryInternalKafkaTopic = schemaHistoryInternalKafkaTopic;
    }
}
