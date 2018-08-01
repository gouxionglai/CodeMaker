package sun.juwin.base.model;

/**
 * @author Juwin.S
 * @version \: PathModel.java,v 0.1 2018-02-09 19:14
 */
public class PathModel {

    private String modelPath;

    private String daoPath;

    private String mapperPath;

    private String servicePath;

    private String serviceImplPath;

    private String controllerPath;


    public static PathModel PathBuilder() {
        return new PathModel();
    }

    public String getModelPath() {
        return modelPath;
    }

    public PathModel setModelPath(String modelPath) {
        this.modelPath = modelPath;
        return this;
    }

    public String getDaoPath() {
        return daoPath;
    }

    public PathModel setDaoPath(String daoPath) {
        this.daoPath = daoPath;
        return this;
    }

    public String getMapperPath() {
        return mapperPath;
    }

    public PathModel setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
        return this;
    }

    public String getServicePath() {
        return servicePath;
    }

    public PathModel setServicePath(String servicePath) {
        this.servicePath = servicePath;
        return this;
    }

    public String getServiceImplPath() {
        return serviceImplPath;
    }

    public PathModel setServiceImplPath(String serviceImplPath) {
        this.serviceImplPath = serviceImplPath;
        return this;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public PathModel setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
        return this;
    }
}
