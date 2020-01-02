package top.andnux.scatter.models;

import java.io.Serializable;
import java.util.List;

/**
 * created on 2020/1/2
 */
public class AbiResponse implements Serializable {

    /**
     * account_name : ggs.main
     * abi : {"version":"eosio::abi/1.1","types":[],"structs":[{"name":"clearpay","base":"","fields":[{"name":"count","type":"uint32"},{"name":"end_time","type":"uint64"}]},{"name":"clearpay2","base":"","fields":[{"name":"max_id","type":"uint32"},{"name":"pay_checksum","type":"checksum256"}]},{"name":"config_table","base":"","fields":[{"name":"key","type":"name"},{"name":"value","type":"string"}]},{"name":"deltoken","base":"","fields":[{"name":"token","type":"asset"}]},{"name":"deltrfacc","base":"","fields":[{"name":"business_code","type":"uint32"}]},{"name":"pay_record","base":"","fields":[{"name":"id","type":"uint64"},{"name":"order_num","type":"uint64"},{"name":"pay_type","type":"uint8"},{"name":"business_code","type":"uint32"},{"name":"pay_account","type":"name"},{"name":"pay_quantity","type":"asset"},{"name":"transfer_account","type":"name"},{"name":"trx_id","type":"checksum256"},{"name":"pay_checksum","type":"checksum256"},{"name":"pay_time","type":"uint64"},{"name":"remark","type":"string"}]},{"name":"record","base":"","fields":[{"name":"notify_account","type":"name"},{"name":"data","type":"string"}]},{"name":"setramacc","base":"","fields":[{"name":"account","type":"name"}]},{"name":"setsigner","base":"","fields":[{"name":"pub_key","type":"string"}]},{"name":"setstatus","base":"","fields":[{"name":"new_status","type":"uint8"}]},{"name":"settoken","base":"","fields":[{"name":"min_quantity","type":"asset"},{"name":"max_quantity","type":"asset"},{"name":"code","type":"name"},{"name":"issuer","type":"name"}]},{"name":"settrfacc","base":"","fields":[{"name":"business_code","type":"uint32"},{"name":"account","type":"name"}]},{"name":"signinproof","base":"","fields":[{"name":"signin","type":"string"}]},{"name":"token_table","base":"","fields":[{"name":"id","type":"uint64"},{"name":"code","type":"name"},{"name":"issuer","type":"name"},{"name":"min_quantity","type":"asset"},{"name":"max_quantity","type":"asset"}]},{"name":"transfer_account","base":"","fields":[{"name":"business_code","type":"uint64"},{"name":"tranfer_account","type":"name"}]},{"name":"userpay","base":"","fields":[{"name":"from","type":"name"},{"name":"business_code","type":"uint32"},{"name":"order_num","type":"uint64"},{"name":"type","type":"uint8"},{"name":"pay_quantity","type":"asset"},{"name":"remark","type":"string"}]}],"actions":[{"name":"clearpay","type":"clearpay","ricardian_contract":""},{"name":"clearpay2","type":"clearpay2","ricardian_contract":""},{"name":"deltoken","type":"deltoken","ricardian_contract":""},{"name":"deltrfacc","type":"deltrfacc","ricardian_contract":""},{"name":"record","type":"record","ricardian_contract":""},{"name":"setramacc","type":"setramacc","ricardian_contract":""},{"name":"setsigner","type":"setsigner","ricardian_contract":""},{"name":"setstatus","type":"setstatus","ricardian_contract":""},{"name":"settoken","type":"settoken","ricardian_contract":""},{"name":"settrfacc","type":"settrfacc","ricardian_contract":""},{"name":"signinproof","type":"signinproof","ricardian_contract":""},{"name":"userpay","type":"userpay","ricardian_contract":""}],"tables":[{"name":"config","index_type":"i64","key_names":[],"key_types":[],"type":"config_table"},{"name":"pay","index_type":"i64","key_names":[],"key_types":[],"type":"pay_record"},{"name":"token","index_type":"i64","key_names":[],"key_types":[],"type":"token_table"},{"name":"trfacc","index_type":"i64","key_names":[],"key_types":[],"type":"transfer_account"}],"ricardian_clauses":[],"error_messages":[],"abi_extensions":[],"variants":[]}
     */

