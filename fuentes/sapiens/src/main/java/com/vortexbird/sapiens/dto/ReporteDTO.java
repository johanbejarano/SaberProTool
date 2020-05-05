package com.vortexbird.sapiens.dto;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
public class ReporteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer facuId;
    private Integer progId;
    private Integer usuaId;
    private Integer tiusId;
    private Integer prueId;
    private Integer esprId;
    private Integer pregId;
    private Integer respId;
    private Integer respOk;
    private Integer moduId;

    private String pdf;

    public Integer getFacuId() {
        return this.facuId;
    }

    public void setFacuId(Integer facuId) {
        this.facuId = facuId;
    }

    public Integer getProgId() {
        return this.progId;
    }

    public void setProgId(Integer progId) {
        this.progId = progId;
    }

    public Integer getUsuaId() {
        return this.usuaId;
    }

    public void setUsuaId(Integer usuaId) {
        this.usuaId = usuaId;
    }

    public Integer getTiusId() {
        return this.tiusId;
    }

    public void setTiusId(Integer tiusId) {
        this.tiusId = tiusId;
    }

    public Integer getPrueId() {
        return this.prueId;
    }

    public void setPrueId(Integer prueId) {
        this.prueId = prueId;
    }

    public Integer getEsprId() {
        return this.esprId;
    }

    public void setEsprId(Integer esprId) {
        this.esprId = esprId;
    }

    public Integer getPregId() {
        return this.pregId;
    }

    public void setPregId(Integer pregId) {
        this.pregId = pregId;
    }

    public Integer getRespId() {
        return this.respId;
    }

    public void setRespId(Integer respId) {
        this.respId = respId;
    }

    public Integer getRespOk() {
        return this.respOk;
    }

    public void setRespOk(Integer respOk) {
        this.respOk = respOk;
    }

    public Integer getModuId() {
        return this.moduId;
    }

    public void setModuId(Integer moduId) {
        this.moduId = moduId;
    }

    public String getPdf() {
        return this.pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}
