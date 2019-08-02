package com.huafeng.client.JPush;

import java.util.Set;

public class TagAliasBean {
    private int action;
    private Set<String> tags;
    private String alias;
    private boolean isAliasAction;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isAliasAction() {
        return isAliasAction;
    }

    public void setAliasAction(boolean aliasAction) {
        isAliasAction = aliasAction;
    }

//    作者：奔跑的佩恩
//    链接：https://www.jianshu.com/p/1b1dd62b2d13
//    來源：简书
//    简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
}
