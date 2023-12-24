package com.liu.weathermail.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By misteryliu.
 * 响应
 *
 * @author misteryliu@outlook.com
 * @since 2023/12/24 16:20 Copyright ©2023 misteryliu. All rights reserved.
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class RespResult<T> implements Serializable {
    private static final String SUCCESS_CODE;
    private static final String SUCCESS_MESSAGE;
    private String retCode;
    private String retMsg;
    private T busBody;

    public RespResult() {
    }

    public RespResult(T busBody) {
        this.retCode = SUCCESS_CODE;
        this.retMsg = SUCCESS_MESSAGE;
        this.busBody = busBody;
    }

    public RespResult(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public RespResult(String retCode, String retMsg, T busBody) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.busBody = busBody;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.retCode);
    }

    public String getRetCode() {
        return this.retCode;
    }

    public String getRetMsg() {
        return this.retMsg;
    }

    public T getBusBody() {
        return this.busBody;
    }

    public RespResult<T> setRetCode(final String retCode) {
        this.retCode = retCode;
        return this;
    }

    public RespResult<T> setRetMsg(final String retMsg) {
        this.retMsg = retMsg;
        return this;
    }

    public RespResult<T> setBusBody(final T busBody) {
        this.busBody = busBody;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RespResult)) {
            return false;
        } else {
            RespResult<?> other = (RespResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$retCode = this.getRetCode();
                    Object other$retCode = other.getRetCode();
                    if (this$retCode == null) {
                        if (other$retCode == null) {
                            break label47;
                        }
                    } else if (this$retCode.equals(other$retCode)) {
                        break label47;
                    }

                    return false;
                }

                Object this$retMsg = this.getRetMsg();
                Object other$retMsg = other.getRetMsg();
                if (this$retMsg == null) {
                    if (other$retMsg != null) {
                        return false;
                    }
                } else if (!this$retMsg.equals(other$retMsg)) {
                    return false;
                }

                Object this$busBody = this.getBusBody();
                Object other$busBody = other.getBusBody();
                if (this$busBody == null) {
                    if (other$busBody != null) {
                        return false;
                    }
                } else if (!this$busBody.equals(other$busBody)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RespResult;
    }

    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $retCode = this.getRetCode();
        result = result * 59 + ($retCode == null ? 43 : $retCode.hashCode());
        Object $retMsg = this.getRetMsg();
        result = result * 59 + ($retMsg == null ? 43 : $retMsg.hashCode());
        Object $busBody = this.getBusBody();
        result = result * 59 + ($busBody == null ? 43 : $busBody.hashCode());
        return result;
    }

    public String toString() {
        return "RespResult(retCode=" + this.getRetCode() + ", retMsg=" + this.getRetMsg() + ", busBody=" + this.getBusBody() + ")";
    }

    static {
        SUCCESS_CODE = DefaultResponseErrorType.SUCCESS.getCode();
        SUCCESS_MESSAGE = DefaultResponseErrorType.SUCCESS.getMessage();
    }
}
