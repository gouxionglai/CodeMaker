/**
 * Copyright (c) 2009-2017 All Rights Reserved.
 */
package sun.juwin.baseproject.model;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Juwin.S
 * @version \: TaskModel.java,v 0.1 2017-03-02 11:13 
 * 最终参与业务处理的实体类
 */
public class TaskModel {

    private List<TableModel> models;

    private Set<String> packages;

    public List<TableModel> getModels() {
        return models;
    }

    public void setModels(List<TableModel> models) {
        this.models = models;
    }

    public Set<String> getPackages() {
        return packages;
    }

    public void setPackages(Set<String> packages) {
        this.packages = packages;
    }
}
