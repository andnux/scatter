package top.andnux.scatter;

public abstract class Scatter {

    protected ScatterClient scatterClient;

    public Scatter(ScatterClient scatterClient) {
        this.scatterClient = scatterClient;
    }

    abstract public void destroy();

    abstract public void start();
}
