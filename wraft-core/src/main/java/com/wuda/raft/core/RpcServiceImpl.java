package com.wuda.raft.core;

import com.wuda.raft.grpc.codegen.RpcServiceGrpc;

public class RpcServiceImpl extends RpcServiceGrpc.RpcServiceImplBase {

    private LeaderElectionService leaderElectionService;
    private LogReplicationService logReplicationService;

    public RpcServiceImpl(LeaderElectionService leaderElectionService, LogReplicationService logReplicationService) {
        this.leaderElectionService = leaderElectionService;
        this.logReplicationService = logReplicationService;
    }

    @Override
    public void requestVote(com.wuda.raft.grpc.codegen.RequestVoteParameter request,
                            io.grpc.stub.StreamObserver<com.wuda.raft.grpc.codegen.RequestVoteResult> responseObserver) {
        RequestVoteParameter requestVoteParameter = new RequestVoteParameter(request.getCandidateTerm(), request.getCandidateId());
        RequestVoteResult requestVoteResult = leaderElectionService.onReceivedRequestVoteRpc(requestVoteParameter);

        com.wuda.raft.grpc.codegen.RequestVoteResult response = com.wuda.raft.grpc.codegen.RequestVoteResult.newBuilder()
                .setBambooId(requestVoteResult.getBambooId())
                .setTerm(requestVoteResult.getTerm())
                .setVoteGranted(requestVoteResult.isVoteGranted())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void appendEntriesRpc(com.wuda.raft.grpc.codegen.AppendEntryParameter request,
                                 io.grpc.stub.StreamObserver<com.wuda.raft.grpc.codegen.AppendEntryResult> responseObserver) {
        AppendEntryParameter appendEntryParameter = AppendEntryParameter.createHeartbeat(request.getTerm(), request.getLeaderId());
        AppendEntryResult appendEntryResult = logReplicationService.onReceivedAppendEntriesRpc(appendEntryParameter);

        com.wuda.raft.grpc.codegen.AppendEntryResult response = com.wuda.raft.grpc.codegen.AppendEntryResult.newBuilder()
                .setTerm(appendEntryResult.getTerm())
                .setSuccess(appendEntryResult.getSuccess())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
