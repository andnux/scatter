package top.andnux.scatter.models.requests.transaction.request;

public class Action {
    private String account;
    private String name;
    private Authorization[] authorization;
    private Object data;

    public Action(String account, String name, Authorization[] authorization, String data) {
        super();
        this.account = account;
        this.name = name;
        this.authorization = authorization;
        this.data = data;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorization(Authorization[] authorization) {
        this.authorization = authorization;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public Authorization[] getAuthorization() {
        return authorization;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Action{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", authorization=" + authorization +
                ", data='" + data + '\'' +
                '}';
    }
}
