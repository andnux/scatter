package top.andnux.scatter;

import top.andnux.scatter.ScatterClient;

public abstract class Scatter {

    protected ScatterClient scatterClient;

    public Scatter(ScatterClient scatterClient) {
        this.scatterClient = scatterClient;
    }

    abstract public void destroy();

    abstract public void start();
}
