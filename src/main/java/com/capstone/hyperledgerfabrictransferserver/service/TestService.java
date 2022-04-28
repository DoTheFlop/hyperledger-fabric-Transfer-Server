package com.capstone.hyperledgerfabrictransferserver.service;

import lombok.RequiredArgsConstructor;
import org.hyperledger.fabric.gateway.*;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class TestService {

    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
    }

    public static Gateway connect() throws Exception{
        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get("wallet");
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        // load a CCP
//        Path networkConfigPath = Paths.get("","Users", "jeongjaeyeob", "go", "src", "github.com", "Jaeyeop-Jung", "fabric-samples", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");
        Path networkConfigPath = Paths.get("/Users/jeongjaeyeob/go/src/github.com/Jaeyeop-Jung/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/connection-org1.yaml");

        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);
        return builder.connect();
    }

    public String initLedger() throws Exception {
        Gateway gateway = connect();

        Network network = gateway.getNetwork("mychannel");
        Contract contract = network.getContract("basic");

        byte[] result;

        System.out.println("Submit Transaction: InitLedger creates the initial set of assets on the ledger.");
        contract.submitTransaction("InitLedger");

        return "OK";
    }

    public String getAllAsset() throws Exception {
        Gateway gateway = connect();

        Network network = gateway.getNetwork("mychannel");
        Contract contract = network.getContract("basic");

        byte[] result;

        result = contract.evaluateTransaction("GetAllAssets");

        return new String(result, StandardCharsets.UTF_8);
    }

    public String createAsset() throws Exception {
        Gateway gateway = connect();

        Network network = gateway.getNetwork("mychannel");
        Contract contract = network.getContract("basic");

        byte[] result;

        contract.submitTransaction("CreateAsset", "asset4", "테스트");

        return "OK";
    }

    public String createCoin(String coinName) throws Exception {

        Gateway gateway = connect();

        Network network = gateway.getNetwork("mychannel");
        Contract contract = network.getContract("basic");

        byte[] result;

        result = contract.submitTransaction("CreateCoin", coinName);


        return new String(result, StandardCharsets.UTF_8);
    }

}
