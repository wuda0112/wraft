package com.wuda.raft.grpc.codegen;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: RpcService.proto")
public final class RpcServiceGrpc {

  private RpcServiceGrpc() {}

  public static final String SERVICE_NAME = "com.wuda.raft.grpc.codegen.RpcService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.wuda.raft.grpc.codegen.RequestVoteParameter,
      com.wuda.raft.grpc.codegen.RequestVoteResult> getRequestVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "requestVote",
      requestType = com.wuda.raft.grpc.codegen.RequestVoteParameter.class,
      responseType = com.wuda.raft.grpc.codegen.RequestVoteResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.wuda.raft.grpc.codegen.RequestVoteParameter,
      com.wuda.raft.grpc.codegen.RequestVoteResult> getRequestVoteMethod() {
    io.grpc.MethodDescriptor<com.wuda.raft.grpc.codegen.RequestVoteParameter, com.wuda.raft.grpc.codegen.RequestVoteResult> getRequestVoteMethod;
    if ((getRequestVoteMethod = RpcServiceGrpc.getRequestVoteMethod) == null) {
      synchronized (RpcServiceGrpc.class) {
        if ((getRequestVoteMethod = RpcServiceGrpc.getRequestVoteMethod) == null) {
          RpcServiceGrpc.getRequestVoteMethod = getRequestVoteMethod = 
              io.grpc.MethodDescriptor.<com.wuda.raft.grpc.codegen.RequestVoteParameter, com.wuda.raft.grpc.codegen.RequestVoteResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.wuda.raft.grpc.codegen.RpcService", "requestVote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wuda.raft.grpc.codegen.RequestVoteParameter.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wuda.raft.grpc.codegen.RequestVoteResult.getDefaultInstance()))
                  .setSchemaDescriptor(new RpcServiceMethodDescriptorSupplier("requestVote"))
                  .build();
          }
        }
     }
     return getRequestVoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.wuda.raft.grpc.codegen.AppendEntryParameter,
      com.wuda.raft.grpc.codegen.AppendEntryResult> getAppendEntriesRpcMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "appendEntriesRpc",
      requestType = com.wuda.raft.grpc.codegen.AppendEntryParameter.class,
      responseType = com.wuda.raft.grpc.codegen.AppendEntryResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.wuda.raft.grpc.codegen.AppendEntryParameter,
      com.wuda.raft.grpc.codegen.AppendEntryResult> getAppendEntriesRpcMethod() {
    io.grpc.MethodDescriptor<com.wuda.raft.grpc.codegen.AppendEntryParameter, com.wuda.raft.grpc.codegen.AppendEntryResult> getAppendEntriesRpcMethod;
    if ((getAppendEntriesRpcMethod = RpcServiceGrpc.getAppendEntriesRpcMethod) == null) {
      synchronized (RpcServiceGrpc.class) {
        if ((getAppendEntriesRpcMethod = RpcServiceGrpc.getAppendEntriesRpcMethod) == null) {
          RpcServiceGrpc.getAppendEntriesRpcMethod = getAppendEntriesRpcMethod = 
              io.grpc.MethodDescriptor.<com.wuda.raft.grpc.codegen.AppendEntryParameter, com.wuda.raft.grpc.codegen.AppendEntryResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.wuda.raft.grpc.codegen.RpcService", "appendEntriesRpc"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wuda.raft.grpc.codegen.AppendEntryParameter.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wuda.raft.grpc.codegen.AppendEntryResult.getDefaultInstance()))
                  .setSchemaDescriptor(new RpcServiceMethodDescriptorSupplier("appendEntriesRpc"))
                  .build();
          }
        }
     }
     return getAppendEntriesRpcMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RpcServiceStub newStub(io.grpc.Channel channel) {
    return new RpcServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RpcServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RpcServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RpcServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void requestVote(com.wuda.raft.grpc.codegen.RequestVoteParameter request,
        io.grpc.stub.StreamObserver<com.wuda.raft.grpc.codegen.RequestVoteResult> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestVoteMethod(), responseObserver);
    }

    /**
     */
    public void appendEntriesRpc(com.wuda.raft.grpc.codegen.AppendEntryParameter request,
        io.grpc.stub.StreamObserver<com.wuda.raft.grpc.codegen.AppendEntryResult> responseObserver) {
      asyncUnimplementedUnaryCall(getAppendEntriesRpcMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRequestVoteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.wuda.raft.grpc.codegen.RequestVoteParameter,
                com.wuda.raft.grpc.codegen.RequestVoteResult>(
                  this, METHODID_REQUEST_VOTE)))
          .addMethod(
            getAppendEntriesRpcMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.wuda.raft.grpc.codegen.AppendEntryParameter,
                com.wuda.raft.grpc.codegen.AppendEntryResult>(
                  this, METHODID_APPEND_ENTRIES_RPC)))
          .build();
    }
  }

  /**
   */
  public static final class RpcServiceStub extends io.grpc.stub.AbstractStub<RpcServiceStub> {
    private RpcServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RpcServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RpcServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void requestVote(com.wuda.raft.grpc.codegen.RequestVoteParameter request,
        io.grpc.stub.StreamObserver<com.wuda.raft.grpc.codegen.RequestVoteResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestVoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void appendEntriesRpc(com.wuda.raft.grpc.codegen.AppendEntryParameter request,
        io.grpc.stub.StreamObserver<com.wuda.raft.grpc.codegen.AppendEntryResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAppendEntriesRpcMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RpcServiceBlockingStub extends io.grpc.stub.AbstractStub<RpcServiceBlockingStub> {
    private RpcServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RpcServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RpcServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.wuda.raft.grpc.codegen.RequestVoteResult requestVote(com.wuda.raft.grpc.codegen.RequestVoteParameter request) {
      return blockingUnaryCall(
          getChannel(), getRequestVoteMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.wuda.raft.grpc.codegen.AppendEntryResult appendEntriesRpc(com.wuda.raft.grpc.codegen.AppendEntryParameter request) {
      return blockingUnaryCall(
          getChannel(), getAppendEntriesRpcMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RpcServiceFutureStub extends io.grpc.stub.AbstractStub<RpcServiceFutureStub> {
    private RpcServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RpcServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RpcServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.wuda.raft.grpc.codegen.RequestVoteResult> requestVote(
        com.wuda.raft.grpc.codegen.RequestVoteParameter request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestVoteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.wuda.raft.grpc.codegen.AppendEntryResult> appendEntriesRpc(
        com.wuda.raft.grpc.codegen.AppendEntryParameter request) {
      return futureUnaryCall(
          getChannel().newCall(getAppendEntriesRpcMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REQUEST_VOTE = 0;
  private static final int METHODID_APPEND_ENTRIES_RPC = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RpcServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REQUEST_VOTE:
          serviceImpl.requestVote((com.wuda.raft.grpc.codegen.RequestVoteParameter) request,
              (io.grpc.stub.StreamObserver<com.wuda.raft.grpc.codegen.RequestVoteResult>) responseObserver);
          break;
        case METHODID_APPEND_ENTRIES_RPC:
          serviceImpl.appendEntriesRpc((com.wuda.raft.grpc.codegen.AppendEntryParameter) request,
              (io.grpc.stub.StreamObserver<com.wuda.raft.grpc.codegen.AppendEntryResult>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RpcServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.wuda.raft.grpc.codegen.RpcServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RpcService");
    }
  }

  private static final class RpcServiceFileDescriptorSupplier
      extends RpcServiceBaseDescriptorSupplier {
    RpcServiceFileDescriptorSupplier() {}
  }

  private static final class RpcServiceMethodDescriptorSupplier
      extends RpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RpcServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RpcServiceFileDescriptorSupplier())
              .addMethod(getRequestVoteMethod())
              .addMethod(getAppendEntriesRpcMethod())
              .build();
        }
      }
    }
    return result;
  }
}
