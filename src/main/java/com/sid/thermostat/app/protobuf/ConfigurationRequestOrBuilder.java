// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: app.proto

package com.sid.thermostat.app.protobuf;

public interface ConfigurationRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:thermostat.app.ConfigurationRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string requestId = 1;</code>
   */
  java.lang.String getRequestId();
  /**
   * <code>string requestId = 1;</code>
   */
  com.google.protobuf.ByteString
      getRequestIdBytes();

  /**
   * <code>string in_config_topic = 2;</code>
   */
  java.lang.String getInConfigTopic();
  /**
   * <code>string in_config_topic = 2;</code>
   */
  com.google.protobuf.ByteString
      getInConfigTopicBytes();

  /**
   * <code>string out_config_topic = 3;</code>
   */
  java.lang.String getOutConfigTopic();
  /**
   * <code>string out_config_topic = 3;</code>
   */
  com.google.protobuf.ByteString
      getOutConfigTopicBytes();

  /**
   * <code>string in_data_topic = 4;</code>
   */
  java.lang.String getInDataTopic();
  /**
   * <code>string in_data_topic = 4;</code>
   */
  com.google.protobuf.ByteString
      getInDataTopicBytes();

  /**
   * <code>int32 data_interval = 5;</code>
   */
  int getDataInterval();

  /**
   * <code>string serial_no = 6;</code>
   */
  java.lang.String getSerialNo();
  /**
   * <code>string serial_no = 6;</code>
   */
  com.google.protobuf.ByteString
      getSerialNoBytes();
}
