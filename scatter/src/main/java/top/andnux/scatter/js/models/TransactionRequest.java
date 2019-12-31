package top.andnux.scatter.js.models;

import java.io.Serializable;
import java.util.List;

public class TransactionRequest implements Serializable {

    /**
     * transaction : {"expiration":"2019-12-12T11:07:34.000","ref_block_num":1949,"ref_block_prefix":135533081,"actions":[{"account":"ggs.main","name":"signinproof","authorization":[{"actor":"zhangchunlin","permission":"owner"}],"data":"1F54686973207369676E617475726520697320666F72206C6F67696E20474753"}]}
     * availableKeys : ["EOS76pwnQG8tdctc4ytSXZEGjVhQdkLXgyFFZ1dWGf3iAU4PRMbqq"]
     */

    private TransactionBean transaction;
    private List<String> availableKeys;

    public TransactionBean getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionBean transaction) {
        this.transaction = transaction;
    }

    public List<String> getAvailableKeys() {
        return availableKeys;
    }

    public void setAvailableKeys(List<String> availableKeys) {
        this.availableKeys = availableKeys;
    }

    public static class TransactionBean implements Serializable{
        /**
         * expiration : 2019-12-12T11:07:34.000
         * ref_block_num : 1949
         * ref_block_prefix : 135533081
         * actions : [{"account":"ggs.main","name":"signinproof","authorization":[{"actor":"zhangchunlin","permission":"owner"}],"data":"1F54686973207369676E617475726520697320666F72206C6F67696E20474753"}]
         */

        private String expiration;
        private long ref_block_num;
        private long ref_block_prefix;
        private long max_net_usage_words;
        private long max_cpu_usage_ms;
        private long delay_sec;
        private List<ActionsBean> actions;

        public String getExpiration() {
            return expiration;
        }

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }

        public long getRef_block_num() {
            return ref_block_num;
        }

        public void setRef_block_num(long ref_block_num) {
            this.ref_block_num = ref_block_num;
        }

        public long getRef_block_prefix() {
            return ref_block_prefix;
        }

        public void setRef_block_prefix(long ref_block_prefix) {
            this.ref_block_prefix = ref_block_prefix;
        }

        public long getMax_net_usage_words() {
            return max_net_usage_words;
        }

        public void setMax_net_usage_words(long max_net_usage_words) {
            this.max_net_usage_words = max_net_usage_words;
        }

        public long getMax_cpu_usage_ms() {
            return max_cpu_usage_ms;
        }

        public void setMax_cpu_usage_ms(long max_cpu_usage_ms) {
            this.max_cpu_usage_ms = max_cpu_usage_ms;
        }

        public long getDelay_sec() {
            return delay_sec;
        }

        public void setDelay_sec(long delay_sec) {
            this.delay_sec = delay_sec;
        }

        public List<ActionsBean> getActions() {
            return actions;
        }

        public void setActions(List<ActionsBean> actions) {
            this.actions = actions;
        }

        public static class ActionsBean implements Serializable{
            /**
             * account : ggs.main
             * name : signinproof
             * authorization : [{"actor":"zhangchunlin","permission":"owner"}]
             * data : 1F54686973207369676E617475726520697320666F72206C6F67696E20474753
             */

            private String account;
            private String name;
            private String data;
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

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public List<AuthorizationBean> getAuthorization() {
                return authorization;
            }

            public void setAuthorization(List<AuthorizationBean> authorization) {
                this.authorization = authorization;
            }

            public static class AuthorizationBean implements Serializable{
                /**
                 * actor : zhangchunlin
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
}