    private String account_name;
    private AbiBean abi;

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public AbiBean getAbi() {
        return abi;
    }

    public void setAbi(AbiBean abi) {
        this.abi = abi;
    }

    public static class AbiBean implements Serializable {
        /**
         * version : eosio::abi/1.1
         * types : []
         * structs : [{"name":"clearpay","base":"","fields":[{"name":"count","type":"uint32"},{"name":"end_time","type":"uint64"}]},{"name":"clearpay2","base":"","fields":[{"name":"max_id","type":"uint32"},{"name":"pay_checksum","type":"checksum256"}]},{"name":"config_table","base":"","fields":[{"name":"key","type":"name"},{"name":"value","type":"string"}]},{"name":"deltoken","base":"","fields":[{"name":"token","type":"asset"}]},{"name":"deltrfacc","base":"","fields":[{"name":"business_code","type":"uint32"}]},{"name":"pay_record","base":"","fields":[{"name":"id","type":"uint64"},{"name":"order_num","type":"uint64"},{"name":"pay_type","type":"uint8"},{"name":"business_code","type":"uint32"},{"name":"pay_account","type":"name"},{"name":"pay_quantity","type":"asset"},{"name":"transfer_account","type":"name"},{"name":"trx_id","type":"checksum256"},{"name":"pay_checksum","type":"checksum256"},{"name":"pay_time","type":"uint64"},{"name":"remark","type":"string"}]},{"name":"record","base":"","fields":[{"name":"notify_account","type":"name"},{"name":"data","type":"string"}]},{"name":"setramacc","base":"","fields":[{"name":"account","type":"name"}]},{"name":"setsigner","base":"","fields":[{"name":"pub_key","type":"string"}]},{"name":"setstatus","base":"","fields":[{"name":"new_status","type":"uint8"}]},{"name":"settoken","base":"","fields":[{"name":"min_quantity","type":"asset"},{"name":"max_quantity","type":"asset"},{"name":"code","type":"name"},{"name":"issuer","type":"name"}]},{"name":"settrfacc","base":"","fields":[{"name":"business_code","type":"uint32"},{"name":"account","type":"name"}]},{"name":"signinproof","base":"","fields":[{"name":"signin","type":"string"}]},{"name":"token_table","base":"","fields":[{"name":"id","type":"uint64"},{"name":"code","type":"name"},{"name":"issuer","type":"name"},{"name":"min_quantity","type":"asset"},{"name":"max_quantity","type":"asset"}]},{"name":"transfer_account","base":"","fields":[{"name":"business_code","type":"uint64"},{"name":"tranfer_account","type":"name"}]},{"name":"userpay","base":"","fields":[{"name":"from","type":"name"},{"name":"business_code","type":"uint32"},{"name":"order_num","type":"uint64"},{"name":"type","type":"uint8"},{"name":"pay_quantity","type":"asset"},{"name":"remark","type":"string"}]}]
         * actions : [{"name":"clearpay","type":"clearpay","ricardian_contract":""},{"name":"clearpay2","type":"clearpay2","ricardian_contract":""},{"name":"deltoken","type":"deltoken","ricardian_contract":""},{"name":"deltrfacc","type":"deltrfacc","ricardian_contract":""},{"name":"record","type":"record","ricardian_contract":""},{"name":"setramacc","type":"setramacc","ricardian_contract":""},{"name":"setsigner","type":"setsigner","ricardian_contract":""},{"name":"setstatus","type":"setstatus","ricardian_contract":""},{"name":"settoken","type":"settoken","ricardian_contract":""},{"name":"settrfacc","type":"settrfacc","ricardian_contract":""},{"name":"signinproof","type":"signinproof","ricardian_contract":""},{"name":"userpay","type":"userpay","ricardian_contract":""}]
         * tables : [{"name":"config","index_type":"i64","key_names":[],"key_types":[],"type":"config_table"},{"name":"pay","index_type":"i64","key_names":[],"key_types":[],"type":"pay_record"},{"name":"token","index_type":"i64","key_names":[],"key_types":[],"type":"token_table"},{"name":"trfacc","index_type":"i64","key_names":[],"key_types":[],"type":"transfer_account"}]
         * ricardian_clauses : []
         * error_messages : []
         * abi_extensions : []
         * variants : []
         */

