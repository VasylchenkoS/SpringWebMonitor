package com.vasilchenko.web.metric;

import org.springframework.http.HttpStatus;

/**
 * Класс для хранения Response в удобной форме
 *
 * @version 1.0
 * @autor Viacheslav Vasilchenko
 */
public class MetricClass {
    private long ID;
    private HttpStatus status;
    private String uri;
    private long time;
    private String responseBody;
    private int responseLength;
    private boolean validation;

    /**
     * Конструктор по-умолчанию
     */
    public MetricClass() {
    }

    /**
     * Конструктор
     *
     * @Param ID -номер записи
     * @Param status - HTTP статус
     * @Param uri - путь с Request
     * @Param time - время загрузки
     * @Param responseBody - полный Response
     * @Param responseLength - длинна Response
     * @Value validation - служит для определения лимитов времени загрузки
     */
    public MetricClass(long ID, HttpStatus status, String uri, long time, String responseBody, int responseLength) {
        this.ID = ID;
        this.status = status;
        this.uri = uri;
        this.time = time;
        this.responseBody = responseBody;
        this.responseLength = responseLength;
        this.validation = true;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getResponseLength() {
        return responseLength;
    }

    public void setResponseLength(int responseLength) {
        this.responseLength = responseLength;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }
}
