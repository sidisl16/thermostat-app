// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: app.proto

package com.sid.thermostat.app.protobuf;

public interface ConfigurationResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:thermostat.app.ConfigurationResponse)
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
   * <code>string serial_no = 2;</code>
   */
  java.lang.String getSerialNo();
  /**
   * <code>string serial_no = 2;</code>
   */
  com.google.protobuf.ByteString
      getSerialNoBytes();

  /**
   * <code>.thermostat.app.ConfigurationResponse.ConfigStatus status = 3;</code>
   */
  int getStatusValue();
  /**
   * <code>.thermostat.app.ConfigurationResponse.ConfigStatus status = 3;</code>
   */
  com.sid.thermostat.app.protobuf.ConfigurationResponse.ConfigStatus getStatus();
}