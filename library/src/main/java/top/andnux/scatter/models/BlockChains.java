package top.andnux.scatter.models;

/**
 * created on 2020/1/3
 */
public enum BlockChains {

    EOS("eos"),
    ETH("eth"),
    TRX("trx");

    private String name;

    BlockChains(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
