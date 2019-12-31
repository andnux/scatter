package top.andnux.scatter.models.requests.transaction.request;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Transaction {
    private String expiration;
    @SerializedName("ref_block_num")
    private long refBlockNum;
    @SerializedName("ref_block_prefix")
    private long refBlockPrefix;
    @SerializedName("max_net_usage_words")
    private int maxNetUsageWords;
    @SerializedName("max_cpu_usage_ms")
    private int maxCpuUsageMs;
    @SerializedName("delay_sec")
    private int delaySec;
    @SerializedName("context_free_actions")
    private Action[] contextFreeActions;
    private Action[] actions;

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public void setRefBlockNum(long refBlockNum) {
        this.refBlockNum = refBlockNum;
    }

    public void setRefBlockPrefix(long refBlockPrefix) {
        this.refBlockPrefix = refBlockPrefix;
    }

    public void setMaxNetUsageWords(int maxNetUsageWords) {
        this.maxNetUsageWords = maxNetUsageWords;
    }

    public void setMaxCpuUsageMs(int maxCpuUsageMs) {
        this.maxCpuUsageMs = maxCpuUsageMs;
    }

    public void setDelaySec(int delaySec) {
        this.delaySec = delaySec;
    }

    public void setContextFreeActions(Action[] contextFreeActions) {
        this.contextFreeActions = contextFreeActions;
    }

    public void setActions(Action[] actions) {
        this.actions = actions;
    }

    public String getExpiration() {
        return expiration;
    }

    public long getRefBlockNum() {
        return refBlockNum;
    }

    public long getRefBlockPrefix() {
        return refBlockPrefix;
    }

    public int getMaxNetUsageWords() {
        return maxNetUsageWords;
    }

    public int getMaxCpuUsageMs() {
        return maxCpuUsageMs;
    }

    public int getDelaySec() {
        return delaySec;
    }

    public Action[] getContextFreeActions() {
        return contextFreeActions;
    }

    public Action[] getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "expiration='" + expiration + '\'' +
                ", refBlockNum=" + refBlockNum +
                ", refBlockPrefix=" + refBlockPrefix +
                ", maxNetUsageWords=" + maxNetUsageWords +
                ", maxCpuUsageMs=" + maxCpuUsageMs +
                ", delaySec=" + delaySec +
                ", contextFreeActions=" + Arrays.toString(contextFreeActions) +
                ", actions=" + Arrays.toString(actions) +
                '}';
    }
}