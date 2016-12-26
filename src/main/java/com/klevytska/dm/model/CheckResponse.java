package com.klevytska.dm.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by katerynalevytska on 26.12.16.
 */
@XmlRootElement
public class CheckResponse {

    private boolean result;

    @SuppressWarnings("unused")
    public boolean isResult(){
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
