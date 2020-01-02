package top.andnux.scatter.models;

import java.io.Serializable;
import java.util.List;

/**
 * 交易信息对象
 */
public class TransactionBean implements Serializable {


    /**
     * expiration : 2020-01-02T06:08:20.000
     * ref_block_num : 55897
     * ref_block_prefix : 3484450959
     * max_net_usage_words : 0
     * max_cpu_usage_ms : 0
     * delay_sec : 0
     * context_free_actions : []
     * actions : [{"account":"ggs.main","name":"signinproof","authorization":[{"actor":"liuliang","permission":"owner"}],"data":"1F54686973207369676E617475726520697320666F72206C6F67696E20474753"}]
     * transaction_extensions : []
     */

    private String expiration;
    private int ref_block_num;
    private long ref_block_prefix;
    private int max_net_usage_words;
    private int max_cpu_usage_ms;
    private int delay_sec;
    private List<?> context_free_actions;
    private List<ActionsBean> actions;
    private List<?> transaction_extensions;

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public int getRef_block_num() {
        return ref_block_num;
    }

    public void setRef_block_num(int ref_block_num) {
        this.ref_block_num = ref_block_num;
    }

    public long getRef_block_prefix() {
        return ref_block_prefix;
    }

    public void setRef_block_prefix(long ref_block_prefix) {
        this.ref_block_prefix = ref_block_prefix;
    }

    public int getMax_net_usage_words() {
        return max_net_usage_words;
    }

    public void setMax_net_usage_words(int max_net_usage_words) {
        this.max_net_usage_words = max_net_usage_words;
    }

    public int getMax_cpu_usage_ms() {
        return max_cpu_usage_ms;
    }

    public void setMax_cpu_usage_ms(int max_cpu_usage_ms) {
        this.max_cpu_usage_ms = max_cpu_usage_ms;
    }

    public int getDelay_sec() {
        return delay_sec;
    }

    public void setDelay_sec(int delay_sec) {
        this.delay_sec = delay_sec;
    }

    public List<?> getContext_free_actions() {
        return context_free_actions;
    }

    public void setContext_free_actions(List<?> context_free_actions) {
        this.context_free_actions = context_free_actions;
    }

    public List<ActionsBean> getActions() {
        return actions;
    }

    public void setActions(List<ActionsBean> actions) {
        this.actions = actions;
    }

    public List<?> getTransaction_extensions() {
        return transaction_extensions;
    }

    public void setTransaction_extensions(List<?> transaction_extensions) {
        this.transaction_extensions = transaction_extensions;
    }

    public static class ActionsBean implements Serializable {
        /**
         * account : ggs.main
         * name : signinproof
         * authorization : [{"actor":"liuliang","permission":"owner"}]
         * data : 1F54686973207369676E617475726520697320666F72206C6F67696E20474753
         */

        private String account;
        private String name;
        private Object data;
        private List<AuthorizationBean> authorization;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public List<AuthorizationBean> getAuthorization() {
            return authorization;
        }

        public void setAuthorization(List<AuthorizationBean> authorization) {
            this.authorization = authorization;
        }

        public static class AuthorizationBean implements Serializable  {
            /**
             * actor : liuliang
             * permission : owner
             */

            private String actor;
            private String permission;

            public String getActor() {
                return actor;
            }

            public void setActor(String actor) {
                this.actor = actor;
            }

            public String getPermission() {
                return permission;
            }

            public void setPermission(String permission) {
                this.permission = permission;
            }
        }
    }
}
