// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: app.proto

package com.sid.thermostat.app.protobuf;

public final class App {
  private App() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_thermostat_app_Data_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_thermostat_app_Data_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\tapp.proto\022\016thermostat.app\"C\n\004Data\022\021\n\ts" +
      "erial_no\030\001 \001(\t\022\023\n\013device_time\030\002 \001(\004\022\023\n\013t" +
      "emperature\030\003 \001(\002B#\n\037com.sid.thermostat.a" +
      "pp.protobufP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_thermostat_app_Data_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_thermostat_app_Data_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_thermostat_app_Data_descriptor,
        new java.lang.String[] { "SerialNo", "DeviceTime", "Temperature", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
