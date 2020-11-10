### 8. 分布式理论：一致性协议3PC
```text
3PC，全称“three phase commit”，是2PC的改进版，将2PC的“提交事务请求”过程一分为二，形成了
CanCommit、PreCommit和DoCommit三个阶段组成的事务处理协议。
```
1. 阶段一：CanCommit
2. 阶段二：PreCommit
3. 阶段三：DoCommit

### 9. 分布式理论：一致性算法Paxos
1. Paxos相关概念
   ```text
    首先一个很重要的概念叫提案（Proposal）。最终要达成一致的value就在提案里。
    提案（Proposal）：
       Proposal信息包括提案编号（Proposal ID）和提议的值（Value）
    
    在Paxos算法中，有如下角色：
       Client：客户端
          客户端向分布式系统发出请求，并等待响应。例如，对分布式文件服务器中文件的写请求。
    
       Proposer：提案发起者
          提案者提倡客户请求，试图说服Acceptor对此达成一致，并在发生冲突时充当协调者以推动协议向前发展
    
       Acceptor：决策者，可以批准提案
          Acceptor可以接受提案；如果某个提案被选定，那么提案里的value就被选定了
    
       Learners：最终决策的学习者
          学习者充当该协议的复制因素
    ```
2. Paxos算法的几点要求：
   ```text
    假设有一组可以提出提案的进程集合，那么对于一个一致性算法需要保证以下几点：
       1. 在这些被提出的提案中，只有一个会被选定
       2. 如果没有提案被提出，就不应该有被选定的提案
       3. 当一个提案被选定后，那么所有进程都应该能学习到这个被选定的value
   ```