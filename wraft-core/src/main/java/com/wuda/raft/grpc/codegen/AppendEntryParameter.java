// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RpcService.proto

package com.wuda.raft.grpc.codegen;

/**
 * Protobuf type {@code com.wuda.raft.grpc.codegen.AppendEntryParameter}
 */
public  final class AppendEntryParameter extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.wuda.raft.grpc.codegen.AppendEntryParameter)
    AppendEntryParameterOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AppendEntryParameter.newBuilder() to construct.
  private AppendEntryParameter(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AppendEntryParameter() {
    term_ = 0L;
    leaderId_ = "";
    entries_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AppendEntryParameter(
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
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            term_ = input.readInt64();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            leaderId_ = s;
            break;
          }
          case 26: {

            entries_ = input.readBytes();
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
    return com.wuda.raft.grpc.codegen.RpcServiceOuterClass.internal_static_com_wuda_raft_grpc_codegen_AppendEntryParameter_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.wuda.raft.grpc.codegen.RpcServiceOuterClass.internal_static_com_wuda_raft_grpc_codegen_AppendEntryParameter_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.wuda.raft.grpc.codegen.AppendEntryParameter.class, com.wuda.raft.grpc.codegen.AppendEntryParameter.Builder.class);
  }

  public static final int TERM_FIELD_NUMBER = 1;
  private long term_;
  /**
   * <code>int64 term = 1;</code>
   */
  public long getTerm() {
    return term_;
  }

  public static final int LEADERID_FIELD_NUMBER = 2;
  private volatile java.lang.Object leaderId_;
  /**
   * <code>string leaderId = 2;</code>
   */
  public java.lang.String getLeaderId() {
    java.lang.Object ref = leaderId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      leaderId_ = s;
      return s;
    }
  }
  /**
   * <code>string leaderId = 2;</code>
   */
  public com.google.protobuf.ByteString
      getLeaderIdBytes() {
    java.lang.Object ref = leaderId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      leaderId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ENTRIES_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString entries_;
  /**
   * <code>bytes entries = 3;</code>
   */
  public com.google.protobuf.ByteString getEntries() {
    return entries_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (term_ != 0L) {
      output.writeInt64(1, term_);
    }
    if (!getLeaderIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, leaderId_);
    }
    if (!entries_.isEmpty()) {
      output.writeBytes(3, entries_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (term_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, term_);
    }
    if (!getLeaderIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, leaderId_);
    }
    if (!entries_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, entries_);
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
    if (!(obj instanceof com.wuda.raft.grpc.codegen.AppendEntryParameter)) {
      return super.equals(obj);
    }
    com.wuda.raft.grpc.codegen.AppendEntryParameter other = (com.wuda.raft.grpc.codegen.AppendEntryParameter) obj;

    boolean result = true;
    result = result && (getTerm()
        == other.getTerm());
    result = result && getLeaderId()
        .equals(other.getLeaderId());
    result = result && getEntries()
        .equals(other.getEntries());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TERM_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTerm());
    hash = (37 * hash) + LEADERID_FIELD_NUMBER;
    hash = (53 * hash) + getLeaderId().hashCode();
    hash = (37 * hash) + ENTRIES_FIELD_NUMBER;
    hash = (53 * hash) + getEntries().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.wuda.raft.grpc.codegen.AppendEntryParameter parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.wuda.raft.grpc.codegen.AppendEntryParameter prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
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
   * Protobuf type {@code com.wuda.raft.grpc.codegen.AppendEntryParameter}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.wuda.raft.grpc.codegen.AppendEntryParameter)
      com.wuda.raft.grpc.codegen.AppendEntryParameterOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.wuda.raft.grpc.codegen.RpcServiceOuterClass.internal_static_com_wuda_raft_grpc_codegen_AppendEntryParameter_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.wuda.raft.grpc.codegen.RpcServiceOuterClass.internal_static_com_wuda_raft_grpc_codegen_AppendEntryParameter_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.wuda.raft.grpc.codegen.AppendEntryParameter.class, com.wuda.raft.grpc.codegen.AppendEntryParameter.Builder.class);
    }

    // Construct using com.wuda.raft.grpc.codegen.AppendEntryParameter.newBuilder()
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
    public Builder clear() {
      super.clear();
      term_ = 0L;

      leaderId_ = "";

      entries_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.wuda.raft.grpc.codegen.RpcServiceOuterClass.internal_static_com_wuda_raft_grpc_codegen_AppendEntryParameter_descriptor;
    }

    public com.wuda.raft.grpc.codegen.AppendEntryParameter getDefaultInstanceForType() {
      return com.wuda.raft.grpc.codegen.AppendEntryParameter.getDefaultInstance();
    }

    public com.wuda.raft.grpc.codegen.AppendEntryParameter build() {
      com.wuda.raft.grpc.codegen.AppendEntryParameter result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.wuda.raft.grpc.codegen.AppendEntryParameter buildPartial() {
      com.wuda.raft.grpc.codegen.AppendEntryParameter result = new com.wuda.raft.grpc.codegen.AppendEntryParameter(this);
      result.term_ = term_;
      result.leaderId_ = leaderId_;
      result.entries_ = entries_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.wuda.raft.grpc.codegen.AppendEntryParameter) {
        return mergeFrom((com.wuda.raft.grpc.codegen.AppendEntryParameter)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.wuda.raft.grpc.codegen.AppendEntryParameter other) {
      if (other == com.wuda.raft.grpc.codegen.AppendEntryParameter.getDefaultInstance()) return this;
      if (other.getTerm() != 0L) {
        setTerm(other.getTerm());
      }
      if (!other.getLeaderId().isEmpty()) {
        leaderId_ = other.leaderId_;
        onChanged();
      }
      if (other.getEntries() != com.google.protobuf.ByteString.EMPTY) {
        setEntries(other.getEntries());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.wuda.raft.grpc.codegen.AppendEntryParameter parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.wuda.raft.grpc.codegen.AppendEntryParameter) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long term_ ;
    /**
     * <code>int64 term = 1;</code>
     */
    public long getTerm() {
      return term_;
    }
    /**
     * <code>int64 term = 1;</code>
     */
    public Builder setTerm(long value) {
      
      term_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 term = 1;</code>
     */
    public Builder clearTerm() {
      
      term_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object leaderId_ = "";
    /**
     * <code>string leaderId = 2;</code>
     */
    public java.lang.String getLeaderId() {
      java.lang.Object ref = leaderId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        leaderId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string leaderId = 2;</code>
     */
    public com.google.protobuf.ByteString
        getLeaderIdBytes() {
      java.lang.Object ref = leaderId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        leaderId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string leaderId = 2;</code>
     */
    public Builder setLeaderId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      leaderId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string leaderId = 2;</code>
     */
    public Builder clearLeaderId() {
      
      leaderId_ = getDefaultInstance().getLeaderId();
      onChanged();
      return this;
    }
    /**
     * <code>string leaderId = 2;</code>
     */
    public Builder setLeaderIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      leaderId_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString entries_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes entries = 3;</code>
     */
    public com.google.protobuf.ByteString getEntries() {
      return entries_;
    }
    /**
     * <code>bytes entries = 3;</code>
     */
    public Builder setEntries(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      entries_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes entries = 3;</code>
     */
    public Builder clearEntries() {
      
      entries_ = getDefaultInstance().getEntries();
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.wuda.raft.grpc.codegen.AppendEntryParameter)
  }

  // @@protoc_insertion_point(class_scope:com.wuda.raft.grpc.codegen.AppendEntryParameter)
  private static final com.wuda.raft.grpc.codegen.AppendEntryParameter DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.wuda.raft.grpc.codegen.AppendEntryParameter();
  }

  public static com.wuda.raft.grpc.codegen.AppendEntryParameter getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AppendEntryParameter>
      PARSER = new com.google.protobuf.AbstractParser<AppendEntryParameter>() {
    public AppendEntryParameter parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AppendEntryParameter(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AppendEntryParameter> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AppendEntryParameter> getParserForType() {
    return PARSER;
  }

  public com.wuda.raft.grpc.codegen.AppendEntryParameter getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

