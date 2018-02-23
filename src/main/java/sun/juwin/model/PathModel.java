package sun.juwin.model;

/**
 *
 * @author Juwin.S
 * @version \: PathModel.java,v 0.1 2018-02-09 19:14 
 *
 */
public class PathModel {

    private String modelPath;

    private String daoPath;

    private String mapperPath;

    private String servicePath;

    private String serviceImplPath;

    private String controllerPath;

    private String className;


    public static PathModel PathBuilder(){
        return new PathModel();
    }

    public String getModelPath() {
        return String.format(modelPath, className);
    }

    public PathModel setModelPath(String modelPath) {
        this.modelPath = modelPath;
        return this;
    }

    public String getDaoPath() {
        return String.format(daoPath, className);
    }

    public PathModel setDaoPath(String daoPath) {
        this.daoPath = daoPath;
        return this;
    }

    public String getMapperPath() {
        return String.format(mapperPath, className);
    }

    public PathModel setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
        return this;
    }

    public String getServicePath() {
        return String.format(servicePath, className);
    }

    public PathModel setServicePath(String servicePath) {
        this.servicePath = servicePath;
        return this;
    }

    public String getServiceImplPath() {
        return String.format(serviceImplPath, className);
    }

    public PathModel setServiceImplPath(String serviceImplPath) {
        this.serviceImplPath = serviceImplPath;
        return this;
    }

    public String getControllerPath() {
        return String.format(controllerPath, className);
    }

    public PathModel setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
        return this;
    }

    public PathModel setClassName(String className) {
        this.className = className.toLowerCase();
        return this;
    }
}
