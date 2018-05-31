package com.whucs.energyriver.Bean;

public class Scene {
    private Boolean isOpen;
    private String sceneName;
    private Long sceneID;

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public Long getSceneID() {
        return sceneID;
    }

    public void setSceneID(Long sceneID) {
        this.sceneID = sceneID;
    }
}
