// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: app.proto

package com.sid.thermostat.app.protobuf;

/**
 * Protobuf type {@code thermostat.app.ProvisioningResponse}
 */
public  final class ProvisioningResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:thermostat.app.ProvisioningResponse)
    ProvisioningResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ProvisioningResponse.newBuilder() to construct.
  private ProvisioningResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ProvisioningResponse() {
    serialNo_ = "";
    configTopic_ = "";
    dataTopic_ = "";
    status_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ProvisioningResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            serialNo_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            configTopic_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            dataTopic_ = s;
            break;
          }
          case 32: {
            int rawValue = input.readEnum();

            status_ = rawValue;
            break;
          }
          case 40: {

            qos_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.sid.thermostat.app.protobuf.App.internal_static_thermostat_app_ProvisioningResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.sid.thermostat.app.protobuf.App.internal_static_thermostat_app_ProvisioningResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.sid.thermostat.app.protobuf.ProvisioningResponse.class, com.sid.thermostat.app.protobuf.ProvisioningResponse.Builder.class);
  }

  /**
   * Protobuf enum {@code thermostat.app.ProvisioningResponse.ProvStatus}
   */
  public enum ProvStatus
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>SUCCESS = 0;</code>
     */
    SUCCESS(0),
    /**
     * <code>FAILED = 1;</code>
     */
    FAILED(1),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>SUCCESS = 0;</code>
     */
    public static final int SUCCESS_VALUE = 0;
    /**
     * <code>FAILED = 1;</code>
     */
    public static final int FAILED_VALUE = 1;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static ProvStatus valueOf(int value) {
      return forNumber(value);
    }

    public static ProvStatus forNumber(int value) {
      switch (value) {
        case 0: return SUCCESS;
        case 1: return FAILED;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ProvStatus>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        ProvStatus> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ProvStatus>() {
            public ProvStatus findValueByNumber(int number) {
              return ProvStatus.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.sid.thermostat.app.protobuf.ProvisioningResponse.getDescriptor().getEnumTypes().get(0);
    }

    private static final ProvStatus[] VALUES = values();

    public static ProvStatus valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private ProvStatus(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:thermostat.app.ProvisioningResponse.ProvStatus)
  }

  public static final int SERIAL_NO_FIELD_NUMBER = 1;
  private volatile java.lang.Object serialNo_;
  /**
   * <code>string serial_no = 1;</code>
   */
  public java.lang.String getSerialNo() {
    java.lang.Object ref = serialNo_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      serialNo_ = s;
      return s;
    }
  }
  /**
   * <code>string serial_no = 1;</code>
   */
  public com.google.protobuf.ByteString
      getSerialNoBytes() {
    java.lang.Object ref = serialNo_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      serialNo_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CONFIG_TOPIC_FIELD_NUMBER = 2;
  private volatile java.lang.Object configTopic_;
  /**
   * <code>string config_topic = 2;</code>
   */
  public java.lang.String getConfigTopic() {
    java.lang.Object ref = configTopic_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      configTopic_ = s;
      return s;
    }
  }
  /**
   * <code>string config_topic = 2;</code>
   */
  public com.google.protobuf.ByteString
      getConfigTopicBytes() {
    java.lang.Object ref = configTopic_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      configTopic_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DATA_TOPIC_FIELD_NUMBER = 3;
  private volatile java.lang.Object dataTopic_;
  /**
   * <code>string data_topic = 3;</code>
   */
  public java.lang.String getDataTopic() {
    java.lang.Object ref = dataTopic_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      dataTopic_ = s;
      return s;
    }
  }
  /**
   * <code>string data_topic = 3;</code>
   */
  public com.google.protobuf.ByteString
      getDataTopicBytes() {
    java.lang.Object ref = dataTopic_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      dataTopic_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int STATUS_FIELD_NUMBER = 4;
  private int status_;
  /**
   * <code>.thermostat.app.ProvisioningResponse.ProvStatus status = 4;</code>
   */
  public int getStatusValue() {
    return status_;
  }
  /**
   * <code>.thermostat.app.ProvisioningResponse.ProvStatus status = 4;</code>
   */
  public com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus getStatus() {
    @SuppressWarnings("deprecation")
    com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus result = com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus.valueOf(status_);
    return result == null ? com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus.UNRECOGNIZED : result;
  }

  public static final int QOS_FIELD_NUMBER = 5;
  private int qos_;
  /**
   * <code>int32 qos = 5;</code>
   */
  public int getQos() {
    return qos_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getSerialNoBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, serialNo_);
    }
    if (!getConfigTopicBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, configTopic_);
    }
    if (!getDataTopicBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, dataTopic_);
    }
    if (status_ != com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus.SUCCESS.getNumber()) {
      output.writeEnum(4, status_);
    }
    if (qos_ != 0) {
      output.writeInt32(5, qos_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getSerialNoBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, serialNo_);
    }
    if (!getConfigTopicBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, configTopic_);
    }
    if (!getDataTopicBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, dataTopic_);
    }
    if (status_ != com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus.SUCCESS.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(4, status_);
    }
    if (qos_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, qos_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.sid.thermostat.app.protobuf.ProvisioningResponse)) {
      return super.equals(obj);
    }
    com.sid.thermostat.app.protobuf.ProvisioningResponse other = (com.sid.thermostat.app.protobuf.ProvisioningResponse) obj;

    if (!getSerialNo()
        .equals(other.getSerialNo())) return false;
    if (!getConfigTopic()
        .equals(other.getConfigTopic())) return false;
    if (!getDataTopic()
        .equals(other.getDataTopic())) return false;
    if (status_ != other.status_) return false;
    if (getQos()
        != other.getQos()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SERIAL_NO_FIELD_NUMBER;
    hash = (53 * hash) + getSerialNo().hashCode();
    hash = (37 * hash) + CONFIG_TOPIC_FIELD_NUMBER;
    hash = (53 * hash) + getConfigTopic().hashCode();
    hash = (37 * hash) + DATA_TOPIC_FIELD_NUMBER;
    hash = (53 * hash) + getDataTopic().hashCode();
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + status_;
    hash = (37 * hash) + QOS_FIELD_NUMBER;
    hash = (53 * hash) + getQos();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.sid.thermostat.app.protobuf.ProvisioningResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.sid.thermostat.app.protobuf.ProvisioningResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code thermostat.app.ProvisioningResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:thermostat.app.ProvisioningResponse)
      com.sid.thermostat.app.protobuf.ProvisioningResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.sid.thermostat.app.protobuf.App.internal_static_thermostat_app_ProvisioningResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.sid.thermostat.app.protobuf.App.internal_static_thermostat_app_ProvisioningResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.sid.thermostat.app.protobuf.ProvisioningResponse.class, com.sid.thermostat.app.protobuf.ProvisioningResponse.Builder.class);
    }

    // Construct using com.sid.thermostat.app.protobuf.ProvisioningResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      serialNo_ = "";

      configTopic_ = "";

      dataTopic_ = "";

      status_ = 0;

      qos_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.sid.thermostat.app.protobuf.App.internal_static_thermostat_app_ProvisioningResponse_descriptor;
    }

    @java.lang.Override
    public com.sid.thermostat.app.protobuf.ProvisioningResponse getDefaultInstanceForType() {
      return com.sid.thermostat.app.protobuf.ProvisioningResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.sid.thermostat.app.protobuf.ProvisioningResponse build() {
      com.sid.thermostat.app.protobuf.ProvisioningResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.sid.thermostat.app.protobuf.ProvisioningResponse buildPartial() {
      com.sid.thermostat.app.protobuf.ProvisioningResponse result = new com.sid.thermostat.app.protobuf.ProvisioningResponse(this);
      result.serialNo_ = serialNo_;
      result.configTopic_ = configTopic_;
      result.dataTopic_ = dataTopic_;
      result.status_ = status_;
      result.qos_ = qos_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.sid.thermostat.app.protobuf.ProvisioningResponse) {
        return mergeFrom((com.sid.thermostat.app.protobuf.ProvisioningResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.sid.thermostat.app.protobuf.ProvisioningResponse other) {
      if (other == com.sid.thermostat.app.protobuf.ProvisioningResponse.getDefaultInstance()) return this;
      if (!other.getSerialNo().isEmpty()) {
        serialNo_ = other.serialNo_;
        onChanged();
      }
      if (!other.getConfigTopic().isEmpty()) {
        configTopic_ = other.configTopic_;
        onChanged();
      }
      if (!other.getDataTopic().isEmpty()) {
        dataTopic_ = other.dataTopic_;
        onChanged();
      }
      if (other.status_ != 0) {
        setStatusValue(other.getStatusValue());
      }
      if (other.getQos() != 0) {
        setQos(other.getQos());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.sid.thermostat.app.protobuf.ProvisioningResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.sid.thermostat.app.protobuf.ProvisioningResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object serialNo_ = "";
    /**
     * <code>string serial_no = 1;</code>
     */
    public java.lang.String getSerialNo() {
      java.lang.Object ref = serialNo_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        serialNo_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string serial_no = 1;</code>
     */
    public com.google.protobuf.ByteString
        getSerialNoBytes() {
      java.lang.Object ref = serialNo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        serialNo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string serial_no = 1;</code>
     */
    public Builder setSerialNo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      serialNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string serial_no = 1;</code>
     */
    public Builder clearSerialNo() {
      
      serialNo_ = getDefaultInstance().getSerialNo();
      onChanged();
      return this;
    }
    /**
     * <code>string serial_no = 1;</code>
     */
    public Builder setSerialNoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      serialNo_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object configTopic_ = "";
    /**
     * <code>string config_topic = 2;</code>
     */
    public java.lang.String getConfigTopic() {
      java.lang.Object ref = configTopic_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        configTopic_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string config_topic = 2;</code>
     */
    public com.google.protobuf.ByteString
        getConfigTopicBytes() {
      java.lang.Object ref = configTopic_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        configTopic_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string config_topic = 2;</code>
     */
    public Builder setConfigTopic(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      configTopic_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string config_topic = 2;</code>
     */
    public Builder clearConfigTopic() {
      
      configTopic_ = getDefaultInstance().getConfigTopic();
      onChanged();
      return this;
    }
    /**
     * <code>string config_topic = 2;</code>
     */
    public Builder setConfigTopicBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      configTopic_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object dataTopic_ = "";
    /**
     * <code>string data_topic = 3;</code>
     */
    public java.lang.String getDataTopic() {
      java.lang.Object ref = dataTopic_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        dataTopic_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string data_topic = 3;</code>
     */
    public com.google.protobuf.ByteString
        getDataTopicBytes() {
      java.lang.Object ref = dataTopic_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        dataTopic_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string data_topic = 3;</code>
     */
    public Builder setDataTopic(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      dataTopic_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string data_topic = 3;</code>
     */
    public Builder clearDataTopic() {
      
      dataTopic_ = getDefaultInstance().getDataTopic();
      onChanged();
      return this;
    }
    /**
     * <code>string data_topic = 3;</code>
     */
    public Builder setDataTopicBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      dataTopic_ = value;
      onChanged();
      return this;
    }

    private int status_ = 0;
    /**
     * <code>.thermostat.app.ProvisioningResponse.ProvStatus status = 4;</code>
     */
    public int getStatusValue() {
      return status_;
    }
    /**
     * <code>.thermostat.app.ProvisioningResponse.ProvStatus status = 4;</code>
     */
    public Builder setStatusValue(int value) {
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.thermostat.app.ProvisioningResponse.ProvStatus status = 4;</code>
     */
    public com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus getStatus() {
      @SuppressWarnings("deprecation")
      com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus result = com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus.valueOf(status_);
      return result == null ? com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus.UNRECOGNIZED : result;
    }
    /**
     * <code>.thermostat.app.ProvisioningResponse.ProvStatus status = 4;</code>
     */
    public Builder setStatus(com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      status_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.thermostat.app.ProvisioningResponse.ProvStatus status = 4;</code>
     */
    public Builder clearStatus() {
      
      status_ = 0;
      onChanged();
      return this;
    }

    private int qos_ ;
    /**
     * <code>int32 qos = 5;</code>
     */
    public int getQos() {
      return qos_;
    }
    /**
     * <code>int32 qos = 5;</code>
     */
    public Builder setQos(int value) {
      
      qos_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 qos = 5;</code>
     */
    public Builder clearQos() {
      
      qos_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:thermostat.app.ProvisioningResponse)
  }

  // @@protoc_insertion_point(class_scope:thermostat.app.ProvisioningResponse)
  private static final com.sid.thermostat.app.protobuf.ProvisioningResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.sid.thermostat.app.protobuf.ProvisioningResponse();
  }

  public static com.sid.thermostat.app.protobuf.ProvisioningResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ProvisioningResponse>
      PARSER = new com.google.protobuf.AbstractParser<ProvisioningResponse>() {
    @java.lang.Override
    public ProvisioningResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ProvisioningResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ProvisioningResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ProvisioningResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.sid.thermostat.app.protobuf.ProvisioningResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