        private String version;
        private List<?> types;
        private List<StructsBean> structs;
        private List<ActionsBean> actions;
        private List<TablesBean> tables;
        private List<?> ricardian_clauses;
        private List<?> error_messages;
        private List<?> abi_extensions;
        private List<?> variants;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public List<?> getTypes() {
            return types;
        }

        public void setTypes(List<?> types) {
            this.types = types;
        }

        public List<StructsBean> getStructs() {
            return structs;
        }

        public void setStructs(List<StructsBean> structs) {
            this.structs = structs;
        }

        public List<ActionsBean> getActions() {
            return actions;
        }

        public void setActions(List<ActionsBean> actions) {
            this.actions = actions;
        }

        public List<TablesBean> getTables() {
            return tables;
        }

        public void setTables(List<TablesBean> tables) {
            this.tables = tables;
        }

        public List<?> getRicardian_clauses() {
            return ricardian_clauses;
        }

        public void setRicardian_clauses(List<?> ricardian_clauses) {
            this.ricardian_clauses = ricardian_clauses;
        }

        public List<?> getError_messages() {
            return error_messages;
        }

        public void setError_messages(List<?> error_messages) {
            this.error_messages = error_messages;
        }

        public List<?> getAbi_extensions() {
            return abi_extensions;
        }

        public void setAbi_extensions(List<?> abi_extensions) {
            this.abi_extensions = abi_extensions;
        }

        public List<?> getVariants() {
            return variants;
        }

        public void setVariants(List<?> variants) {
            this.variants = variants;
        }

        public static class StructsBean {
            /**
             * name : clearpay
             * base :
             * fields : [{"name":"count","type":"uint32"},{"name":"end_time","type":"uint64"}]
             */

            private String name;
            private String base;
            private List<FieldsBean> fields;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBase() {
                return base;
            }

            public void setBase(String base) {
                this.base = base;
            }

            public List<FieldsBean> getFields() {
                return fields;
            }

            public void setFields(List<FieldsBean> fields) {
                this.fields = fields;
            }

            public static class FieldsBean {
                /**
                 * name : count
                 * type : uint32
                 */

                private String name;
                private String type;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }

        public static class ActionsBean {
            /**
             * name : clearpay
             * type : clearpay
             * ricardian_contract :
             */

            private String name;
            private String type;
            private String ricardian_contract;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getRicardian_contract() {
                return ricardian_contract;
            }

            public void setRicardian_contract(String ricardian_contract) {
                this.ricardian_contract = ricardian_contract;
            }
        }

        public static class TablesBean {
            /**
             * name : config
             * index_type : i64
             * key_names : []
             * key_types : []
             * type : config_table
             */

            private String name;
            private String index_type;
            private String type;
            private List<?> key_names;
            private List<?> key_types;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIndex_type() {
                return index_type;
            }

            public void setIndex_type(String index_type) {
                this.index_type = index_type;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<?> getKey_names() {
                return key_names;
            }

            public void setKey_names(List<?> key_names) {
                this.key_names = key_names;
            }

            public List<?> getKey_types() {
                return key_types;
            }

            public void setKey_types(List<?> key_types) {
                this.key_types = key_types;
            }
        }
    }
}
